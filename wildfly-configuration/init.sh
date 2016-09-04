Set-Location C:\
# remove old content if it extists
if(Test-Path C:\wildfly+configuration){
	Remove-Item .\wildfly+configuration -Force -Recurse
}
New-Item C:\wildfly+configuration -type directory
Set-Location C:\wildfly+configuration
# download wildfly10.0.0.Final server, expand, delete .zip
wget http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip -OutFile server.zip
Expand-Archive C:\wildfly+configuration\server.zip -dest C:\wildfly+configuration\server
Remove-Item C:\wildfly+configuration\server.zip
#override standalone-full.xml file
Remove-Item C:\wildfly+configuration\server\wildfly-10.0.0.Final\standalone\configuration\standalone-full.xml
Copy-Item $PSScriptroot\files\standalone-full.xml C:\wildfly+configuration\server\wildfly-10.0.0.Final\standalone\configuration\
#copy configuration files (JDBC driver, module.xml)
New-Item C:\wildfly+configuration\server\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres -type directory
Copy-Item $PSScriptroot\files\postgres\* C:\wildfly+configuration\server\wildfly-10.0.0.Final\modules\system\layers\base\org\postgres\