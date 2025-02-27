package asiankoala.ftc2022.sunmi.constants

import com.acmerobotics.dashboard.config.Config

@Config
object ClawConstants {
    @JvmField var open = 0.36
    @JvmField var close = 0.52
    @JvmField var intakeThreshold = 60.0
    @JvmField var semiOpenForAuto = 0.45

    @JvmField var openAngle = 107.0 // guessing
    @JvmField var gripAngle = 74.0 // funny number
    const val SERVO_EPSILON_DEG = 4.0
}
