package belot.model.gamemodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import belot.model.cardmodel.Card;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;

import java.util.ArrayList;
import java.util.List;

class DeclarationsTest {
    Declarations declarations = new Declarations();

    @BeforeEach
    public void setup() {
        this.declarations = new Declarations();
    }

    @Test
    public void scanForDeclarationsTestIfTierce() {
        Card card = new Card(SuitEnum.CLUBS, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.CLUBS, RankEnum.EIGHT);
        Card card3 = new Card(SuitEnum.CLUBS, RankEnum.NINE);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);

        String declarationsTierce = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Tierce - Clubs";

        Assertions.assertTrue(declarationsTierce.equals(expectedString));
    }

    @Test
    public void scanForDeclarationsTestIfNoTierceIn5Cards() {
        Card card = new Card(SuitEnum.CLUBS, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.CLUBS, RankEnum.EIGHT);
        Card card5 = new Card(SuitEnum.HEARTS, RankEnum.NINE);
        Card card3 = new Card(SuitEnum.CLUBS, RankEnum.KING);
        Card card4 = new Card(SuitEnum.CLUBS, RankEnum.TEN);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);

        String declarationsTierce = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Tierce - Clubs";

        Assertions.assertFalse(declarationsTierce.equals(expectedString));
    }

    @Test
    public void scanForDeclarationsTestIfTierceIn5Cards() {
        Card card = new Card(SuitEnum.CLUBS, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.CLUBS, RankEnum.EIGHT);
        Card card3 = new Card(SuitEnum.CLUBS, RankEnum.NINE);
        Card card4 = new Card(SuitEnum.HEARTS, RankEnum.JACK);
        Card card5 = new Card(SuitEnum.HEARTS, RankEnum.QUEEN);
        Card card6 = new Card(SuitEnum.HEARTS, RankEnum.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        String declarationsTierce = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Tierce - Clubs Tierce - Hearts";

        Assertions.assertTrue(declarationsTierce.equals(expectedString));
    }


    @Test
    public void scanForDeclarationsTestIfNoTierce() {
        Card card = new Card(SuitEnum.CLUBS, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.HEARTS, RankEnum.EIGHT);
        Card card3 = new Card(SuitEnum.CLUBS, RankEnum.NINE);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);

        String declarationsTierce = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Tierce - Clubs";

        Assertions.assertFalse(declarationsTierce.equals(expectedString));
    }


    @Test
    public void scanForDeclarationsTestIfQuarte() {
        Card card = new Card(SuitEnum.SPADES, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.SPADES, RankEnum.EIGHT);
        Card card3 = new Card(SuitEnum.SPADES, RankEnum.NINE);
        Card card4 = new Card(SuitEnum.SPADES, RankEnum.TEN);
        Card card5 = new Card(SuitEnum.HEARTS, RankEnum.QUEEN);
        Card card6 = new Card(SuitEnum.HEARTS, RankEnum.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        String declarationsQuarte = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Quarte - Spades";

        Assertions.assertTrue(declarationsQuarte.equals(expectedString));
    }

    @Test
    public void scanForDeclarationsTestIfQuinte() {
        Card card = new Card(SuitEnum.SPADES, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.SPADES, RankEnum.EIGHT);
        Card card3 = new Card(SuitEnum.SPADES, RankEnum.NINE);
        Card card4 = new Card(SuitEnum.SPADES, RankEnum.TEN);
        Card card5 = new Card(SuitEnum.SPADES, RankEnum.JACK);
        Card card6 = new Card(SuitEnum.HEARTS, RankEnum.KING);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        String declarationsQuarte = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Quinte - Spades";

        Assertions.assertTrue(declarationsQuarte.equals(expectedString));
    }

    @Test
    public void scanForDeclarationsTestIfTierceAndQuinte() {
        Card card = new Card(SuitEnum.SPADES, RankEnum.SEVEN);
        Card card2 = new Card(SuitEnum.SPADES, RankEnum.EIGHT);
        Card card3 = new Card(SuitEnum.SPADES, RankEnum.NINE);
        Card card4 = new Card(SuitEnum.SPADES, RankEnum.TEN);
        Card card5 = new Card(SuitEnum.SPADES, RankEnum.JACK);
        Card card6 = new Card(SuitEnum.SPADES, RankEnum.QUEEN);
        Card card7 = new Card(SuitEnum.SPADES, RankEnum.KING);
        Card card8 = new Card(SuitEnum.SPADES, RankEnum.ACE);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);

        String declarationsQuinte = this.declarations.scanForDeclarations(cards).trim();
        String expectedString = "Quinte - Spades Tierce - Spades";

        Assertions.assertTrue(declarationsQuinte.equals(expectedString));
    }

}