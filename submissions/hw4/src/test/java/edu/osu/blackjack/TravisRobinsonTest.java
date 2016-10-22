package edu.osu.blackjack;


//import java.util.Random;



//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TravisRobinsonTest{
    /*
    Test Card Class Here
     */

    //test that constructor will build a card with the correct values
    @Test
    public void testCardConstructorOneCard(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        assertEquals(Card.Face.SEVEN.name(),c1.face.name());
        assertEquals(Card.Suit.CLUB.name(),c1.suit.name());

    }

    //test that constructor will build two cards with correct values
    //ie doesn't always jsut build the same card
    @Test
    public void testCardConstructorTwoCard(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        assertFalse(c1.suit.name() == c2.suit.name());
        assertFalse(c1.face.name() == c2.face.name());
    }

    //test that toString will correctly build string
    @Test
    public void testToStringCard(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        String str1 = c1.toString();
        String str2 = c1.face.name() + c1.suit.name();
        assertEquals(str2,str1);//works because strings==objects, else use assertTrue
    }

    //test newDeck will return correct 52 card deck
    @Test
    public void testNewDeckReturnCorrectDeck(){
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

//test dealer class

    //test that new dealer deck is full deck
    @Test
    public void testDealerConstructorProperDeck(){
        Dealer dealer1 = new Dealer();
        ArrayList<Card> deck2 = Card.newDeck();//to ensure that dealer1 deck is complete
            //not used here because dealer1 deck has no return function, even if used in
            //return hand
        fail();//due to not being able to properly access deck
    }

    //test that new dealer hand is empty
    @Test
    public void testDealerConstructorEmptyHand() {
        Dealer dealer1 = new Dealer();
        ArrayList<Card> dealerHand = new ArrayList<Card>(dealer1.getHand());
//        assertTrue(dealerHand.isEmpty());
    }

    //in lieu of proper documentation, assuming that acceptCard is intended to play card from
    //deck into dealer hand, as it makes no sense to play card into deck
    @Test
    public void testAcceptCardActualCard(){
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Dealer dealer1 = new Dealer();
        dealer1.acceptCard(c1);

        assertEquals(1,dealer1.getHand().size());//assert hand size == 1

        Card[] dealerHand = dealer1.getHand().toArray(new Card[0]);
        Card[] expectedDealerHand = new Card[]{c1};

        assertArrayEquals(expectedDealerHand,dealerHand);//assert dealer hand is desired card
    }


    @Test
    public void testAcceptCardNullCard(){
        Card c1 = null;
        Dealer dealer1 = new Dealer();
        dealer1.acceptCard(c1);

  //      assertEquals(0,dealer1.getHand().size());//assert hand size == 1

        Card[] dealerHand = dealer1.getHand().toArray(new Card[0]);
        Card[] expectedDealerHand = new Card[]{c1};

  //      assertArrayEquals(expectedDealerHand,dealerHand);//assert dealer and expectedDealerHand are the same
    }


    @Test
    public void testShuffleDeck(){
        Dealer dealer1 = new Dealer();
        ArrayList<Card> deck2 = Card.newDeck();//to ensure that dealer1 deck is no longer in same order

        dealer1.shuffleDeck();

        fail();//no deck return function-fail due to being unable to verify deck is shuffled
        //assuming that getHand is intended to return the hand and not the deck
    }

    @Test
    public void testDealCardFromDeckTrue(){

        fail();//can't test function due to being private

//        Dealer dealer1 = new Dealer();
//        Card c1 = dealer1.dealCardFromDeck(true);
//        ArrayList<Card> deck2 = Card.newDeck();//to compare first card

//        Card c2 = deck2.get(0);
//        c2.setVisible(true);

//        assertEquals(c2.face.name(),c1.face.name());
//        assertEquals(c2.suit.name(),c1.suit.name());
//        assertEquals(c2.isVisible(),c1.isVisible());
    }

    @Test
    public void testDealCardFromDeckFalse(){

        fail();//can't test function due to being private

//        Dealer dealer1 = new Dealer();
//        Card c1 = dealer1.dealCardFromDeck(false);
//        ArrayList<Card> deck2 = Card.newDeck();//to compare first card

//       Card c2 = deck2.get(0);
//        c2.setVisible(false);

//        assertEquals(c2.face.name(),c1.face.name());
//        assertEquals(c2.suit.name(),c1.suit.name());
//        assertEquals(c2.isVisible(),c1.isVisible());
    }

    @Test
    public void testDealCardTestDeck(){
        Player player1 = new Player();

        Dealer dealer1 = new Dealer();
        dealer1.dealCard(player1);

        //deck1 = get deck from dealer //will fail since we can't get this to verify

        ArrayList<Card> deck2 = Card.newDeck();//to compare decks
        deck2.remove(0);

//        Card[] actualDeck = dealer1.getDeck().toArray(new Card[0]);
        Card[] expectedDeck = deck2.toArray(new Card[0]);

//        assertArrayEquals(expectedDeck,actualDeck);

        fail();//can't test that card is no longer in deck
    }

    @Test
    public void testDealCardTestPlayerHand(){
        Player player1 = new Player();

        Dealer dealer1 = new Dealer();
        dealer1.dealCard(player1);

        ArrayList<Card> deck2 = Card.newDeck();//to compare cards

        Card expectedCard = deck2.remove(0);
        expectedCard.setVisible(true);

        Card[] expectedCardArray = new Card[]{expectedCard};

        Card actualCard = player1.getHand().get(0);
        assertEquals(1,player1.getHand().size());

        assertEquals(expectedCard.isVisible(),actualCard.isVisible());

        Card[] actualCardArray = new Card[]{actualCard};

        assertArrayEquals(expectedCardArray,actualCardArray);

    }

    @Test
    public void testCompareHandAndSettleProperlyReAddedCards(){
        ArrayList<Card> expectedDeck = Card.newDeck();

        Player player1 = new Player();
        Dealer dealer1 = new Dealer();

        player1.makeBet();

        List<Card> dealerHand = new ArrayList<Card>();
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);

        player1.acceptCard(c1);
        player1.acceptCard(c3);

        dealer1.compareHandAndSettle(player1);

        expectedDeck.add(c1);
        expectedDeck.add(c1);
        expectedDeck.add(c2);
        expectedDeck.add(c3);

        fail();//need to compare deck to expectedDeck to ensure cards properly re-added
    }

    @Test
    public void testCompareHandAndSettleVerifyPlayerWalletPlayerWin(){

        Player player1 = new Player();
        Dealer dealer1 = new Dealer();

        player1.makeBet();

        List<Card> dealerHand = new ArrayList<Card>();
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);

        player1.acceptCard(c1);
        player1.acceptCard(c3);

        dealer1.compareHandAndSettle(player1);

        assertEquals((long)player1.currentWallet,(long)1020);//1020 from adding 2*base bet of 10 to base wallet of 1000//error
    }

    @Test
    public void testCompareHandAndSettleVerifyPlayerWalletPlayerLoss(){

        Player player1 = new Player();
        Dealer dealer1 = new Dealer();

        player1.makeBet();

        List<Card> dealerHand = new ArrayList<Card>();
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c3);

        player1.acceptCard(c1);
        player1.acceptCard(c2);

        dealer1.compareHandAndSettle(player1);

        assertEquals((long)player1.currentWallet,(long)990);//990 from placing base bet of 10 from base 1000 wallet and losing
    }

    @Test
    public void testCompareHandAndSettleVerifyPlayerWalletPlayerTie(){

        Player player1 = new Player();
        Dealer dealer1 = new Dealer();

        player1.makeBet();

        List<Card> dealerHand = new ArrayList<Card>();
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);

        player1.acceptCard(c1);
        player1.acceptCard(c2);

        dealer1.compareHandAndSettle(player1);

        assertEquals((long)player1.currentWallet,(long)1000);//1000 from result of push, making player neither win nor lose base wallet of 1000//error
    }

    @Test
    public void testResetTestResetDeck(){
        ArrayList<Card> deck1 = Card.newDeck();

        Dealer dealer1 = new Dealer();
        dealer1.reset();

        fail();//no way to verify that dealer1 has reset the deck
    }

    @Test
    public void testResetTestResetHand(){
        Dealer dealer1 = new Dealer();
        dealer1.reset();

        assertEquals(0,dealer1.getHand().size());
    }

    @Test
    public void testSetDeckNotEmpty(){
        Dealer dealer1 = new Dealer();

        List<Card> testDeck = new ArrayList<Card>();
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        testDeck.add(c1);
        testDeck.add(c2);
        testDeck.add(c3);

        dealer1.setDeck(testDeck);

        fail();//due to no return deck function to test that deck was properly set
    }

    @Test
    public void testSetDeckEmpty(){
        Dealer dealer1 = new Dealer();

        List<Card> testDeck = new ArrayList<Card>();

        dealer1.setDeck(testDeck);

        fail();//due to no return deck function to test that deck was properly set
    }

    @Test
    public void testIsInsuranceAvailableAce(){
        Dealer dealer1 = new Dealer();
        Card c1 = new Card(Card.Face.ACE,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);
        c1.setVisible(true);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);
        dealer1.acceptCard(c3);

        Card[] dealerHand = dealer1.getHand().toArray(new Card[0]);
        boolean insuranceAvailable = false;
        for ( int i = 0; i < dealerHand.length; i++){
            if (dealerHand[i].face.name() == Card.Face.ACE.name() && dealerHand[i].isVisible() == true){
                insuranceAvailable = true;
            }
        }
        assertTrue(insuranceAvailable);
    }

    @Test
    public void testIsInsuranceAvailableNoAce(){
        Dealer dealer1 = new Dealer();
        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);
        c1.setVisible(true);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);
        dealer1.acceptCard(c3);

        Card[] dealerHand = dealer1.getHand().toArray(new Card[0]);
        boolean insuranceAvailable = false;
        for ( int i = 0; i < dealerHand.length; i++){
            if (dealerHand[i].face.name() == Card.Face.ACE.name() && dealerHand[i].isVisible() == true){
                insuranceAvailable = true;
            }
        }
        assertFalse(insuranceAvailable);
    }


    @Test
    public void testIsInsuranceAvailableAceNotVisible(){
        Dealer dealer1 = new Dealer();
        Card c1 = new Card(Card.Face.ACE,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);
        c1.setVisible(false);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);
        dealer1.acceptCard(c3);

        Card[] dealerHand = dealer1.getHand().toArray(new Card[0]);
        boolean insuranceAvailable = false;
        for ( int i = 0; i < dealerHand.length; i++){
            if (dealerHand[i].face.name() == Card.Face.ACE.name() && dealerHand[i].isVisible() == true){
                insuranceAvailable = true;
            }
        }
        assertFalse(insuranceAvailable);

    }

    @Test
    public void testDumpDeck(){
        Dealer dealer1 = new Dealer();
        ArrayList<Card> deck1 = new ArrayList<Card>();

        Card c1 = new Card(Card.Face.ACE,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);

        deck1.add(c1);
        deck1.add(c2);

        String str1 = new String();
        str1 = str1 + "Deck:";

        for ( int i = 0; i < deck1.size(); i++ ){
            str1 = str1 + deck1.get(i).toString() + ";" + "\n" + "";
        }

        dealer1.setDeck(deck1);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        dealer1.dumpDeck(deck1);
        System.out.flush();
        System.setOut(old);

        assertEquals(str1,baos.toString());
    }

    @Test
    public void testGetHandEmptyHand(){//empty because hand should be empty on a fresh dealer
        Dealer dealer1 = new Dealer();
        assertEquals(0,dealer1.getHand().size());
    }

    @Test
    public void testGetHandNotEmptyHand(){
        Dealer dealer1 = new Dealer();

        Card c1 = new Card(Card.Face.ACE,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);

        dealer1.acceptCard(c1);
        dealer1.acceptCard(c2);

        Card[] expectedHand = new Card[]{c1,c2};
        Card[] actualHand = dealer1.getHand().toArray(new Card[0]);

        assertArrayEquals(expectedHand,actualHand);

    }


    //test PlayerAction Class


    @Test
    public void testAcceptCardPlayer(){
        Player player1 = new Player();

        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        player1.acceptCard(c1);

        Card[] expectedHand = new Card[]{c1};
        Card[] actualHand = player1.getHand().toArray(new Card[0]);

        assertArrayEquals(expectedHand,actualHand);
    }

    @Test
    public void testAcceptCardEmptyCard(){
        Player player1 = new Player();

        Card c1 = null;

        player1.acceptCard(c1);

        Card[] expectedHand = new Card[]{c1};
        Card[] actualHand = player1.getHand().toArray(new Card[0]);

        assertArrayEquals(expectedHand,actualHand);
    }

    @Test
    public void testMakeBetCompareCurrentBet(){
        Player player1 = new Player();
        player1.makeBet();
        assertEquals(10,player1.getCurrentBet());
    }

    @Test
    public void testMakeBetTestReturnOfCurrentBet(){
        Player player1 = new Player();
        assertEquals(10,player1.makeBet());
    }

    @Test
    public void testDoubleDownBetBaseBet(){
        Player player1 = new Player();
        player1.makeBet();
        player1.doubleDownBet();
        assertEquals(20,player1.getCurrentBet());
    }

    @Test
    public void testDoubleDownNoBet(){
        Player player1 = new Player();
        player1.doubleDownBet();//error
        assertEquals(0,player1.getCurrentBet());//error
    }

    @Test
    public void testMakeInsuranceBet(){//this function returns currentInsurance--without documentation I will
        //test for that being the intent, though I suspect that is not the case
        Player player1 = new Player();
        assertEquals((long)player1.currentInsurance,(long)player1.makeInsuranceBet());//error
    }

    @Test
    public void testGetAction(){//this function only returns ActionType.STAND--I will test for that being
        //the case because I have no way of setting the action, though I suspect that is not the intent for this function
        Player player1 = new Player();
        assertEquals(player1.getAction().name(),PlayerAction.ActionType.STAND.name());
    }

    @Test
    public void testGetHandWithCard(){
        Player player1 = new Player();

        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);

        player1.acceptCard(c1);

        Card[] expectedHand = player1.currentHand.toArray(new Card[0]);
        Card[] actualHand = player1.getHand().toArray(new Card[0]);

        assertArrayEquals(expectedHand,actualHand);
    }

    @Test
    public void testGetHandWithoutCard(){
        Player player1 = new Player();

        Card[] expectedHand = player1.currentHand.toArray(new Card[0]);
        Card[] actualHand = player1.getHand().toArray(new Card[0]);

        assertArrayEquals(expectedHand,actualHand);
    }

    @Test
    public void testGetCurrentReturnProper(){
        Player player1 = new Player();
        player1.currentBet = 50;
        assertEquals((long)player1.currentBet,(long)player1.getCurrentBet());
    }

    @Test
    public void testGetCurrentBetEmpty(){
        Player player1 = new Player();
       assertEquals(0,player1.getCurrentBet());//error
    }

    @Test
    public void testGetCurrentBetNotEmpty(){
        Player player1 = new Player();
        player1.makeBet();
        assertEquals(10,player1.getCurrentBet());
    }

    @Test
    public void testAcceptMoneyEmpty(){
        Player player1 = new Player();
        int amount = 0;
        player1.acceptMoney(amount);
        assertEquals((long)(1000+amount),(long)player1.currentWallet);//1000 being from base wallet + amount 0 accepted
    }

    @Test
    public void testAcceptMoneyNotEmpty(){
        Player player1 = new Player();
        int amount = 100;
        player1.acceptMoney(amount);
        assertEquals((long)(1000+amount),(long)player1.currentWallet);//1100 being from base wallet + amount 100 accepted
    }

    @Test
    public void testMoveMoneyToInsuranceSomeAmount(){
        int someAmount = 500;
        Player player1 = new Player();
        player1.moveMoneyToInsurance(someAmount);
        assertEquals((long)(1000-someAmount),(long)player1.currentWallet);
        assertEquals((long)someAmount,(long)player1.makeInsuranceBet());
    }

    @Test
    public void testMoveMoneyToInsuranceNoAmount(){
        int noAmount = 0;
        Player player1 = new Player();
        player1.moveMoneyToInsurance(noAmount);
        assertEquals((long)(1000-noAmount),(long)player1.currentWallet);
        assertEquals((long)noAmount,(long)player1.makeInsuranceBet());
    }

    @Test(expected=RuntimeException.class)//assuming the intent is that runtime exception be thrown
    public void testMoveMoneyToInsuranceTooMuchAmount(){
        int tooMuchAmount = 1500;
        Player player1 = new Player();
        player1.moveMoneyToInsurance(tooMuchAmount);
    }


    @Test
    public void testMoveMoneyToBetSomeAmount(){
        int someAmount = 500;
        Player player1 = new Player();
        player1.moveMoneyToBet(someAmount);
        assertEquals((long)(1000-someAmount),(long)player1.currentWallet);
        assertEquals((long)someAmount,(long)player1.getCurrentBet());
    }

    @Test
    public void testMoveMoneyToBetNoAmount(){
        int noAmount = 0;
        Player player1 = new Player();
        player1.moveMoneyToBet(noAmount);
        assertEquals((long)(1000-noAmount),(long)player1.currentWallet);
        assertEquals((long)noAmount,(long)player1.getCurrentBet());
    }

    @Test(expected=RuntimeException.class)//assuming the intent is that runtime exception be thrown
    public void testMoveMoneyToBetTooMuchAmount(){
        int tooMuchAmount = 1500;
        Player player1 = new Player();
        player1.moveMoneyToBet(tooMuchAmount);
    }

    @Test
    public void testNextHandBeforeBet(){
        Player player1 = new Player();
        player1.nextHand();
        assertTrue(player1.currentBet == null || player1.getCurrentBet() == 0); //null may possible since currentBet starts there
        assertTrue(player1.currentInsurance == null || player1.makeInsuranceBet() == 0); //null may possible since currentInsurance starts there
        assertEquals((long)1000,(long)player1.currentWallet);
    }

    @Test
    public void testNextHandAfterBet(){
        Player player1 = new Player();
        player1.makeBet();
        player1.nextHand();
        assertTrue(player1.currentBet == null || player1.getCurrentBet() == 0); //null may possible since currentBet starts there
        assertTrue(player1.currentInsurance == null || player1.makeInsuranceBet() == 0); //null may possible since currentInsurance starts there
        assertEquals((long)1000,(long)player1.currentWallet);
    }

    @Test
    public void testToStringPlayerNoBetNoInsurance(){
        Player player1 = new Player();

        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        player1.acceptCard(c1);
        player1.acceptCard(c2);
        player1.acceptCard(c3);

        String str = new String();
        str = str +"Player: ";
        for (int i = 0; i < player1.getHand().size(); i++ ){
            str = str + player1.getHand().get(i).toString();
        }

        str = str + "w " + player1.currentWallet + "b " + player1.getCurrentBet() + "i " + player1.makeInsuranceBet();//error

        assertEquals(str,player1.toString());
    }

    @Test
    public void testToStringPlayerBetNoInsurance(){
        Player player1 = new Player();

        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        player1.acceptCard(c1);
        player1.acceptCard(c2);
        player1.acceptCard(c3);

        player1.makeBet();

        String str = new String();
        str = str +"Player: ";
        for (int i = 0; i < player1.getHand().size(); i++ ){
            str = str + player1.getHand().get(i).toString();
        }

        str = str + "w " + player1.currentWallet + "b " + player1.getCurrentBet() + "i " + player1.makeInsuranceBet();//error

        assertEquals(str,player1.toString());
    }

    @Test
    public void testToStringPlayerNoBetInsurance(){
        Player player1 = new Player();

        Card c1 = new Card(Card.Face.SEVEN,Card.Suit.CLUB);
        Card c2 = new Card(Card.Face.EIGHT,Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Face.NINE,Card.Suit.HEART);

        player1.acceptCard(c1);
        player1.acceptCard(c2);
        player1.acceptCard(c3);

        player1.moveMoneyToInsurance(100);

        String str = new String();
        str = str +"Player: ";
        for (int i = 0; i < player1.getHand().size(); i++ ){
            str = str + player1.getHand().get(i).toString();
        }

        str = str + "w " + player1.currentWallet + "b " + player1.getCurrentBet() + "i " + player1.makeInsuranceBet();//error

        assertEquals(str,player1.toString());
    }


}