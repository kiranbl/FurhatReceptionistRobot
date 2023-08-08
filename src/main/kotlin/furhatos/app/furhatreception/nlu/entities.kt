
import furhatos.nlu.*
import furhatos.util.*
import kotlin.sequences.Sequence

class ProfessorNames : ListEntity<Names>()
class MailEntity : ListEntity<Mail>()
class Names : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("Test","Kiran",
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
            "Mark Hepple"
            )
    }

}


class Mail : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("Email","Electronic Mail","Mail")
    }

}