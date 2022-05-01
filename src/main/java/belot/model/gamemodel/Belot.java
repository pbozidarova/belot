package belot.model.gamemodel;

import belot.model.cardmodel.Card;
import belot.model.cardmodel.Deck;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.exceptions.NoContractException;
import belot.model.gamemodel.enums.BidEnum;
import belot.model.playermodel.AbstractPlayer;
import belot.model.playermodel.BelotPlayer;
import belot.service.belot.BeloteDrawServiceImpl;
import belot.service.belot.BeloteRuleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Belot extends AbstractGame implements BeloteRuleService {
    private final BelotInput belotInput;

    private static final int CARDS_ABOVE = RankEnum.SEVEN.getCode();
    private static final int MINIMUM_TEAM_POINTS_TO_EXIT_THE_GAME = 151;
    private static final int TOTAL_TRICKS = 8;

    private final Deck deck;
    private BidEnum bid;
    private AbstractPlayer playerDeclaredBid;
    private BidEnum contract;
    private AbstractPlayer playerDeclaredContract;
    private final List<AbstractPlayer> players;
    private BeloteDrawServiceImpl beloteDraw;

    private int scoreTeam1;
    private int scoreTeam2;

    public Belot(BelotPlayer p1, BelotPlayer p2, BelotPlayer p3, BelotPlayer p4, BelotInput belotInput) {
        super();
        this.deck = new Deck(CARDS_ABOVE);
        this.players = new ArrayList<>();

        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        this.players.add(p4);

        this.contract = BidEnum.NO_CONTRACT;
        this.bid = BidEnum.PASS;
        this.belotInput = belotInput;
        this.beloteDraw = new BeloteDrawServiceImpl();
    }

    @Override
    public void dealCardsToEachPlayer(int numberOfCardsToDeal) {
        players.forEach(p -> p.setCards(deck.deal(numberOfCardsToDeal)));
    }

    @Override
    public String play() {

        this.dealCardsToEachPlayer(3);
        this.dealCardsToEachPlayer(2);

        System.out.println(beloteDraw.drawPlayground(players));

        this.bid();

        try {
            if (!contractIsChosen()) {
                throw new NoContractException("No contract has been chosen and thus the game cannot proceed.");
            }
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
            return endGame();
        }

        this.dealCardsToEachPlayer(3);
        this.declare();

        int trickCount = TOTAL_TRICKS;

        while (trickCount-- > 0 && !areMinimumPointsReached()) {
            this.trick();
        }

        return endGame();
    }

    private boolean areMinimumPointsReached() {
        return getScoreTeam1() > MINIMUM_TEAM_POINTS_TO_EXIT_THE_GAME
                || getScoreTeam2() > MINIMUM_TEAM_POINTS_TO_EXIT_THE_GAME;

    }

    private boolean isKapo() {
        return getScoreTeam1() == getScoreTeam2();
    }

    @Override
    public String endGame() {
        StringBuilder gameResult = new StringBuilder();
        gameResult.append("The game has ended!").append(System.lineSeparator());
        gameResult.append("The bid is ").append(this.bid).append(System.lineSeparator());
        gameResult.append("The contract is ").append(this.contract).append(System.lineSeparator());
        gameResult.append("The first team score is ").append(this.scoreTeam1);
        gameResult.append("The second team score is ").append(this.scoreTeam2);

        return  gameResult.toString();
    }

    private boolean contractIsChosen() {
        return this.contract.getCode() > BidEnum.PASS.getCode();
    }

    @Override
    public void score() {
        this.calculateScoreTeam1();
        this.calculateScoreTeam2();
    }

    @Override
    public void bid() {
        int[] passCount = new int[1];

        while (!contractIsChosen() && passCount[0] < 3) {
            passCount[0] = 0;
            this.players.forEach(player -> {
                BidEnum playerBid = singlePlayerBid(player);

                if (playerBid.equals(BidEnum.PASS)) {
                    passCount[0]++;
                }
            });
        }
    }

    private BidEnum singlePlayerBid(AbstractPlayer player) {
        String call = "";
        if (this.contract.getCode() <= BidEnum.PASS.getCode()) {
            call = this.belotInput.suggestInitialContract(player.getUsername());
        } else {
            call = this.belotInput.suggestHigherContract(player.getUsername(), this.contract.toString());
        }
        boolean isCallValid = false;
        String message = "";

        while (!isCallValid) {
            if (BidEnum.valueOf(call) == BidEnum.PASS
                    || (BidEnum.valueOf(call).getCode() > BidEnum.ALL_TRUMPS.getCode() && contractIsChosen())) {

                if (this.bid.getCode() < BidEnum.DOUBLE.getCode() && BidEnum.valueOf(call).equals(BidEnum.RE_DOUBLE)) {
                    message = "Double must be declared before re-double!";
                }

                this.bid = BidEnum.valueOf(call);
                this.playerDeclaredBid = player;
                isCallValid = true;

            } else if (BidEnum.valueOf(call).getCode() > this.contract.getCode()) {
                this.contract = BidEnum.valueOf(call);
                this.playerDeclaredContract = player;
                isCallValid = true;

            } else if (BidEnum.valueOf(call).getCode() < this.contract.getCode()) {
                message = "Please suggest a higher contract!"
                        + "\nThe possible contracts in their increasing order are: \n" + Arrays.toString(BidEnum.values());
            }

            if (!isCallValid) {
                System.out.println(message);
                call = this.belotInput.suggestHigherContract(player.getUsername(), this.contract.toString());
            }
        }

        return BidEnum.valueOf(call);
    }

    @Override
    public void trick() {
        Trick trick = new Trick(this.contract);

        players.forEach(player -> {
            Card card = player.drawCard(this.contract.getCode());
            trick.addCard(card);
        });

        int winningPlayerIndex = trick.indexOfWinningCard();
        int trickScore = trick.calculateScore();

        players.get(winningPlayerIndex).setScore(trickScore);
        score();
    }

    public void declare() {
        this.players.forEach(AbstractPlayer::declare);
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    private void calculateScoreTeam1() {
        this.scoreTeam1 = players.get(0).getScore() + players.get(2).getScore();
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    private void calculateScoreTeam2() {
        this.scoreTeam2 = players.get(1).getScore() + players.get(3).getScore();
    }

    public BidEnum getContract() {
        return contract;
    }

    public BidEnum getBid() {
        return bid;
    }

}
