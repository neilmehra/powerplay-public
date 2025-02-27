package asiankoala.ftc2022.sunmi.opmodes.diagnostics

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.commands.sequence.IdleArmClaw
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.AlignerConstants
import asiankoala.ftc2022.sunmi.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.*
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(group = "b")
class ServoDiagnostics : KOpMode() {
    private lateinit var arm: Arm
    private lateinit var claw: Claw

    override fun mInit() {
        arm = Arm(true)
//        claw = Claw()
//        val wrist = Wrist()
        val aligner = Aligner(true)
//        val intake = Intake(false)

//        driver.rightTrigger.onPress(IntakeArm(arm, claw))
//        driver.rightBumper.onPress(GIDLEArm(arm, claw))
//        driver.leftBumper.onPress(HomeArm(arm, claw))
//        driver.leftTrigger.onPress(DepositArm(arm, claw))

//        driver.b.onPress(CloseClaw(claw))
//        driver.a.onPress(OpenClaw(claw))

//        driver.y.onPress(IdleArmClaw(arm, claw, Strategy.HIGH))

        driver.dpadUp.onPress(AlignerCmd(aligner, AlignerConstants.home))
        driver.dpadLeft.onPress(AlignerCmd(aligner, AlignerConstants.init))
        driver.dpadRight.onPress(AlignerCmd(aligner, AlignerConstants.low))
        driver.dpadDown.onPress(AlignerCmd(aligner, AlignerConstants.deposit))

//        driver.dpadUp.onPress(TurnOffIntake(intake))
//        driver.dpadDown.onPress(TurnOnIntake(intake))
    }

    override fun mLoop() {
//        Logger.put("arm pos", arm.pos)
//        Logger.put("at deposit", arm.isArmAtDeposit)
//        Logger.put("at intake", arm.isArmAtIntake)
//        Logger.put()
//        Logger.put("claw pos", claw.pos)
//        Logger.put("is claw closed", claw.isGripped)
//        Logger.put("is claw open", claw.isOpen)
//        Logger.put()
    }
}