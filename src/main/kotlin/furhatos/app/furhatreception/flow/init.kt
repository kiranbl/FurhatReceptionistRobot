package furhatos.app.furhatreceptionist.flow

import furhatos.app.furhatreceptionist.flow.main.Idle
import furhatos.app.furhatreceptionist.flow.main.Greeting
import furhatos.app.furhatreceptionist.setting.DISTANCE_TO_ENGAGE
import furhatos.app.furhatreceptionist.setting.MAX_NUMBER_OF_USERS
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val Init: State = state {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)
    }
    onEntry {
        /** start interaction */
        when {
            furhat.isVirtual() -> goto(Greeting) // Convenient to bypass the need for user when running Virtual Furhat
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting)
            }
            else -> goto(Idle)
        }
    }

}
