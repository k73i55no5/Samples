package vending180219;

import java.util.ArrayList;

import vending180219.coin.CashBox;
import vending180219.coin.Coin;
import vending180219.drink.Drink;
import vending180219.drink.Storage;

public class Vending {

	private CashBox cashbox = new CashBox();
	private Storage storage = new Storage();

	public void requestStoreCoin(Coin insertCoin) {
		cashbox.storeCoin(insertCoin);
	}

	public Boolean requestPermitPurchase() {
		return cashbox.permitPurchase();
	}

	public void requestOfferDrink(Drink selectDrink) {
		if (storage.decrement(selectDrink)) { this.requestAcceptPayment(selectDrink); };
	}

	public void requestAcceptPayment(Drink selectDrink) {
		cashbox.acceptPayment(selectDrink);
	}

	public ArrayList<Coin> requestReturnCoin() {
		return cashbox.returnCoin();
	}
}