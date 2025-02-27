package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.LiftConstants
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.control.motor.FFGains
import com.asiankoala.koawalib.control.profile.MotionConstraints
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.subsystem.KSubsystem

class Lift(br: KMotor) : KSubsystem() {
    private val lt = MotorFactory("lt")
        .reverse
        .pairEncoder(
            br,
            EncoderFactory(367.0 / 12.0).reverse.zero()
        )
        .withMotionProfileControl(
            PIDGains(
                LiftConstants.kP,
                LiftConstants.kI,
                LiftConstants.kD
            ),
            FFGains(kG = LiftConstants.kG),
            MotionConstraints(LiftConstants.vel, LiftConstants.accel, LiftConstants.deccel),
            0.1,
            0.0
        )
        .float
        .build()

    private val lb = MotorFactory("lb").float.build()
    private val rt = MotorFactory("rt").float.build()
    private val rb = MotorFactory("rb").float.reverse.build()
    private val limit = KLimitSwitch("limit")
    private val chainedMotors = listOf(rt, lb, rb)
    private val allMotors = listOf(lt, *chainedMotors.toTypedArray())
    var isAttemptingZero = false
    var isFailed = false
    val pos get() = lt.pos
    val vel get() = lt.vel
    val setpoint get() = lt.setpoint
    val power get() = lt.power

    fun setTarget(pos: Double) {
        lt.setProfileTarget(pos)
    }

    fun startAttemptingZero() {
        isAttemptingZero = true
    }

    fun startFailsafeZero() {
        allMotors.forEach(KMotor::disable)
        allMotors.forEach { it.power = LiftConstants.failsafePow }
        isAttemptingZero = true
        isFailed = true
    }

    private fun checkFailsafeZero() {
        if(isFailed) {
            allMotors.forEach(KMotor::enable)
            Logger.logInfo("lift unfailed")
        }
    }

    override fun periodic() {
        chainedMotors.forEach { it.power = lt.power }
        if(isAttemptingZero && limit.invoke()) {
            checkFailsafeZero()
            lt.zero()
            isAttemptingZero = false
            setTarget(LiftConstants.home)
            Logger.logInfo("zeroed")
        }
    }
}