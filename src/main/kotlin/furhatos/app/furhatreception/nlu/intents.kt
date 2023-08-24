package furhatos.app.furhatreceptionist.nlu
import CompulsorySynonyms
import MailEntity
//import ModuleNames
import ModuleSynonyms
import ProfessorNames
import ProgrammeNames
import RepeatEntity
import RoleEntity
import RoomNames
import RoomSynonyms
import SemesterNames
import furhatos.nlu.Intent
import furhatos.nlu.TextGenerator
import furhatos.util.*

class StaffInformationIntent(
    val staffname : ProfessorNames? = null,
    val staffemail : MailEntity? = null,
    val staffrole : RoleEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Can you tell me about Professor @staffname in the computer science department",
            "Can you tell me about Professor @staffname",
            "I wanted information about @staffname",
            "@staffname information",
            "Could you provide some information about @staffname?",
            "I'm interested in learning about @staffname who works in the Computer Science department. Can you help?",
            //Staff name and email
            "what is the @staffemail for Professor @staffname",
            "I wanted the @staffname's @staffemail id",
            "Could you please give me the @staffemail of @staffname?",
            "Would you mind sharing professor @staffname @staffemail ID with me, please?",
            "Could you please provide me with @staffname @staffemail address?",
            //Staff name and role
            "What is professor @staffname @staffrole within the department?",
            "Could you please outline professor @staffname @staffrole within the department?",
            "What is professor @staffname academic @staffrole within the department?",
            "What is @staffname @staffrole in the department?",
            "What is @staffname @staffrole?",
            "Give me @staffname @staffrole"


        )
    }
//    override fun toText(lang : Language) : String {
//        println("sTAFF NAME == "+staffname)
//        return generate(
//            "Is it about professor $staffname $staffemail ? | Is it about professor $staffname $staffrole in the department ?? | Is it about professor $staffname ");
//    }
//
//    override fun toString(): String {
//        return toText()
//    }
}

class RoomInformationIntent (
    val staffname : ProfessorNames? = null,
    val room: RoomSynonyms?=null,
    val roomname: RoomNames?=null
):Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(

            //Getting direction for professor room
            "Where can I @room @staffname ?",
            "Where can I  @room professor @staffname ?",
            "I'm  @room for Professor @staffname" ,
            "Hello, I'm  @room for Professor @staffname" ,
            "Hello, I'm looking for Professor @staffname's @room" ,
            "Where can I find @staffname @room ?",
            "Where can I find  @staffname @room in the department ?",
            "Where can I find  professor @staffname @room in the department",
            "Can you give me directions to @staffname @room ??",
            "could you please help me find Professor @staffname's @room?" ,
            "Excuse me, could you please help me find Professor @staffname's @room?" ,
            "Excuse me, could you please help me find Professor @staffname's @room? I need to discuss something related to my studies.",
            "Good morning, I'm looking for Professor @staffname's @room. Would you be able to direct me to it?",
            "Would you be able to direct me to @staffname's @room?",
            "Hello, I have an appointment with Professor  @staffname, and I'm not sure where their @room is. Could you assist me in locating it?",
            "Hi there, I need to see Professor @staffname, but I'm not sure where their @room is. Would you mind pointing me in the right direction?",
            "Good day, I'm trying to find Professor @staffname's @room. Could you guide me to it, please?",
            "Excuse me, could you please help me with directions to Professor @staffname's @room in the computer science department?",
            "Hello, I'm looking for Professor @staffname's @room . Could you guide me there?",
            "Good morning, I need to find Professor @staffname's @room in the computer science department. Can you point me in the right direction?",
            "Hi there, I'm trying to locate Professor @staffname's @room in the computer science department. Would you be able to assist me?",
            "Hello, I have an appointment with Professor @staffname , but I'm not sure where their @room is. Could you give me directions?",
            "Excuse me, I need to visit Professor @staffname and I'm a bit lost. Could you provide me with directions to their @room?",
            "Hi, I'm a student looking for Professor  @staffname's @room in the computer science department. Could you tell me how to get there?",
            "Good day, I'm trying to locate Professor  @staffname's @room in the computer science department. Can you please show me the way?",
            "Hello there, I need to meet with Professor @staffname in the computer science department, but I'm not familiar with the @room. Could you direct me?",
            "Hi, sorry to bother you, but I'm looking for Professor @staffname @room in the department. Could you provide me with directions?",


            //Give direction for labs and rooms
            "Where can I find @roomname",
            "I am looking for the @roomname room",
            "I am looking for the @roomname",
            "Excuse me, could you please tell me how to get to the @roomname?",
            "Can you guide me to the @roomname?",
            "Hey, do you know where I can find the @roomname?",
            "Hi there, I'm a bit lost. Can you point me in the right direction for the @roomname?",
            "I'm trying to locate the @roomname. Could you show me the way?",
            "Sorry to bother you, but I'm not sure how to find the @roomname. Can you help?",
            "Hello, I'm new here. Could you give me directions to the @roomname?",
            "Hey, I'm looking for the @roomname. Any chance you could let me know how to get there?",
            "Excuse me, I'm trying to find the @roomname. Could you give me some directions?",
            "Hi, I have a class in the @roomname, but I'm not sure where it is. Could you assist me?"
        )

    }
//    override fun toText(lang : Language) : String {
//        println("Staff Room Name "+staffname)
//        return generate(
//            "Do you want to know the directions for professor $staffname room ? | " +
//                    "Do you want to know the directions for $roomname ?");
//    }
//
//    override fun toString(): String {
//        return toText()
//    }
}
//class RepeatInformationIntent(
//    val repeat : RepeatEntity? = null
//):Intent() {
//    override fun getExamples(lang: Language): List<String> {
//        return listOf(
//            "Can you @repeat the information",
//            "Please @repeat once again",
//            "@repeat",
//            "I'm terribly sorry, but could you please @repeat that information for me?",
//            "I apologize for any inconvenience, but could you kindly go over that information @repeat?",
//            "I'm afraid I didn't quite catch all the details. Could you please @repeat the information, if you don't mind?",
//            "I'm sorry to trouble you, but would it be possible for you to @repeat the information for me?",
//            "I hope you don't mind me asking, but could you @repeat the information again? I want to make sure I've got it right.",
//            "I hate to bother you, but could you please @repeat the information one more time? I want to be sure I've understood correctly.",
//            "I'm sorry for any confusion, but would you be able to @repeat the information so that I can ensure I have all the details?",
//            "I apologize for needing to ask, but could you please @repeat the information? I want to be certain I have it accurately.",
//            "Pardon me, could you @repeat the information once again, please?",
//            "Pardon me for asking, but could you @repeat that information one more time?",
//            "I beg your pardon, but would you mind @repeat the information for me?",
//            "I'm sorry to interrupt, but could you pardon me and @repeat the information?",
//            "Pardon the inconvenience, but could you kindly @repeat the information?",
//            "Pardon me for needing to ask again, but could you @repeat the information once more?",
//            "Pardon my request, but could you please @repeat the information?",
//            "I beg your pardon, but could you provide the information @repeat for clarity?",
//            "Pardon my interruption, but could you @repeat the information again, if you wouldn't mind?",
//            "Pardon my curiosity, but could you @repeat the information for me?"
//        )
//
//    }
//}

class ModuleInformationIntent(
    val programmename : ProgrammeNames? = null,
    val semester : SemesterNames? = null,
    val modules : ModuleSynonyms? = null,
    val compulsory: CompulsorySynonyms?=null
//    val modulename: ModuleNames?= null
) : Intent(), TextGenerator {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            // Get the module list for a particular programme
            "What are the different @modules available in the @programmename programme",
            "Could you provide me with a list of @modules offered in the @programmename programme within the computer science department?",
            "What @modules are taught in the @programmename offered by the computer science department?",
            "Can you give me an overview of the @modules covered in the @programmename programme within the computer science department?",
            "What are the @modules in @programmename programme",
            "Could you provide me with a list of @modules offered in the @programmename programme within our department?",
            "What @modules are taught in the @programmename offered by our department?",
            "Can you give me an overview of the @modules covered in the @programmename programme within our department?",
            "@modules @programmename",

            // Get the module list for a particular programme and the semester
            "What are the @modules offered for @semester semester?",
            "Can you provide a list of @modules for @semester term?",
            "Which @modules do students need to enroll in @semester semester?",
            "which @modules are scheduled for @semester semester?",
            "Could you tell me about the @modules being offered @semester semester?",
            "@modules @semester semester",

            "What are the @modules offered for @semester semester in the @programmename program?",
            "Can you provide a list of @modules for @semester term in the @programmename program???",
            "Which @modules do students need to enroll in @semester semester for the @programmename program??",
            "which @modules are scheduled for @semester semester in the @programmename program?",
            "What are the @modules required for @semester semester in the @programmename program?",
            "Which @modules should I enroll in @semester semester for the @programmename program?",
            "In the @programmename program, which @modules are scheduled for @semester semester?",
            "What @modules are part of the curriculum for the @programmename program in the @semester semester?",
            "Could you tell me about the @modules being offered @semester semester for the @programmename program?",

            // Getting the compulsory modules list for a programme
            "What are the @compulsory @modules available in the @programmename programme",
            "Could you provide me with a list of @compulsory @modules offered in the @programmename programme ?",
            "Which @compulsory @modules taught in the @programmename offered by the computer science department?",
            "Can you give me an overview of the @compulsory @modules covered in the @programmename programme within the computer science department?",
            "What are the @compulsory @modules in @programmename programme",
            "Could you provide me with a list of @compulsory @modules offered in the @programmename programme within our department?",
            "What @compulsory @modules are taught in the @programmename offered by our department?",
            "Can you give me an overview of the @compulsory @modules covered in the @programmename programme within our department?",
            "@compulsory @modules @programmename",
            "@compulsory @modules",
            "What are the @compulsory @modules available ?",
            "What are the @compulsory @modules available in this programme?",
            "Could you provide me with a list of @compulsory @modules offered in the programme ?",
            "Which @compulsory @modules are being taught in the programme",
            "What @compulsory @modules are taught in this programme offered by our department?",
            "Can you give me an overview of the @compulsory @modules covered in this programme within our department?"






//            // Getting module number value with module name and programme name
//
//            "What is the number for @modulename @modules?",
//            "Could you tell me the number for @modulename @modules?",
//            "What's the @modules number of @modulename?",
//            "What's the number allocation for @modulename?",
//            "Could you provide @modules number for @modulename?",
//            "@modulename",
//
//            "What is the number for @modulename @modules in the @programmename programme?",
//            "Could you tell me the number for @modulename @modules in the @programmename programme?",
//            "What's the @modules number of @modulename in the @programmename programme?",
//            "What's the number allocation for @modulename in the @programmename programme?",
//            "Could you provide @modules number for @modulename in the @programmename programme?"



        )
    }


    override fun toText(lang : Language) : String {
        return generate(
            "Is it about $modules in  $programmename programme for $semester semester ? | Is it about $compulsory $modules in $programmename programme ? | " +
                    "Is it about modules in $programmename programme ?");
    }

    override fun toString(): String {
        return toText()
    }

}