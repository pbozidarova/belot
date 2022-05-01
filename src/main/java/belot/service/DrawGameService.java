package belot.service;

import belot.model.playermodel.AbstractPlayer;

import java.util.List;

public interface DrawGameService {
    String drawPlayground(List<AbstractPlayer> players);

    String drawStage();

    String drawScore();
}
