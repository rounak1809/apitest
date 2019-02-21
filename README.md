# Test automation for Oxford Dictionary API

If you are working behind a proxy, ensure you work through the next section 'Dealing with Proxies'.   

## Prerequisite to Run project
* Installed Java 1.8.** JDK
* Installed Apace Maven 3.5.***
* Installed Git (or use via Eclipse)
* Installed Eclipse or IntelliJ IDE
* Set Environment Variables for Java Home, Maven Home and Path variable for the same

## Clone the Project from Git Repo
* Open a command prompt and navigate to workspace where projects to be cloned
    * type `git clone https://gitlab.com/rounak.anerao/`

* Open Eclipse and Import Project as an 'Existing Maven Projects'

## Dealing with HTTP and HTTPS Proxies
If working from behind a proxy, then you will need to manage some proxy configuration as specified below, for Maven, Service Testing, and Eclipse.

### Maven
update settings.xml in maven .m2 folder with proxy details.
### ServiceTesting
For an initial example, see `config.properies`, in the src\main\java\com\dictionary\apitest\config directory, and update the `proxyrequired` parameters as required. You must set `proxyrequired = true` to use HTTP and HTTPS format of proxy configuration. Also update details of proxyHost, proxyPort, proxyUser, proxyPassword. 

### With Eclipse
`Eclipse > Window > Preferences > General > Network Connections`

* Add Manual for Http and Https: 
  * host: webproxysouth.corp.ssi.govt.nz
  * port: 8080
  * authentication: required
  * proxy bypass: [hostsToBypass]
 
## To Execute Tests
* from cmd go to project directory and run command **mvn clean test**

### From an IDE (e.g. Eclipse)
* Right click on pom.xml and Run As **Maven test**

## To Check Test Report
* After Test Run you can see Test Report inside **test-output** folder with name **OxfordDictionaryAPI_Automation_Test_Report.html**

## To Check Test Logs
* Test execution log4j log file is available in project root folder with name **OxfordDictionaryAPITestAutomationLogs.out**
 
## To Change API Key, Id and Base URL
* To update API Key, Id and Base URL in project, go to `config.properies`, in the src\main\java\com\dictionary\apitest\config directory, and update the same.