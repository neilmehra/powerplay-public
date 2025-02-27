package asiankoala.ftc2022.sunmi.commands

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.TeleBot
import com.asiankoala.koawalib.command.commands.InstantCmd

class SetState(sunmi: TeleBot, state: State) : InstantCmd({ sunmi.state = state })