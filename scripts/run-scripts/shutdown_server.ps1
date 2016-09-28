$runningPath = Split-Path -Parent $PSCommandPath

if(Test-Path "C:\WildFly\") {
	Set-Location "C:\WildFly\wildfly-10.0.0.Final\bin\"
	
	Try {
		$connection = (New-Object Net.Sockets.TcpClient)
		$connected = $connection.Connect("127.0.0.1",8080)
		
		
		$deploymentsList = .\jboss-cli.bat --connect --command=deployment-info

		if($deploymentsList -Match ".*Project-ClientRegistry-web-1.0-SNAPSHOT.war.*") {
		.\jboss-cli.bat --connect --command="undeploy Project-ClientRegistry-web-1.0-SNAPSHOT.war"
		}
		
		.\jboss-cli.bat --connect --command="shutdown"
    }
	Catch {
		"ERROR: Server is not running."
    }
} 
else {
	echo "ERROR: Server is not found in the directory C:\"
}

Set-Location $runningPath