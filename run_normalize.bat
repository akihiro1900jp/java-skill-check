@echo off
REM Compile the Java program
javac NormalizeData.java
if %ERRORLEVEL% neq 0 (
    echo Compilation failed. Please check the Java code for errors.
    exit /b 1
)

REM Run the Java program
java NormalizeData
if %ERRORLEVEL% neq 0 (
    echo Program execution failed.
    exit /b 1
)

echo Execution completed successfully. Check the output.tsv file for results.
echo Press Enter to exit...
pause >nul

