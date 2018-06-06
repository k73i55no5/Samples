package vending180219;

import vending180219.drink.Drink;
import vending180219.drink.Storage;
import vending180219.money.CashBox;
import vending180219.money.Change;
import vending180219.money.Money;

public class Vending {

	private CashBox cashBox = new CashBox();
	private Storage storage = new Storage();

	public void requestStoreMoney(Money insertMoney) {
		try {
			this.cashBox.permitStoreMoney(insertMoney);
			this.requestAmountInserted();
			this.requestSaveMoneyStored();
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	public void requestAmountInserted() {
		int amountInserted = this.cashBox.requestAmountInserted();
		System.out.println("投入金額は" + amountInserted + "円です。");
	}

	public void requestSaveMoneyStored() {
		this.cashBox.requestSaveMoneyStored();
	}

	public void requestPermitPurchase() throws Exception {
		this.cashBox.permitPurchase();
	}

	public void requestOfferDrink(Drink selectDrink) {
		try {
			this.storage.decrement(selectDrink);
			this.requestAcceptPayment(selectDrink);
			this.requestSaveMoneyInHand(selectDrink);
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	public void requestAcceptPayment(Drink selectDrink) {
		int amountInserted = this.cashBox.acceptPayment(selectDrink);
		System.out.println("投入金額は" + amountInserted + "円です。");
	}

	public void requestSaveMoneyInHand(Drink selectDrink) {
		LogCard.saveMoneyInHand(selectDrink);
	}

	public Change requestReturnCoin() {
		try { return this.cashBox.returnCoin(); } catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
		return null;
	}

	public void requestDisplayLog() {
		try {
			LogCard.displayLog();
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}
}