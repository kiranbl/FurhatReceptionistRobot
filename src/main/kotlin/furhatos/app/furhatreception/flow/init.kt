package furhatos.app.furhatreceptionist.flow

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import furhatos.app.furhatreceptionist.flow.main.Idle
import furhatos.app.furhatreceptionist.flow.main.Greeting
import furhatos.app.furhatreceptionist.setting.DISTANCE_TO_ENGAGE
import furhatos.app.furhatreceptionist.setting.MAX_NUMBER_OF_USERS
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.JsonParser
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import com.mongodb.client.MongoCollection
import org.bson.Document
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

//fun authorize(httpTransport: NetHttpTransport, jsonFactory: JsonFactory): Credential {
//    val inputStream = FileInputStream("C:\\Users\\kiran\\OneDrive\\Documents\\FURHAT DISSERTATION\\FurhatReception\\client_secret_162433007502-l2oq3ckd6l2o3tvl39hq5r0nne707t31.apps.googleusercontent.com.json")
//    val clientSecrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(inputStream))
//    println("Client Secrets JSON: $clientSecrets")
//    val flow = GoogleAuthorizationCodeFlow.Builder(
//        httpTransport, jsonFactory, clientSecrets, listOf("https://www.googleapis.com/auth/spreadsheets.readonly")
//    )
//        .setDataStoreFactory(FileDataStoreFactory(File("tokens")))
//        .setAccessType("offline")
//        .build()
//
//    val receiver = LocalServerReceiver.Builder().setPort(8888).build()
//    return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
//}
//fun getData(database:MongoDatabase) {
//    val httpTransport: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()
//    val jsonFactory: JsonFactory = JacksonFactory.getDefaultInstance()
//
//    val credentials: Credential = authorize(httpTransport, jsonFactory)
//
//    val sheetsService = Sheets.Builder(httpTransport, jsonFactory, credentials)
//        .setApplicationName("FurhatReceptionistApplication")
//        .build()
//
//    val spreadsheetId = "13X3ofSuZObVF3yEm4Y6b10zBwSi99ouUIj0CPQaW-Vs"
//    val range = "Sheet1!A:D" // Change to the desired range
//
//    val response: ValueRange = sheetsService.spreadsheets().values()[spreadsheetId, range].execute()
//    val values: List<List<Any>> = response.getValues()
//    val collection: MongoCollection<Document> = database.getCollection("roomInformation")
//
//
//    if (values.isNotEmpty()) {
//        for (row in values) {
//            val document = Document()
//            document["roomnumber"] = row[0] // adjust based on your columns
//            document["floornumber"] = row[1]
//            document["name"] = row[2]
//            document["category"] = row[3]
//            collection.insertOne(document)
//
//        }
//    } else {
//        println("No data found.")
//    }
//}


fun connectToMongoDB(): MongoDatabase {
    val connectionString = "mongodb+srv://FurhatReceptionRobot:Robot123@furhatrecptionistcluste.np7i1yx.mongodb.net/?retryWrites=true&w=majority"
    val mongoClient: MongoClient = MongoClients.create(connectionString)
    return mongoClient.getDatabase("FurhatReceptionist")
}
var database: MongoDatabase = connectToMongoDB();

val Init: State = state {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(DISTANCE_TO_ENGAGE, MAX_NUMBER_OF_USERS)
        database = connectToMongoDB()
        //getData(database)
    }
    onEntry {
        /** start interaction */
        when {
            furhat.isVirtual() -> goto(Greeting(database)) // Convenient to bypass the need for user when running Virtual Furhat
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting(database))
            }
            else -> goto(Idle(database))
        }
    }
    onReentry {
        database = connectToMongoDB()
        when {
            furhat.isVirtual() -> goto(Greeting(database)) // Convenient to bypass the need for user when running Virtual Furhat
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting(database))
            }
            else -> goto(Idle(database))
        }
    }

}
