package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class BadGrabFailsafe(sunmi: TeleBot) : SequentialGroup(
    CloseClaw(sunmi.claw),
    WaitCmd(0.2),
    GIDLEArm(sunmi.arm, sunmi.claw, true),
    FailsafeWrist(sunmi.wrist),
    WaitCmd(0.2),
    OpenClaw(sunmi.claw)
) {
    init {
        addRequirements(sunmi.instance)
    }
}