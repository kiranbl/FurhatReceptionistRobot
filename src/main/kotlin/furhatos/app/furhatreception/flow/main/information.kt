package furhatos.app.furhatreceptionist.flow.main


import Modules
import furhatos.app.furhatreceptionist.flow.Parent
import furhatos.flow.kotlin.*

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import furhatos.gestures.Gestures
import org.bson.Document
import java.util.regex.Pattern
import javax.print.Doc
import java.time.*

var infoFlag = false;
var autumnsemstart = Month.SEPTEMBER;
var autumnsemend = Month.JANUARY;
var springsemstart = Month.FEBRUARY;
var springsemend = Month.JUNE;
var summersemstart = Month.JULY;
var summersemend = Month.AUGUST;
val spring = arrayOf("spring","second")
val autumn = arrayOf("autumn","first")
val summer = arrayOf("summer","third")
val currsem = arrayOf("this","current")
val nextsem = arrayOf("upcoming","next")
val prevsem = arrayOf("last","previous")
var sem: String? = null

var previousSpeech: String?=null;
//fun connectToMongoDB(): MongoDatabase {
//    val connectionString = "mongodb+srv://FurhatReceptionRobot:Robot123@furhatrecptionistcluste.np7i1yx.mongodb.net/?retryWrites=true&w=majority"
//    val mongoClient: MongoClient = MongoClients.create(connectionString)
//    return mongoClient.getDatabase("FurhatReceptionist")
//}
fun queryStaffCollection(database: MongoDatabase,profName: String,profemail: Boolean?=false,profrole: Boolean?=false): String {
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

fun queryModuleCollection(database: MongoDatabase,progName: String): List<Document> {
    val collection: MongoCollection<Document> = database.getCollection("moduleInformation")
    println("Reached queryCOlleciton "+progName.capitalize())
//    val words = progName.split(" ").map { it.capitalize() }.joinToString(" ")
//
//    println("Reached queryCOlleciton after capitalize "+words)
    val regexPattern = Pattern.compile(progName, Pattern.CASE_INSENSITIVE)

    val filter = Document("programmename", regexPattern)
    val result = collection.find(filter);
    println(result)
    val iterator = result.iterator()

    println("Reached queryCOlleciton Result "+result)
    if(iterator.hasNext()){
     infoFlag = true;
    }
    var sourceDocumentsList = ArrayList<Document>()
    for (document in result) {
        println(document)
        val modulename = document.getString("modulename")
         val compulsorymodule = document.getString("compulsorymodule")
        val semester = document.getString("semester")
        val modulesList=Document();
        modulesList["modulename"]= modulename;
        modulesList["compulsorymodule"] = compulsorymodule;
        modulesList["semester"] = semester;
        sourceDocumentsList.add(modulesList)
    }

    println(sourceDocumentsList)
    return  sourceDocumentsList;
}

fun StaffInformation(database:MongoDatabase,profName : String,profemail:String?=null,profRole:String?=null,repeatFlag:Boolean=false)  = state(Parent) {
    onEntry {

        //val database = connectToMongoDB()
        var data:String?="";
        println("Database "+database)
        if(!repeatFlag) {
            furhat.say {
                +Gestures.GazeAway
                random {
                    +"Hmm"
                    +"Let's see"
                    +"Let me think"
                    +"Wait a second"
                }
            }
        }
        // Query the collection and process results
    if(profName!=null && profemail!=null){

        data = queryStaffCollection(database,profName,true)
        furhat.stopGestures();
    } else if(profName!=null && profRole!=null){
        data = queryStaffCollection(database,profName,false,true)
        furhat.stopGestures();
    }
        else{

        data = queryStaffCollection(database,profName)
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


fun ModuleInformation(database:MongoDatabase,
                      programmeName:String?=null,
                      semester:String?=null,
                      modules: Boolean?=false,
                      compulsory:Boolean?=false,
                      repeatFlag: Boolean=false) = state(Parent) {
    onEntry {
        println("Database " + database)
        if(!repeatFlag){
        furhat.say{+ Gestures.GazeAway
            random{
                +"Hmm"
                +"Let's see"
                +"Let me think"
                +"Wait a second"
            }
        }
        }
        val data = programmeName?.let { queryModuleCollection(database, it) }
        furhat.stopGestures();

        var numberofModules = data?.size;
        if (infoFlag) {
//            if(modulename!=null){
//                furhat.say("The module number for $modulename is ")
//                if (data != null) {
//                    for(document in data){
//                        if(document.getString("modulename").toLowerCase()==modulename.toLowerCase()){
//                            furhat.say(document.getString("modulename"))
//                            break;
//                        }
//                        else{
//                            furhat.say("Sorry but the module number that you requested for is not part of the programme name mentioned." +
//                                    " Please retry with a correct programme name to which that module belongs to...")
//                            break;
//                        }
//                    }
//                }
//            }
             if (programmeName != null && semester==null && modules == false) {
            furhat.say("There are a total of "+numberofModules+" modules to choose from in the programme.")
            furhat.say("There are a few modules which are compulsory and must pass modules !! Those are ")
            if (data != null) {
                for(document in data){
                    if(document.getString("compulsorymodule")=="YES"){
                        furhat.say(document.getString("modulename"))
                    }
                }
            }
            furhat.say("Rest of the module options which are not compulsary modules are ")
            if (data != null) {
                for(document in data){
                    if(document.getString("compulsorymodule")=="NO"){
                        furhat.say(document.getString("modulename"))
                    }
                }
            }
             furhat.say("Each module credit is valued at fifteen credits. And you must choose 120 credits from this list which also includes the compulsory modules.")
        }
            else if(programmeName != null && semester!=null && modules==true ){
                val currentDate = LocalDate.now()
                val currentMonth = currentDate.month
                 sem = when {
                    currentMonth in autumnsemstart..autumnsemend -> "AUTUMN"
                    currentMonth in springsemstart..springsemend -> "SPRING"
                    currentMonth in summersemstart..summersemend -> "SUMMER"
                     else -> ""
                 }

                // Adjust the semester based on predefined arrays
                when {
                    semester.toLowerCase() in autumn -> sem = "AUTUMN"
                    semester.toLowerCase() in spring -> sem = "SPRING"
                    semester.toLowerCase() in summer -> sem = "SUMMER"
                    semester.toLowerCase() in nextsem -> sem = when (sem) {
                        "AUTUMN" -> "SPRING"
                        "SPRING" -> "SUMMER"
                        "SUMMER" -> "AUTUMN"
                        else -> sem
                    }
                    semester.toLowerCase() in prevsem -> sem = when (sem) {
                        "AUTUMN" -> "SUMMER"
                        "SPRING" -> "AUTUMN"
                        "SUMMER" -> "SPRING"
                        else -> sem
                    }
                    semester.toLowerCase() in currsem -> sem
                }

                println(" semester is: $sem")
                if(sem =="SUMMER"){
                    furhat.say("Msc Dissertation project happens during this semester and starts " +
                            "from the end of the spring examination period until mid september. The dissertation project is 60 credits -  " +
                            "60 credits per semester equates to about 40 hours work per week. ")
                }
                else {
                    furhat.say("These are a modules that happen during the $sem semester for $programmeName programme..")
                    if (data != null) {
                        for (document in data) {
                            if (document.getString("semester") == sem) {
                                furhat.say(document.getString("modulename") + ", ")
                            }

                        }
                    }

                }
            }
            else if(programmeName != null && semester==null && modules==true && compulsory==true ){
                furhat.say("There are a few modules which are compulsory and must pass modules !! Those are ")
                if (data != null) {
                    for(document in data){
                        if(document.getString("compulsorymodule")=="YES"){
                            furhat.say(document.getString("modulename"))
                        }
                    }
                }
            }
            infoFlag=false;
        }
        else{
            Gestures.ExpressSad();
            furhat.say("Sorry I was not able to find the information that you requested for at the moment ! Could you please try later")
            delay(1000)
        }

        terminate()
    }

}



//    fun RepeatInformation()  = state(Parent) {
//    onEntry {
//        if (previousSpeech != null) {
//            furhat.say{
//                + Gestures.Smile
//                random{
//                +"Certainly, I'd be happy to go over that information again,"
//                +"Of course, I'll be glad to repeat the information for you,"
//                +"Absolutely, I can certainly give you the information once more,"
//                +"No problem, I'll repeat the information for you,"
//                +"Sure thing, let me recap the information for you,"
//                +"Certainly, I'll run through the information again,"
//                +"Certainly, I'd be happy to provide you with the information once more,"
//                +"Of course, I'm here to help. Let me repeat the information,"
//                +"Sure, I can give you the information again if you'd like,"
//                +"Of course, feel free to ask any questions. I'll repeat the information,"
//                +"Absolutely, let's go through the information again,"
//                +"Certainly, let's revisit the information one more time,"
//                +"Of course, I'll be happy to repeat the details,"
//                +"Sure, let's go over the information once more,"
//                +"Certainly, I'll be glad to reiterate the information,"
//                }
//            }
//            furhat.say(previousSpeech!!)
//        } else {
//            furhat.say("I haven't said anything to repeat.")
//        }
//        terminate()
//        }

//    }
