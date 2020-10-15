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


## Contributors
* COGNE Gabriel
* D'ANDREA William
* CLODONG Yann
* CHOUHABI Mohammed Amine
