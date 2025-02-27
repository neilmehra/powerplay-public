package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.Cone
import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.Rumble
import asiankoala.ftc2022.sunmi.commands.SetState
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.*
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KGamepad
import kotlin.math.max

class Stack(sunmi: TeleBot, stackIndex: Int, gamepad: KGamepad) : SequentialGroup(
    ParallelGroup(
        IntakeArm(sunmi.arm, sunmi.claw, true),
        LiftCmd(sunmi.lift, LiftConstants.stacksUp[stackIndex]),
        BruhWrist(sunmi.wrist),
        OpenClaw(sunmi.claw)
    ),

    LiftCmd(sunmi.lift, LiftConstants.stacksDown[stackIndex])
        .waitUntil(gamepad.rightTrigger::isJustPressed),

    WaitCmd(SeqConstants.afterArmDownDt),

    WaitUntilCmd(gamepad.rightTrigger::isJustPressed),

    ParallelGroup(
        CloseClaw(sunmi.claw),
        Rumble(gamepad),
    ),

    WaitCmd(SeqConstants.afterClawGrabDt),

    ParallelGroup(
        SequentialGroup(
            LiftCmd(sunmi.lift, 9.0),
            WaitUntilCmd { sunmi.arm.pos < 120.0 },
            HomeLift(sunmi.lift)
        ),
        HomeArm(sunmi.arm, sunmi.claw, true),
        HomeWrist(sunmi.wrist),
        InstantCmd({
            sunmi.stack = max(sunmi.stack - 1, 0)
        }),
    ),

)