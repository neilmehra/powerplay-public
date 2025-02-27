package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.SeqConstants
import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import com.asiankoala.koawalib.command.commands.BlankCmd
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class IdleArmClaw(arm: Arm, claw: Claw, strat: Strategy) : SequentialGroup(
    ChooseCmd(
        SequentialGroup(
            GIDLEArm(arm, claw, true),
            OpenClaw(claw)
        ),
        SequentialGroup(
            CloseClaw(claw),
            GIDLEArm(arm, claw),
            WaitCmd(SeqConstants.openAfterDeposit),
            OpenClaw(claw)
        )
    ) { strat == Strategy.GROUND }
)