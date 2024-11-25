@echo off
REM Compile the Java program
javac ReverseNormalization.java
if %ERRORLEVEL% neq 0 (
    echo Compilation failed. Please check the Java code for errors.
    exit /b 1
)

REM Run the Java program
java ReverseNormalization
if %ERRORLEVEL% neq 0 (
    echo Program execution failed.
    exit /b 1
)

echo Execution completed successfully. Check the output_reverse.tsv file for results.
echo Press Enter to exit...
pause >nul
