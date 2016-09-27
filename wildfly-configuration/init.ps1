Set-Location "C:\"

# remove old content if it extists
if(Test-Path "C:\WildFly")
{
	Remove-Item .\WildFly -Force -Recurse
}
New-Item "C:\WildFly" -type directory
Set-Location "C:\WildFly"

# download wildfly10.0.0.Final server, expand, delete .zip
wget http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip -OutFile server.zip
Expand-Archive "C:\WildFly\server.zip" -dest "C:\WildFly\server"
Remove-Item "C:\WildFly\server.zip"

#override standalone-full.xml file
Remove-Item "C:\WildFly\server\wildfly-10.0.0.Final\standalone\configuration\standalone-full.xml"
Copy-Item $PSScriptroot\files\standalone-full.xml "C:\WildFly\server\wildfly-10.0.0.Final\standalone\configuration\"
#copy configuration files (JDBC driver, module.xml)
New-Item "C:\WildFly\server\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres" -type directory
New-Item "C:\WildFly\server\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres\main" -type directory
Copy-Item $PSScriptroot\files\postgres\main\* "C:\WildFly\server\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres\main"