package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.Arm
import asiankoala.ftc2022.sunmi.subsystems.Claw
import com.asiankoala.koawalib.command.commands.BlankCmd
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger


private class ArmCmd(arm: Arm, pos: Double) : InstantCmd({ arm.setTarget(pos) })

// first lets test if the arm can move safely at the grip angle
// if it can, then we can just use that for safe move instead of the fully closed angle
// so i'll replace claw::isClosed with claw::isGripped here, and have a backup for gripped cmds
// later in this file

// we have an override for when we want to move the arm with the claw open; this is really only
// when we're intaking and want to move the arm up or down
abstract class SafeMoveArm(val arm: Arm, claw: Claw, pos: Double, override: Boolean = false) : SequentialGroup(
    ChooseCmd(BlankCmd(), WaitUntilCmd(claw::isGripped)) { override },
    ArmCmd(arm, pos),
) {

    override fun execute() {
        super.execute()
        Logger.put("arm angle", arm.pos)
        Logger.put("is arm at depo", arm.isArmAtDeposit)
    }

    init {
        addRequirements(arm)
    }
}

class GIDLEArm(arm: Arm, claw: Claw, override: Boolean = false) : SafeMoveArm(arm, claw, ArmConstants.gidle, override)
class DepositArm(arm: Arm, claw: Claw, override: Boolean = false) : SafeMoveArm(arm, claw, ArmConstants.deposit, override)
class HomeArm(arm: Arm, claw: Claw, override: Boolean = false) : SafeMoveArm(arm, claw, ArmConstants.home, override)
class IntakeArm(arm: Arm, claw: Claw, override: Boolean = false) : SafeMoveArm(arm, claw, ArmConstants.intake, override)
class GroundArm(arm: Arm, claw: Claw) : SafeMoveArm(arm, claw, ArmConstants.ground, true) // only can go ground when we're depositing, so ill just override
class TeleOpIntake(arm: Arm, claw: Claw) : SafeMoveArm(arm, claw, ArmConstants.teleopIntake, true)