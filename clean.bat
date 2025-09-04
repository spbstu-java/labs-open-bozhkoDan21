@echo off
echo ===============================
echo    CLEANING PROJECT
echo ===============================
echo.

if exist bin (
    echo Deleting bin folder...
    rmdir /s /q bin
    echo Bin folder deleted
) else (
    echo Bin folder does not exist
)

echo.
pause