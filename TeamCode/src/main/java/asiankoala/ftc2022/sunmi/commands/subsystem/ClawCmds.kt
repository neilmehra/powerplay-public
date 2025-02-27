package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.subsystems.Claw
import asiankoala.ftc2022.sunmi.constants.ClawConstants
import asiankoala.ftc2022.sunmi.subsystems.Arm
import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger

abstract class ClawCmd(claw: Claw, pos: Double) : InstantCmd({ claw.setTarget(pos) })
class CloseClaw(claw: Claw) : ClawCmd(claw, ClawConstants.close)
class OpenClaw(claw: Claw) : ClawCmd(claw, ClawConstants.open)
class StartReadingClaw(claw: Claw) : InstantCmd(claw::startReading)
class StopReadingClaw(claw: Claw) : InstantCmd(claw::stopReading)
class AutoPreInitClaw(claw: Claw) : ClawCmd(claw, ClawConstants.semiOpenForAuto)

