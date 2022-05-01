package belot.model.gamemodel;

import java.util.Scanner;

public class BelotInput {
    Scanner scanner = new Scanner(System.in);

    public String suggestInitialContract(String username) {
        System.out.println(username + ", please suggest a contract: ");
        return scanner.nextLine().toUpperCase();
    }

    public String suggestHigherContract(String username, String contract) {
        System.out.println(username + ", please suggest a higher contract. The current contract is: " + contract);
        return scanner.nextLine().toUpperCase();
    }


}
