package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Wrist
import asiankoala.ftc2022.sunmi.constants.WristConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class WristCmd(private val wrist: Wrist, private val pos: Double) :
    InstantCmd({ wrist.setTarget(pos) })

class HomeWrist(wrist: Wrist) : WristCmd(wrist, WristConstants.home)
class HighWrist(wrist: Wrist) : WristCmd(wrist, WristConstants.high)
class FailsafeWrist(wrist: Wrist) : WristCmd(wrist, WristConstants.failsafe)
class BruhWrist(wrist: Wrist) : WristCmd(wrist,  WristConstants.bruh)
