package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.commands.SetState
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.ArmConstants
import asiankoala.ftc2022.sunmi.constants.SeqConstants
import com.asiankoala.koawalib.command.commands.BlankCmd
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup

class Idle(sunmi: TeleBot) : SequentialGroup(
    ParallelGroup(
        ChooseCmd(WaitCmd(0.5), BlankCmd()) { sunmi.strat == Strategy.LOW }.andThen(HomeLift(sunmi.lift)),
        WaitCmd(SeqConstants.depositAlignerWait + if(sunmi.strat == Strategy.LOW) 0.2 else 0.0).andThen(HomeAligner(sunmi.aligner)), // make sure aligner doesnt hit arm
        HomeWrist(sunmi.wrist),
        TurnOffIntake(sunmi.intake),
        IdleArmClaw(sunmi.arm, sunmi.claw, sunmi.strat),
    ),
    SetState(sunmi, State.IDLE)
) {
    init {
        addRequirements(sunmi.instance)
    }
}
