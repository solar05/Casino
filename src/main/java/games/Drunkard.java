package games;

import static  games.Cards.*;
import  org.slf4j.Logger;

public class Drunkard {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);
    private static int[][] playersCards = new int[2][Cards.CARDS_TOTAL_COUNT];
    private  static int[] cardsInGame = new int[2];
    private static int endCardNumber = Cards.CARDS_TOTAL_COUNT / 2;
    private static int[] beginCardsPointer = {0,0};
    private static int[] endCardsPointer = {endCardNumber,endCardNumber - 1};

    private static void startGame() {
        int[] cardsFull = getShuffledCards();
        for (int i = 0; i <= CARDS_TOTAL_COUNT /2 - 1; i++) {
            playersCards[0][i] = cardsFull[i];
            playersCards[1][i] = cardsFull[i + 18];
        }
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
        endCardsPointer[playerNumber] = (endCardsPointer[playerNumber] + 1) % CARDS_TOTAL_COUNT;
        playersCards[playerNumber][endCardsPointer[playerNumber]] = cardsInGame[0];
        endCardsPointer[playerNumber] = (endCardsPointer[playerNumber] + 1) % CARDS_TOTAL_COUNT;
        playersCards[playerNumber][endCardsPointer[playerNumber]] = cardsInGame[1];}

    private static  void putCardsBack() {
        endCardsPointer[0] = (endCardsPointer[0] + 1) % Cards.CARDS_TOTAL_COUNT;
        playersCards[0][endCardsPointer[0]] = cardsInGame[0];
        endCardsPointer[1] = (endCardsPointer[1] + 1) % Cards.CARDS_TOTAL_COUNT;
        playersCards[1][endCardsPointer[1]] = cardsInGame[1];

    }

    private static void printResult(int iterator) {
        log.info("Итерация №{} Игрок №1 карта: {}; игрок №2 карта: {}.", iterator, Cards.toString(cardsInGame[0]), Cards.toString(cardsInGame[1]));
    }

    private static void getRes() {
        log.info("Всего карт: у игрока №1 - {}, у игрока №2 - {}.",getPlayerCardsCount(0),CARDS_TOTAL_COUNT - getPlayerCardsCount(0));
    }

    private static void makeTurn (final int result, int iter) {
        printResult(iter);
        switch (result) {
            case 0:
                log.info("Спор - каждый при своих!");
                putCardsBack();
                break;
            case 1:
                log.info("Выиграл игрок 1!");
                playCard(0);
                break;
            case -1:
                log.info("Выиграл игрок 2!");
                playCard(1);
                break;
        }
    }


    private static int getPlayerCardsCount(final int playerNumber) {
        return beginCardsPointer[playerNumber] <= endCardsPointer[playerNumber] ? endCardsPointer[playerNumber] - beginCardsPointer[playerNumber] + 1 :
                endCardsPointer[playerNumber] + Cards.CARDS_TOTAL_COUNT - beginCardsPointer[playerNumber] + 1;
    }


    public static void main(String... __) {
        int iterationCount = 1;
        startGame();

        do {
            getTopDeck();
            makeTurn(checkWinCondition(getPar(cardsInGame[0]),getPar(cardsInGame[1])), iterationCount);
            iterationCount += 1;
            getRes();
        }while (getPlayerCardsCount(0) != CARDS_TOTAL_COUNT && getPlayerCardsCount(1) != CARDS_TOTAL_COUNT);
        if (getPlayerCardsCount(0) == CARDS_TOTAL_COUNT) {
            log.info("Победил первый игрок! Количество произведенных итераций: ${}", iterationCount);
        } else {
            log.info("Победил второй игрок! Количество произведенных итераций: ${}", iterationCount);
        }
    }
}