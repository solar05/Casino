package games;

import static org.apache.commons.math3.util.MathArrays.shuffle;


public class Cards {
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


    static final int PARS_TOTAL_COUNT = Par.values().length;

    static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    static int[] getShuffledCards () {
        int[] cards = new int[CARDS_TOTAL_COUNT];
        for (int i = 0; i < CARDS_TOTAL_COUNT; i += 1) {
            cards[i] = i;
        }
        shuffle(cards);
        return cards;
    }
}
