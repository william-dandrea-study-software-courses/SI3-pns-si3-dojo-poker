# Dojo poker 2021 - Groupe D

## Description
Inspiré du célèbre jeu de poker, cet extrait permet de comparer deux mains de 5 cartes afin de savoir qui a gagné et par quel moyen.

## Comment l'utiliser ?
**Sur linux ou mac,**
il s'uffit d'éxécuter le script launcher.sh

**Sur Windows,** d'éxécuter launcher.bat

**Avec Java directement** :<br>
Dans le répertoire du projet,
<ol>
    <li>Compiler depuis le répertoire src/ avec la commande : <br><code>javac -d ../bin cards/Card.java cards/Color.java cards/Value.java hands/Hand.java hands/HandBuilder.java hands/HandComparator.java interaction/ColorVictory.java interaction/ResultType.java interaction/TwoCardVictory.java interaction/Victorieu.java interaction/Victory.java util/cmdline/CmdLineController.java util/cmdline/CmdLineUserInterface.java launcher/CmdLineMain.java</code>
    <li>Copier les dossiers et les fichiers ressources de /res vers /bin</li>
    <li>Enfin, Executer <code>java launcher.CmdLineMain</code> dans le répertoire /bin</li>
</li>
</ol>


## Comment lancer les tests ?

**Sur Windows,** éxécuter le script tests.bat

**Avec Java directement** : <br>
Dans le répertoire du projet,
<ol>
    <li>
        Y Placer l'archive jar, télécharger à l'adresse : <code>https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.0/junit-platform-console-standalone-1.7.0-all.jar</code>
    </li>
    <li>
        Compiler toutes les classes avec <br> <code>javac -d out src/cards/Card.java src/cards/Color.java src/cards/Value.java src/hands/Hand.java src/hands/HandBuilder.java src/hands/HandComparator.java src/interaction/ColorVictory.java src/interaction/ResultType.java src/interaction/TwoCardVictory.java src/interaction/Victorieu.java src/interaction/Victory.java src/util/cmdline/CmdLineController.java src/util/cmdline/CmdLineUserInterface.java src/launcher/CmdLineMain.java tests/cards/CardTest.java tests/hands/*.java -cp junit-platform-console-standalone-1.7.0-all.jar</code>
    </li>
    <li>
        Lancer les tests avec la commande : <br><code>java -jar junit-platform-console-standalone-1.7.0-all.jar --class-path out --scan-class-path</code>
    </li>
</ol>

## Contributors
* COGNE Gabriel
* D'ANDREA William
* CLODONG Yann
* CHOUHABI Mohammed Amine
