@echo off

if exist junit-platform-console-standalone-1.7.0-all.jar goto afd

echo downloading package...
powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar', 'junit-platform-console-standalone-1.7.0-all.jar')"
powershell -Command "Invoke-WebRequest https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar -OutFile junit-platform-console-standalone-1.7.0-all.jar"
cls
echo package downloaded !

:afd
echo compiling...

javac -d out src/cards/Card.java src/cards/Color.java src/cards/Value.java src/hands/Hand.java src/hands/HandBuilder.java src/hands/HandComparator.java src/interaction/ColorVictory.java src/interaction/ResultType.java src/interaction/TwoCardVictory.java src/interaction/Victorieu.java src/interaction/Victory.java src/util/cmdline/CmdLineController.java src/util/cmdline/CmdLineUserInterface.java src/launcher/CmdLineMain.java tests/cards/CardTest.java tests/hands/*.java -cp junit-platform-console-standalone-1.7.0-all.jar
cls

java -jar junit-platform-console-standalone-1.7.0-all.jar --class-path out --scan-class-path

pause
