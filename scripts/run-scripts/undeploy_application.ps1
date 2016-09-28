$runningPath = Split-Path -Parent $PSCommandPath

Set-Location "c:\WildFly\wildfly-10.0.0.Final\bin\"

$deploymentsList = .\jboss-cli.bat --connect --command=deployment-info

if($deploymentsList -Match ".*Project-ClientRegistry-web-1.0-SNAPSHOT.war.*") {
	.\jboss-cli.bat --connect --command="undeploy Project-ClientRegistry-web-1.0-SNAPSHOT.war"
}
else {
	"ERROR: Application is not deployed."
}
Set-Location $runningPath