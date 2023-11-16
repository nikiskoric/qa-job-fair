package utility;

import java.util.ArrayList;
import java.util.List;

import cards.*;

public class Utility {
    public static List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();

        // Generate AttackCards (from 3 to 7)
        for (int i = 3; i <= 7; i++) {
            cards.add(new AttackCard(i));
            cards.add(new AttackCard(i));
        }

        // Generate 5 ProtectCards
        for (int i = 0; i < 5; i++) {
            cards.add(new ProtectCard()); // Using a higher ID range to distinguish from AttackCards
        }
        // Generate 10 BoostAttackCards
        for (int i = 0; i < 10; i++) {
            cards.add(new BoostAttackCard()); // Using a higher ID range to distinguish from other cards
        }

        return cards;
    }

    // test commands

    public static List<Card> testCommandGenerateCards(List<Integer> numbers) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i) == 1) { // protect card
                cards.add(new ProtectCard());
            } else if(numbers.get(i) == 2) { // boost attack card
                cards.add(new BoostAttackCard());
            } else { // attack card
                cards.add(new AttackCard(numbers.get(i)));
            }
        }

        return cards;
    }
}
