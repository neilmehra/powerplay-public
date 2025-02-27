package asiankoala.ftc2022.sunmi.constants

import com.acmerobotics.dashboard.config.Config

@Config
object SeqConstants {
    @JvmField var afterArmDownDt = 0.15
    @JvmField var afterClawGrabDt = 0.2
    @JvmField var waitAfterDeposit = 0.2
    @JvmField var idleAlignerWait = 0.1
    @JvmField var depositAlignerWait = 0.3
    @JvmField var openAfterDeposit = 0.7
}