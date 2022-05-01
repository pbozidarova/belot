package belot.model.gamemodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import belot.model.cardmodel.Card;
import belot.model.cardmodel.enums.RankEnum;
import belot.model.cardmodel.enums.SuitEnum;
import belot.model.exceptions.NoContractException;
import belot.model.gamemodel.enums.BidEnum;
import belot.model.playermodel.BelotPlayer;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BelotTest {
    Belot belotWithMockedPlayersAndInput;
    Belot belotWithMockedInput;
    BelotInput belotInputMock;
    BelotPlayer belotPlayer1Mock;
    BelotPlayer belotPlayer2Mock;
    BelotPlayer belotPlayer3Mock;
    BelotPlayer belotPlayer4Mock;

    BelotPlayer belotPlayer1;
    BelotPlayer belotPlayer2;
    BelotPlayer belotPlayer3;
    BelotPlayer belotPlayer4;

    @BeforeEach
    void setup() {
        this.belotPlayer1Mock = Mockito.mock(BelotPlayer.class);
        this.belotPlayer2Mock  = Mockito.mock(BelotPlayer.class);
        this.belotPlayer3Mock  = Mockito.mock(BelotPlayer.class);
        this.belotPlayer4Mock  = Mockito.mock(BelotPlayer.class);

        belotInputMock = Mockito.mock(BelotInput.class);
        this.belotWithMockedPlayersAndInput = new Belot(belotPlayer1Mock, belotPlayer2Mock, belotPlayer3Mock, belotPlayer4Mock, belotInputMock);

        this.belotPlayer1 = new BelotPlayer("user1");
        this.belotPlayer2 = new BelotPlayer("user2");
        this.belotPlayer3 = new BelotPlayer("user3");
        this.belotPlayer4 = new BelotPlayer("user4");

        this.belotWithMockedInput = new Belot(belotPlayer1, belotPlayer2, belotPlayer3, belotPlayer4, belotInputMock);
    }

    @Test
    public void dealCardsToEachPlayerTest() {
        int beforeDealingCardsSize = this.belotPlayer1.getCards().size();
        this.belotWithMockedInput.dealCardsToEachPlayer(2);
        int afterDealingCardsSize = this.belotPlayer1.getCards().size();

        Assertions.assertEquals(2, afterDealingCardsSize - beforeDealingCardsSize);
    }

    @Test
    public void bidTestWrongInitialContractThrowNoSuchElementException() {
        when(belotInputMock.suggestInitialContract(anyString())).thenReturn("TEST");

        Assertions.assertThrows(IllegalArgumentException.class, () -> belotWithMockedInput.bid());
    }

    @Test
    public void bidTestWrongHigherContractThrowNoSuchElementException() {
        when(belotInputMock.suggestInitialContract(anyString())).thenReturn("HEARTS");
        when(belotInputMock.suggestHigherContract(anyString(), anyString())).thenReturn("TEST");

        Assertions.assertThrows(IllegalArgumentException.class, () -> belotWithMockedInput.bid());
    }


    @Test
    public void belotPlayExitsWithNoContractChosen() {
        when(belotInputMock.suggestInitialContract(anyString())).thenReturn(BidEnum.PASS.toString().toUpperCase());

        Assertions.assertThrows(NoContractException.class, () -> belotWithMockedInput.play());
    }


    @Test
    public void bidTestCorrectContract() {
        when(belotInputMock.suggestInitialContract(anyString())).thenReturn("HEARTS");
        when(belotInputMock.suggestHigherContract(anyString(), anyString())).thenReturn("PASS");

        belotWithMockedInput.bid();
        Assertions.assertEquals(belotWithMockedInput.getContract(), BidEnum.HEARTS);
    }

    @Test
    public void bidTestCorrectBidDOUBLE() {
        when(belotInputMock.suggestInitialContract(anyString())).thenReturn("HEARTS");
        when(belotInputMock.suggestHigherContract(anyString(), anyString())).thenReturn("DOUBLE");

        belotWithMockedInput.bid();
        Assertions.assertEquals(belotWithMockedInput.getBid(), BidEnum.DOUBLE);
    }

    @Test
    public void belotPlayWithFirstTeamWinner() {

        when(belotPlayer1Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.CLUBS, RankEnum.ACE));
        when(belotPlayer2Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.HEARTS, RankEnum.KING));
        when(belotPlayer3Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.CLUBS, RankEnum.JACK));
        when(belotPlayer4Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.CLUBS, RankEnum.NINE));

        when(belotPlayer1Mock.getScore()).thenReturn(15);
        when(belotPlayer2Mock.getScore()).thenReturn(1);

        belotWithMockedPlayersAndInput.trick();

        //most likely compare belot play exit string
        Assertions.assertTrue(belotWithMockedPlayersAndInput.getScoreTeam1() > belotWithMockedPlayersAndInput.getScoreTeam2());
    }

    @Test
    public void belotPlayWithSecondTeamWinner() {

        when(belotPlayer1Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.CLUBS, RankEnum.ACE));
        when(belotPlayer2Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.HEARTS, RankEnum.KING));
        when(belotPlayer3Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.CLUBS, RankEnum.JACK));
        when(belotPlayer4Mock.drawCard(anyInt())).thenReturn(new Card(SuitEnum.CLUBS, RankEnum.NINE));

        when(belotPlayer1Mock.getScore()).thenReturn(1);
        when(belotPlayer2Mock.getScore()).thenReturn(15);

        belotWithMockedPlayersAndInput.trick();

        //most likely compare belot play exit string
        Assertions.assertTrue(belotWithMockedPlayersAndInput.getScoreTeam1() < belotWithMockedPlayersAndInput.getScoreTeam2());
    }

}