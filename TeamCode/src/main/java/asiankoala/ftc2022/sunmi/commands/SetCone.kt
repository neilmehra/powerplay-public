package asiankoala.ftc2022.sunmi.commands

import asiankoala.ftc2022.sunmi.Cone
import asiankoala.ftc2022.sunmi.TeleBot
import com.asiankoala.koawalib.command.commands.InstantCmd

class SetCone(sunmi: TeleBot, cone: Cone) : InstantCmd({ sunmi.cone = cone })