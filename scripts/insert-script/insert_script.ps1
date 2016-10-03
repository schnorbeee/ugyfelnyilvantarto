$runningPath = Split-Path -Parent $PSCommandPath

Set-Location "C:\Program Files\PostgreSQL\"

$foldersInPSQL = ls
if($foldersInPSQL.length -eq 1 -And $foldersInPSQL -match "[0-9].*") {
cd .\$foldersInPSQL\bin
}


.\psql.exe -f "$runningPath\insert_person_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_address.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_user_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_connection_channel.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_num_item_per_page_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_role_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_company_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_project_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_company_project_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_event_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_note_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_invitation_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_user_event_table.sql" -U postgres "dbname=clientregistry password=laszlo"
.\psql.exe -f "$runningPath\insert_contactperson_table.sql" -U postgres "dbname=clientregistry password=laszlo"
Set-Location $runningPath