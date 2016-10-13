package edu.osu.cs362;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class CardCollectionTest {

    //ensures that when passed nothing constructor will create empty collections
    @Test
    public void testCardCollectionConstructorEmpty() {

        CardCollection collection1 = new CardCollection();
        List<Card> deck2 = new ArrayList<Card>();
        Card[] deckArr2 = deck2.toArray(new Card[0]);

        List<Card> deck1 = collection1.getCards();
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    //ensures constructor will correctly create the collection and return it in the
    //correct order
    @Test
    public void testCardCollectionConstructorCorrectlyConstructs() {
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c3 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c4 = new Card(Card.Face.ACE,Card.Suit.HEART);
        Card c5 = new Card(Card.Face.QUEEN,Card.Suit.SPADE);

        CardCollection collection1 = new CardCollection(c1,c2,c3,c4,c5);
        List<Card> deck2 = new ArrayList<Card>();
        deck2.add(c1);
        deck2.add(c2);
        deck2.add(c3);
        deck2.add(c4);
        deck2.add(c5);
        Card[] deckArr2 = deck2.toArray(new Card[0]);

        List<Card> deck1 = collection1.getCards();
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    //ensures that card collection constructor is consistent in construction,
    //ie if given the same inputs will produce the same output
    @Test
    public void testCardCollectionConstructorConsistentlyConstructs(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c3 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c4 = new Card(Card.Face.ACE,Card.Suit.HEART);
        Card c5 = new Card(Card.Face.QUEEN,Card.Suit.SPADE);

        CardCollection collection1 = new CardCollection(c1,c2,c3,c4,c5);
        Card[] deckArr1 = collection1.getCards().toArray(new Card[0]);

        CardCollection collection2 = new CardCollection(c1,c2,c3,c4,c5);
        Card[] deckArr2 = collection2.getCards().toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    //ensures that the add function is capable of adding a card correctly
    @Test
    public void testAddCardOneCardSame(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);

        CardCollection collection1 = new CardCollection();
        CardCollection collection2 = new CardCollection();

        collection1.add(c1);
        collection2.add(c1);

        Card[] deckArr1 = collection1.getCards().toArray(new Card[0]);
        Card[] deckArr2 = collection2.getCards().toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    //ensures that the add function doesn't simply add the same card repeatedly
    @Test
    public void testAddCardOneCardDifferent(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);

        CardCollection collection1 = new CardCollection();
        CardCollection collection2 = new CardCollection();

        collection1.add(c1);
        collection2.add(c2);

        Card[] deckArr1 = collection1.getCards().toArray(new Card[0]);
        Card[] deckArr2 = collection2.getCards().toArray(new Card[0]);

        assertFalse(deckArr1 == deckArr2);
    }

    //ensures that card collection gan have multiple cards added to it, one at time
    //makes sure that card collection doesn't have cap that will limits addition of cards
    //or overwrites cards after a certain number are added

    //could have tested above add method for single card for invalid input, but comiler will catch that


    @Test
    public void testAddCardListOneAtTime(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        Card[] deckArr1 = deck1.toArray(new Card[0]);

        CardCollection collection1 = new CardCollection();
        for ( int i = 0; i < deck1.size(); i++ ){
            collection1.add(deck1.get(i));
        }
        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    //ensures that when given an empty list the list adder won't add anything
    @Test
    public void testAddEmptyList(){
        ArrayList<Card> deck1 = new ArrayList<Card>();
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);
        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    @Test
    public void testAddCardWholeList(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        Card[] deckArr1 = deck1.toArray(new Card[0]);

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);

        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        assertArrayEquals(deckArr1,deckArr2);
    }

    @Test
    public void testAddCardWholeListChange1(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        Card[] deckArr1 = deck1.toArray(new Card[0]);
        deckArr1[0] = deckArr1[deckArr1.length-1];//change first element to last for testing negative

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);

        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        assertFalse(deckArr1 == deckArr2);
    }

    @Test
    public void testDiscardCardEmpty(){
        CardCollection collection1 = new CardCollection();

        collection1.discardCard(0);
        //Card[] deckArr1 = collection1.getCards().toArray(new Card[0]);

        assertTrue(collection1.getCards().isEmpty());
    }

    @Test
    public void testDiscardNoDuplicateMiddle(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);

        deck1.remove(10);
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        collection1.discardCard(10);
        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        assertEquals(deckArr1,deckArr2);
    }

    @Test
    public void testDiscardNoDuplicateLast(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);

        collection1.discardCard(deck1.size()-1);
        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        deck1.remove(deck1.size()-1);
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        assertEquals(deckArr1,deckArr2);
    }

    @Test
    public void testDiscardCardADuplicateMiddle(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);

        deck1.remove(10);
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        collection1.discardCard(10);
        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        assertEquals(deckArr1,deckArr2);
    }

    @Test
    public void testDiscardCardADuplicateLast(){
        ArrayList<Card> deck1 = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        for ( Card.Suit suit : Card.Suit.values()){
            for ( Card.Face face : Card.Face.values()){
                deck1.add(new Card(face,suit));
            }
        }

        CardCollection collection1 = new CardCollection();
        collection1.add(deck1);

        collection1.discardCard(deck1.size()-1);
        Card[] deckArr2 = collection1.getCards().toArray(new Card[0]);

        deck1.remove(deck1.size()-1);
        Card[] deckArr1 = deck1.toArray(new Card[0]);

        assertEquals(deckArr1,deckArr2);
    }

}