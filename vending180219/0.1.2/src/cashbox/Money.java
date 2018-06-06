package vending180219.cashbox;

public enum Money {
	ONE_HUNDRED(100),
	FIVE_HUNDRED(500),
	ONE_THOUSAND(1000);

	private int amount;

	private Money(int amount) {
		this.amount = amount;
	}

	int getAmount() {
		return amount;
	}
}
