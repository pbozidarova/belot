package belot.model.cardmodel.enums;

import belot.model.exceptions.InvalidSuit;

public enum SuitEnum {
    CLUBS(3),
    DIAMONDS(4),
    HEARTS(5),
    SPADES(6);

    private final int code;

    public int getCode() {
        return code;
    }

    SuitEnum(int code) {
        this.code = code;
    }


    public static String valueOf(int code) {
        for (SuitEnum suit: SuitEnum.values()) {
            if (suit.getCode() == code) {
                return suit.toString();
            }
        }
        throw new InvalidSuit("INVALID CODE");
    }

    @Override public String toString() {
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
}