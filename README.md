The application uses GitHub REST API as a data source
Technology Stack:
java 25, Spring Boot 4, Gradle Kotlin DLS

Run Application: /gradlew.bat |||||
Application starts on localhost and port 8080
Endpoints: /repositories/{username}
Successful response example :   {
"name": "git-consortium",
"owner": {
"login": "octocat"
},
"fork": false
},


When users doesnt exist status - 404 and message - user not found

