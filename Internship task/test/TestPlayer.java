package test;

import cards.AttackCard;
import org.junit.Test;
import player.Player;
import utility.Utility;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPlayer {

    @Test
    public void testGetHealth(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        assertEquals(10, player.getHealth());
        player.testCommandHealth(20);
        assertEquals(20, player.getHealth());
    }

    @Test
    public void testTakeDamage(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.takeDamage(1);
        assertEquals(9, player.getHealth());
    }

    @Test
    public void testAttackingStatusGetterAndResetter(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        assertEquals(false, player.getAttackingStatus());
        player.testCommandSetAttackingStatus();
        assertEquals(true, player.getAttackingStatus());
        player.resetAttackingStatus();
        assertEquals(false, player.getAttackingStatus());
    }

    @Test
    public void testDamageGetterAndResetter(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        assertEquals(0, player.getDamage());
        player.testCommandSetDamage(5);
        assertEquals(5, player.getDamage());
        player.resetDamage();
        assertEquals(0, player.getDamage());
    }

    @Test
    public void testDrawInitialCards(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.drawInitialCards();
        assertEquals(6, player.getNumberOfCardsInHand());
        assertEquals(19, player.getNumberOfCardsInDeck());
    }

    @Test
    public void testDrawCard(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.drawCard();
        assertEquals(1, player.getNumberOfCardsInHand());
    }

    @Test
    public void testGettersForOtherFields(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        // initial values
        assertEquals(6, player.getInitialNumberOfCards());
        assertEquals(null, player.getLastPlayedCard());
        assertEquals(25, player.getNumberOfCardsInDeck());
        assertEquals(0, player.getNumberOfCardsInHand());
        // draw attack card
        player.testCommandDrawCard(0);
        assertEquals(1, player.getNumberOfCardsInHand());
        assertEquals(24, player.getNumberOfCardsInDeck());
        // draw protect card and boost card
        player.testCommandDrawCard(10);
        player.testCommandDrawCard(17);
        assertEquals(3, player.getNumberOfCardsInHand());
        assertEquals(22, player.getNumberOfCardsInDeck());
        // play cards
        player.playCard(1);
        assertEquals(2, player.getNumberOfCardsInHand());
        assertEquals(22, player.getNumberOfCardsInDeck());
        assertNotEquals(null, player.getLastPlayedCard());
        player.playCard(2);
        assertEquals(1, player.getNumberOfCardsInHand());
        assertEquals(22, player.getNumberOfCardsInDeck());
        assertNotEquals(null, player.getLastPlayedCard());
        player.playCard(3);
        assertEquals(0, player.getNumberOfCardsInHand());
        assertEquals(22, player.getNumberOfCardsInDeck());
        assertNotEquals(null, player.getLastPlayedCard());
    }

    @Test
    public void testPlayCard(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.drawInitialCards();
        player.drawInitialCards();
        player.drawInitialCards();
        // 10 boost attack cards, 5 protective cards and one of the 6, 7 and 7 attack cards
        assertEquals(18, player.getNumberOfCardsInHand());
        // test protective card
        player.playCard(1);
        assertEquals(1, player.getLastPlayedCard().getNumber());
        // test attack card
        player.playCard(6);
        assertEquals(6, player.getLastPlayedCard().getNumber());
        assertEquals(true, player.getAttackingStatus());
        assertEquals(6, player.getDamage());
        // test boost attack card
        player.playCard(2);
        assertEquals(2, player.getLastPlayedCard().getNumber());
        assertEquals(true, player.getAttackingStatus());
        assertEquals(9, player.getDamage());
    }

    @Test
    public void testFindCardInHand() {
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.testCommandDrawCard(0);
        player.testCommandDrawCard(10);
        player.testCommandDrawCard(17);
        player.printHand();
        // an attack 3 card, a protect card and a boost card
        assertEquals(3, player.getNumberOfCardsInHand());
        assertEquals(true, player.findNumberInHand(3));
        assertEquals(true, player.findNumberInHand(1));
        assertEquals(true, player.findNumberInHand(2));
        assertEquals(false, player.findNumberInHand(5));
    }

    @Test
    public void testCheckPossibilitiesForProtection() {
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.testCommandDrawCard(0);
        // an attack 3 card
        assertEquals(false, player.checkForProtectionPossibilitiesInHand(new AttackCard(4)));

        player.testCommandDrawCard(10);
        // an attack 3 card and a protect card
        assertEquals(true, player.checkForProtectionPossibilitiesInHand(new AttackCard(4)));
        player.playCard(1);

        player.testCommandDrawCard(17);
        // an attack 3 card, a protect card and a boost card
        assertEquals(true, player.checkForProtectionPossibilitiesInHand(new AttackCard(3)));
    }

    @Test
    public void testPlayCardInDefence(){
        new Utility();
        Player player = new Player(10, Utility.generateCards());
        player.playCardInDefense(3);
        assertEquals(null, player.getLastPlayedCard());
        assertEquals(false, player.getAttackingStatus());

        player.testCommandDrawCard(0); // an attack 3 card
        player.playCardInDefense(3);
        assertEquals(3, player.getLastPlayedCard().getNumber());
        assertEquals(true, player.getAttackingStatus());
        assertEquals(10, player.getHealth());
    }
}
