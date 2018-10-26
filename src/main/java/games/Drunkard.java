package games;

import static  games.Cards.*;

public class Drunkard {

    private static int[] deck = Cards.getShuffledCards();
    private static int[][] playersCards = new int[2][Cards.CARDS_TOTAL_COUNT];
    private  static int[] cardsInGame = new int[2];
    private static int endCardNumber = Cards.CARDS_TOTAL_COUNT / 2;
    private static int[] beginCardsPointer = {0,0};
    private static int[] endCardsPointer = {endCardNumber,endCardNumber - 1};

    private static void startGame() {
        System.arraycopy(deck, 0 ,playersCards[0], 0, endCardNumber);
        System.arraycopy(deck, endCardNumber, playersCards[1], 0, endCardNumber);
    }

    private static int checkWinCondition(final Par firstCard, final Par secondCard) {
        return (firstCard.compareTo(secondCard) == 0) ? 0 : (firstCard.equals(Par.SIX) && secondCard.equals(Par.ACE)) ? 1 :
                (firstCard.equals(Par.ACE) && secondCard.equals(Par.SIX)) ? -1 : (firstCard.compareTo(secondCard) > 0) ? 1 : -1;
    }

    private static void getTopDeck() {
        cardsInGame[0] = playersCards[0][beginCardsPointer[0]];
        cardsInGame[1] = playersCards[1][beginCardsPointer[1]];
        beginCardsPointer[0] = (beginCardsPointer[0] + 1) % Cards.CARDS_TOTAL_COUNT;
        beginCardsPointer[1] = (beginCardsPointer[1] + 1) % Cards.CARDS_TOTAL_COUNT;
    }

    private static void playCard(final int playerNumber) {
        endCardsPointer[playerNumber] = (endCardsPointer[playerNumber] + 1) % Cards.CARDS_TOTAL_COUNT;
        playersCards[playerNumber][endCardsPointer[playerNumber]] = cardsInGame[0];
        playersCards[playerNumber][endCardsPointer[playerNumber]] = cardsInGame[1];
    }

    private static  void putCardsBack() {
        endCardsPointer[0] = (endCardsPointer[0] + 1) % Cards.CARDS_TOTAL_COUNT;
        playersCards[0][endCardsPointer[0]] = cardsInGame[0];
        endCardsPointer[1] = (endCardsPointer[1] + 1) % Cards.CARDS_TOTAL_COUNT;
        playersCards[1][endCardsPointer[1]] = cardsInGame[1];

    }

    private static void printResult(int iterator) {
        System.out.printf("Итерация №%d Игрок №1 карта: %s; игрок №2 карта: %s.%n", iterator, Cards.toString(cardsInGame[0]), Cards.toString(cardsInGame[1]));
    }

    private static void makeTurn (final int result, int iter) {
        printResult(iter);
        switch (result) {
            case 0:
                System.out.println("Спор - каждый при своих!");
                putCardsBack();
                break;
            case 1:
                System.out.println("Выиграл игрок 1!");
                playCard(0);
                break;
            case -1:
                System.out.println("Выиграл игрок 2!");
                playCard(1);
                break;
        }
    }


    private static int getPlayerCardsCount(final int playerNumber) {
        if (beginCardsPointer[playerNumber] <= endCardsPointer[playerNumber]) {
            return endCardsPointer[playerNumber] - beginCardsPointer[playerNumber] + 1;
        }
        return endCardsPointer[playerNumber] + Cards.CARDS_TOTAL_COUNT - beginCardsPointer[playerNumber] + 1;
    }


    public static void main(String... __) {
        int iterationCount = 1;
        startGame();

        do {
            getTopDeck();
            makeTurn(checkWinCondition(getPar(cardsInGame[0]),getPar(cardsInGame[1])),iterationCount);
            iterationCount += 1;
        }while (getPlayerCardsCount(0) != Cards.CARDS_TOTAL_COUNT && getPlayerCardsCount(1) != Cards.CARDS_TOTAL_COUNT);
        if (getPlayerCardsCount(0) == Cards.CARDS_TOTAL_COUNT) {
            System.out.printf("Победил первый игрок! Количество произведенных итераций: %d.%n",iterationCount);
        } else {
        System.out.printf("Победил второй игрок! Количество произведенных итераций: %d.%n",iterationCount);
        }
    }
}
