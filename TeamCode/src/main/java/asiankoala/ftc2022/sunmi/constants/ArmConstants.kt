package asiankoala.ftc2022.sunmi.constants

import com.acmerobotics.dashboard.config.Config

@Config
object ArmConstants {
    @JvmField var home = 0.42 // 90.0
    @JvmField var intake = 0.09
//    @JvmField var gidle = intake + 0.06
    @JvmField var gidle = intake + 0.08
    @JvmField var deposit = 0.57 // 12.0
    @JvmField var ground = intake + 0.02
    @JvmField var encoderOffset = 118.0
    @JvmField var autoInit = 0.23
    @JvmField var teleopIntake = 0.11

    fun angle(servo: Double): Double {
        val a = 0.0
        val g = 0.15
        val a2 = 90.0
        val h = 0.42
        val r = (a2 - a) / (h - g)
        val o = 230.0
        return o - servo * r
    }

    @JvmField var depositAngle = 41.5 // guessing
    @JvmField var gidleAngle = 181.0 // guessing
    @JvmField var SERVO_EPSILON_DEG = 5.0
}