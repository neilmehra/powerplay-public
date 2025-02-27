package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.Cone
import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.SetCone
import asiankoala.ftc2022.sunmi.commands.SetState
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.gamepad.KGamepad

// GIDLE is cancelled only upon scheduling Idle
// so we dont really need to make sure this is prematurely cancelled
class GIDLE(sunmi: TeleBot, gamepad: KGamepad) : SequentialGroup(
    SetState(sunmi, State.INTAKING),
    SetCone(sunmi, Cone.LOOKING),
    ChooseCmd(
        Stack(sunmi, sunmi.stack, gamepad),
        SmartIntake(sunmi, gamepad),
        sunmi::isStacking
    ),
    SetState(sunmi, State.TRANSIT),
    SetCone(sunmi, Cone.GRABBED)
) {
    init {
        addRequirements(sunmi.instance)
    }
}