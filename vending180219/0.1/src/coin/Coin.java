package vending180219.coin;

public enum Coin {
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500);

	private int amount;

	private Coin(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}
