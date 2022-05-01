package belot.model.gamemodel;

import belot.model.playermodel.Member;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Casino {

    List<AbstractGame> games;
    Set<Member> members;

    public Casino() {
        this.games = new ArrayList<>();
        this.members = new HashSet<>();
    }

    public void chooseGame(AbstractGame game) {

    }
}
