package edu.osu.blackjack;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import static org.mockito.Mockito.*;

public class TravisRobinsonSimpleBlackJackMockTest{

    //verify that all players make initial bet
    @Test
    public void testAllPlayersMakeBet(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);
        PlayerAction pa3 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2,pa3};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.STAND);
        when(pa3.getAction()).thenReturn(PlayerAction.ActionType.STAND);

        when(pa1.makeBet()).thenReturn(10);
        when(pa2.makeBet()).thenReturn(10);
        when(pa3.makeBet()).thenReturn(10);

        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(pa1, times(1)).makeBet();
        verify(pa2, times(1)).makeBet();
        verify(pa3, times(1)).makeBet();
    }

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

    //verify that players don't make insurance when not allowed
    @Test
    public void testNoInsuranceBetsMade(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.STAND);

        when(da.isInsuranceAvailable()).thenReturn(false);

        when(pa1.makeInsuranceBet()).thenReturn(10);
        when(pa2.makeInsuranceBet()).thenReturn(10);

        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(pa1, times(0)).makeInsuranceBet();
        verify(pa2, times(0)).makeInsuranceBet();
    }

    //verify that players can make insurance bets when not allowed
    @Test
    public void testInsuranceBetsMade(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.STAND);

        when(da.isInsuranceAvailable()).thenReturn(true);

        when(pa1.makeInsuranceBet()).thenReturn(10);
        when(pa2.makeInsuranceBet()).thenReturn(10);

        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(pa1, times(1)).makeInsuranceBet();
        verify(pa2, times(1)).makeInsuranceBet();
    }

    //verify players don't get cards after standing
    @Test
    public void testPlayersStand(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.STAND);
//        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.HIT);//causes hangup during testing

        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.STAND);
//        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.HIT);//causes hangup during testing

        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(da, times(2)).dealCard(pa1);
        verify(da, times(2)).dealCard(pa2);
    }

    //verify players don't get cards after doubling
    @Test
    public void testPlayersDouble(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.DOUBLE);
//        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.HIT);//causes hangup during testing

        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.DOUBLE);
//        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.HIT);//causes hangup during testing

        when(pa1.doubleDownBet()).thenReturn(20);
        when(pa2.doubleDownBet()).thenReturn(20);

        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(da, times(3)).dealCard(pa1);
        verify(da, times(3)).dealCard(pa2);
        verify(pa1, times(1)).doubleDownBet();
        verify(pa2, times(1)).doubleDownBet();
    }

    //verify players get proper number of cards
    @Test
    public void testPlayersHit(){
        DealerAction da = mock(DealerAction.class);
        PlayerAction pa1 = mock(PlayerAction.class);
        PlayerAction pa2 = mock(PlayerAction.class);

        PlayerAction[] players = {pa1,pa2};

        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.HIT);
        when(pa1.getAction()).thenReturn(PlayerAction.ActionType.STAND);

        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.HIT);
        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.HIT);
        when(pa2.getAction()).thenReturn(PlayerAction.ActionType.STAND);


        SimpleBlackjack j = new SimpleBlackjack(da,players);
        j.playRound();

        verify(da, times(3)).dealCard(pa1);
        verify(da, times(4)).dealCard(pa2);
        verify(pa1, times(1)).doubleDownBet();
        verify(pa2, times(1)).doubleDownBet();
    }

}