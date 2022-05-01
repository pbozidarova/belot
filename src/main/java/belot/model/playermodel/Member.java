package belot.model.playermodel;

import belot.model.gamemodel.AbstractGame;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String username;
    private List<AbstractGame> gamesPlayed;
    private int rank;

    public Member() {
        this.gamesPlayed = new ArrayList<>();
    }

    public Member(String username) {
        this.gamesPlayed = new ArrayList<>();
        this.username = username;
    }

    public void addPlayedGame(AbstractGame game) {
        this.gamesPlayed.add(game);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }

        Member member = (Member) o;

        return username.equals(member.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
