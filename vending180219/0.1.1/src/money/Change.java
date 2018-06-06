package vending180219.money;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import vending180219.LogCard;

public class Change {

	private List<Money> change = new ArrayList<Money>();

	public Change returnChange(List<Money> storedMoney, AmountInserted amountInserted) {
		int change = amountInserted.getAmountInserted();
		int requiredOneHundred = change / 100;
		IntStream.range(0, requiredOneHundred).forEach(i -> this.change.add(Money.ONE_HUNDRED));
		IntStream.range(0, requiredOneHundred).forEach(i -> storedMoney.remove(Money.ONE_HUNDRED));
		amountInserted.withdrawAll();
		this.requestSaveMoneyInHand(change);
		return this;
	}

	public void requestSaveMoneyInHand(int change) {
		LogCard.saveMoneyInHand(change);
	}
}
