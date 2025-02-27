package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.subsystems.Lift
import asiankoala.ftc2022.sunmi.constants.LiftConstants
import com.asiankoala.koawalib.command.commands.InstantCmd

open class LiftCmd(lift: Lift, pos: Double) :
    InstantCmd({ lift.setTarget(pos) })
class HomeLift(lift: Lift) : InstantCmd({
    lift.setTarget(LiftConstants.zeroPos)
    lift.startAttemptingZero()
})
