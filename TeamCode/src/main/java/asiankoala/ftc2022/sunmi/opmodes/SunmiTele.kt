package asiankoala.ftc2022.sunmi.opmodes

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.commands.SetStrat
import asiankoala.ftc2022.sunmi.commands.sequence.*
import asiankoala.ftc2022.sunmi.commands.subsystem.*
import asiankoala.ftc2022.sunmi.constants.LiftConstants
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import kotlin.math.max
import kotlin.math.min

@TeleOp(name = "にゃあ", group = "a")
open class SunmiTele : KOpMode(photonEnabled = true, maxParallelCommands = 8) {
    protected lateinit var sunmi: TeleBot

    override fun mInit() {
        sunmi = TeleBot()
        sunmi.drive.defaultCommand = DriveCmd(sunmi.drive, driver.leftStick, driver.rightStick)
        configureGamepad()
        sunmi.strat = Strategy.MED
    }

    private fun configureGamepad() {
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && sunmi.state == State.IDLE) {
                    + GIDLE(sunmi, driver)
                }
            }
        }
        + object : Cmd() {
            override fun execute() {
                if(driver.rightTrigger.isJustPressed && sunmi.state == State.TRANSIT) {
                    + Miyeon(sunmi, driver)
                }
            }
        }
        driver.y.onPress(ChooseCmd(IdleDuringDeploy(sunmi), Idle(sunmi)) { sunmi.state == State.DEPOSITING })
        driver.a.onPress(SetStrat(sunmi, Strategy.GROUND))
        driver.b.onPress(BadGrabFailsafe(sunmi))
        driver.x.onPress(LiftFailsafe(sunmi.lift))
        driver.leftBumper.onPress(SetStrat(sunmi, Strategy.LOW))
        driver.rightBumper.onPress(SetStrat(sunmi, Strategy.MED))
        driver.leftTrigger.onPress(SetStrat(sunmi, Strategy.HIGH))
        driver.dpadRight.onPress(InstantCmd({ sunmi.stack = min(sunmi.stack + 1, 4) }))
        driver.dpadLeft.onPress(InstantCmd({ sunmi.stack = max(sunmi.stack - 1, 1) }))
        + object : Cmd() {
            override fun execute() {
                if(driver.dpadDown.isJustPressed) {
                    sunmi.isStacking = !sunmi.isStacking
                    + if(sunmi.isStacking) {
                        RetractIntake(sunmi.intake)
                    } else {
                        DeployIntake(sunmi.intake)
                    }
                }
            }
        }
        driver.dpadUp.onPress(ConeInRobotFailsafe(sunmi))
    }

    override fun mStart() {
        + Idle(sunmi)
    }
    override fun mLoop() {
        Logger.put("strat", sunmi.strat)
        Logger.put("state", sunmi.state)
        Logger.put("cone", sunmi.cone)

        Logger.put()
        Logger.put("lift", sunmi.lift.pos)
//        Logger.put("claw", sunmi.claw.pos)
//        Logger.put("arm", sunmi.arm.pos)

        Logger.put()
        Logger.put("is stacking", sunmi.isStacking)
        Logger.put("stack", sunmi.stack)
        Logger.put("up", LiftConstants.stacksUp)
        Logger.put("down", LiftConstants.stacksDown)
    }

    override fun mStop() {
        sunmi.stats.logStats()
    }
}