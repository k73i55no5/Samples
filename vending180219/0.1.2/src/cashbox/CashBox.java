package vending180219.cashbox;

import vending180219.storage.Drink;

public interface CashBox {

	void storeMoney(Money insertedMoney) throws Exception;
	void permitPurchase() throws Exception;
	void acceptPayment(Drink selectedDrink);
	void returnCoin() throws Exception;
	void refillCoin();
}
