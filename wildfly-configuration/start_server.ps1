$runningPath = Split-Path -Parent $PSCommandPath

if(Test-Path "C:\WildFly\") {
	Set-Location "C:\WildFly\wildfly-10.0.0.Final\bin\"
	
	Try {
		$connection = (New-Object Net.Sockets.TcpClient)
		$connected = $connection.Connect("127.0.0.1",8080)
		"ERROR: Server is already running."
		Set-Location $runningPath
    }
	Catch {
		.\standalone.bat
    }
} 
else {
	echo "ERROR: Server is not found in the directory C:\"
}

Set-Location $runningPath