package vending180219.money;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import vending180219.LogCard;
import vending180219.drink.Drink;

public class CashBox {

	private List<Money> storedMoney = new ArrayList<Money>();
	private AmountInserted amountInserted = new AmountInserted();

	public CashBox() {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			br.readLine();
			br.readLine();
			List<String> cntStoredMoney  = Arrays.asList(br.readLine().split(","));
			IntStream.range(1, Integer.valueOf(cntStoredMoney.get(0)) + 1).forEach(i -> this.storedMoney.add(Money.ONE_HUNDRED));
			IntStream.range(1, Integer.valueOf(cntStoredMoney.get(1)) + 1).forEach(i -> this.storedMoney.add(Money.FIVE_HUNDRED));
			IntStream.range(1, Integer.valueOf(cntStoredMoney.get(2))+ 1).forEach(i -> this.storedMoney.add(Money.ONE_THOUSAND));
		} catch (IOException e) { e.printStackTrace(); }
	}

	public void permitStoreMoney(Money insertMoney) throws Exception {
		if(this.isIncorrect(insertMoney)) throw new Exception("投入されたコインの種類が正しくありません。");
		if(this.exceedsCapacity()) throw new Exception("これ以上コインを投入できません。");
		this.requestCorrectAmountInserted(insertMoney);
	}

	public void permitPurchase() throws Exception {
		if(this.isNotInserted()) throw new Exception("投入金額が不足しています。");
	}

	public int acceptPayment(Drink selectDrink) {
		return this.amountInserted.withdraw(selectDrink);
	}

	public void requestCorrectAmountInserted(Money insertMoney) throws Exception {
		this.amountInserted.correctAmountInserted(insertMoney);
		this.storeMoney(insertMoney);
	}

	public int requestAmountInserted() {
		int amountInserted = this.amountInserted.getAmountInserted();
		return amountInserted;
	}

	public void requestSaveMoneyStored() {
		LogCard.saveMoneyStored(this.storedMoney);
	}

	public void storeMoney(Money insertMoney) {
		this.storedMoney.add(insertMoney);
	}

	public Change returnCoin() throws Exception {
		if(this.isNotInserted()) { throw new Exception("返却するコインがありません。"); }
		if(this.isNotEnough()) { throw new Exception("返却するコインが足りません。"); }
		Change change = this.requestChange();
		this.requestSaveMoneyStored();
		System.out.println("コインを返却しました。");
		System.out.println("投入金額は" + this.requestAmountInserted() + "円です。");
		return change;
	}

	public Change requestChange() {
		Change change = new Change();
		return change.returnChange(this.storedMoney, this.amountInserted);
	}

	private Boolean isIncorrect(Money insertMoney) {
		return insertMoney == null;
	}

	private Boolean exceedsCapacity() {
		return this.storedMoney.stream().filter(money -> money.equals(Money.ONE_HUNDRED)).count() == 10;
	}

	private  Boolean isNotInserted() {
		return this.requestAmountInserted() == 0;
	}

	private  Boolean isNotEnough() {
		int requiredOneHundred = this.amountInserted.getAmountInserted() / 100;
		return this.storedMoney.stream().filter(money -> money.equals(Money.ONE_HUNDRED)).count() < requiredOneHundred;
	}
}
