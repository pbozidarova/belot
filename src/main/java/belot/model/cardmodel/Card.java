package belot.model.cardmodel;

import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;

public class Card {

    private RankEnum rank;
    private SuitEnum suit;
    private int power;
    private int points;

    public Card(SuitEnum suit, RankEnum rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(SuitEnum suit, RankEnum rank, int power, int points) {
        this.rank = rank;
        this.suit = suit;
        this.power = power;
        this.points = points;
    }

    public RankEnum getRank() {
        return rank;
    }

    public SuitEnum getSuit() {
        return suit;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Card)) {
            return false;
        }

        Card card = (Card) o;

        if (rank != card.rank) {
            return false;
        }

        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (suit != null ? suit.hashCode() : 0);
        return result;
    }
}
