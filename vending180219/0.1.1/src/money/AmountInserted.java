package vending180219.money;

import vending180219.drink.Drink;

public class AmountInserted {

	private int amountInserted = 0;

	public void correctAmountInserted(Money insertMoney) throws Exception {
		if(this.exceedsCapacity(insertMoney)) throw new Exception("これ以上コインを投入できません。");
		this.amountInserted += insertMoney.getAmount();
	}

	public int withdraw(Drink selectDrink) {
		return this.amountInserted -= selectDrink.getPrice();
	}

	public void withdrawAll() {
		this.amountInserted = 0;
	}

	public int getAmountInserted() {
		return this.amountInserted;
	}

	private Boolean exceedsCapacity(Money insertMoney) {
		return this.amountInserted + insertMoney.getAmount() > 1000;
	}


}
