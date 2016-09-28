$runningPath = Split-Path -Parent $PSCommandPath

if(-Not(Test-Path "C:\WildFly")) {
	New-Item "C:\WildFly" -type directory
	New-Item "C:\WildFly\original_server" -type directory
	Set-Location "C:\WildFly\original_server"

	# download wildfly10.0.0.Final server, expand, delete .zip
	wget http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip -OutFile server.zip
	Expand-Archive "C:\WildFly\original_server\server.zip" -dest "C:\WildFly\"

	#override standalone-full.xml file
	Remove-Item "C:\WildFly\wildfly-10.0.0.Final\standalone\configuration\standalone-full.xml"
	Copy-Item $PSScriptroot\server-conf-files\standalone-full.xml "C:\WildFly\wildfly-10.0.0.Final\standalone\configuration\"

	#copy configuration files (JDBC driver, module.xml)
	New-Item "C:\WildFly\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres" -type directory
	New-Item "C:\WildFly\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres\main" -type directory
	Copy-Item $PSScriptroot\server-conf-files\postgres\main\* "C:\WildFly\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres\main"
}
else {
	"ERROR: Folder C:\Wildfly already exists. To run the script success please remove the folder."
}

Set-Location $runningPath