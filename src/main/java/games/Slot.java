package games;

import static java.lang.Math.round;
import static java.lang.Math.random;

public class Slot {

    public static void main(String... __) {
        int initialAccount = 100;
        int betAmount = 10;
        int winAccount = 1_000;
        int randNum = 7;
        int firstRoll = 0;
        int secondRoll = 0;
        int thirdRoll = 0;
        do {
            firstRoll = (firstRoll + (int) round(random() * 100)) % randNum;
            secondRoll = (secondRoll + (int) round(random() * 100)) % randNum;
            thirdRoll = (thirdRoll + (int) round(random() * 100)) % randNum;
            System.out.format("У вас %d, ставка - %d\n", initialAccount, betAmount);
            System.out.format("Крутим барабаны! Розыгрыш принёс следующие результаты:\n" +
                    "первый барабан - %d, второй - %d, третий - %d\n", firstRoll, secondRoll, thirdRoll);
            if ((firstRoll == secondRoll) && (secondRoll == thirdRoll)) {
                initialAccount += winAccount;
                System.out.format("Выигрыш %d, ваш капитал теперь составляет: %d\n", winAccount, initialAccount);
            } else {
                initialAccount -= betAmount;
                System.out.format("Проигрыш %d, ваш капитал теперь составляет: %d\n", betAmount, initialAccount);
            }
        } while (initialAccount != 0);
    }
}