package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.TeleBot
import com.asiankoala.koawalib.command.group.ParallelGroup

class Soyeon(sunmi: TeleBot) : ParallelGroup(
    sunmi.liftCmd,
    sunmi.armCmd,
    sunmi.wristCmd,
    sunmi.alignerCmd
)