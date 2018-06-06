package vending180219.money;

public enum Money {
	ONE_HUNDRED(100),
	FIVE_HUNDRED(500),
	ONE_THOUSAND(1000);

	private int amount;

	private Money(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}
