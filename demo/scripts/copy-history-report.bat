@echo off

set "source_history=..\.\target\allure-report\history\"
set "destination_history=..\.\target\allure-results\history\"

echo Cleaning up temporary folder...
rmdir /S /Q ..\.\target\allure-results\

echo Copying history folder to allure-results...
xcopy "%source_history%" "%destination_history%" /E /Y /Q

echo Generating Allure report...
allure generate .././target/allure-results --clean -o .././target/allure-report

echo Allure report generation complete!
pause