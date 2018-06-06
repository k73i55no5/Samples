package vending180219.storage;

public interface Storage {
	void offerDrink(Drink selectedDrink) throws Exception;
	void saveMoneyInHand(Drink selectedDrink);
	void displayHistory() throws Exception;
	void refillDrink();
}
