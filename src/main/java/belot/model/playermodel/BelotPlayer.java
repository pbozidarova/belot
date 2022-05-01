package belot.model.playermodel;

import belot.model.cardmodel.Card;
import belot.model.gamemodel.Declarations;

import java.util.List;
import java.util.stream.Collectors;

public class BelotPlayer extends AbstractPlayer {
    private Declarations declarations;
    private int score;

    public BelotPlayer(String username) {
        super(username);
        this.declarations = new Declarations();
    }

    @Override
    public String declare() {
        return this.declarations.scanForDeclarations(this.getCards());
    }

    @Override
    public List<Card> getCards() {
        return super.getCards().stream()
                .sorted((a, b) -> a.getRank().getCode() - a.getRank().getCode())
                .collect(Collectors.toList());
    }

    @Override
    public int setScore(int points) {
        return this.score += points;
    }

    public int getScore() {
        return this.score;
    }


    @Override
    public Card drawCard(int code) {
        //TODO allocate correct card;
        return this.getCards().get(0);
    }
}
