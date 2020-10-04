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
    private HandComparator comparator;

    @BeforeEach
    public void initHands() {
        builder = new HandBuilder();
        comparator = new HandComparator();
    }

    @Test
    public void testCompareHighestCardJackAndQueen () throws Exception {
        Hand jackDiamond = builder.buildHandFromString("VCa");
        Hand queenDiamond = builder.buildHandFromString("DCa");
        Hand asDiamond = builder.buildHandFromString("ACa");
        Hand queenSpade = builder.buildHandFromString("DPi");

        Victory jackAndQueenVictory = comparator.compare(jackDiamond, queenDiamond);
        assertEquals(Victorieu.main2, jackAndQueenVictory.getWinner());
        assertEquals(ResultType.higherCard, jackAndQueenVictory.getWinType());

        Victory asAndQueenVictory = comparator.compare(asDiamond, queenDiamond);
        assertEquals(Victorieu.main1, asAndQueenVictory.getWinner());
        assertEquals(ResultType.higherCard, asAndQueenVictory.getWinType());

        Victory queenAndQueenVictory = comparator.compare(queenSpade, queenDiamond);
        assertEquals(Victorieu.egalite, queenAndQueenVictory.getWinner());
        assertEquals(ResultType.higherCard, queenAndQueenVictory.getWinType());
    }

    @Test
    public void testComparePairAndPair () throws Exception {
        Hand jackPair = builder.buildHandFromString("VCa VPi");
        Hand queenPair = builder.buildHandFromString("DCa DPi");
        Hand kingPair = builder.buildHandFromString("RCa RPi");
        Hand jackPair2 = builder.buildHandFromString("VCo VTr");

        Victory jackAndQueenVictory = comparator.compare(jackPair, queenPair);
        assertEquals(Victorieu.main2, jackAndQueenVictory.getWinner(),
                "Test compare jackPair with queenPair, who's the winner ?");
        assertEquals(ResultType.pair, jackAndQueenVictory.getWinType(),
                "Test compare jackPair with queenPair, why do he won ?");

        Victory kingAndQueenVictory = comparator.compare(kingPair, queenPair);
        assertEquals(Victorieu.main1, kingAndQueenVictory.getWinner(),
                "Test compare kingPair with queenPair, who's the winner ?");
        assertEquals(ResultType.pair, kingAndQueenVictory.getWinType(),
                "Test compare kingPair with queenPair, why do he won ?");

        Victory jackAndJackVictory = comparator.compare(jackPair, jackPair2);
        assertEquals(Victorieu.egalite, jackAndJackVictory.getWinner(),
                "Test compare jackPair with jackPair, who's the winner ?");
    }

    @Test
    public void testComparePairAndHighestCard () throws Exception {
        Hand queenPair = builder.buildHandFromString("DCa DPi");
        Hand kingAndAs = builder.buildHandFromString("RCa APi");

        Victory kingAndAsCompareQueensVictory = comparator.compare(kingAndAs, queenPair);
        assertEquals(Victorieu.main2, kingAndAsCompareQueensVictory.getWinner(),
                "Test compare kingAndAs with queenPair, who's winner ?");
        assertEquals(ResultType.pair, kingAndAsCompareQueensVictory.getWinType(),
                "Test compare kingAndAs with queenPair, why do he won ?");
        assertEquals(ResultType.higherCard, kingAndAsCompareQueensVictory.getLoseType(),
                "Test compare kingAndAs with queenPair, why do he lose ?");

        Victory queensCompareKingAndAsVictory = comparator.compare(queenPair, kingAndAs);
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

        Victory twoWithQueenVictory = comparator.compare(twoSet, queenSet);
        assertEquals(Victorieu.main2, twoWithQueenVictory.getWinner(),
                "Test compare trip twos with trip queens, who's the winner ?");
        assertEquals(ResultType.brelan, twoWithQueenVictory.getWinType(),
                "Test compare trip twos with trip queens, why do he won ?");

        Victory AceWithQueenVictory = comparator.compare(aceSet, queenSet);
        assertEquals(Victorieu.main1, AceWithQueenVictory.getWinner(),
                "Test compare trip aces with trip queens, who's the winner ?");
        assertEquals(ResultType.brelan, AceWithQueenVictory.getWinType(),
                "Test compare trip aces with trip queens, why do he won ?");

        try {
            Victory queenWithQueen = comparator.compare(queenSet, queenSet);
            fail ("Must raise an exception because two trip in a four colors games is impossible");
        } catch (Exception e) {
            assertTrue(e != null, "Test raise exception when two trips have the same value");
        }
    }

    @Test
    public void testCompareTripsAndLess () throws Exception {
        Hand queenSet = builder.buildHandFromString("DCa DPi DTr");
        Hand acesAndKing = builder.buildHandFromString("ATr ACo RCo");

        Victory queenWithAcesAndKing = comparator.compare(queenSet, acesAndKing);
        assertEquals(Victorieu.main1, queenWithAcesAndKing.getWinner(),
                "Test compare trip queens with kingAndAces, who's winner ?");
        assertEquals(ResultType.brelan, queenWithAcesAndKing.getWinType(),
                "Test compare trip queens with kingAndAces, why do he won ?");

        Victory acesAndKingWithQueens = comparator.compare(acesAndKing, queenSet);
        assertEquals(Victorieu.main2, acesAndKingWithQueens.getWinner(),
                "Test compare kingAndAces with trip queens, who's winner ?");
        assertEquals(ResultType.brelan, acesAndKingWithQueens.getWinType(),
                "Test compare kingAndAces with trip queens, why do he won ?");
    }
}
