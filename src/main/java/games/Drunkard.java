package games;

public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;

    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36


    public static void main(String... __) {
        System.out.println("Масть 36-й карты - " + getSuit(35));
        System.out.println("Размерность 36-й карты - " + getPar(35));
        System.out.println(toString(35));
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    enum Suit {
        SPADES,
        HEARTS,
        CLUBS,
        DIAMONDS
    }

    enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
}
