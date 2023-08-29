
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
            "find",
            "look",
            "looking",
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
            "Roger K Moore:Moore, Roger,Roger K Moore",
            "Guy Brown:Brown,Guy,Guy Brown",
            "Temitope Adeosun:Adeosun, Temitope,Temitope Adeosun",
            "Behzad Abdolmaleki:Abdolmaleki, Behzad,Behzad Abdolmaleki",
            "Nikos Aletras:Aletras, Nikos,Nikos Aletras",
            "Jon Barker:Barker, Jon,Jon Barker",
            "Harsh Beohar:Beohar, Harsh,Harsh Beohar",
            "Kirill Bogdanov:Bogdanov, Kirill,Kirill Bogdanov",
            "Kalina Bontcheva:Bontcheva, Kalina,Kalina Bontcheva",
            "Gergely Buday:Buday, Gergely,Gergely Buday",
            "Zhixiang Chen:Chen, Zhixiang,Zhixiang Chen",
            "Heidi Christensen:Christensen, Heidi,Heidi Christensen",
            "John Clark:Clark, John,John Clark",
            "Richard Clayton:Clayton, Richard,Richard Clayton",
            "Mike Cruchten:Cruchten, Mike,Mike Cruchten",
            "Hamish Cunningham:Cunningham, Hamish,Hamish Cunningham",
            "John Derrick:Derrick, John,John Derrick",
            "Benjamin Dowling:Dowling, Benjamin,Benjamin Dowling",
            "Islam Elgendy:Elgendy, Islam,Islam Elgendy",
            "Matt Ellis:Ellis, Matt,Matt Ellis",
            "Andreas Feldmann:Feldmann, Andreas,Andreas Feldmann",
            "Rob Gaizauskas:Gaizauskas, Rob,Rob Gaizauskas",
            "Stefan Goetze:Goetze, Stefan,Stefan Goetze",
            "Prosanta Gope:Gope, Prosanta,Prosanta Gope",
            "Yoshi Gotoh:Gotoh, Yoshi,Yoshi Gotoh",
            "Philip Green:Green, Philip,Philip Green",
            "Charles Grellois:Grellois, Charles,Charles Grellois",
            "Thomas Hain:Hain, Thomas,Thomas Hain",
            "Jungong Han:Han, Jungong,Jungong Han",
            "Rob Hierons:Hierons, Rob,Rob Hierons",
            "Mark Hepple:Hepple, Mark,Mark Hepple",
            "Tahsinur Khan:Tahsinur, Khan, Tahsinur Khan",
            "Vitaveska Lanfranchi:Vitaveska, Lanfranchi, Vitaveska Lanfranchi",
            "James Law:James, Law, James Law",
            "Jochen Leidner:Jochen, Leidner, Jochen Leidner",
            "Andrew Lewis-Smith:Andrew, Lewis-Smith, Andrew Lewis-Smith",
            "Chenghua Lin:Chenghua, Lin, Chenghua Lin",
            "Robert Loftin:Robert, Loftin, Robert Loftin",
            "Haiping Lu:Haiping, Lu, Haiping Lu",
            "Alexandr Lucas:Alexandr, Lucas, Alexandr Lucas",
            "Fatima Maikore:Fatima, Maikore, Fatima Maikore",
            "Steve Maddock:Steve, Maddock, Steve Maddock",
            "Luca Manneschi:Luca, Manneschi, Luca Manneschi",
            "Michael Mangan:Michael, Mangan, Michael Mangan",
            "James Marshall:James, Marshall, James Marshall",
            "Phil McMinn:Phil, McMinn, Phil McMinn",
            "John McNamara:John, McNamara, John McNamara",
            "Nafise Sadat Moosavi:Nafise Sadat, Moosavi, Nafise Sadat Moosavi",
            "Sagnik Mukhopadhyay:Sagnik, Mukhopadhyay, Sagnik Mukhopadhyay",
            "Emma Norling:Emma, Norling, Emma Norling",
            "Siobhán North:Siobhán, North, Siobhán North",
            "Olakunle Olayinka:Olakunle, Olayinka, Olakunle Olayinka",
            "Pietro Oliveto:Pietro, Oliveto, Pietro Oliveto",
            "Varvara Papazoglou:Varvara, Papazoglou, Varvara Papazoglou",
            "Aryan Pasikhani:Aryan, Pasikhani, Aryan Pasikhani",
            "Andrei Popescu:Andrei, Popescu, Andrei Popescu",
            "Tony Prescott:Tony, Prescott, Tony Prescott",
            "Anton Ragni:Anton, Ragni, Anton Ragni",
            "Paul Richmond:Paul, Richmond, Paul Richmond",
            "José Miguel Rojas:José Miguel, Rojas, José Miguel Rojas",
            "Max Sandström:Max, Sandström, Max Sandström",
            "Carolina Scarton:Carolina, Scarton, Carolina Scarton",
            "Areeb Sherwani:Areeb, Sherwani, Areeb Sherwani",
            "Donghwan Shin:Donghwan, Shin, Donghwan Shin",
            "Anthony Simons:Anthony, Simons, Anthony Simons",
            "Michael Smith:Michael, Smith, Michael Smith",
            "Xingyi Song:Xingyi, Song, Xingyi Song",
            "Joachim Spoerhase:Joachim, Spoerhase, Joachim Spoerhase",
            "Mike Stannett:Mike, Stannett, Mike Stannett",
            "Mark Stevenson:Mark, Stevenson, Mark Stevenson",
            "Andrew Stratton:Andrew, Stratton, Andrew Stratton",
            "Georg Struth:Georg, Struth, Georg Struth",
            "Navid Talebanfard:Navid, Talebanfard, Navid Talebanfard",
            "Ramsay Taylor:Ramsay, Taylor, Ramsay Taylor",
            "Eleni Vasilaki:Eleni, Vasilaki, Eleni Vasilaki",
            "Mari Cruz Villa Uriol:Mari Cruz, Villa Uriol, Mari Cruz Villa Uriol",
            "Aline Villavicencio:Aline, Villavicencio, Aline Villavicencio",
            "Jonni Virtema:Jonni, Virtema, Jonni Virtema",
            "Dawn Walker:Dawn, Walker, Dawn Walker",
            "Neil Walkinshaw:Neil, Walkinshaw, Neil Walkinshaw",
            "Paul Watton:Paul, Watton, Paul Watton",
            "Stuart Wilson:Stuart, Wilson, Stuart Wilson",
            "Bhagya Wimalasiri:Bhagya, Wimalasiri, Bhagya Wimalasiri",
            "Joab Winkler:Joab, Winkler, Joab Winkler",
            "Po Yang:Po, Yang, Po Yang",
            "Shuo Zhou:Shuo, Zhou, Shuo Zhou",
            "Maksim Zhukovskii:Maksim, Zhukovskii, Maksim Zhukovskii"

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