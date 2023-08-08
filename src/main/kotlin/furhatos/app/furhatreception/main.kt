package furhatos.app.furhatreception

import furhatos.app.furhatreceptionist.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill




class FurhatreceptionSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
