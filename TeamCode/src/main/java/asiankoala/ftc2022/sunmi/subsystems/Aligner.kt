package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.AlignerConstants
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Aligner(isAuto: Boolean) : KSubsystem() {
    private val servo = KServo("aligner")
        .startAt(if(isAuto) AlignerConstants.init else AlignerConstants.home)

    fun setPos(pos: Double) {
        servo.position = pos
    }
}