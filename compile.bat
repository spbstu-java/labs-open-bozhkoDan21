@echo off
echo ===============================
echo    COMPILING JAVA PROJECT
echo ===============================
echo.

echo Compiling source code...
javac -d bin src\com\game\hero\*.java src\com\game\hero\strategy\*.java

if %errorlevel% equ 0 (
    echo COMPILATION SUCCESSFUL!
    echo .class files created in bin folder
) else (
    echo COMPILATION FAILED!
    echo Check your source code
)

echo.
pause