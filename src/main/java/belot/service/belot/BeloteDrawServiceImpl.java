package belot.service.belot;

import belot.model.playermodel.AbstractPlayer;
import belot.service.DrawGameService;

import java.util.List;

public class BeloteDrawServiceImpl implements DrawGameService {

    public BeloteDrawServiceImpl() {
    }

    @Override
    public String drawPlayground(List<AbstractPlayer> players) {
        StringBuilder usersWithCards = new StringBuilder();

        usersWithCards.append(" ".repeat(15));
        usersWithCards.append(" ".repeat(45));
        usersWithCards.append(players.get(0).getUsername()).append(System.lineSeparator());
        usersWithCards.append(" ".repeat(45));
        players.get(0).getCards().forEach(card -> {
            usersWithCards.append(Character.toChars(card.getSuit().getCode()))
                    .append(" ")
                    .append(card.getRank())
                    .append(" |");;
        });
        usersWithCards.append(System.lineSeparator());

        usersWithCards.append(" ".repeat(15));
        usersWithCards.append(players.get(1).getUsername());
        usersWithCards.append(" ".repeat(15));
        usersWithCards.append(" ".repeat(65));
        usersWithCards.append(players.get(2).getUsername()).append(System.lineSeparator());
        players.get(1).getCards().forEach(card -> {
            usersWithCards.append(Character.toChars(card.getSuit().getCode()))
                    .append(" ")
                    .append(card.getRank())
                    .append(" |");;
        });
        usersWithCards.append(" ".repeat(50));
        players.get(2).getCards().forEach(card -> {
            usersWithCards.append(Character.toChars(card.getSuit().getCode()))
                    .append(" ")
                    .append(card.getRank())
                    .append(" |");;
        });
        usersWithCards.append(System.lineSeparator());

        usersWithCards.append(" ".repeat(15));
        usersWithCards.append(" ".repeat(45));
        usersWithCards.append(players.get(3).getUsername()).append(System.lineSeparator());
        usersWithCards.append(" ".repeat(45));
        players.get(3).getCards().forEach(card -> {
            usersWithCards.append(Character.toChars(card.getSuit().getCode()))
                    .append(" ")
                    .append(card.getRank())
                    .append(" |");;
        });

        return usersWithCards.toString();
    }


    @Override
    public String drawStage() {
        return null;
    }

    @Override
    public String drawScore() {
        return null;
    }
}
