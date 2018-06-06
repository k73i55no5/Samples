package vending180219.vending;

import vending180219.cashbox.CashBox;
import vending180219.cashbox.CashBoxImpl;
import vending180219.cashbox.Money;
import vending180219.storage.Drink;
import vending180219.storage.Storage;
import vending180219.storage.StorageImpl;

public class VendingService implements Vending {

	private CashBox c = new CashBoxImpl();
	private Storage s = new StorageImpl();

	public void insertMoney(Money insertedMoney) {
		try {
			this.c.storeMoney(insertedMoney);
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	public void selectDrink(Drink selectedDrink) {
		try {
			this.c.permitPurchase();
			this.s.offerDrink(selectedDrink);
			this.c.acceptPayment(selectedDrink);
			this.s.saveMoneyInHand(selectedDrink);
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	public void selectReturn() {
		try {
			this.c.returnCoin();
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	public void displayHistory() {
		try { this.s.displayHistory(); } catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	public void refillDrink() {
		this.s.refillDrink();
	}

	public void refillCoin() {
		this.c.refillCoin();
	}
}