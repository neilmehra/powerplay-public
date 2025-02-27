package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.Cone
import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.Rumble
import asiankoala.ftc2022.sunmi.commands.SetState
import asiankoala.ftc2022.sunmi.commands.subsystem.DeployIntake
import asiankoala.ftc2022.sunmi.commands.subsystem.OpenClaw
import asiankoala.ftc2022.sunmi.constants.SeqConstants
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KGamepad

class Miyeon(sunmi: TeleBot, gamepad: KGamepad) : SequentialGroup(
    SetState(sunmi, State.DEPOSITING),
    Soyeon(sunmi),
    OpenClaw(sunmi.claw).waitUntil(gamepad.rightTrigger::isJustPressed),
    InstantCmd({
        sunmi.stats.update(sunmi.strat)
        sunmi.cone = Cone.NONE
        if(sunmi.stack == 0) {
            + DeployIntake(sunmi.intake)
            sunmi.isStacking = false
        }
    }),
    Rumble(gamepad),
    WaitCmd(SeqConstants.waitAfterDeposit),
    Idle(sunmi),
) {
    init {
        addRequirements(sunmi.instance)
    }
}