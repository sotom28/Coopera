@echo off
echo ========================================
echo Sistema Coopera - Panel JavaFX
echo ========================================
echo.
echo Ejecutando aplicación JavaFX...
echo.

gradlew.bat --no-daemon compileJava

if %ERRORLEVEL% NEQ 0 (
    echo Error al compilar. Verifica el código.
    pause
    exit /b 1
)

java --module-path "%USERPROFILE%\.gradle\caches\modules-2\files-2.1\org.openjfx" ^
     --add-modules javafx.controls,javafx.fxml ^
     -cp "build\classes\java\main;build\resources\main;%USERPROFILE%\.gradle\caches\modules-2\files-2.1\com.oracle.database.jdbc\ojdbc11\23.7.0.25.01\*;%USERPROFILE%\.gradle\caches\modules-2\files-2.1\com.oracle.database.security\oraclepki\21.9.0.0\*;%USERPROFILE%\.gradle\caches\modules-2\files-2.1\com.oracle.database.security\osdt_core\21.9.0.0\*;%USERPROFILE%\.gradle\caches\modules-2\files-2.1\com.oracle.database.security\osdt_cert\21.9.0.0\*" ^
     com.example.Coopera.javafx.CooperaJavaFXApp

pause
