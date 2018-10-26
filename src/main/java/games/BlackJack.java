package games;

import java.io.IOException;

public class BlackJack {
    private static int convertValue(final int card) {
        switch (Cards.getPar(card)) {
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 2;
            case QUEEN: return 3;
            case KING: return 4;
            case ACE:
            default: return 11;
        }
    }
    public static void main(String... __) {
        System.out.println("Hello, World!");
    }
}
