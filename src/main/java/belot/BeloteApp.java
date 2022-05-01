package belot;

import belot.model.gamemodel.Belot;
import belot.model.gamemodel.BelotInput;
import belot.model.playermodel.BelotPlayer;

/**
 *
 *
 * @author Petya
 */
@SuppressWarnings("PMD")
public class BeloteApp {

    /**
     * Starting point of the program
     */
    public static void main(String[] args) {


        BelotPlayer player1 = new BelotPlayer("user1");
        BelotPlayer player2  = new BelotPlayer("user2");
        BelotPlayer player3  = new BelotPlayer("user3");
        BelotPlayer player4  = new BelotPlayer("user4");
        BelotInput belotInput = new BelotInput();

        Belot belot = new Belot(player1, player2, player3, player4, belotInput);

        System.out.println(belot.play());

    }

}
