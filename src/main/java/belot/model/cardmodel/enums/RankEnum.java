package belot.model.cardmodel.enums;

public enum RankEnum {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    JACK(11),
    QUEEN(12),
    KING(13),
    TEN(10),
    ACE(14);

    private final int code;

    public int getCode() {
        return code;
    }

    RankEnum(int code) {
        this.code = code;
    }


    @Override
    public String toString() {
        String s = super.toString();

        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }
}
