Write-Host "Starting Finance Manager..." -ForegroundColor Cyan

Start-Process powershell -ArgumentList "-NoExit", "-Command", "C:\Gradle\gradle-8.14.4\bin\gradle.bat bootRun"

Start-Sleep -Seconds 5

Start-Process "http://localhost:8080"

Write-Host "System running successfully!" -ForegroundColor Green