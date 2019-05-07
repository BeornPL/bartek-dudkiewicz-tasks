call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startBrowser
echo.
echo runcrud.bat has errors - breaking work
goto fail

:startBrowser
start firefox http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors in showtasks.bat

:end
echo.
echo Work is finished. Period.