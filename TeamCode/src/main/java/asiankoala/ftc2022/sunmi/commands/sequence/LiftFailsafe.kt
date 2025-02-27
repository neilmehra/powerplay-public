package asiankoala.ftc2022.sunmi.commands.sequence

import asiankoala.ftc2022.sunmi.subsystems.Lift
import com.asiankoala.koawalib.command.commands.InstantCmd

class LiftFailsafe(lift: Lift) : InstantCmd(lift::startFailsafeZero)