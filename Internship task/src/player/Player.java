package player;

import java.util.ArrayList;
import java.util.List;
import cards.*;

public class Player {

    private int health;
    private List<Card> hand;
    private List<Card> deck;
    private Card lastPlayedCard;
    private static int initialNumberOfCards = 6;
    private boolean attackingStatus;
    private int damage;

    public Player(int health, List<Card> deck) {
        this.health = health;
        this.deck = deck;
        this.hand = new ArrayList<>();
        lastPlayedCard = null;
        attackingStatus = false;
        damage = 0;
        shuffleDeck();
    }

    public void takeDamage(int amountOfDamage){ // found and fixed bug
        health -= amountOfDamage;
    }

    public boolean getAttackingStatus(){
        return attackingStatus;
    }

    public void resetAttackingStatus(){
        attackingStatus = false;
    }

    public int getDamage(){
        return damage;
    }

    public void resetDamage(){
        damage = 0;
    } // found and fixed bug

    public int getHealth() {
        return health;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public int getInitialNumberOfCards() {
        return initialNumberOfCards;
    }

    public void shuffleDeck() {
        return;
    }

    public void populateDeck(List<Card> cardList) {
        deck.addAll(cardList);
    }

    public int getNumberOfCardsInDeck(){
        return deck.size();
    }

    public int getNumberOfCardsInHand(){
        return hand.size();
    }

    public void drawCard() {
        Card drawnCard = deck.remove(deck.size() - 1);
        hand.add(drawnCard);
    }

    public void drawInitialCards() {
        for (int i = 0; i < initialNumberOfCards; i++) {
            drawCard();
        }
    }

    public void playCard(int cardNumber) {
        Card cardToPlay = null;
        for (Card card : hand) {
            if (card.getNumber() == cardNumber) {
                cardToPlay = card;
                break;
            }
        }
        hand.remove(cardToPlay);
        cardToPlay.effect();

        lastPlayedCard = cardToPlay;

        if(cardToPlay instanceof AttackCard){
            attackingStatus = true;
            damage += cardToPlay.getNumber();
        }
        if(cardToPlay instanceof BoostAttackCard){
            attackingStatus = true;
            damage += ((BoostAttackCard)cardToPlay).getBoost();
        }
    }

    public void playCardInDefense(int cardNumber){
         Card cardToPlay = null;
        for (Card card : hand) {
            if (card.getNumber() == cardNumber) {
                cardToPlay = card;
                break;
            }
        }

        if(cardToPlay != null){
            lastPlayedCard = cardToPlay;
            attackingStatus = true;

            System.out.println(String.format("You've defended yourself! You've been attacked for %d damage and you've used special ability of Attacking card %d to deflect the attack\r\n", cardNumber, cardNumber));
        }
        else {
            System.out.println("You don't have this card in your hand...\r\n");
        }
    }

    public boolean checkForProtectionPossibilitiesInHand(Card lastPlayedCard){
        for (Card card : hand) {
            if (card instanceof ProtectCard || card.getNumber() == lastPlayedCard.getNumber()) {
                return true;
            }
        }
        return false;
    }

    public boolean findNumberInHand(int number) { // found and fixed bug
        for(Card card : hand){
            if(card.getNumber() == number) {
                return true;
            }
        }
        System.out.println(String.format("There is no card in hand with number %d\r\n", number));
        return false;
    }

    public void printHand() {
        System.out.println("Player's Hand:");
        for (Card card : hand) {
            System.out.println(card.getNumber() + "(" + card.description()+ ")\r");
            // You can add additional details about the card if needed
        }
        System.out.println();
    }

    // testCommand functions

    public void testCommandHealth(int h) {
        health = h;
    }

    public void testCommandSetAttackingStatus() {
        attackingStatus = true;
    }

    public void testCommandSetDamage(int d) {
        damage = d;
    }

    public void testCommandDrawCard(int ind) {
        // ind represent index in deck
        Card drawnCard = deck.remove(ind);
        hand.add(drawnCard);
    }

    public void testCommandEmptyHandAndOrDeck(int i) {
        if (i == 0) {
            deck.clear();
        } else {
            deck.clear();
            hand.clear();
        }
    }
}

