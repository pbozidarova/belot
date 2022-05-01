package belot.model.gamemodel;

import belot.model.cardmodel.Card;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.gamemodel.enums.BidEnum;

import java.util.ArrayList;
import java.util.List;

public class Trick {
    private List<Card> cards;
    private int score;
    private BidEnum contract;

    public Trick(BidEnum contract) {
        cards = new ArrayList<>();
        this.contract = contract;
    }

    public boolean addCard(Card card) {
        setCardPoints(card);
        setCardPower(card);

        return this.cards.add(card);
    }

    public int indexOfWinningCard() {
        int index = 0;
        Card winningCard = this.cards.get(0);

        for (int i = 1; i < cards.size(); i++) {
            if (winningCard.getPower() < cards.get(i).getPower()) {
                winningCard = cards.get(i);
                index = i;
            }
        }

        return index;
    }

    public int calculateScore() {
        for (int i = 0; i < cards.size(); i++) {
            this.score += cards.get(i).getPoints();
        }

        return this.score;
    }

    private void setCardPower(Card card) {
        RankEnum rank = card.getRank();
        int power = 0;


        if (contract.equals(BidEnum.ALL_TRUMPS)) {
            switch (rank) {
                case TEN -> power = 13;
                case JACK, QUEEN, KING -> power -= 1;
            }
        } else {
            power = switch (rank) {
                case NINE -> 13;
                case TEN -> 11;
                case JACK -> 14;
                case QUEEN -> 9;
                case KING -> 10;
                case ACE -> 12;
                default -> power;
            };
        }

        if (card.getSuit().getCode() == this.contract.getCode()) {
            power *= 10;
        }

        card.setPower(power);
    }

    private void setCardPoints(Card card) {
        RankEnum rank = card.getRank();

        int points = 0;

        if (card.getSuit().getCode() != this.contract.getCode()) {
            points = switch (rank) {
                case TEN -> 10;
                case JACK -> 2;
                case QUEEN -> 3;
                case KING -> 4;
                case ACE -> 11;
                default -> 0;
            };
        } else {
            points = switch (rank) {
                case NINE -> 14;
                case TEN -> 10;
                case JACK -> 20; //JACk
                case QUEEN -> 3;  //QUEEN
                case KING -> 4; //KING
                case ACE -> 11; //A
                default -> 0;
            };
        }

        card.setPoints(points);
    }


}
