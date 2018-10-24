package games;

import static java.lang.Math.round;
import static java.lang.Math.random;

public class Slot {

    public static void main(String... __) {
        int initialAccount = 100;
        int betAmount = 10;
        int winAccount = 10000;
        int randNum = 7;
        int firstRoll = 0;
        int secondRoll = 0;
        int thirdRoll = 0;
        do {
            firstRoll = (firstRoll + (int) round(random() * 100)) % randNum;
            secondRoll = (secondRoll + (int) round(random() * 100)) % randNum;
            thirdRoll = (thirdRoll + (int) round(random() * 100)) % randNum;
            System.out.println(String.format("У вас %d, ставка - %d", initialAccount, betAmount));
            System.out.println(String.format("Крутим барабаны! Розыгрыш принёс следующие результаты:\n" +
                    "первый барабан - %d, второй - %d, третий - %d", firstRoll, secondRoll, thirdRoll));
            if ((firstRoll == secondRoll) && (secondRoll == thirdRoll)) {
                initialAccount += winAccount;
                System.out.println(String.format("Выигрыш %d, ваш капитал теперь составляет: %d", winAccount, initialAccount));
            } else {
                initialAccount -= betAmount;
                System.out.println(String.format("Проигрыш %d, ваш капитал теперь составляет: %d", betAmount, initialAccount));
            }
        } while (initialAccount != 0);
    }
}