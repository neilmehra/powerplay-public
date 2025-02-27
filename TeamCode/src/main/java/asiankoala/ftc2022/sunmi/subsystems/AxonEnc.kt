package asiankoala.ftc2022.sunmi.subsystems

import asiankoala.ftc2022.sunmi.constants.ArmConstants
import com.asiankoala.koawalib.hardware.KDevice
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.hardware.AnalogInput

class AxonEnc(deviceName: String) : KDevice<AnalogInput>(deviceName) {
    val pos get() = device.voltage / 3.3 * 360.0 - ArmConstants.encoderOffset
}