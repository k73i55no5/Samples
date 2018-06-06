package vending180219.vending;

import vending180219.cashbox.Money;
import vending180219.storage.Drink;

public interface Vending {

	void insertMoney(Money insertedMoney);
	void selectDrink(Drink selectedDrink);
	void selectReturn();
	void displayHistory();
	void refillDrink();
	void refillCoin();
}
