package test;

import cards.Card;
import game.Game;
import org.junit.Test;
import player.Player;
import utility.Utility;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    }

}
