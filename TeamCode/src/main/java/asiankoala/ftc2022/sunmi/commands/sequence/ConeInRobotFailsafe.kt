package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.subsystem.DepositAligner
import asiankoala.ftc2022.sunmi.commands.subsystem.HomeAligner
import asiankoala.ftc2022.sunmi.commands.subsystem.HomeLift
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.constants.LiftConstants
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup

class ConeInRobotFailsafe(sunmi: TeleBot) : SequentialGroup(
    LiftCmd(sunmi.lift, LiftConstants.high),
    WaitCmd(0.2),
    DepositAligner(sunmi.aligner),
    WaitCmd(0.7),
    HomeAligner(sunmi.aligner),
    HomeLift(sunmi.lift)
)