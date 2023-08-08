package furhatos.app.furhatreceptionist.flow.main


import furhatos.app.furhatreceptionist.flow.Parent
import furhatos.app.furhatreceptionist.nlu.StaffInformationIntent
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import java.util.*


val Greeting: State = state(Parent) {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    var moreInfoFlag :Boolean = false;
    println(hour)
    val timeofday = when{
        hour<12 -> "Morning"
        hour in 13..17 -> "Afternoon"
        else -> "Evening"
    }
    println(timeofday)
    val greeting = utterance {

    }
    val askhelp = utterance {

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
        furhat.ask({ random{
            +"Can I help you with anything?"
            +"Do you need any assistance?"
            +"Is there anything I can help you with?"
            +"Would you like some help?"
            +"Do you need help or information?"
            +"Is there something I can assist you with?"
            +"May I offer you any information?"
            +"Are you looking for any specific information?"
            +"Can I provide you with any help or guidance?"
            +"Do you require any help or details?"
            +"Is there anything you would like to know or need help with?"
            +"Would you like me to assist you with anything or provide information?"
            +"Can I be of any help or give you information on something?"
            +"Do you need any support or have questions about something?"
            +"Is there anything in particular you need help with or want to know about?"
            +"Is there a specific topic or area you need assistance or information on?"
            +"Do you have any inquiries or need assistance with anything related to department?"
            +"Can I help you find what you're looking for or provide more information?"
            +"Are you seeking help or information regarding a specific matter?"
            +"Do you need any help or have any questions about the department?"
        }})

    }
    onReentry {
        if(!moreInfoFlag) {
            furhat.ask({
                random {
                    +"Can I help you with anything?"
                    +"Do you need any assistance?"
                    +"Is there anything I can help you with?"
                    +"Would you like some help?"
                    +"Do you need help or information?"
                    +"Is there something I can assist you with?"
                    +"May I offer you any information?"
                    +"Are you looking for any specific information?"
                    +"Can I provide you with any help or guidance?"
                    +"Do you require any help or details?"
                    +"Is there anything you would like to know or need help with?"
                    +"Would you like me to assist you with anything or provide information?"
                    +"Can I be of any help or give you information on something?"
                    +"Do you need any support or have questions about something?"
                    +"Is there anything in particular you need help with or want to know about?"
                    +"Is there a specific topic or area you need assistance or information on?"
                    +"Do you have any inquiries or need assistance with anything related to department?"
                    +"Can I help you find what you're looking for or provide more information?"
                    +"Are you seeking help or information regarding a specific matter?"
                    +"Do you need any help or have any questions about the department?"
                }
            })
        }
        else{
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


    onUserEnter {
        furhat.say({ random { greeting } })
        furhat.ask({ random{askhelp}})
    }
    onResponse<StaffInformationIntent>{
        Gestures.Oh();
        println(it.intent.getString("staffname"));
        if(it.intent.getString("staffname")!=null && it.intent.getString("staffemail")!=null){
            val confirmation = furhat.askYN("Is it about professor "+it.intent.getString("staffname")+ " email address ?");
            if(confirmation){
                println("Reached with name and email here")
                call(StaffInformation(it.intent.getString("staffname"),it.intent.getString("staffemail")));
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
            val confirmation = furhat.askYN("Is it about professor "+it.intent.getString("staffname")+ " role in the department ?");
            if(confirmation){
                println("Reached with name and role here")
                call(StaffInformation(it.intent.getString("staffname"),null,it.intent.getString("staffrole")));
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
            val confirmation = furhat.askYN("Is it about professor "+it.intent.getString("staffname"));
            if(confirmation){
                call(StaffInformation(it.intent.getString("staffname")));
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


    onResponse<No> {
        furhat.say {
            +Gestures.BigSmile()
            +"Hope I could help you with some information today"
            +Gestures.BigSmile()
            +"Good Bye and Have a great day !!!"
            +Gestures.BigSmile()
    }}

}

