# Web-Crawler

System-Rquirement:
 1. Java 8
 2. Maven

Steps:

 1.Change 'output.file.path' according to user's system in properties file present at src/main/resouces directory. 
 2. Open CMD and navigate to the project location 
 3.Maven build using command: 
    mvn clean install
 3 After maven built run application 
    mvn exec:java -Dexec.mainClass="com.prudential.controller.WebCrawler"
 4. Wait for process to terminate and check outputFile present at location as mentioned in properties file for URLs

 
 Scope:
 
 1. Logging can be implemented 
 2. A Constant.java file can be created for holding Strings
 3. Sitemap can be implemented.
 4. Paent-child relation can be shown between URLs. for ex. page 1 has a particular set of URLs and so on  
 
 
 
 
 
 