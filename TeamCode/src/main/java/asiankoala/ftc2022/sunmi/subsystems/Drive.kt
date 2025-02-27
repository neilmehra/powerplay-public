package asiankoala.ftc2022.sunmi.subsystems

import com.asiankoala.koawalib.control.controller.PIDFController
import com.asiankoala.koawalib.control.controller.PIDGains
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry
import kotlin.math.PI

class Drive(
    fl: KMotor,
    bl: KMotor,
    br: KMotor,
    fr: KMotor,
    odo: KThreeWheelOdometry
) : KMecanumOdoDrive(fl, bl, br, fr, odo, false) {
    var isAutoDriving = false
    var autoDriveHeading = (-90.0).radians
    var autoDriveX = 0.0
    private val xController = PIDFController(PIDGains(1.0 / 18.0))
    private val thetaController = PIDFController(PIDGains(1.0 / 180.0.radians))
        .apply { setInputBounds(-PI, PI) }

    fun enableAutoDrive() {
        isAutoDriving = true
        autoDriveX = 24.0 * (pose.x.toInt() / 24) + 12.0
        xController.targetPosition = autoDriveX
        thetaController.targetPosition = autoDriveHeading
    }

    fun disableAutoDrive() {
        isAutoDriving = false
    }

    override fun periodic() {
        if(isAutoDriving) {
            val xOutput = xController.update(pose.x)
            val theta = pose.heading
            val thetaOutput = thetaController.update(theta)
            val trans = Vector(xOutput, powers.y).rotate(PI / 2.0 - theta)
            setPowers(Pose(trans, thetaOutput))
        }
        super.periodic()
    }
}