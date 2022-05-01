package belot.model.playermodel;

import belot.model.cardmodel.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {

    private String username;
    private List<Card> cards;
    private int score;

    public AbstractPlayer(String username) {

        this.username = username;
        this.cards = new ArrayList<>();
        this.score = 0;
    }

    public boolean setCards(List<Card> cards) {
        return this.cards.addAll(cards);
    }

    public boolean setCard(Card cards) {
        return this.cards.add(cards);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int setScore(int points) {
        return this.score += points;
    }

    public int getScore() {
        return this.score;
    }

    public abstract Card drawCard(int code);

    public String getUsername() {
        return username;
    }

    public abstract String declare();
}
