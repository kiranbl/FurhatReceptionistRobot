package furhatos.app.furhatreceptionist.flow.main


import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import furhatos.app.furhatreceptionist.flow.Parent
import furhatos.app.furhatreceptionist.nlu.StaffInformationIntent
import furhatos.app.furhatreceptionist.nlu.ModuleInformationIntent
import furhatos.app.furhatreceptionist.nlu.RoomInformationIntent
//import furhatos.app.furhatreceptionist.nlu.RoomInformationIntent
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.RequestRepeat
import furhatos.util.Language
import java.util.*

var currentprogrammename:String? =null

var repeatprofName:String?=null
var repeatprofemail:String?=null
var repeatprofRole:String?=null

var repeatprogrammeName: String?=null
var repeatsemester:String?=null
var repeatmodules:Boolean=false
var repeatcompulsory:Boolean=false
//var repeatmodulename:String?=null

var repeatprofnameRoom :String?=null
var repeatprofroom: Boolean=false
var repeatroomname:String?=null

var moduleIntentFlag:Boolean=false
var staffIntentFlag:Boolean=false
var staffRoomIntentFlag:Boolean=false

fun connectToMongoDB(): MongoDatabase {
    val connectionString = "mongodb+srv://FurhatReceptionRobot:Robot123@furhatrecptionistcluste.np7i1yx.mongodb.net/?retryWrites=true&w=majority"
    val mongoClient: MongoClient = MongoClients.create(connectionString)
    return mongoClient.getDatabase("FurhatReceptionist")
}

//println(timeofday)

//val greeting = utterance {
//    random {
//        +"Hello Nice to meet you"
//        +"Hey Pleasure to meet you"
//        +"Good to see you"
//        +"Good $timeofday !!! Nice to meet you "
//    }
//
//}
//val askhelp = utterance {
//    random {
//        +"Can I help you with anything related to department?"
//        +"Do you need any assistance?"
//        +"Is there anything I can help you with related to the department?"
//        +"Would you like some help?"
//        +"Do you need help or information about the department?"
//        +"Is there something I can assist you with?"
//        +"May I offer you information related to the department?"
//        +"Are you looking for any specific information related to our department?"
//        +"Can I provide you with any help or guidance?"
//        +"Do you require any help or details regarding our department?"
//        +"Is there anything you would like to know or need help with our department?"
//        +"Would you like me to assist you with information about our department?"
//        +"Can I be of any help or give you information on something related to department?"
//        +"Do you need any support or have questions about our department?"
//        +"Is there anything in particular you need help with or want to know about in our department?"
//        +"Is there a specific topic or area you need assistance or information on our department?"
//        +"Do you have any inquiries or need assistance with anything related to department?"
//        +"Can I help you find what you're looking for or provide more information on our department?"
//        +"Are you seeking help or information regarding a specific matter related to the department?"
//        +"Do you need any help or have any questions about the department?"
//    }
//}

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


val Greeting :State = state(Parent) {


    var database: MongoDatabase = connectToMongoDB();

    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    var moreInfoFlag :Boolean = false;
//println(hour)
    val timeofday = when{
        hour<12 -> "Morning"
        hour in 13..17 -> "Afternoon"
        else -> "Evening"
    }
    onEntry {
        furhat.gesture(Gestures.Smile)
        furhat.say {
            random {
                +"Hello Nice to meet you"
                +"Hey Pleasure to meet you"
                +"Good to see you"
                +"Good $timeofday !!! Nice to meet you "
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
        })
    }
    onReentry {
        if(!moreInfoFlag) {
            furhat.ledStrip.solid(java.awt.Color.WHITE)
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
            })
        }
        else{
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


//    onUserEnter {
//        furhat.say { random { greeting } }
//        furhat.ask{ random{askhelp} }
//    }
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
    onResponse<StaffInformationIntent>{
        //furhat.gesture(Gestures.Oh());

        staffIntentFlag = true;
        moduleIntentFlag = false;
        staffRoomIntentFlag = false;
        println("Identified Staff name "+it.intent.getString("staffname"));
        var staffname:String = getStaffName(it.intent.getString("staffname"))
        println("Found Staff name "+staffname);

        if(it.intent.getString("staffname")!=null && it.intent.getString("staffemail")!=null){
            repeatprofName = staffname
            repeatprofemail = it.intent.getString("staffemail")
            repeatprofRole=null
            val confirmation = furhat.askYN( "Is it about professor $staffname"+ it.intent.getString("staffemail") +" ?");
            if(confirmation){
                println("Reached with name and email here")
                call(StaffInformation(database,staffname,it.intent.getString("staffemail")));
                println("Reached again here after info")
                moreInfoFlag = true;
                reentry();
            }else{
                furhat.ask({ random{
                    +"Could you please repeat your last query?"
                }});
            }
        }
        else if(it.intent.getString("staffname")!=null && it.intent.getString("staffrole")!=null) {
            repeatprofName = staffname
            repeatprofRole = it.intent.getString("staffrole")
            repeatprofemail=null
            val confirmation = furhat.askYN("Is it about professor $staffname's "+it.intent.getString("staffrole") +"in the department ??");
            if(confirmation){
                println("Reached with name and role here")
                call(StaffInformation(database,staffname,null,it.intent.getString("staffrole")));
                println("Reached again here after info")
                moreInfoFlag = true;
                reentry();
            }else{
                furhat.ask({ random{
                    +"Could you please repeat the last query?"
                }});
            }
        }
        else{
            repeatprofName = staffname
            repeatprofRole = null
            repeatprofemail=null
            val confirmation = furhat.askYN("Is it about professor $staffname ");
            if(confirmation){
                call(StaffInformation(database,staffname));
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

    onResponse<ModuleInformationIntent>{
        //furhat.gesture(Gestures.Oh());

        staffIntentFlag = false;
        moduleIntentFlag = true;
        staffRoomIntentFlag = false;

        if(currentprogrammename == null && it.intent.getString("programmename") == null){
            furhat.ask{random{
                +"Can you repeat the last query with the programme name ???"
                +"Could you please repeat the last query with the programme name ???"
                +"Could you please repeat the last query with the course name that you are asking for ???"
            }
            }
        }

        if(it.intent.getString("programmename")!=null){
            currentprogrammename = it.intent.getString("programmename");
        }

        println("Current programme name is "+ currentprogrammename)
        println(it.intent.getString("programmename"));
//        if(it.intent.getString("modulename")!=null && it.intent.getString("modules")!=null){
//            val confirmation = furhat.askYN("Is it about module number for "+it.intent.getString("modulename")+ " ?");
//            if(confirmation){
//                println("Reached with programme name here")
//                if(it.intent.getString("programmename")!=null){
//                    call(ModuleInformation(database,it.intent.getString("programmename"),null,false,false,it.intent.getString("modulename")));
//
//                }
//                else{
//                    call(ModuleInformation(database, currentprogrammename,null,false,false,it.intent.getString("modulename")));
//
//                }
//                println("Reached again here after info")
//                moreInfoFlag = true;
//                reentry();
//            }else{
//                furhat.ask({ random{
//                    +"Could you please repeat your last query?"
//                }});
//            }
//        }  else
       if(it.intent.getString("programmename")!=null && it.intent.getString("modules")!=null && it.intent.getString("semester")==null && it.intent.getString("compulsory")==null){
            repeatprogrammeName = it.intent.getString("programmename")
           repeatmodules = false
           repeatsemester =  null
           repeatcompulsory = false
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


    onResponse<RoomInformationIntent>{
        //furhat.gesture(Gestures.Oh());

        staffIntentFlag = false;
        moduleIntentFlag = false;
        staffRoomIntentFlag = true;
        var staffname: String? =null;
        if(it.intent.getString("staffname")!=null){
            staffname = getStaffName( it.intent.getString("staffname"));
        }

        if(it.intent.getString("staffname")!=null && it.intent.getString("room")!=null) {
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
//
//    onUserLeave {
//        goto(Idle)
//    }
    onResponse {
        furhat.say{
            +Gestures.Smile
            +"Sorry, I didn't understand that. "
            random {
                +"You can ask me information related to our department. You can ask information about a professor, about the professor role and even their email address."
                +"You can ask me information related to our department. You can ask information about modules in a particular programme, about compulsory modules in a programme and what are the modules that happen in a particular semester."
                +"You can ask me information related to our department. You can ask directions to professor's room , or , for a particular room. "

            }
        }
        reentry();
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

