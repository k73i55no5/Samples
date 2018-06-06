package vending180219.cashbox;

import vending180219.storage.Drink;

class InsertedAmount {

	private int insertedAmount = 0;

	void addAmount(Money insertedMoney) throws Exception {
		if(this.isExceededCapacity(insertedMoney)) throw new Exception("これ以上コインを投入できません。");
		this.insertedAmount += insertedMoney.getAmount();
	}

	void displayInsertedAmount() {
		System.out.println("投入金額は" + this.insertedAmount + "円です。");
	}

	void acceptPayment(Drink selectedDrink) {
		this.insertedAmount -= selectedDrink.getPrice();
		this.displayInsertedAmount();
	}

	int returnCoin(CashBoxORMapper orm) {
		int requiredOneHundred = this.insertedAmount / 100;
		this.insertedAmount = 0;
		orm.saveMoneyInHand(requiredOneHundred * 100);
		return requiredOneHundred;
	}

	private Boolean isExceededCapacity(Money insertMoney) {
		return this.insertedAmount + insertMoney.getAmount() > 1000;
	}

	Boolean isNotInserted() {
		return this.insertedAmount == 0;
	}

	Boolean isNotEnough(StoredMoney sm) {
		int requiredOneHundred = this.insertedAmount / 100;
		return sm.count(Money.ONE_HUNDRED) < requiredOneHundred;
	}
}
