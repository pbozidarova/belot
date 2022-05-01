package belot.model.gamemodel.enums;

import belot.model.exceptions.InvalidDeclaration;

public enum DeclarationEnum {
//    A tierce — a sequence of three (sequences are in the "A K Q J 10 9 8 7" order of the same suit) — is worth 20 points
//    A quarte — a sequence of four — is worth 50 points.
//    A quinte — a sequence of five — is worth 100 points (longer sequences are not awarded, a sequence of eight is counted as a quinte plus a tierce)
//    A carré — 4 of the same rank — of Jacks is worth 200 points.
//    A carré of Nines is worth 150 points.
//    A carré of Aces, Kings, Queens, or Tens is worth 100 points. (Sevens and Eights are not awarded.)

    TIERCE(20), QUARTE(50), QUINTE(100), CARRE9(150), CARREJ(200), CARRE(100), BELOTE(20);

    private final int code;

    public int getCode() {
        return code;
    }

    DeclarationEnum(int code) {
        this.code = code;
    }

    public static String valueOf(int code) {
        for (DeclarationEnum declaration: DeclarationEnum.values()) {
            if (declaration.getCode() == code) {
                return declaration.toString();
            }
        }
        throw new InvalidDeclaration("INVALID CODE");
    }


    @Override public String toString() {
        String s = super.toString();

        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
}
