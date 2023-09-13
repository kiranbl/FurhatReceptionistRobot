package furhatos.app.furhatreceptionist.flow.main

import com.mongodb.client.MongoDatabase
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

val Idle= state {
    onEntry {
        // Setting up Furhat robot LED light color to blue when it enters this state
        furhat.ledStrip.solid(java.awt.Color.BLUE)
        furhat.attendNobody()
    }
    onReentry {
        // Setting up Furhat robot LED light color to blue when it enters this state
        furhat.ledStrip.solid(java.awt.Color.BLUE)
        furhat.attendNobody()
    }
    onUserEnter {
        furhat.attend(it)
        // flow transition to the Greeting state on user enter.
        goto(Greeting)
    }

}
