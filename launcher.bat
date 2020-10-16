@echo off
@chcp 65001
@title dojo-poker-2021-d
cls

if not exist bin/ (
	mkdir bin
)

cd src

javac -d ../bin cards/Card.java cards/Color.java cards/Value.java hands/Hand.java hands/HandBuilder.java hands/HandComparator.java interaction/ColorVictory.java interaction/ResultType.java interaction/TwoCardVictory.java interaction/Victorieu.java interaction/Victory.java util/cmdline/CmdLineController.java util/cmdline/CmdLineUserInterface.java launcher/CmdLineMain.java

cd ..

xcopy /E /Y res\values\* bin\values\*

cls

cd bin

java launcher.CmdLineMain

set /p var=""

cd ..