package furhatos.app.furhatreceptionist.nlu
import MailEntity
import ProfessorNames
import furhatos.nlu.Intent
import furhatos.util.*

class StaffInformationIntent(
    val staffname : ProfessorNames? = null,
    val staffemail : MailEntity? = null
) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Can you tell me about Professor @staffname in the computer science department",
            "Can you tell me about Professor @staffname",
            "I wanted information about @staffname",
            "@staffname information",
            "Could you provide some information about @staffname?",
            "I'm interested in learning about @staffname who works in the Computer Science department. Can you help?",
            "I wanted the @staffname's @staffemail id",
            "Could you please give me the @staffemail of @staffname?",
            "Would you mind sharing professor @staffname @staffemail ID with me, please?",
            "Could you please provide me with @staffname @staffemail address?"

        )
    }
}