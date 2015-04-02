This is a web application that supports client Peer to Peer chat applications.

Requirements:
	Java 7 or later
	Maven 3 or later (if not using eclipse)

To run using maven:
	Use: mvn compile tomcat7:run
	If you would like to create a war file and run the project from the war file use: mvn compile package tomcat7:run-war

To run using eclipse:
	Use the Java EE version of Eclipse. (or install the http://www.eclipse.org/m2e/ plugin.)
	Import this project as an existing Maven project.
	Run or Debug the project as a maven build.
	
The web server will run on http://localhost:80/P2PChat/
