package asiankoala.ftc2022.sunmi

import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.*
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive

class TeleBot : SunmiBase(false) {
    val drive = KMecanumDrive(fl, bl, br, fr)
    var strat = Strategy.LOW
    var state = State.IDLE
    var cone = Cone.NONE
    var isStacking = false
    var stack = 4
    val stats = MatchStats()

    val liftCmd
        get() = LiftCmd(
            lift,
            when(strat) {
                Strategy.GROUND -> LiftConstants.ground
                Strategy.LOW -> LiftConstants.low
                Strategy.MED-> LiftConstants.med
                Strategy.HIGH -> LiftConstants.high
            }
        )

    val armCmd
        get() = ChooseCmd(
            GroundArm(arm, claw),
            WaitCmd(0.2).andThen(DepositArm(arm, claw, true)) // avoid hitting aligner
        ) { strat == Strategy.GROUND }

    val wristCmd
        get() = WristCmd(
            wrist,
            when(strat) {
                Strategy.GROUND -> WristConstants.ground // keep dat shit parallel
                Strategy.LOW -> WristConstants.low
                Strategy.MED -> WristConstants.med
                Strategy.HIGH -> WristConstants.high
            }
        )

    val alignerCmd
        get() = ChooseCmd(
            InstantCmd({}), // no point to align when ground
            AlignerCmd(
                aligner,
                when(strat) {
                    Strategy.LOW -> AlignerConstants.low
                    else -> AlignerConstants.deposit
                }
            )
        ) { strat == Strategy.GROUND }
}