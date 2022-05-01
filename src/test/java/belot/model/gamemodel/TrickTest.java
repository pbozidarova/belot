package belot.model.gamemodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import belot.model.cardmodel.Card;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;
import belot.model.gamemodel.enums.BidEnum;

class TrickTest {
    private Trick trick;

    @BeforeEach
    void setup() {
        this.trick = new Trick(BidEnum.CLUBS);

        Card card8 = new Card(SuitEnum.CLUBS, RankEnum.EIGHT);
        Card cardA = new Card(SuitEnum.DIAMONDS, RankEnum.ACE);
        Card cardJ = new Card(SuitEnum.CLUBS, RankEnum.JACK);
        Card card9 = new Card(SuitEnum.CLUBS, RankEnum.NINE);

        trick.addCard(card8);
        trick.addCard(cardA);
        trick.addCard(cardJ);
        trick.addCard(card9);
    }

    @Test
    public void cardIsAddedTest() {
        Card card9 = new Card(SuitEnum.CLUBS, RankEnum.NINE, 9, 0);
        boolean cardIsAdded = trick.addCard(card9);

        Assertions.assertTrue(cardIsAdded);
    }

    @Test
    public void indexOfWinningCardTest() {
        int expected = 2;
        int calculated = trick.indexOfWinningCard();
        Assertions.assertEquals(expected, calculated);
    }

    @Test
    public void scoreIsAsExpectedTest() {
        int expected = 45;
        int calculated = trick.calculateScore();

        Assertions.assertEquals(expected, calculated);
    }

}