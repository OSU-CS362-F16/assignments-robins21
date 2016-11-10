package edu.osu.blackjack;




import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.Mockito.*;


/*
Assumtions:
number of tests 100
minimumum deck size: 100
maximum deck size: 1000
    deck size must be large enough for enough cards to be generated for all deal card actions
maximum wallet size: 2000, just so I have a range to give it
randomization give to player class for gettin action method
 */

public class TravisRobinsonRandomTest{

    @Test
    public void testPlayRound(){

        final int numOfTests = 100;//arbitrary number of tests
        final int minPlayer = 1;//as per requirements
        final int maxPlayer = 4;
        final int minDeckSize = 100;//arbitrary min to ensure there are cards available
        final int maxDeckSize = 1000;//arbitrary max size of deck
        final int maxWalletSize = 2000;//arbitrary max starting size of wallet

        Random r = new Random();
        ArrayList<Card> seedDeck = Card.newDeck();

        //perform tests
        for (int testNumber = 0; testNumber < numOfTests; testNumber++){

            //make dealer
            Dealer dealer = new Dealer();


            //make players
            int numPlayers = r.nextInt(maxPlayer - minPlayer) + minPlayer;
            Player[] players = new Player[numPlayers];
            int[] playerInitialWallets = new int[numPlayers];
            for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                players[playerNum] = new Player();
                players[playerNum].currentWallet = r.nextInt(maxWalletSize);
                playerInitialWallets[playerNum] = players[playerNum].getCurrentWallet();
            }


            //make simpleBlackjack
            SimpleBlackjack j = new SimpleBlackjack(dealer,players);


            //build deck
            ArrayList<Card> deck = new ArrayList<Card>();
            int deckSize = r.nextInt(maxDeckSize - minDeckSize) + minDeckSize;
            for (int i = 0; i < deckSize; i++){
                deck.add(seedDeck.get(r.nextInt(seedDeck.size())));
            }
            ArrayList<Card> controlDeck = new ArrayList<Card>(deck);
            dealer.setDeck(deck);


            //shuffle deck
            j.playRoundShuffleDeck();
            assertFalse(controlDeck.toArray().equals(dealer.getDeck().toArray()));


            //make bets
            j.playRoundMakeBets();
            for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                if (players[playerNum].getCurrentWallet() != 0){
                    players[playerNum].moveMoneyToBet(r.nextInt(players[playerNum].getCurrentWallet()));
                }
                else{
                    players[playerNum].moveMoneyToBet(0);
                }

                assertTrue(players[playerNum].getCurrentWallet() + players[playerNum].makeBet() == playerInitialWallets[playerNum]);
            }


            //dealcards
            j.playRoundDealCards();
            assertTrue(dealer.getHand().size() == 2);
            for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                assertTrue(players[playerNum].getHand().size() == 2);
            }


            //make insurance bets
            if (dealer.isInsuranceAvailable()){
                for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                    if (players[playerNum].getCurrentWallet() != 0){
                        int insurance = r.nextInt(players[playerNum].getCurrentWallet());
                        players[playerNum].moveMoneyToInsurance(insurance);
                    }
                    else{
                        players[playerNum].moveMoneyToInsurance(0);
                    }
                }
            }

            j.playRoundMakeInsuranceBets();

            if (dealer.isInsuranceAvailable()){
                for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                    assertTrue(players[playerNum].getCurrentWallet() + players[playerNum].getCurrentBet() + players[playerNum].getInsuranceBet() == playerInitialWallets[playerNum]);
                }
            }
            else{
                for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                    assertTrue(players[playerNum].getCurrentWallet() + players[playerNum].getCurrentBet() == playerInitialWallets[playerNum]);
                    assertTrue(players[playerNum].getInsuranceBet() == 0);
                }
            }


            //get player actions
            j.playRoundGetAction();
            assertTrue(dealer.getHand().size() >= 2);
            for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                assertTrue(players[playerNum].getHand().size() == (2 + players[playerNum].getHitCounter()));
                assertFalse(players[playerNum].getHand().isEmpty());
            }


            //settle things up
            Dealer dealerControl = new Dealer(dealer);

            Player[] controlArray = new Player[numPlayers];
            System.arraycopy(players,0,controlArray,0,players.length);

            j.playRoundSettle();

            for (int playerNum = 0; playerNum < numPlayers; playerNum++){
                dealerControl.compareHandAndSettle(controlArray[playerNum]);
//                assertTrue(players[playerNum].equals(controlArray[playerNum]));
            }

            players.equals(controlArray);
            dealer.equals(dealerControl);
        }






//        assertTrue(true);
    }


}