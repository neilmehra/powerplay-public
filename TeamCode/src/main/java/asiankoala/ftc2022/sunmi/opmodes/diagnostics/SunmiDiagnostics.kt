package asiankoala.ftc2022.sunmi.opmodes.diagnostics

import asiankoala.ftc2022.sunmi.opmodes.SunmiTele
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(group = "b")
class SunmiDiagnostics : SunmiTele() {
    override fun mLoop() {
        Logger.put("------DRIVE------")
        Logger.put("power", sunmi.drive.powers)
//        Logger.put("position", sunmi.drive.pose)
//        Logger.put("velocity", sunmi.drive.vel)
//        Logger.put("robot centric vel", Pose(sunmi.drive.vel.vec.rotate
//            (PI / 2.0 - sunmi.drive.pose.heading), sunmi.drive.pose.heading)
//        )
//        Logger.put("is auto driving", sunmi.drive.isAutoDriving)
//        Logger.put("auto drive X", sunmi.drive.autoDriveX)
//        Logger.put("auto drive heading", sunmi.drive.autoDriveHeading)

        Logger.put()
        Logger.put("------LIFT------")
        Logger.put("position", sunmi.lift.pos)
        Logger.put("velocity", sunmi.lift.vel)
        Logger.put("setpoint", sunmi.lift.setpoint)
        Logger.put("power", sunmi.lift.power)
        Logger.put("is attempting zero", sunmi.lift.isAttemptingZero)

        Logger.put()
        Logger.put("------CLAW------")
        Logger.put("position", sunmi.claw.pos)
        Logger.put("distance", sunmi.claw.lastRead)

        Logger.put()
        Logger.put("------ARM------")
        Logger.put("position", sunmi.arm.pos)

        Logger.put()
        Logger.put("------PIVOT------")
        Logger.put("position", sunmi.wrist.pos)

        Logger.put()
        Logger.put("------STATE------")
        Logger.put("strat", sunmi.strat)
        Logger.put("state", sunmi.state)
        Logger.put("is stacking", sunmi.isStacking)
        Logger.put("stack", sunmi.stack)

        Logger.put()
        sunmi.stats.telem()
    }
}