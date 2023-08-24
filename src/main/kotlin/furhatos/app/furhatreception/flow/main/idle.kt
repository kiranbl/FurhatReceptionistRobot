package furhatos.app.furhatreceptionist.flow.main

import com.mongodb.client.MongoDatabase
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

val Idle= state {
    onEntry {
        furhat.ledStrip.solid(java.awt.Color.BLUE)
        furhat.attendNobody()
    }
    onReentry {
        furhat.ledStrip.solid(java.awt.Color.BLUE)
    }
    onUserEnter {
        furhat.attend(it)
        goto(Greeting)
    }

}
