package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.IntakeConstants
import com.asiankoala.koawalib.hardware.servo.KCRServo
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.KSubsystem

class Intake(isAuto: Boolean) : KSubsystem() {
    private val retract = KServo("r")
        .startAt(if(isAuto) IntakeConstants.retract else IntakeConstants.extend)
    private val intakeLeft = KCRServo("inL").reverse()
    private val intakeRight = KCRServo("inR")

    fun setPower(power: Double) {
        intakeLeft.power = power
        intakeRight.power = power
    }

    fun setRetract(pos: Double) {
        retract.position = pos
    }
}