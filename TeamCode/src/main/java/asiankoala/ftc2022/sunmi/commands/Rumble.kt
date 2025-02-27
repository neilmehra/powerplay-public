package asiankoala.ftc2022.sunmi.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.gamepad.KGamepad

class Rumble(gamepad: KGamepad) : InstantCmd({ gamepad.rumble(500) })