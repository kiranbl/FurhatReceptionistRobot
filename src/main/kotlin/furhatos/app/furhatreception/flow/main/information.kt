package furhatos.app.furhatreceptionist.flow.main


import furhatos.app.furhatreceptionist.flow.Parent
import furhatos.flow.kotlin.*

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import furhatos.gestures.Gestures
import org.bson.Document

var infoFlag = false;
fun connectToMongoDB(): MongoDatabase {
    val connectionString = "mongodb+srv://FurhatReceptionRobot:Robot123@furhatrecptionistcluste.np7i1yx.mongodb.net/?retryWrites=true&w=majority"
    val mongoClient: MongoClient = MongoClients.create(connectionString)
    return mongoClient.getDatabase("FurhatReceptionist")
}
fun queryCollection(database: MongoDatabase,profName: String,profemail: Boolean?=false,profrole: Boolean?=false): String {
    val collection: MongoCollection<Document> = database.getCollection("staffInformation")
    println("Reached queryCOlleciton "+profName.capitalize())
    val words = profName.split(" ").map { it.capitalize() }.joinToString(" ")

    println("Reached queryCOlleciton after capitalize "+words)
    val filter = Document("name", words)
    val result = collection.find(filter).toList();
    println("Reached queryCOlleciton Result "+result)
    // Process the query results
    val document: Document? = collection.find(filter).firstOrNull()

    return if (document != null) {
        // Value found, return the profileinfo field
        infoFlag=true;
        var data = document["profileinfo"].toString()
        if(profemail==true){
            data =document["email"].toString()
        }
        if(profrole==true){
            data =document["role"].toString()
        }
        println(data)
        data
    } else {
        // Value not found in the database
        println("Data not found")
        "Sorry I was not able to find the information that you requested for at the moment ! Could you please try later"
    }

}


fun StaffInformation(profName : String,profemail:String?=null,profRole:String?=null)  = state(Parent) {
    onEntry {

        val database = connectToMongoDB()
        var data:String?="";
        println("Database "+database)
        // Query the collection and process results
    if(profName!=null && profemail!=null){
        furhat.gesture(Gestures.Thoughtful(duration = 10.0))
        data = queryCollection(database,profName,true)
        furhat.stopGestures();
    } else if(profName!=null && profRole!=null){
        furhat.gesture(Gestures.Thoughtful(duration = 10.0))
        data = queryCollection(database,profName,false,true)
        furhat.stopGestures();
    }
        else{
        furhat.gesture(Gestures.Thoughtful(duration = 10.0))
        data = queryCollection(database,profName)
        furhat.stopGestures();
    }

        if(infoFlag) {
            if(profemail!=null){
                furhat.say {
                    +"Here is the email address of professor "
                    +profName
                    +delay(1000)
                    +data
                }
                furhat.say {  random{
                    +"Let me spell the email address for you."
                    +"I will spell the email for you."
                    +"Allow me to spell the email address."
                    +"Here's the spelling of the email address."
                    +"The email address is spelled as follows."
                }}
                println("email address "+data)
                for (letter in data) {
                    if(letter=='.'){
                        furhat.say("dot")
                        continue;
                    }

                    println("email letter "+letter)
                    furhat.say(letter.toString())
                }
            }
            else if(profRole!=null){
                furhat.say{
                +profName
                +" is the"
                +data
                }
            }
            else {
                furhat.say(data)
            }
            infoFlag=false;
        }
        else{
            Gestures.ExpressSad();
            furhat.say(data)
            delay(1000)

        }
        terminate()
    }
}

