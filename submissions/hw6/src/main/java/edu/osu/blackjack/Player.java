package edu.osu.blackjack;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public  class Player implements PlayerAction{

	public Integer currentBet = 0;
	public Integer currentWallet = 1000;
	public Integer currentInsurance = 0;
	public Integer hitCounter = 0;//used to check number of cards dealt after initial two

	public List<Card> currentHand = new ArrayList<Card>();
	
	@Override
	public void acceptCard(Card c) {
		currentHand.add(c);
	}
	@Override
	public int makeBet(){
		return currentBet;
	}
	@Override
	public  int doubleDownBet(){
		this.currentWallet = this.currentWallet - currentBet;
		this.currentBet *= 2;
		return this.currentBet;
	}
	@Override
	public  int makeInsuranceBet(){
		return this.currentInsurance;
	}
	@Override
	public  ActionType getAction(){//randomization added for testing playRound()
		Random r = new Random();
		int action = r.nextInt(3);
		if (action == 0){
			this.hitCounter++;
			return ActionType.DOUBLE;
		}
		else if(action == 1){
			this.hitCounter++;
			return ActionType.HIT;
		}
		else{
			return ActionType.STAND;
		}
	}
	public int getHitCounter(){
		return this.hitCounter;
	}
	@Override
	public List<Card> getHand() {
		return this.currentHand;
	}
	@Override
	public int getCurrentBet() {
		return this.currentBet;
	}
	@Override
	public int getInsuranceBet(){
		return this.currentInsurance;
	}
	public int getCurrentWallet(){
		return this.currentWallet;
	}

	@Override
	public void acceptMoney(int i) {
		this.currentWallet += i;
	}
	
	
	public void moveMoneyToInsurance(int amount) {
		if (currentWallet >= amount) {
			currentWallet = currentWallet - amount;
			currentInsurance = amount;
		} else{
			currentInsurance = currentWallet;
			currentWallet = 0;
		}
	}
	public void moveMoneyToBet(int amount) {
		if (currentWallet >= amount) {
			currentBet = amount;
			currentWallet -= amount;
		} else{
			currentBet = currentWallet;
			currentWallet = 0;
		}
	}
	@Override
	public void nextHand() {
		currentBet = 0;
		currentInsurance = 0;
	}

	@Override	
	public String toString() {
		return "Player: " + this.currentHand + "w " +this.currentWallet + "b " + currentBet + "i " + currentInsurance;
	}


	
}
