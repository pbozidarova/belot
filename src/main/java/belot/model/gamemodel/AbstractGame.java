package belot.model.gamemodel;

import belot.model.playermodel.AbstractPlayer;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGame {
    private int id;
    private Set<AbstractPlayer> players;

    public AbstractGame() {
        this.players = new HashSet<>();
    }


}
