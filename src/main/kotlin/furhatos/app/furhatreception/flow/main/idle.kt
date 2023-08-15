package furhatos.app.furhatreceptionist.flow.main

import com.mongodb.client.MongoDatabase
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

fun Idle(database:MongoDatabase)= state {
    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Greeting(database))
    }

}
