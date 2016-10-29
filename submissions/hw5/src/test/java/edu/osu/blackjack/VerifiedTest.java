package edu.osu.blackjack;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import static org.mockito.Mockito.*;

public class VerifiedTest{

    //verify that player and dealer are all dealt two cards from the deck
    @Test
    public void testCorrectNumberOfCardsDealt(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.STAND);

        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(da, times(2)).dealCard(pa1);
        verify(da, times(2)).dealCard(pa2);
        verify(da, times(2)).dealCard(da);
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
    public void testNextHandBeforeBet(){
        Player player1 = new Player();
        player1.nextHand();
        assertTrue(player1.currentBet == null || player1.getCurrentBet() == 0); //null may possible since currentBet starts there
        assertTrue(player1.currentInsurance == null || player1.makeInsuranceBet() == 0); //null may possible since currentInsurance starts there
        assertEquals((long)1000,(long)player1.currentWallet);
    }

}