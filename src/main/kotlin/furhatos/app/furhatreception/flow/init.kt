package furhatos.app.furhatreceptionist.flow

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import furhatos.app.furhatreceptionist.flow.main.Idle
import furhatos.app.furhatreceptionist.flow.main.Greeting
import furhatos.app.furhatreceptionist.setting.DISTANCE_TO_ENGAGE
import furhatos.app.furhatreceptionist.setting.MAX_NUMBER_OF_USERS
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users


fun connectToMongoDB(): MongoDatabase {
    val connectionString = "mongodb+srv://FurhatReceptionRobot:Robot123@furhatrecptionistcluste.np7i1yx.mongodb.net/?retryWrites=true&w=majority"
    val mongoClient: MongoClient = MongoClients.create(connectionString)
    return mongoClient.getDatabase("FurhatReceptionist")
}
var database: MongoDatabase = connectToMongoDB();

val Init: State = state {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)
        database = connectToMongoDB()
    }
    onEntry {
        /** start interaction */
        when {
            furhat.isVirtual() -> goto(Greeting(database)) // Convenient to bypass the need for user when running Virtual Furhat
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting(database))
            }
            else -> goto(Idle(database))
        }
    }
    onReentry {
        database = connectToMongoDB()
        when {
            furhat.isVirtual() -> goto(Greeting(database)) // Convenient to bypass the need for user when running Virtual Furhat
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting(database))
            }
            else -> goto(Idle(database))
        }
    }

}
