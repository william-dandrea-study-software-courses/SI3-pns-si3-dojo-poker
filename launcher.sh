#!/bin/bash

echo off

if not exist bin/
then
	mkdir bin
fi

cd src

javac -d ../bin cards/Card.java cards/Color.java cards/Value.java hands/Hand.java hands/HandBuilder.java hands/HandComparator.java interaction/ColorVictory.java interaction/ResultType.java interaction/TwoCardVictory.java interaction/Victorieu.java interaction/Victory.java util/cmdline/CmdLineController.java util/cmdline/CmdLineUserInterface.java launcher/CmdLineMain.java

cd ..

cp -r res/values/ bin/values/

clear

cd bin

java launcher.CmdLineMain

cd ..