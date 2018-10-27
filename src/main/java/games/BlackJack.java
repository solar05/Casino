package games;

import org.slf4j.Logger;

import java.io.IOException;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);
    private static int[] cards;
    private static int cursor;
    private static int[][] playersCards;
    private static int[] playersCursors;
    private static int[] playersMoney = {100, 100};
    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static final int MAX_PLAYER_VALUE = 20;
    private static final int MAX_CPU_VALUE = 17;
    private static int bet = 10;

    private static void initRound() {
        System.out.printf("%nУ вас %d$ у компьютера - %d$. Начинаем новый раунд!%n", playersMoney[0], playersMoney[1]);
        cards = Cards.getShuffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0,0};
        cursor = 0;
    }

    private static void addCard2Player(final int player) {
        playersCards[player][playersCursors[player]] = cards[cursor];
        printCard(player, cards[cursor++]);
        playersCursors[player] += 1;
    }

    private static void printCard (final int player, final int card) {
      if (player == 0) {
          System.out.println("Вам выпала карта " + Cards.toString(card));
      } else {
          System.out.println("Компьютеру выпала карта " + Cards.toString(card));
      }
    }

    static int sum(int player) {
        int sum = 0;
        for (int i = 0; i < playersCursors[player]; i += 1) {
            sum += convertValue(playersCards[player][i]);
        }
        return sum;
    }

    private static int getFinalSum(int player) {
        int playerSum = sum(player);
        return playerSum > MAX_VALUE ? 0 : playerSum;
    }

    private static boolean confirm(String message) throws IOException {
        System.out.println(message + "\"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (Choice.getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }

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
    public static void main(String... __) throws IOException {
        do {
            initRound();
            while (playersCursors[0] < 2 || (sum(0) < MAX_PLAYER_VALUE && confirm("Берем ещё? "))) {
                addCard2Player(0);
            }
            while (playersCursors[1] < 2 || (getFinalSum(0) != 0 && sum(1) < MAX_CPU_VALUE)) {
                addCard2Player(1);
            }
            System.out.printf("Сумма ваших очков - %d, у копмьютера %d%n", getFinalSum(0), getFinalSum(1));
            if (getFinalSum(0) > getFinalSum(1)){
                System.out.printf("Вы выиграли раунд! Получаете %d$%n", bet);
                playersMoney[0] += bet;
                playersMoney[1] -= bet;
            } else if (getFinalSum(0) < getFinalSum(1)) {
                System.out.printf("Вы проиграли раунд! Теряете %d$%n", bet);
                playersMoney[0] -= bet;
                playersMoney[1] += bet;
            } else {
                System.out.println("Ничья! Все остались при своих деньгах.%n");
            }
        }while (playersMoney[0] != 0 && playersMoney[1] != 0);


        if (playersMoney[0] > 0){
            System.out.println("Вы выиграли! Поздравляем!");
        } else {
            System.out.println("Вы проиграли. Соболезнуем...");
        }
    }
}
