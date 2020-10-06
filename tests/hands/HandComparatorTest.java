package hands;

import interaction.ResultType;
import interaction.Victorieu;
import interaction.Victory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic JUnit test on <i>HandComparator</i> class
 *
 * @author Gabriel Cogne
 */

public class HandComparatorTest {

    private HandBuilder builder;
    private HandComparator referee;

    @BeforeEach
    public void initHands() {
        builder = new HandBuilder();
        referee = new HandComparator();
    }

    @Test
    public void testCompareHighestCardJackAndQueen () throws Exception {
        Hand jackDiamond = builder.buildHandFromString("VCa");
        Hand queenDiamond = builder.buildHandFromString("DCa");
        Hand asDiamond = builder.buildHandFromString("ACa");
        Hand queenSpade = builder.buildHandFromString("DPi");

        Victory jackAndQueenVictory = referee.compare(jackDiamond, queenDiamond);
        assertEquals(Victorieu.main2, jackAndQueenVictory.getWinner());
        assertEquals(ResultType.higherCard, jackAndQueenVictory.getWinType());

        Victory asAndQueenVictory = referee.compare(asDiamond, queenDiamond);
        assertEquals(Victorieu.main1, asAndQueenVictory.getWinner());
        assertEquals(ResultType.higherCard, asAndQueenVictory.getWinType());

        Victory queenAndQueenVictory = referee.compare(queenSpade, queenDiamond);
        assertEquals(Victorieu.egalite, queenAndQueenVictory.getWinner());
        assertEquals(ResultType.higherCard, queenAndQueenVictory.getWinType());
    }

    @Test
    public void testComparePairAndPair () throws Exception {
        Hand jackPair = builder.buildHandFromString("VCa VPi");
        Hand queenPair = builder.buildHandFromString("DCa DPi");
        Hand kingPair = builder.buildHandFromString("RCa RPi");
        Hand jackPair2 = builder.buildHandFromString("VCo VTr");

        Victory jackAndQueenVictory = referee.compare(jackPair, queenPair);
        assertEquals(Victorieu.main2, jackAndQueenVictory.getWinner(),
                "Test compare jackPair with queenPair, who's the winner ?");
        assertEquals(ResultType.pair, jackAndQueenVictory.getWinType(),
                "Test compare jackPair with queenPair, why do he won ?");

        Victory kingAndQueenVictory = referee.compare(kingPair, queenPair);
        assertEquals(Victorieu.main1, kingAndQueenVictory.getWinner(),
                "Test compare kingPair with queenPair, who's the winner ?");
        assertEquals(ResultType.pair, kingAndQueenVictory.getWinType(),
                "Test compare kingPair with queenPair, why do he won ?");

        Victory jackAndJackVictory = referee.compare(jackPair, jackPair2);
        assertEquals(Victorieu.egalite, jackAndJackVictory.getWinner(),
                "Test compare jackPair with jackPair, who's the winner ?");
    }

    @Test
    public void testComparePairAndHighestCard () throws Exception {
        Hand queenPair = builder.buildHandFromString("DCa DPi");
        Hand kingAndAs = builder.buildHandFromString("RCa APi");

        Victory kingAndAsCompareQueensVictory = referee.compare(kingAndAs, queenPair);
        assertEquals(Victorieu.main2, kingAndAsCompareQueensVictory.getWinner(),
                "Test compare kingAndAs with queenPair, who's winner ?");
        assertEquals(ResultType.pair, kingAndAsCompareQueensVictory.getWinType(),
                "Test compare kingAndAs with queenPair, why do he won ?");
        assertEquals(ResultType.higherCard, kingAndAsCompareQueensVictory.getLoseType(),
                "Test compare kingAndAs with queenPair, why do he lose ?");

        Victory queensCompareKingAndAsVictory = referee.compare(queenPair, kingAndAs);
        assertEquals(Victorieu.main1, queensCompareKingAndAsVictory.getWinner(),
                "Test compare queenPair with kingAndAs, who's winner ?");
        assertEquals(ResultType.pair, queensCompareKingAndAsVictory.getWinType(),
                "Test compare queenPair with kingAndAs, why do he won ?");
        assertEquals(ResultType.higherCard, queensCompareKingAndAsVictory.getLoseType(),
                "Test compare queenPair with kingAndAs, why do he lose ?");
    }

    @Test
    public void testCompareTripsAndTrip () throws Exception {
        Hand twoSet = builder.buildHandFromString("2Ca 2Pi 2Tr");
        Hand queenSet = builder.buildHandFromString("DCa DPi DTr");
        Hand aceSet = builder.buildHandFromString("ATr ACa ACo");

        Victory twoWithQueenVictory = referee.compare(twoSet, queenSet);
        assertEquals(Victorieu.main2, twoWithQueenVictory.getWinner(),
                "Test compare trip twos with trip queens, who's the winner ?");
        assertEquals(ResultType.brelan, twoWithQueenVictory.getWinType(),
                "Test compare trip twos with trip queens, why do he won ?");

        Victory aceWithQueenVictory = referee.compare(aceSet, queenSet);
        assertEquals(Victorieu.main1, aceWithQueenVictory.getWinner(),
                "Test compare trip aces with trip queens, who's the winner ?");
        assertEquals(ResultType.brelan, aceWithQueenVictory.getWinType(),
                "Test compare trip aces with trip queens, why do he won ?");

        try {
            referee.compare(queenSet, queenSet);
            fail ("Must raise an exception because two same value trips in a four colors games is impossible");
        } catch (Exception e) {
            assertTrue(true, "Test raise exception when two trips have the same value");
        }
    }

    @Test
    public void testCompareTripsAndLess () throws Exception {
        Hand queenSet = builder.buildHandFromString("DCa DPi DTr");
        Hand acesAndKing = builder.buildHandFromString("ATr ACo RCo");

        Victory queenWithAcesAndKing = referee.compare(queenSet, acesAndKing);
        assertEquals(Victorieu.main1, queenWithAcesAndKing.getWinner(),
                "Test compare trip queens with kingAndAces, who's winner ?");
        assertEquals(ResultType.brelan, queenWithAcesAndKing.getWinType(),
                "Test compare trip queens with kingAndAces, why do he won ?");

        Victory acesAndKingWithQueens = referee.compare(acesAndKing, queenSet);
        assertEquals(Victorieu.main2, acesAndKingWithQueens.getWinner(),
                "Test compare kingAndAces with trip queens, who's winner ?");
        assertEquals(ResultType.brelan, acesAndKingWithQueens.getWinType(),
                "Test compare kingAndAces with trip queens, why do he won ?");
    }

    @Test
    public void testCompareQuadAndQuad () throws Exception {
        Hand jackQuad = builder.buildHandFromString("VCa VPi VTr VCo");
        Hand queenQuad = builder.buildHandFromString("DCa DPi DTr DCo");
        Hand kingQuad = builder.buildHandFromString("RTr RCa RCo RPi");

        Victory jackWithQueenVictory = referee.compare(jackQuad, queenQuad);
        assertEquals(Victorieu.main2, jackWithQueenVictory.getWinner(),
                "Test compare quad jacks with quad queens, who's the winner ?");
        assertEquals(ResultType.carre, jackWithQueenVictory.getWinType(),
                "Test compare quad jacks with four of a kind, queens, why do he won ?");

        Victory kingWithQueenVictory = referee.compare(kingQuad, queenQuad);
        assertEquals(Victorieu.main1, kingWithQueenVictory.getWinner(),
                "Test compare quad kings with quad queens, who's the winner ?");
        assertEquals(ResultType.carre, kingWithQueenVictory.getWinType(),
                "Test compare quad kings with quad queens, why do he won ?");

        try {
            referee.compare(queenQuad, queenQuad);
            fail ("Must raise an exception because two same value quads in a four colors games is impossible");
        } catch (Exception e) {
            assertTrue(true, "Test raise exception when two trips have the same value");
        }
    }

    @Test
    public void testCompareQuadAndLess () throws Exception {
        Hand queenQuad = builder.buildHandFromString("DCa DPi DTr DCo");
        Hand acesAndKing = builder.buildHandFromString("ATr ACa RCo APi");

        Victory queenWithAcesAndKing = referee.compare(queenQuad, acesAndKing);
        assertEquals(Victorieu.main1, queenWithAcesAndKing.getWinner(),
                "Test compare quad queens with kingAndAces, who's winner ?");
        assertEquals(ResultType.carre, queenWithAcesAndKing.getWinType(),
                "Test compare quad queens with kingAndAces, why do he won ?");

        Victory acesAndKingWithQueens = referee.compare(acesAndKing, queenQuad);
        assertEquals(Victorieu.main2, acesAndKingWithQueens.getWinner(),
                "Test compare kingAndAces with quad queens, who's winner ?");
        assertEquals(ResultType.carre, acesAndKingWithQueens.getWinType(),
                "Test compare kingAndAces with quad queens, why do he won ?");
    }

    @Test
    public void testRefereeTwoPairWithTwoPair () throws Exception {
        Hand jacksAndQueens = builder.buildHandFromString("VTr VPi DTr DPi");
        Hand tensAndQueens = builder.buildHandFromString("10Tr 10Pi DCo DCa");
        Hand acesAndKings = builder.buildHandFromString("ATr APi RCo RCa");
        Hand acesAndKings2 = builder.buildHandFromString("ACo ACa RTr RPi");

        Victory jackWithTenVictory = referee.compare(jacksAndQueens, tensAndQueens);
        assertEquals(Victorieu.main1, jackWithTenVictory.getWinner(),
                "Test compare jacksAndQueens with tensAndQueens, who's the winner ?");
        assertEquals(ResultType.doublePair, jackWithTenVictory.getWinType(),
                "Test compare jacksAndQueens with tensAndQueens, why do he won ?");

        Victory queensWithAcesVictory = referee.compare(jacksAndQueens, acesAndKings);
        assertEquals(Victorieu.main2, queensWithAcesVictory.getWinner(),
                "Test compare jacksAndQueens with acesAndKings, who's the winner ?");
        assertEquals(ResultType.doublePair, queensWithAcesVictory.getWinType(),
                "Test compare jacksAndQueens with acesAndKings, why do he won ?");

        Victory acesWithAcesVictory = referee.compare(acesAndKings, acesAndKings2);
        assertEquals(Victorieu.egalite, acesWithAcesVictory.getWinner(),
                "Test compare acesAndKings with acesAndKings, who's the winner ?");
    }

    @Test
    public void testRefereeTwoPairWithLess () throws Exception {
        Hand jacksAndQueens = builder.buildHandFromString("VTr VPi DTr DPi");
        Hand nothing = builder.buildHandFromString("ATr 10Tr 8Ca 2Co");

        Victory jacksAndQueensWithNothing = referee.compare(jacksAndQueens, nothing);
        assertEquals(Victorieu.main1, jacksAndQueensWithNothing.getWinner(),
                "Test compare jacksAndQueens with nothing special, who's winner ?");
        assertEquals(ResultType.doublePair, jacksAndQueensWithNothing.getWinType(),
                "Test compare jacksAndQueens with nothing special, why do he won ?");

    }

    @Test
    public void testRefereeStraightWithStraight () throws Exception {
        Hand kingHighStraight = builder.buildHandFromString("RCo DCo VCo 10Ca 9Co");
        Hand aceHighStraight = builder.buildHandFromString("ACo RCa DCa VCa 10Tr");
        Hand kingHighStraight2 = builder.buildHandFromString("RPi DTr VTr 10Pi 9Ca");

        Victory kingWithAce = referee.compare(kingHighStraight, aceHighStraight);
        assertEquals(Victorieu.main2, kingWithAce.getWinner(),
                "Winner between a king-high straight and a ace-high straight");
        assertEquals(ResultType.suite, kingWithAce.getWinType(),
                "Why that winner between a king-high straight and a ace-high straight");

        Victory kingWithKing = referee.compare(kingHighStraight, kingHighStraight2);
        assertEquals(Victorieu.egalite, kingWithKing.getWinner(),
                "Winner between a king-high straight and another king-high straight");

        Victory aceWithKing = referee.compare(aceHighStraight, kingHighStraight2);
        assertEquals(Victorieu.main1, aceWithKing.getWinner(),
                "Winner between a ace-high straight and a king-high straight");
        assertEquals(ResultType.suite, aceWithKing.getWinType(),
                "Why that winner between a ace-high straight and a king-high straight");
    }

    @Test
    public void testRefereeStraightWithLess () throws Exception {
        Hand kingHighStraight = builder.buildHandFromString("RCo DCo VCo 10Ca 9Co");
        Hand twoSet = builder.buildHandFromString("2Ca 2Pi 2Tr 3Tr 4Ca");

        assertEquals(Victorieu.main1, referee.compare(kingHighStraight, twoSet).getWinner(),
                "Straight vs trip");
    }

    @Test
    public void testRefereeFlush () {
        Hand eightHighFlush = builder.buildHandFromString("3Tr 4Tr 5Tr 6Tr 8Tr");
        Hand eightHighFlush2 = builder.buildHandFromString("2Co 4Co 5Co 6Co 8Co");
        Hand nineHighFlush = builder.buildHandFromString("2Ca 4Ca 5Ca 6Ca 9Ca");

        Victory eightByEightFlush = null;
        Victory eightByNineFlush = null;
        try {
            eightByEightFlush = referee.compare(eightHighFlush, eightHighFlush2);
            eightByNineFlush = referee.compare(eightHighFlush2, nineHighFlush);

        } catch (Exception e) {
            fail("No exception should be raised");
        }

        assertEquals(Victorieu.main1, eightByEightFlush.getWinner(),
                "Winner when two flush have same values except the less one");
        assertEquals(Victorieu.main2, eightByNineFlush.getWinner(),
                "Winner when the greatest values are not the same for two flush");

        assertEquals(ResultType.couleur, eightByEightFlush.getWinType());
        assertEquals(ResultType.couleur, eightByNineFlush.getWinType());

        try {
            assertEquals(Victorieu.egalite, referee.compare(
                    builder.buildHandFromString("ATr RTr DTr VTr 9Tr"),
                    builder.buildHandFromString("APi RPi DPi VPi 9Pi")
            ).getWinner());
        } catch (Exception e) {
            fail("No exception should be raised");
        }
    }

    @Test
    public void testRefereeFlushWithLess () {
        Hand eightHighFlush = builder.buildHandFromString("3Tr 4Tr 5Tr 6Tr 8Tr");
        Hand aceAndKing = builder.buildHandFromString("RCo RCa ACo ACa 2Co");

        try {
            assertEquals(Victorieu.main1, referee.compare(
                    eightHighFlush,
                    aceAndKing
            ).getWinner());
        } catch (Exception e) {
            fail ("No exception should be raised");
        }
    }

    @Test
    public void testRefereeStraightFlush () {
        Hand kingHighStraightFlush = builder.buildHandFromString("DTr 10Tr RTr 9Tr VTr");
        Hand aceHighStraightFlush = builder.buildHandFromString("RCo DCo ACo 10Co VCo");
        Hand kingHighStraightFlush2 = builder.buildHandFromString("9Ca 10Ca RCa DCa VCa");

        Victory kingWithAce = null;
        Victory aceWithKing2 = null;
        Victory kingWithKing2 = null;

        try {
            kingWithAce = referee.compare(kingHighStraightFlush, aceHighStraightFlush);
            aceWithKing2 = referee.compare(aceHighStraightFlush, kingHighStraightFlush2);
            kingWithKing2 = referee.compare(kingHighStraightFlush, kingHighStraightFlush2);
        } catch (Exception e) {
            fail ("An exception shouldn't be raised");
        }

        assertEquals(Victorieu.main1, aceWithKing2.getWinner(),
                "Test straight flush with ace (heart) and king2 (diamond)");
        assertEquals(Victorieu.main2, kingWithAce.getWinner(),
                "Test straight flush with king (club) and ace (club)");
        assertEquals(Victorieu.egalite, kingWithKing2.getWinner(),
                "Test straight flush with king (club) and king2 (diamond)");

        assertEquals(ResultType.quinteFlush, aceWithKing2.getWinType(),
                "Test win type on ace-high straight flush");
        assertEquals(ResultType.quinteFlush, kingWithAce.getWinType(),
                "Test win type on ace-high straight flush 2");
    }

    @Test
    public void testRefereeStraightFlushWithLess () {
        Hand kingHighStraightFlush = builder.buildHandFromString("DTr 10Tr RTr 9Tr VTr");
        Hand aceQuad = builder.buildHandFromString("ACa APi ATr ACo 2Co");

        try {
            assertEquals(Victorieu.main1, referee.compare(
                    kingHighStraightFlush,
                    aceQuad
            ).getWinner());
        } catch (Exception e) {
            fail ("No exception should be raised");
        }
    }
}
