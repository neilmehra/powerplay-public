package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.WristConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Wrist : KSubsystem() {
    private val servo = KServo("pivot").startAt(WristConstants.home)
    val pos get() = servo.position
    fun setTarget(pos: Double) {
        servo.position = pos
    }
}
