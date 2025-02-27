package asiankoala.ftc2022.sunmi.commands

import asiankoala.ftc2022.sunmi.TeleBot
import asiankoala.ftc2022.sunmi.Strategy
import asiankoala.ftc2022.sunmi.commands.subsystem.DeployIntake
import asiankoala.ftc2022.sunmi.commands.subsystem.RetractIntake
import com.asiankoala.koawalib.command.commands.BlankCmd
import com.asiankoala.koawalib.command.commands.InstantCmd

class SetStrat(sunmi: TeleBot, strategy: Strategy) : InstantCmd({
    sunmi.strat = strategy
    + if(strategy == Strategy.GROUND) {
        RetractIntake(sunmi.intake)
    } else {
        // only deploy the intake when we're not stacking
        if(!sunmi.isStacking) {
            DeployIntake(sunmi.intake)
        } else {
            BlankCmd()
        }
    }
})