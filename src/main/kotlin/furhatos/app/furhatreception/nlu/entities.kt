
import furhatos.nlu.*
import furhatos.util.*
import kotlin.sequences.Sequence

class ProfessorNames : ListEntity<Names>()
class MailEntity : ListEntity<Mail>()
class RoleEntity :ListEntity<Role>()
class ProgrammeNames :ListEntity<Programme>()
class SemesterNames: ListEntity<Semester>()
class ModuleSynonyms: ListEntity<Modules>()
class CompulsorySynonyms: ListEntity<Compulsory>()

class RoomSynonyms:ListEntity<Rooms>()
class RoomNames:ListEntity<RoomName>()
class RepeatEntity: ListEntity<Repeat>()

class Rooms: EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "Chamber",
            "Space",
            "Area",
            "Quarters",
            "Cubicle",
            "room"
            )


    }
}

class RoomName: EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "Lovelace Ada Room",
            "Verification & Testing Lab",
            "Resource Room",
            "Machine Learning Lab",
            "Hearing & Speech Lab",
            "Student Lab",
            "Social Space",
            "Meeting Room",
            "Reception",
            "Computer Vision, PGR Workspace",
            "Foundations of Computing",
            "Foundations of Computing",
            "Meeting Room",
            "Staff Room",
            "Consultation Room",
            "Research Software Engineering Team",
            "Pervasive Computing Lab",
            "Pervasive Computing Lab",
            "Music Room",
            "Meeting Room",
            "Algorithms Research",
            "Security of Advanced Systems Research",
            "Hearing & Speech",
            "NLP Research Workspace",
            "Centre for Doctoral Training",
            "NLP Lab"
        )


    }
}

class Repeat: EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("repeat", "reiterate", "recap", "once more", "once again", "one more", "go over")


    }
}

//class ModuleNames :ListEntity<Module>()
class Names : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "Test","Kiran",
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

class Role : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("Position", "Role", "Title", "Job", "Duty", "Post")
    }
}
class Programme : EnumEntity(speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("Advanced Computer Science",
            "Computer Science with Speech and Language Processing",
            "Data Analytics",
            "Cybersecurity and Artificial Intelligence")
    }


}


class Semester : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("this","current","upcoming","next","last","previous","autumn","spring","summer","first","second","third")
    }

}

class Modules : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("module","modules","subject","subjects","course","courses","topics")
    }

}



class Compulsory : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf(
            "Must pass",
            "Compulsory",
            "Mandatory",
            "Required",
            "Obligatory",
            "Necessary",
            "Essential")
    }

}

//class Module: EnumEntity(speechRecPhrases = true) {
//
//    override fun getEnum(lang: Language): List<String> {
//        return listOf(
//            "Object Oriented Programming and Software Design",
//            "Professional Issues",
//            "Team Software Project",
//            "Data Science with Python",
//            "Text Processing",
//            "Speech Processing",
//            "3D Computer Graphics",
//            "Testing and Verification in Safety-Critical Systems",
//            "Machine Learning and Adaptive Intelligence",
//            "Software development for mobile devices",
//            "Modelling and Simulation of Natural Systems",
//            "Theory of Distributed Systems",
//            "Computer Security and Forensics",
//            "The Intelligent Web",
//            "Software and Hardware Verification",
//            "Speech Technology",
//            "Natural Language Processing",
//            "Network Performance Analysis",
//            "Parallel Computing with Graphical Processing Units",
//            "Software Reengineering",
//            "Scalable Machine Learning",
//            "Team Project",
//            "Fundamental Security Properties and Mechanisms",
//            "Cyber Threat Hunting and Digital Forensics",
//            "Development of Secure Software",
//            "Security of Control and Embedded Systems"
//
//        )
//    }
//
//}