package belot.model.cardmodel;

import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck  {
    private List<Card> cardList;

    public Deck(int cardsAbove)  {
        setCardList(cardsAbove);
    }

    private List<Card> getCardList()  {
        return cardList;
    }

    private void setCardList(int cardsAbove)  {
        this.cardList = new ArrayList<>();

        Arrays.stream(SuitEnum.values()).forEach(suit ->  {
            for (int i = 0; i < RankEnum.values().length; i++)  {
                if (RankEnum.values()[i].getCode() >= cardsAbove) {
                    Card card = new Card(suit, RankEnum.values()[i]);
                    setCard(card);
                }
            }
        });
    }

    private void setCard(Card card) {
        //TODO check if all the cards are really needed
        this.cardList.add(card);
    }

    public void shuffle() {
        Collections.shuffle(this.cardList);
    }

    public List<Card> deal(int numberOfCardsToDeal) {
        List<Card> dealtCards = new ArrayList<>();

        for (int i = 0; i < numberOfCardsToDeal; i++) {
            int cardIndex = this.cardList.size() - 1;
            dealtCards.add(this.cardList.get(cardIndex));
            this.cardList.remove(cardIndex);
        }

        return dealtCards;
    }

    public List<Card> getCards() {
        return this.cardList;
    }

}
