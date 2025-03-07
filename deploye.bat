
@echo off 

set "webapps=D:\apache-tomcat-10.1.7\webapps\"
set "nomProjet=Ticketing" 

set "temporaire=D:\S4\Web dynamique\Temp"
set "src=src"
set "lib=lib"
set "web=pages" 
set "xml=conf"
set "bin=bin" 

rem Suppression et Creation du dossier temporaire
del /s /q "%temporaire%\*.*" 
rmdir /s /q "%temporaire%"

@REM mkdir "%temporaire%\META-INF"

rem Creation du dossier web, et lib
mkdir "%temporaire%\WEB-INF\lib" && mkdir "%temporaire%\views\" && mkdir "%bin%

rem Copie du dossier web, we.xml et lib
xcopy "%lib%" "%temporaire%\WEB-INF\lib" /E && xcopy "%web%" "%temporaire%\views\" /E && copy "%xml%\*" "%temporaire%\WEB-INF\"

for /R "%src%" %%f in (*.java) do (
    copy "%%f" "%bin%"
)
rem Compilation des fichiers
javac -parameters -cp %lib%\* -d "%temporaire%\WEB-INF\classes" %bin%\*.java

rem Vérification de la compilation
if %errorlevel% neq 0 ( 
    echo Erreur de la compilation
    exit /b 1
)
del /s /q "%bin%\*.*" 

rem Archivage des fichiers
jar -cvf "%webapps%%nomProjet%.war" -C "%temporaire%" .