package belot.service;

public interface RuleService {

    void dealCardsToEachPlayer(int countOfCardsToDeal);

    String play();
    void score();
    String endGame();
}
