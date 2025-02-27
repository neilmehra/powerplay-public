package asiankoala.ftc2022.sunmi.opmodes.diagnostics

import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.HomeLift
import asiankoala.ftc2022.sunmi.constants.LiftConstants
import asiankoala.ftc2022.sunmi.subsystems.*
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(group = "b")
class LiftDiagnostics : KOpMode(true, 8) {
    private lateinit var lift: Lift
    override fun mInit() {
        Logger.config = LoggerConfig.DASHBOARD_CONFIG
        val claw = Claw()
        val arm = Arm(false)
        val wrist = Wrist()
        val aligner = Aligner(false)
        val br = MotorFactory("br").brake.forward.build()
        lift = Lift(br)
        driver.leftTrigger.onPress(LiftCmd(lift, LiftConstants.high))
        driver.rightTrigger.onPress(HomeLift(lift))
        driver.rightBumper.onPress(LiftCmd(lift, LiftConstants.med))
        driver.leftBumper.onPress(LiftCmd(lift, LiftConstants.low))
    }

    override fun mLoop() {
        Logger.put("pos", lift.pos)
        Logger.put("vel", lift.vel)
        Logger.put("tpos", lift.setpoint.x)
        Logger.put("tvel", lift.setpoint.v)
    }
}