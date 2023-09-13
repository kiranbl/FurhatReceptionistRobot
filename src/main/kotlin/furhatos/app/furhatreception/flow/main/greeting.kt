package furhatos.app.furhatreceptionist.flow.main


import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import furhatos.app.furhatreceptionist.flow.Parent
import furhatos.app.furhatreceptionist.nlu.StaffInformationIntent
import furhatos.app.furhatreceptionist.nlu.ModuleInformationIntent
import furhatos.app.furhatreceptionist.nlu.RoomInformationIntent
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.EnumEntity
import furhatos.nlu.common.No
import furhatos.nlu.common.RequestRepeat
import furhatos.util.Language
import java.io.File
import java.util.*

// Variable to store the current related information happening during the conversation
var currentprogrammename:String? =null
var currentstaffname: String ? = null

// Variables defined to store the previous information that was asked by the user
var repeatprofName:String?=null
var repeatprofemail:String?=null
var repeatprofRole:String?=null
var repeatprogrammeName: String?=null
var repeatsemester:String?=null
var repeatmodules:Boolean=false
var repeatcompulsory:Boolean=false
var repeatprofnameRoom :String?=null
var repeatroomname:String?=null

// Variables defined to call state based on the intent
var moduleIntentFlag:Boolean=false
var staffIntentFlag:Boolean=false
var staffRoomIntentFlag:Boolean=false

// Function that defines the definition for MongoDB database connection and retruns the MongoDatabase instance
fun connectToMongoDB(): MongoDatabase {
    val connectionString = "mongodb+srv://FurhatReceptionRobot:Robot123@furhatrecptionistcluste.np7i1yx.mongodb.net/?retryWrites=true&w=majority"
    val mongoClient: MongoClient = MongoClients.create(connectionString)
    return mongoClient.getDatabase("FurhatReceptionist")
}

var  mail = listOf("Email","Electronic Mail","Mail","email address");
var role = listOf("Position", "Role", "Title", "Job", "Duty", "Post");
var roomFind = listOf("Find",
    "Look",
    "Discover",
    "Locate",
    "Uncover",
    "Detect",
    "Identify",
    "Spot",
    "Encounter",
    "Pinpoint",
    "Come across",
    "Come upon",
    "Stumble upon",
    "Detect",
    "Ascertain",
    "Discern",
    "Ferret out",
    "Chamber",
    "Space",
    "Area",
    "Quarters",
    "Cubicle",
    "room"
    )
var module = listOf("semester","module","modules","subject","subjects","course","courses","topics")
// Function to find matching word from the list of professor names
fun findMatchingWord(text: String, wordList: List<String>): String? {
    for (word in wordList) {
        if (text.contains(word, ignoreCase = true)) {
            return word
        }
    }
    return null
}
fun getStaffName(name: String): String {
    var namelist = listOf(
        "Roger K Moore",
        "Guy Brown",
        "Temitope Adeosun",
        "Behzad Abdolmaleki",
        "Nikos Aletras",
        "Jon Barker",
        "Harsh Beohar",
        "Kirill Bogdanov",
        "Kalina Bontcheva",
        "Gergely Buday",
        "Zhixiang Chen",
        "Heidi Christensen",
        "John Clark",
        "Richard Clayton",
        "Mike Cruchten",
        "Hamish Cunningham",
        "John Derrick",
        "Benjamin Dowling",
        "Islam Elgendy",
        "Matt Ellis",
        "Andreas Feldmann",
        "Rob Gaizauskas",
        "Stefan Goetze",
        "Prosanta Gope",
        "Yoshi Gotoh",
        "Philip Green",
        "Charles Grellois",
        "Thomas Hain",
        "Jungong Han",
        "Rob Hierons",
        "Mark Hepple",
        "Tahsinur Khan",
        "Vitaveska Lanfranchi",
        "James Law",
        "Jochen Leidner",
        "Andrew Lewis-Smith",
        "Chenghua Lin",
        "Robert Loftin",
        "Haiping Lu",
        "Alexandr Lucas",
        "Fatima Maikore",
        "Steve Maddock",
        "Luca Manneschi",
        "Michael Mangan",
        "James Marshall",
        "Phil McMinn",
        "John McNamara",
        "Nafise Sadat Moosavi",
        "Sagnik Mukhopadhyay",
        "Emma Norling",
        "Siobhán North",
        "Olakunle Olayinka",
        "Pietro Oliveto",
        "Varvara Papazoglou",
        "Aryan Pasikhani",
        "Andrei Popescu",
        "Tony Prescott",
        "Anton Ragni",
        "Paul Richmond",
        "José Miguel Rojas",
        "Max Sandström",
        "Carolina Scarton",
        "Areeb Sherwani",
        "Donghwan Shin",
        "Anthony Simons",
        "Michael Smith",
        "Xingyi Song",
        "Joachim Spoerhase",
        "Mike Stannett",
        "Mark Stevenson",
        "Andrew Stratton",
        "Georg Struth",
        "Navid Talebanfard",
        "Ramsay Taylor",
        "Eleni Vasilaki",
        "Mari Cruz Villa Uriol",
        "Aline Villavicencio",
        "Jonni Virtema",
        "Dawn Walker",
        "Neil Walkinshaw",
        "Paul Watton",
        "Stuart Wilson",
        "Bhagya Wimalasiri",
        "Joab Winkler",
        "Po Yang",
        "Shuo Zhou",
        "Maksim Zhukovskii"
    )

    return namelist.find {it.contains(name, ignoreCase = true)}!!

}

// Greeting state which inherits the Parent state
val Greeting :State = state(Parent) {

    // Calling the mongodb connection function
    var database: MongoDatabase = connectToMongoDB();

    // Getting current time and setting the time of day
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    var moreInfoFlag :Boolean = false;
    val timeofday = when{
        hour<12 -> "Morning"
        hour in 13..17 -> "Afternoon"
        else -> "Evening"
    }
    //trigger that triggers when it enters this state for the first time
    onEntry {
        //dialogLogger.startSession()
        // Setting up Furhat robot LED light color to white when it enters this state
        furhat.ledStrip.solid(java.awt.Color.WHITE)
        furhat.gesture(Gestures.Smile)
        furhat.say {
            random {
                +"Hello Nice to meet you. "
                +"Hey Pleasure to meet you. "
                +"Good to see you. "
                +"Good $timeofday !!! Nice to meet you. "
            }
        }
        furhat.ask({
            random {
                +"Can I help you with anything related to department?"
                +"Do you need any assistance?"
                +"Is there anything I can help you with related to the department?"
                +"Would you like some help?"
                +"Do you need help or information about the department?"
                +"Is there something I can assist you with?"
                +"May I offer you information related to the department?"
                +"Are you looking for any specific information related to our department?"
                +"Can I provide you with any help or guidance?"
                +"Do you require any help or details regarding our department?"
                +"Is there anything you would like to know or need help with our department?"
                +"Would you like me to assist you with information about our department?"
                +"Can I be of any help or give you information on something related to department?"
                +"Do you need any support or have questions about our department?"
                +"Is there anything in particular you need help with or want to know about in our department?"
                +"Is there a specific topic or area you need assistance or information on our department?"
                +"Do you have any inquiries or need assistance with anything related to department?"
                +"Can I help you find what you're looking for or provide more information on our department?"
                +"Are you seeking help or information regarding a specific matter related to the department?"
                +"Do you need any help or have any questions about the department?"
            }
            +" You can ask me information about a professor, modules in a particular programme for postgraduate taught courses and you can ask me directions to a particular room. "
            random {
                +" Feel free to ask me now. "
                +" What's on your mind? Ask away!"
                +" Your questions are welcome; I'm ready to respond. "
                +" Now you can ask me your question ? "
                +" Go ahead with your query "
            }
        })
    }
    //trigger that triggers when it reenters this state
    onReentry {
        if(!moreInfoFlag) {
            // Setting up Furhat robot LED light color to white when it enters this state
            furhat.ledStrip.solid(java.awt.Color.WHITE)
            furhat.ask({
                random{
                    +" Feel free to ask me now."
                    +" What's on your mind? Ask away!"
                    +" Your questions are welcome; I'm ready to respond."
                    +" Now you can ask me your question ? "
                    +" Go ahead with your query "
                }
                +Gestures.Smile
//                random {
//                    +"Can I help you with anything related to department?"
//                    +"Do you need any assistance?"
//                    +"Is there anything I can help you with related to the department?"
//                    +"Would you like some help?"
//                    +"Do you need help or information about the department?"
//                    +"Is there something I can assist you with?"
//                    +"May I offer you information related to the department?"
//                    +"Are you looking for any specific information related to our department?"
//                    +"Can I provide you with any help or guidance?"
//                    +"Do you require any help or details regarding our department?"
//                    +"Is there anything you would like to know or need help with our department?"
//                    +"Would you like me to assist you with information about our department?"
//                    +"Can I be of any help or give you information on something related to department?"
//                    +"Do you need any support or have questions about our department?"
//                    +"Is there anything in particular you need help with or want to know about in our department?"
//                    +"Is there a specific topic or area you need assistance or information on our department?"
//                    +"Do you have any inquiries or need assistance with anything related to department?"
//                    +"Can I help you find what you're looking for or provide more information on our department?"
//                    +"Are you seeking help or information regarding a specific matter related to the department?"
//                    +"Do you need any help or have any questions about the department?"
//                }
            })
        }
        else{
            // Setting up Furhat robot LED light color to white when it enters this state
            furhat.ledStrip.solid(java.awt.Color.WHITE)
            furhat.ask({
                random {
                    +"Can I help you with anything else?"
                    +"Do you need any more assistance?"
                    +"Would you like some more help?"
                    +"Do you need any more help or information?"
                    +"Is there something else I can assist you with?"
                    +"May I offer you any more information?"
                    +"Are you looking for any other specific information?"
                    +"Do you require any more help or details?"
                }
            })
            moreInfoFlag = false;
        }
    }


    // trigger on request repeat intent
    onResponse<RequestRepeat> {
        furhat.say{
            + Gestures.Smile
            random{
                +"Certainly, I'd be happy to go over that information again,"
                +"Of course, I'll be glad to repeat the information for you,"
                +"Absolutely, I can certainly give you the information once more,"
                +"No problem, I'll repeat the information for you,"
                +"Sure thing, let me recap the information for you,"
                +"Certainly, I'll run through the information again,"
                +"Certainly, I'd be happy to provide you with the information once more,"
                +"Of course, I'm here to help. Let me repeat the information,"
                +"Sure, I can give you the information again if you'd like,"
                +"Of course, feel free to ask any questions. I'll repeat the information,"
                +"Absolutely, let's go through the information again,"
                +"Certainly, let's revisit the information one more time,"
                +"Of course, I'll be happy to repeat the details,"
                +"Sure, let's go over the information once more,"
                +"Certainly, I'll be glad to reiterate the information,"
            }
        }
        // Calling a state based on the previous intent that was asked by the user again to repeat
        if(staffIntentFlag){
            call(StaffInformation(database, repeatprofName!!, repeatprofemail, repeatprofRole,true))
        }
        else if(moduleIntentFlag){
            call(ModuleInformation(database, repeatprogrammeName!!, repeatsemester, repeatmodules,repeatcompulsory,true))
        }
        else if(staffRoomIntentFlag){
            call(RoomInformation(database, repeatprofnameRoom, repeatroomname,true))
        }
            moreInfoFlag = true;
            reentry();


    }

    // trigger on staff information intent
    onResponse<StaffInformationIntent>{

        staffIntentFlag = true;
        moduleIntentFlag = false;
        staffRoomIntentFlag = false;
        var staffname:String?;

        // Checking if user query has the staff name mentioned
        if(currentstaffname == null && it.intent.getString("staffname") == null){
            furhat.ask{random{
                +"Can you repeat the last query with the staff name ???"
                +"Could you please repeat the last query with the staff name ???"
                +"Could you please repeat the last query with the staff name that you are asking for ???"
            }
            }
        }

        // Condition to check if there is multiple entities returned and relevant data is retrieved
        if(it.intent.getString("staffname")!=null){
            val delimiter = " and "
            var markSubstring:String?;
            if(it.intent.getString("staffname").contains(" and ")){
                markSubstring = it.intent.getString("staffname").substringBefore(delimiter)
                staffname = getStaffName(markSubstring)
                currentstaffname = staffname;
            }
            else {
                staffname = getStaffName(it.intent.getString("staffname"))
                currentstaffname = staffname;
            }
        }
        else{
            staffname = currentstaffname;
        }

        // Conditions based on the entites returned from the intents is checked and relevant state is called with these entity parameters.
        if((it.intent.getString("staffname")!=null && it.intent.getString("staffemail")!=null) || it.intent.getString("staffemail")!=null  ) {

            repeatprofemail = it.intent.getString("staffemail")
            repeatprofRole = null

            if (it.intent.getString("staffname") == null) {
                repeatprofName = currentstaffname;
                val confirmation =
                    furhat.askYN("Is it about professor $currentstaffname " + it.intent.getString("staffemail") + " ?");
                if (confirmation) {
                    println("Reached with name and email here")
                    call(StaffInformation(database, currentstaffname!!, it.intent.getString("staffemail")));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                } else {
                    furhat.ask({
                        random {
                            +"Could you please repeat your last query?"
                        }
                    });
                }

            } else {
                repeatprofName = staffname
                val confirmation =
                    furhat.askYN("Is it about professor $staffname " + it.intent.getString("staffemail") + " ?");
                if (confirmation) {
                    println("Reached with name and email here")
                    call(StaffInformation(database, staffname!!, it.intent.getString("staffemail")));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                } else {
                    furhat.ask({
                        random {
                            +"Could you please repeat your last query?"
                        }
                    });
                }
            }
        }
        else if((it.intent.getString("staffname")!=null && it.intent.getString("staffrole")!=null) || it.intent.getString("staffrole")!=null ) {

            repeatprofRole = it.intent.getString("staffrole")
            repeatprofemail=null
            if (it.intent.getString("staffname") == null) {
                repeatprofName = currentstaffname;
                val confirmation =
                    furhat.askYN("Is it about professor $currentstaffname's " + it.intent.getString("staffrole") + " in the department ??");
                if (confirmation) {
                    println("Reached with name and role here")
                    call(StaffInformation(database, currentstaffname!!, null, it.intent.getString("staffrole")));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                } else {
                    furhat.ask({
                        random {
                            +"Could you please repeat the last query?"
                        }
                    });
                }
            }
            else{
                repeatprofName = staffname;
                val confirmation =
                    furhat.askYN("Is it about professor $staffname's " + it.intent.getString("staffrole") + " in the department ??");
                if (confirmation) {
                    println("Reached with name and role here")
                    call(StaffInformation(database, staffname!!, null, it.intent.getString("staffrole")));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                } else {
                    furhat.ask({
                        random {
                            +"Could you please repeat the last query?"
                        }
                    });
                }
            }
        }
        else if(it.intent.getString("staffname")!=null || it.intent.getString("pronoun")!=null){
            repeatprofName = staffname
            repeatprofRole = null
            repeatprofemail=null
            val confirmation = furhat.askYN("Is it about professor $staffname ");
            if(confirmation){
                call(StaffInformation(database, staffname!!));
                println("Reached again here after info")
                moreInfoFlag = true;
                reentry();
            }else{
                furhat.ask({ random{
                    +"Could you please repeat the name again??"
                }});
            }
        }
        }
    // trigger on module information intent
    onResponse<ModuleInformationIntent>{

        staffIntentFlag = false;
        moduleIntentFlag = true;
        staffRoomIntentFlag = false;

        // Checking if user query has the programme name mentioned
        if(currentprogrammename == null && it.intent.getString("programmename") == null){
            furhat.ask{random{
                +"Can you repeat the last query with the programme name ???"
                +"Could you please repeat the last query with the programme name ???"
                +"Could you please repeat the last query with the course name that you are asking for ???"
            }
                +"The programme names could be Advanced Computer Science, or, Data Analytics, or, Computer Science With Speech And Language Processing, or, Cybersecurity and Artificial Intelligence"
            }
        }

        if(it.intent.getString("programmename")!=null){
            currentprogrammename = it.intent.getString("programmename");
        }
        // Checking if user query has the programme name mentioned
        if(it.intent.getString("modules")!=null && it.intent.getString("programmename")==null && currentprogrammename==null ) {
            furhat.ask {
                random {
                    +"Can you repeat the last query with the programme name ???"
                    +"Could you please repeat the last query with the programme name ???"
                    +"Could you please repeat the last query with the course name that you are asking for ???"
                }
            }
        }
        // Conditions based on the entites returned from the intents is checked and relevant state is called with these entity parameters.
         if((it.intent.getString("programmename")!=null && it.intent.getString("modules")!=null && it.intent.getString("semester")==null && it.intent.getString("compulsory")==null) ||
             (currentprogrammename!=null && it.intent.getString("modules")!=null && it.intent.getString("semester")==null && it.intent.getString("compulsory")==null))
        {

           repeatmodules = false
           repeatsemester =  null
           repeatcompulsory = false
            if(it.intent.getString("programmename")==null){
                repeatprogrammeName = currentprogrammename
                val confirmation = furhat.askYN("Is it about modules in $currentprogrammename programme ?");
                if(confirmation){
                    println("Reached with programme name here")
                    call(ModuleInformation(database,it.intent.getString("programmename")));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                }else{
                    furhat.ask({ random{
                        +"Could you please repeat your last query?"
                    }});
                }
            }
            else{
                repeatprogrammeName = it.intent.getString("programmename")
                val confirmation = furhat.askYN(it.intent.toString());
                if(confirmation){
                    println("Reached with programme name here")
                    call(ModuleInformation(database,it.intent.getString("programmename")));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                }else{
                    furhat.ask({ random{
                        +"Could you please repeat your last query?"
                    }});
                }
            }

        }
        else if((it.intent.getString("modules")!=null && it.intent.getString("semester")!=null && it.intent.getString("programmename")==null ) ||
            it.intent.getString("modules")!=null && it.intent.getString("semester")!=null && it.intent.getString("programmename")!=null )
        {
            repeatmodules = true
            repeatsemester =  it.intent.getString("semester")
            repeatcompulsory = false
            if(it.intent.getString("programmename")==null){
                repeatprogrammeName = currentprogrammename
                val confirmation = furhat.askYN("Is it about "+ it.intent.getString("modules") + " in "+
                        currentprogrammename + " programme for "+it.intent.getString("semester")+ " semester ?");
                if(confirmation){
                    println("Reached with programme and semester here")
                    call(ModuleInformation(database, currentprogrammename, it.intent.getString("semester"),true));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                }else{
                    furhat.ask({ random{
                        +"Could you please repeat your last query?"
                    }});
                }
            }
            else{
                repeatprogrammeName = it.intent.getString("programmename")
                val confirmation = furhat.askYN(it.intent.toString());
                if(confirmation){
                    println("Reached with programme and semester here")
                    call(ModuleInformation(database, it.intent.getString("programmename"), it.intent.getString("semester"),true));
                    println("Reached again here after info")
                    moreInfoFlag = true;
                    reentry();
                }else{
                    furhat.ask({ random{
                        +"Could you please repeat your last query?"
                    }});
                }
            }

        }
        else if((it.intent.getString("programmename")!=null && it.intent.getString("compulsory") !=null) ||
           (it.intent.getString("programmename")==null && it.intent.getString("compulsory") !=null)  ){

           repeatmodules = true
           repeatcompulsory = true
           repeatsemester = null

           if(it.intent.getString("programmename")==null){
               repeatprogrammeName = currentprogrammename
               val confirmation = furhat.askYN("Is it about "+ it.intent.getString("compulsory") + it.intent.getString("modules") + " in "+
                       currentprogrammename + " programme ");
               if(confirmation){
                   println("Reached with compulsory modules and current programme  here")
                   call(ModuleInformation(database, currentprogrammename, null,true,true));
                   println("Reached again here after info")
                   moreInfoFlag = true;
                   reentry();
               }else{
                   furhat.ask({ random{
                       +"Could you please repeat your last query?"
                   }});
               }
           }
           else{
               repeatprogrammeName = it.intent.getString("programmename")
               val confirmation = furhat.askYN(it.intent.toString());
               if(confirmation){
                   println("Reached with compulsory modules and current programme  here")
                   call(ModuleInformation(database, it.intent.getString("programmename"), null,true,true));
                   println("Reached again here after info")
                   moreInfoFlag = true;
                   reentry();
               }else{
                   furhat.ask({ random{
                       +"Could you please repeat your last query?"
                   }});
               }
           }


        }


    }

    // trigger on room information intent
    onResponse<RoomInformationIntent>{
        staffIntentFlag = false;
        moduleIntentFlag = false;
        staffRoomIntentFlag = true;
        var staffname: String?;
        if(it.intent.getString("staffname")!=null){
            staffname = getStaffName(it.intent.getString("staffname"))
            currentstaffname = staffname;
        }
        else{
            staffname = currentstaffname;
        }
        // Conditions based on the entites returned from the intents is checked and relevant state is called with these entity parameters.
        if((it.intent.getString("staffname")==null && it.intent.getString("room")!=null && it.intent.getString("roomname")==null)||(it.intent.getString("staffname")==null && it.intent.getString("find")!=null && it.intent.getString("roomname")==null)  ) {
            repeatprofnameRoom = currentstaffname;
            repeatroomname = null
            val confirmation = furhat.askYN("Do you want to know the directions for professor $currentstaffname room ?");
            if (confirmation) {
                println("Reached with staff and room here")
                call(RoomInformation(database, currentstaffname));
                println("Reached again here after info")
                moreInfoFlag = true;
                reentry();
            } else {
                furhat.ask({
                    random {
                        +"Could you please repeat your last query?"
                    }
                });
            }
        }
        else if((it.intent.getString("staffname")!=null && it.intent.getString("room")!=null)||(it.intent.getString("staffname")!=null && it.intent.getString("find")!=null)  ) {
            repeatprofnameRoom = staffname;
            repeatroomname = null
            val confirmation = furhat.askYN("Do you want to know the directions for professor $staffname room ?");
            if (confirmation) {
                println("Reached with staff and room here")
                call(RoomInformation(database, staffname));
                println("Reached again here after info")
                moreInfoFlag = true;
                reentry();
            } else {
                furhat.ask({
                    random {
                        +"Could you please repeat your last query?"
                    }
                });
            }
        }
        else if(it.intent.getString("roomname")!=null ) {
            println("Reached here")
            repeatprofnameRoom = null
            repeatroomname = it.intent.getString("roomname")
            val confirmation = furhat.askYN("Do you want to know the directions for "+ it.intent.getString("roomname")+" ?");
            if (confirmation) {
                println("Reached with roomname here")
                call(RoomInformation(database, null,it.intent.getString("roomname")));
                println("Reached again here after info")
                moreInfoFlag = true;
                reentry();
            } else {
                furhat.ask({
                    random {
                        +"Could you please repeat your last query?"
                    }
                });
            }
        }
    }


    // trigger on No intent
    onResponse<No> {
        furhat.say {
            random{
                +"I'm glad I could help. If you have any more questions in the future, feel free to ask. Goodbye!"
                +"Thank you for reaching out. I hope the information was useful. Have a wonderful day. Goodbye!"
                +"Wishing you a fantastic day ahead. If you ever need information again, don't hesitate to chat. Goodbye!"
                +"Take care and stay informed. If you've got more questions, I am here to help. Goodbye!"
                +"I'm here whenever you need more information. Until next time, goodbye!"
                +"Don't hesitate to return if you need more assistance. Have a great day and goodbye!"
                +"I'm glad I could provide the information you needed. Stay curious and have a wonderful day. Goodbye!"
                +"I hope I've answered your questions. I wish you a pleasant day. Goodbye!"
                +"Thanks for the conversation. Take care and Goodbye!"
                +"If you ever need information, don't hesitate to return. Goodbye!"
                +"Thank you for engaging in this conversation. If you have more queries down the line,I'll be here. Goodbye!"
                +"I'm grateful for the opportunity to assist you. Wishing you a wonderful day. Goodbye!"
            }
            +Gestures.BigSmile
            +Gestures.Smile
        }

        //terminate()
    }

    // trigger on responses outside the intents defined
    onResponse {
        furhat.say("Sorry!!! I didn't understand that. ")

        // Checking for similar words in the user utterances to check what information is being asked
        // And based on that the user is guided on what to ask the Furhat
        val matchingStaffRoleWord = findMatchingWord(it.text, role)
        val matchingStaffMailWord = findMatchingWord(it.text, mail)
        val matchingRoomFindWord = findMatchingWord(it.text, roomFind)
        val matchingModuleWord = findMatchingWord(it.text, module)
        println("Unknown response "+ it.text)

        if (matchingStaffRoleWord != null) {
            furhat.say{
                +Gestures.Smile
                +"Are you asking about a role of a academic staff in the department ??"
                +" If that's the case then you need to make sure you ask about the particular information with the academic staff name. "
                +"You can say something like this. "
                +"What is professor Guy Brown role within the department"
                +"or, "
                +" Give me Guy Brown role. "
            }
        } else if(matchingStaffMailWord!=null) {
            furhat.say{
                +Gestures.Smile
                +"Are you asking me about a email address of a academic staff in the department ??"
                +" If that's the case then you need to make sure you ask about the particular information with the academic staff name. "
                +"You can say something like this. "
                +"I wanted the Guy Brown's email id "
                +"or, "
                +" Could you please provide me with Guy Brown's email address? "
            }
        }
        else if(matchingRoomFindWord!=null){
            furhat.say{
                +Gestures.Smile
                +"Are you asking me for directions to a academic staff room, or, a lab room in the department ??"
                +" If that's the case then you need to make sure you ask about the particular information with the academic staff name or the lab or room name. "
                +"You can ask me something like this. "
                +"Where can I find NLP Lab ,"
                +"or, "
                +"I am looking for the meeting room ,"
                +"or, "
                + "Where can I find  professor Guy Browns room in the department ,"
                +"or, "
                +"Can you give me directions to Guy Brown's room ??"
            }
        }
        else if(matchingModuleWord!=null){
            furhat.say{
                +Gestures.Smile
                +" Are you asking me about modules information in the department ??"
                +" If that's the case then you need to make sure you ask about the particular information with the programme name for the postgraduate taught course. "
                +"You can ask me something like this. "
                +" What are the different modules available in the Data Analytics programme? "
                +"or, "
                +" What are the modules offered for AUTUMN semester? "
                +"or, "
                +" What are the compulsory modules in Advanced Computer Science programme ? "
            }
        }
        else{
            furhat.say {
                +Gestures.Smile
                +" You can ask me information about a professor, his email and his role in the department."
                +" You can ask me about modules in a particular programme for postgraduate taught courses, the compulsory modules in it and about modules taught in a particular semester."
                +" And finally you can ask me directions to a particular staff room"

            }
        }

        reentry()
    }

    onResponseFailed {
        furhat.say {
            +Gestures.Nod()
            +"Sorry, my speech recognizer is not working"
            +Gestures.ExpressSad()
            +"Please try again later !!!"
            +Gestures.BigSmile()
        }
        //goto(Idle())
        //terminate()
    }
    onNetworkFailed {
        furhat.say {
            +Gestures.Nod()
            +"Sorry I guess there is a network issue here !!"
            +Gestures.ExpressSad()
            +"Please try again later !!!"
            +Gestures.BigSmile()
        }
        //goto(Idle())
        //terminate()
    }

}

