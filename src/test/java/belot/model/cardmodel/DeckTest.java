package belot.model.cardmodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck(RankEnum.TEN.getCode());
    }

    @Test
    public void setCardListTestIfContains10SpadesFromTheSameRank() {
        Card card = new Card(SuitEnum.SPADES, RankEnum.TEN);

        boolean[] containsCard = new boolean[1];
        deck.getCards().forEach(cardInDeck -> {
            if (cardInDeck.getSuit().getCode() == card.getSuit().getCode()
                    && cardInDeck.getRank().getCode() == card.getRank().getCode()
            ) {
                containsCard[0] = true;
            }
        });

        Assertions.assertTrue(containsCard[0]);
    }

    @Test
    public void setCardListTestIfContains10ClubsFromTheSameRank() {
        Card card = new Card(SuitEnum.CLUBS, RankEnum.TEN);

        boolean[] containsCard = new boolean[1];
        deck.getCards().forEach(cardInDeck -> {
            if (cardInDeck.getSuit().getCode() == card.getSuit().getCode()
                    && cardInDeck.getRank().getCode() == card.getRank().getCode()
            ) {
                containsCard[0] = true;
            }
        });

        Assertions.assertTrue(containsCard[0]);
    }

    @Test
    public void setCardListTestIfContains10DiamondsFromTheSameRank() {
        Card card1 = new Card(SuitEnum.DIAMONDS, RankEnum.TEN);

        boolean[] containsCard = new boolean[1];
        deck.getCards().forEach(cardInDeck -> {
            if (cardInDeck.getSuit().getCode() == card1.getSuit().getCode()
                    && cardInDeck.getRank().getCode() == card1.getRank().getCode()
            ) {
                containsCard[0] = true;
            }
        });

        Assertions.assertTrue(containsCard[0]);
    }

    @Test
    public void setCardListTestIfContains10HeartsFromTheSameRank() {
        Card card2 = new Card(SuitEnum.HEARTS, RankEnum.TEN);

        boolean[] containsCard = new boolean[1];
        deck.getCards().forEach(cardInDeck -> {
            if (cardInDeck.getSuit().getCode() == card2.getSuit().getCode()
                    && cardInDeck.getRank().getCode() == card2.getRank().getCode()
            ) {
                containsCard[0] = true;
            }
        });

        Assertions.assertTrue(containsCard[0]);
    }

    @Test
    public void setCardListTestIfDoesNotContainCard() {
        Card card = new Card(SuitEnum.CLUBS, RankEnum.EIGHT);

        Assertions.assertFalse(deck.getCards().contains(card));
    }

    @Test
    public void testIfDealtCardsAreAsExpectedLength() {
        int expectedLength = 3;
        int calculatedLength = deck.deal(3).size();

        Assertions.assertTrue(expectedLength == calculatedLength);
    }
}