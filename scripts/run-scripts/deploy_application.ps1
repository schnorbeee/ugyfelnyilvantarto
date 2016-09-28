$runningPath = Split-Path -Parent $PSCommandPath

Set-Location "..\..\Clientregistry\Project-ClientRegistry\Project-ClientRegistry-web\target\"

$webTargetPath = Get-Location
$webTargetPath = $webTargetPath.path

Set-Location "C:\WildFly\wildfly-10.0.0.Final\bin\"

.\jboss-cli.bat --connect --command="deploy $webTargetPath/Project-ClientRegistry-web-1.0-SNAPSHOT.war --force"

Set-Location $runningPath