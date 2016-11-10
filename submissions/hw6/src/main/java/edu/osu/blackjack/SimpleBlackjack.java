package edu.osu.blackjack;



public class SimpleBlackjack {
	public enum ActionType {HIT , DOUBLE, STAND};
	private int numPlayers;
	
	DealerAction dealer;
	PlayerAction[] players ;

	
	public SimpleBlackjack(DealerAction d, PlayerAction[] ip){
		this.dealer = d;
		this.players = ip;
	}

	public void playRoundShuffleDeck(){
		dealer.shuffleDeck();
	}

	public void playRoundMakeBets() {

		for (PlayerAction p : players) {
			p.makeBet();
		}
	}

	public void playRoundDealCards() {

		dealer.dealCard(dealer);
		dealer.dealCard(dealer);

		for (PlayerAction p : players) {
			dealer.dealCard(p);
			dealer.dealCard(p);
		}
	}

	public void playRoundMakeInsuranceBets() {

		if (dealer.isInsuranceAvailable()) {
			for (PlayerAction p : players) {
				p.makeInsuranceBet();
			}
		}
	}

	public void playRoundGetAction() {

		for (PlayerAction p : players) {
			boolean turnOver = false;
			while (!turnOver) {
				PlayerAction.ActionType a = p.getAction();
				switch (a) {
					case HIT:
						dealer.dealCard(p);
						break;
					case DOUBLE:
						p.doubleDownBet();
						dealer.dealCard(p);
						turnOver = true;
						break;
					default: // stand
						turnOver = true;
						break;
				}

			}
		}
	}

	public void playRoundSettle(){
		for(PlayerAction p: players){
			dealer.compareHandAndSettle(p);
		}
	}
}
