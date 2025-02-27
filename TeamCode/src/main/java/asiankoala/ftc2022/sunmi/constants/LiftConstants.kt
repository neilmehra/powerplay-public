package asiankoala.ftc2022.sunmi.constants

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.math.d
import kotlin.math.max

@Config
object LiftConstants {
    @JvmField var kP = 0.82
    @JvmField var kI = 0.0
    @JvmField var kD = 0.006
    @JvmField var kG = 0.1
    @JvmField var vel = 45.0
    @JvmField var accel = 150.0
    @JvmField var deccel = 150.0
    @JvmField var home = 0.0
    @JvmField var ground = 0.0
    @JvmField var low = 5.8
    @JvmField var med = 15.2
    @JvmField var high = 25.1
    @JvmField var zeroPos = -0.65
    @JvmField var upMult = 1.6
    @JvmField var failsafePow = -0.23
    private fun getOffset(mult: Double) = mult * 5 - 5.6
    private val downOffset = getOffset(upMult) // needs to be 5.7 at last index = 1.6 * (4 + 1) - 2.3
    val stacksUp = List(5) { (it.d + 1) * upMult }
    val stacksDown = List(5) { stacksUp[it] - downOffset }
}