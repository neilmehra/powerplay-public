package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.SetState
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.ArmConstants
import asiankoala.ftc2022.sunmi.constants.SeqConstants
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.ParallelGroup

class IdleDuringDeploy(sunmi: TeleBot) : ParallelGroup(
    HomeLift(sunmi.lift),
    HomeArm(sunmi.arm, sunmi.claw, true),
    HomeWrist(sunmi.wrist),
    SetState(sunmi, State.TRANSIT),
    WaitCmd(SeqConstants.idleAlignerWait).andThen(HomeAligner(sunmi.aligner))
) {
    init {
        addRequirements(sunmi.instance)
    }
}
