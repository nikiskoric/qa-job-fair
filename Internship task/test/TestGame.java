package test;

import cards.Card;
import game.Game;
import help.HelpInputProvider;
import org.junit.Test;
import player.Player;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGame {
    @Test
    public void testIsGameEnded() {
        new Utility();
        List<Card> deck = Utility.generateCards();
        Player player1 = new Player(20, deck);
        Player player2 = new Player(20, deck);
        Game game = new Game(player1, player2);

        assertEquals(false, game.getGameEnded());
        game.testCommandForGameEnded(true);
        assertEquals(true, game.getGameEnded());
        game.testCommandForGameEnded(false);
        assertEquals(false, game.getGameEnded());
    }

    @Test
    public void testHasHealth() {
        new Utility();
        List<Card> deck = Utility.generateCards();
        Player player1 = new Player(20, deck);
        Player player2 = new Player(20, deck);
        Game game = new Game(player1, player2);

        boolean b = game.testCommandForHasHealth(0);
        assertEquals(false, b);
        b = game.testCommandForHasHealth(10);
        assertEquals(true, b);
        b = game.testCommandForHasHealth(-10);
        assertEquals(false, b);
    }

    @Test
    public void testIsHandEmpty() {
        new Utility();
        List<Card> deck = Utility.generateCards();
        Player player1 = new Player(20, deck);
        Player player2 = new Player(20, deck);
        Game game = new Game(player1, player2);

        boolean b = game.testCommandHandEmpty(0); // hand is empty
        assertEquals(true, b);
        b = game.testCommandHandEmpty(1); // player draws a card
        assertEquals(false, b);
        b = game.testCommandHandEmpty(2); // player plays a card, hand is empty again
        assertEquals(true, b);
    }

    @Test
    public void testIsPlayerWithoutOptionsToPlay() {
        new Utility();
        List<Card> deck = Utility.generateCards();
        Player player1 = new Player(20, deck);
        Player player2 = new Player(20, deck);
        Game game = new Game(player1, player2);

        boolean b = game.testCommandIsPlayerWithoutOptionsToPlay(0); // hand is empty, deck not
        assertEquals(false, b);
        b = game.testCommandIsPlayerWithoutOptionsToPlay(1); // player draws a card
        assertEquals(false, b);
        b = game.testCommandIsPlayerWithoutOptionsToPlay(2); // deck is empty, hand not
        assertEquals(false, b);
        b = game.testCommandIsPlayerWithoutOptionsToPlay(3); // deck and hand are empty
        assertEquals(true, b);
    }

    @Test
    public void testGetPlayerDistinctInput() {
        new Utility();
        List<Integer> cards = new ArrayList<>();
        cards.add(4);
        List<Card> deck = Utility.testCommandGenerateCards(cards);
        Player player1 = new Player(20, deck);
        Player player2 = new Player(20, deck);
        player1.drawCard();
        Game game = new Game(player1, player2);

        HelpInputProvider inputProvider = new HelpInputProvider("none", "take", "1", "4");

        // case 1
        assertEquals("take", game.testCommandGetPlayersDistinctInput(3, player1, inputProvider));
        // case 2
        assertEquals("1", game.testCommandGetPlayersDistinctInput(3, player1, inputProvider));
        // case 3
        assertEquals("4", game.testCommandGetPlayersDistinctInput(4, player1, inputProvider));
    }

    @Test
    public void testTryToDefend() {
        new Utility();
        List<Integer> cards = new ArrayList<>();
        cards.add(4);
        cards.add(1);
        List<Card> deck = Utility.testCommandGenerateCards(cards);
        Player player1 = new Player(20, deck);
        deck = Utility.testCommandGenerateCards(cards);
        Player player2 = new Player(20, deck);
        player1.drawCard();
        player1.drawCard();
        player2.drawCard();
        player2.drawCard();
        Game game = new Game(player1, player2);

        // both players have one attack 4 card and one protect card in hand
        HelpInputProvider inputProvider = new HelpInputProvider("none", "take", "1", "4");

        // case 1 -- player takes damage
        player2.testCommandSetDamage(4);
        game.testCommandTryToDefend(player1, player2, inputProvider);
        assertEquals(16, player1.getHealth());
        // case 2 -- player uses protect card
        game.testCommandTryToDefend(player1, player2, inputProvider);
        assertEquals(16, player1.getHealth());
        // case 3 -- player uses attack card for defence
        game.testCommandTryToDefend(player1, player2, inputProvider);
        assertEquals(16, player1.getHealth());
    }

    @Test
    public void testCurrentPlayerUnderAttack() {
        new Utility();
        List<Integer> cards = new ArrayList<>();
        cards.add(4);
        cards.add(1);
        List<Card> deck = Utility.testCommandGenerateCards(cards);
        Player player1 = new Player(20, deck);
        deck = Utility.testCommandGenerateCards(cards);
        Player player2 = new Player(20, deck);
        player2.drawCard();
        player2.drawCard();
        Game game = new Game(player1, player2);

        // player2 has one attack 4 card and one protect card in hand
        HelpInputProvider inputProvider = new HelpInputProvider("1", "4");

        // case 1 -- player1 takes damage
        player2.testCommandSetDamage(4);
        game.testCommandCurrentPlayerUnderAttack(player1, player2, inputProvider);
        assertEquals(16, player1.getHealth());
        // case 2 -- player1 can defend himself
        player1.drawCard();
        player1.drawCard();
        game.testCommandCurrentPlayerUnderAttack(player1, player2, inputProvider);
        assertEquals(16, player1.getHealth());
    }

}
