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
    public void testCompareHighestCardJackAndQueen () {
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
    public void testComparePairAndPair () {
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
        assertEquals(ResultType.pair, jackAndJackVictory.getWinType(),
                "Test compare jackPair with jackPair, why do he won ?");
    }

    @Test
    public void testComparePairAndHighestCard () {
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
}
