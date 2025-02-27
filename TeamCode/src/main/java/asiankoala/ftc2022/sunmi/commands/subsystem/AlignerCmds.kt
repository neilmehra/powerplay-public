package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.constants.AlignerConstants
import asiankoala.ftc2022.sunmi.subsystems.Aligner
import com.asiankoala.koawalib.command.commands.InstantCmd

open class AlignerCmd(aligner: Aligner, pos: Double) : InstantCmd({ aligner.setPos(pos) })
class HomeAligner(aligner: Aligner) : AlignerCmd(aligner, AlignerConstants.home)
class LowAligner(aligner: Aligner) : AlignerCmd(aligner, AlignerConstants.low)
class DepositAligner(aligner: Aligner) : AlignerCmd(aligner, AlignerConstants.deposit)
