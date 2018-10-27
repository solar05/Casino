package games;

import org.slf4j.Logger;

import static java.lang.Math.round;
import static java.lang.Math.random;

public class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);
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
            log.info("У вас {}, ставка - {}", initialAccount, betAmount);
            log.info("Крутим барабаны! Розыгрыш принёс следующие результаты:" +
                    "первый барабан - {}, второй - {}, третий - {}", firstRoll, secondRoll, thirdRoll);
            if ((firstRoll == secondRoll) && (secondRoll == thirdRoll)) {
                initialAccount += winAccount;
                log.info("Выигрыш {}, ваш капитал теперь составляет: {}", winAccount, initialAccount);
            } else {
                initialAccount -= betAmount;
                log.info("Проигрыш {}, ваш капитал теперь составляет: {}", betAmount, initialAccount);
            }
        } while (initialAccount != 0);
    }
}