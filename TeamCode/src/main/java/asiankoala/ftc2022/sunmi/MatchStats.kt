package asiankoala.ftc2022.sunmi

import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.EPSILON
import com.asiankoala.koawalib.util.Clock

class MatchStats {
    private var ground = 0
    private var low = 0
    private var med = 0
    private var high = 0
    private var startTime = Clock.seconds
    private val sum = ground + low + med + high
    private val cycleTime = sum / (Clock.seconds - startTime + EPSILON)
    private val str get() = "------STATS------" +
                "Ground: $ground" +
                "Low: $low" +
                "Medium: $med" +
                "High: $high" +
                "Cycle Time: $cycleTime"

    fun start() {
        startTime = Clock.seconds
    }

    fun update(strategy: Strategy) {
        when(strategy) {
            Strategy.GROUND -> ground++
            Strategy.LOW -> low++
            Strategy.MED -> med++
            Strategy.HIGH -> high++
        }
    }

    fun logStats() {
        Logger.logInfo(str)
    }

    fun telem() {
        Logger.put(str)
    }
}