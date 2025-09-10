@echo off
echo ===============================
echo    COMPILING JAVA PROJECT
echo ===============================
echo.

echo Compiling source code...
javac -d bin src\com\example\reflectionapp\*.java src\com\example\reflectionapp\annotation\*.java src\com\example\reflectionapp\domain\*.java src\com\example\reflectionapp\processor\*.java

if %errorlevel% equ 0 (
    echo COMPILATION SUCCESSFUL!
    echo .class files created in bin folder
) else (
    echo COMPILATION FAILED!
    echo Check your source code
)

echo.
pause