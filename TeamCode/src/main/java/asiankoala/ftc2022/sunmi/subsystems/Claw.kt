package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.ClawConstants
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem
import kotlin.math.absoluteValue

class Claw : KSubsystem() {
    private var isReading = false
    val servo = KServo("claw").startAt(ClawConstants.close)
    private val sensor = KDistanceSensor("distance", 50.0)
    private val enc = AxonEnc("cenc")
    val lastRead get() = sensor.lastRead
    val pos get() = enc.pos
    val isOpen get() = isAtTarget(ClawConstants.openAngle) || pos > ClawConstants.openAngle // for some reason goes to 110 at gidle
    val isGripped get() = isAtTarget(ClawConstants.gripAngle) || pos < ClawConstants.gripAngle

    private fun isAtTarget(target: Double): Boolean {
        return (target - pos).absoluteValue < ClawConstants.SERVO_EPSILON_DEG
    }

    fun startReading() {
        isReading = true
    }

    fun stopReading() {
        isReading = false
    }

    fun setTarget(pos: Double) {
        servo.position = pos
    }

    override fun periodic() {
        if(isReading) {
            sensor.periodic()
        }
    }
}
