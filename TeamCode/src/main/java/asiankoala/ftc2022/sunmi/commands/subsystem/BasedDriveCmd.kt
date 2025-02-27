package asiankoala.ftc2022.sunmi.commands.subsystem

import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.control.controller.PIDFController
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.gamepad.KStick
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import kotlin.math.*

class BasedDriveCmd(
    private val drive: KMecanumOdoDrive,
    private val leftStick: KStick,
    private val rightStick: KStick
) : Cmd() {
    private val fastScalars = listOf(1.0, 1.0, 0.65)
    private val valueThreshold = 0.15
    private var isAutoDriving = false
    private var autoDriveHeading = (-90.0).radians
    private var autoDriveX = 0.0
    private val xController = PIDFController(PIDGains())
    private val thetaController = PIDFController(PIDGains())
        .apply { setInputBounds(-PI, PI) }

    private fun joystickFunction(s: Double, k: Double, x: Double): Double {
        return max(0.0, s * x * (k * x.pow(3) - k + 1)) * x.sign
    }

    fun enableAutoDrive() {
        isAutoDriving = true
        autoDriveX = 24.0 * (drive.pose.x.toInt() / 24) + 12.0
        xController.targetPosition = autoDriveX
        thetaController.targetPosition = autoDriveHeading
    }

    fun disableAutoDrive() {
        isAutoDriving = false
    }

    override fun execute() {
        val raws = listOf(
            leftStick.xAxis,
            -leftStick.yAxis,
            rightStick.xAxis,
            -rightStick.yAxis
        ).map { joystickFunction(1.0, 1.0, it) }

        val xMag = raws[0] + raws[2]
        val x = if(xMag.absoluteValue > valueThreshold) xMag else 0.0
        val y = (raws[1] + raws[3]) / 2.0
        val theta = (raws[1] - raws[3]) / 2.0

        if(theta.absoluteValue > 0.15) {
            isAutoDriving = false
        }

        drive.setPowers(
            if(isAutoDriving) {
                val xOutput = xController.update(drive.pose.x)
                val theta = drive.pose.heading
                val thetaOutput = thetaController.update(theta)
                val trans = Vector(xOutput, y).rotate(PI / 2.0 - theta)
                Pose(trans, thetaOutput)
            } else {
                Pose(x * fastScalars[0], y * fastScalars[1], theta * fastScalars[2])
            }
        )
    }
}