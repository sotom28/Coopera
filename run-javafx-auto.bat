@echo off
setlocal enabledelayedexpansion

echo ========================================
echo Sistema Coopera - Panel JavaFX
echo ========================================
echo.

REM Compilar primero
echo [1/3] Compilando aplicacion...
call gradlew.bat --no-daemon compileJava

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Fallo la compilacion
    pause
    exit /b 1
)
echo OK - Compilacion exitosa
echo.

REM Buscar JavaFX en cache de Gradle
echo [2/3] Localizando JavaFX...
set GRADLE_CACHE=%USERPROFILE%\.gradle\caches\modules-2\files-2.1

REM Buscar javafx-controls
for /d %%i in ("%GRADLE_CACHE%\org.openjfx\javafx-controls\*") do (
    for /d %%j in ("%%i\*") do (
        set JAVAFX_CONTROLS=%%j
    )
)

REM Buscar javafx-fxml
for /d %%i in ("%GRADLE_CACHE%\org.openjfx\javafx-fxml\*") do (
    for /d %%j in ("%%i\*") do (
        set JAVAFX_FXML=%%j
    )
)

REM Buscar javafx-graphics
for /d %%i in ("%GRADLE_CACHE%\org.openjfx\javafx-graphics\*") do (
    for /d %%j in ("%%i\*") do (
        set JAVAFX_GRAPHICS=%%j
    )
)

REM Buscar javafx-base
for /d %%i in ("%GRADLE_CACHE%\org.openjfx\javafx-base\*") do (
    for /d %%j in ("%%i\*") do (
        set JAVAFX_BASE=%%j
    )
)

if not defined JAVAFX_CONTROLS (
    echo ERROR: No se encontro JavaFX en cache de Gradle
    echo Ejecuta primero: gradlew build
    pause
    exit /b 1
)

echo OK - JavaFX encontrado
echo.

REM Construir module-path
set MODULE_PATH=!JAVAFX_CONTROLS!;!JAVAFX_FXML!;!JAVAFX_GRAPHICS!;!JAVAFX_BASE!

REM Construir classpath
set CP=build\classes\java\main;build\resources\main

REM Agregar Oracle JDBC
for /r "%GRADLE_CACHE%\com.oracle.database.jdbc\ojdbc11" %%f in (*.jar) do set CP=!CP!;%%f
for /r "%GRADLE_CACHE%\com.oracle.database.security\oraclepki" %%f in (*.jar) do set CP=!CP!;%%f
for /r "%GRADLE_CACHE%\com.oracle.database.security\osdt_core" %%f in (*.jar) do set CP=!CP!;%%f
for /r "%GRADLE_CACHE%\com.oracle.database.security\osdt_cert" %%f in (*.jar) do set CP=!CP!;%%f

echo [3/3] Iniciando aplicacion JavaFX...
echo.
java --module-path "!MODULE_PATH!" ^
     --add-modules javafx.controls,javafx.fxml ^
     -cp "!CP!" ^
     com.example.Coopera.javafx.CooperaJavaFXApp

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: La aplicacion termino con errores
    pause
    exit /b 1
)

endlocal
