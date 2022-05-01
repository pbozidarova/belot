package belot.model.gamemodel.enums;

import belot.model.cardmodel.enums.SuitEnum;

public enum BidEnum {

    NO_CONTRACT(-1),
    PASS(0),
    CLUBS(3),
    DIAMONDS(4),
    HEARTS(5),
    SPADES(6),
    NO_TRUMPS(7),
    ALL_TRUMPS(8),

    DOUBLE(9),
    RE_DOUBLE(10);

    private final int code;

    public int getCode() {
        return code;
    }

    BidEnum(int code) {
        this.code = code;
    }

    public static SuitEnum valueOfAlternate(int code) {
        return SuitEnum.values()[code - 1];
    }

    @Override public String toString() {
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
}
