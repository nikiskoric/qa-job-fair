package test;

import org.junit.Test;
import cards.*;
import static org.junit.Assert.assertEquals;

public class TestCards {

    @Test
    public void testAttackCardGetter(){
        Card c1 = new AttackCard(3);
        assertEquals(3, c1.getNumber());
        Card c2 = new AttackCard(5);
        assertEquals(5, c2.getNumber());
    }

    @Test
    public void testProtectCardGetter(){
        Card c1 = new ProtectCard();
        assertEquals(1, c1.getNumber());
    }

    @Test
    public void testBoostAttackCardGetters(){
        Card c1 = new BoostAttackCard();
        assertEquals(2, c1.getNumber());
        assertEquals(3, ((BoostAttackCard)c1).getBoost());
    }
}
