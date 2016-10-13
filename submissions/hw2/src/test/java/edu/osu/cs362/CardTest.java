package edu.osu.cs362;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class CardTest  {
    //test that equals() will return true when sent itself or a card with same value
    //test that equals() returns false when sent a card with different value or a non-card type

    @Test
    public void testCardConstructorBuildsCorrectly(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);

        //test that constructor provides correct construction
        assertEquals(Card.Face.SEVEN.name(),c1.face.name());
        assertEquals(Card.Suit.CLUB.name(),c1.suit.name());
    }

    @Test
    public void testCardConstructorBuildsConsistently(){
        //test that constructor will consistently build correctly
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        assertEquals(c2.face.name(),c1.face.name());
        assertEquals(c2.suit.name(),c1.suit.name());
    }

    @Test
    public void testCardConstructorBuildsDifferently(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c3 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        assertFalse(c1.suit.name() == c3.suit.name());
        assertFalse(c1.face.name() == c3.face.name());
    }

    @Test
    public void testEqualsVsSelf(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        assertTrue(c1.equals(c1));
    }

    @Test
    public void testEqualsVsNotSame(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.ACE,Card.Suit.DIAMOND);
        assertFalse(c1.equals(c2));
    }

    @Test
    public void testEqualsVsNotCard(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        assertFalse(c1.equals(7));
    }

    @Test
    public void testEqualsVsSame(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c3 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        assertTrue(c1.equals(c3));
    }

    @Test
    public void testToString(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        String str1 = c1.toString();
        String str2 = c1.face.name() + c1.suit.name();
        assertEquals(str2,str1);//works because strings==objects, else use assertTrue
    }

    @Test
    public void testNewDeckReturnsSameDeck(){
        ArrayList<Card> deck1 = Card.newDeck();
        ArrayList<Card> deck2 = Card.newDeck();

        Card[] deckArr1 = deck1.toArray(new Card[0]);
        Card[] deckArr2 = deck2.toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);

    }

    @Test
    public void testNewDeckReturnsCorrectDeck(){
        ArrayList<Card> deck1 = Card.newDeck();
        ArrayList<Card> deck2 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck2.add(new Card(face,suit));
            }
        }

        Card[] deckArr1 = deck1.toArray(new Card[0]);
        Card[] deckArr2 = deck2.toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    @Test
    public void testIsVisibleTrue(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        c1.setVisible(true);
        assertTrue(c1.isVisible());
    }

    @Test
    public void testIsVisibleFalse(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        c1.setVisible(false);
        assertFalse(c1.isVisible());
    }
}
