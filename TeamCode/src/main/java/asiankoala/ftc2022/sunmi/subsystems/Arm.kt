package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.ArmConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem
import kotlin.math.absoluteValue

class Arm(isAuto: Boolean) : KSubsystem() {
    private val arm1 = KServo("arm1")
        .startAt(if(isAuto) ArmConstants.autoInit else ArmConstants.home)
        .reverse()
    private val arm2 = KServo("arm2")
        .startAt(if(isAuto) ArmConstants.autoInit else ArmConstants.home)
    private val enc = AxonEnc("aenc")
    val pos get() = enc.pos
    // when the claw is open and depositing, the arm will think it's safe even though it really isnt
    val isArmAtDeposit get() = isAtTarget(ArmConstants.depositAngle)

    private fun isAtTarget(target: Double): Boolean {
        return (target - pos).absoluteValue < ArmConstants.SERVO_EPSILON_DEG
    }

    fun setTarget(pos: Double) {
        arm1.position = pos
        arm2.position = pos
    }
}