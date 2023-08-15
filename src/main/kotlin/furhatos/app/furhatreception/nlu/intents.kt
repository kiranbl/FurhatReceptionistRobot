package furhatos.app.furhatreceptionist.nlu
import CompulsorySynonyms
import MailEntity
//import ModuleNames
import ModuleSynonyms
import ProfessorNames
import ProgrammeNames
import RepeatEntity
import RoleEntity
import SemesterNames
import furhatos.nlu.Intent
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
}

class RepeatInformationIntent(
    val repeat : RepeatEntity? = null
):Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Can you @repeat the information",
            "Please @repeat once again",
            "@repeat",
            "I'm terribly sorry, but could you please @repeat that information for me?",
            "I apologize for any inconvenience, but could you kindly go over that information @repeat?",
            "I'm afraid I didn't quite catch all the details. Could you please @repeat the information, if you don't mind?",
            "I'm sorry to trouble you, but would it be possible for you to @repeat the information for me?",
            "I hope you don't mind me asking, but could you @repeat the information again? I want to make sure I've got it right.",
            "I hate to bother you, but could you please @repeat the information one more time? I want to be sure I've understood correctly.",
            "I'm sorry for any confusion, but would you be able to @repeat the information so that I can ensure I have all the details?",
            "I apologize for needing to ask, but could you please @repeat the information? I want to be certain I have it accurately.",
            "Pardon me, could you @repeat the information once again, please?",
            "Pardon me for asking, but could you @repeat that information one more time?",
            "I beg your pardon, but would you mind @repeat the information for me?",
            "I'm sorry to interrupt, but could you pardon me and @repeat the information?",
            "Pardon the inconvenience, but could you kindly @repeat the information?",
            "Pardon me for needing to ask again, but could you @repeat the information once more?",
            "Pardon my request, but could you please @repeat the information?",
            "I beg your pardon, but could you provide the information @repeat for clarity?",
            "Pardon my interruption, but could you @repeat the information again, if you wouldn't mind?",
            "Pardon my curiosity, but could you @repeat the information for me?"
        )

    }
}

class ModuleInformationIntent(
    val programmename : ProgrammeNames? = null,
    val semester : SemesterNames? = null,
    val modules : ModuleSynonyms? = null,
    val compulsory: CompulsorySynonyms?=null
//    val modulename: ModuleNames?= null
) : Intent() {
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
            "@compulsory @modules @programmename"


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
}