package asiankoala.ftc2022.sunmi

import asiankoala.ftc2022.sunmi.constants.StaticConstants
import asiankoala.ftc2022.sunmi.subsystems.*
import com.asiankoala.koawalib.hardware.motor.MotorFactory

open class SunmiBase(isAuto: Boolean) {
    protected val fl = MotorFactory("fl")
        .brake
        .reverse
        .voltageCorrected
        .build()
    protected val bl = MotorFactory("bl")
        .brake
        .reverse
        .voltageCorrected
        .build()
    protected val br = MotorFactory("br")
        .brake
        .forward
        .voltageCorrected
        .build()

    protected val fr = MotorFactory("fr")
        .brake
        .forward
        .voltageCorrected
        .build()

    val lift = Lift(br)
    val claw = Claw()
    val wrist = Wrist()
    val arm = Arm(isAuto)
    val instance = Instance()
    val aligner = Aligner(isAuto)
    val intake = Intake(isAuto)
}