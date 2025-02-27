package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.Cone
import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.Rumble
import asiankoala.ftc2022.sunmi.commands.SetState
import asiankoala.ftc2022.sunmi.commands.subsystem.*
//import asiankoala.ftc2022.sunmi.commands.subsystem.ClawStartReadingCmd
//import asiankoala.ftc2022.sunmi.commands.subsystem.ClawStopReadingCmd
import asiankoala.ftc2022.sunmi.constants.ArmConstants
import asiankoala.ftc2022.sunmi.constants.ClawConstants
import asiankoala.ftc2022.sunmi.constants.SeqConstants
import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KGamepad
import com.asiankoala.koawalib.logger.Logger

class SmartIntake(sunmi: TeleBot, gamepad: KGamepad) : SequentialGroup(
    ParallelGroup(
        DeployIntake(sunmi.intake),
        StartReadingClaw(sunmi.claw),
        TurnOnIntake(sunmi.intake),
        OpenClaw(sunmi.claw)
    ),

    ParallelGroup(
        StopReadingClaw(sunmi.claw),
        IntakeArm(sunmi.arm, sunmi.claw, true),
    ).waitUntil { sunmi.claw.lastRead < ClawConstants.intakeThreshold },

//    IntakeArm(sunmi.arm, sunmi.claw, true),
    TeleOpIntake(sunmi.arm, sunmi.claw),

    WaitCmd(SeqConstants.afterArmDownDt),

    ParallelGroup(
        CloseClaw(sunmi.claw),
        Rumble(gamepad),
        TurnOffIntake(sunmi.intake)
    ),

    WaitCmd(SeqConstants.afterClawGrabDt),
    HomeArm(sunmi.arm, sunmi.claw, true),

    // retract intake when we're going for ground junctions
    // the intake will be retracted anyway if we swap to Ground strat again,
    // so this just makes life easier when driving
    ChooseCmd(
        RetractIntake(sunmi.intake),
        BlankCmd()
    ) { sunmi.strat == Strategy.GROUND }
)
