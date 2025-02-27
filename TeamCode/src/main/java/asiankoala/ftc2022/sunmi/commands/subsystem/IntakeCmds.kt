package asiankoala.ftc2022.sunmi.commands.subsystem

import asiankoala.ftc2022.sunmi.constants.IntakeConstants
import asiankoala.ftc2022.sunmi.subsystems.Intake
import com.asiankoala.koawalib.command.commands.InstantCmd

class TurnOnIntake(intake: Intake) : InstantCmd({ intake.setPower(IntakeConstants.intakeSpeed) })
class TurnOffIntake(intake: Intake) : InstantCmd({ intake.setPower(0.0) })
class RetractIntake(intake: Intake) : InstantCmd({ intake.setRetract(IntakeConstants.retract) })
class DeployIntake(intake: Intake) : InstantCmd({ intake.setRetract(IntakeConstants.extend) })
