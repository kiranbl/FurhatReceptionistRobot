package furhatos.app.furhatreceptionist.nlu
import MailEntity
import ProfessorNames
import RoleEntity
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
            "I wanted the @staffname's @staffemail id",
            "Could you please give me the @staffemail of @staffname?",
            "Would you mind sharing professor @staffname @staffemail ID with me, please?",
            "Could you please provide me with @staffname @staffemail address?",
            //Staff name and role
            "What is professor @staffname @staffrole within the department?",
            "Could you please outline professor @staffname @staffrole within the department?",
            "What is professor @staffname academic @staffrole within the department?",
            "What is @staffname @staffrole in the department?",
            "What is @staffname @staffrole?"


        )
    }
}