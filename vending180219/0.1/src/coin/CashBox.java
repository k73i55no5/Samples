package vending180219.coin;

import java.util.ArrayList;
import java.util.stream.IntStream;

import vending180219.drink.Drink;

public class CashBox {

	private ArrayList<Coin> coins = new ArrayList<Coin>();
	private int amountInserted = 0;

	public void storeCoin(Coin insertCoin) {
		try {
			if(this.isIncorrect(insertCoin)) { throw new Exception("投入されたコインの種類が正しくありません。"); }
			if(this.isReachThousand(insertCoin)) { throw new Exception("これ以上コインを投入できません。"); }
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return;
		}
		this.coins.add(insertCoin);
		this.amountInserted += insertCoin.getAmount();
		System.out.println("投入金額は" + amountInserted + "円です。");
	}

	public Boolean permitPurchase() {
		try {
			if(this.isNotInserted()) { throw new Exception("投入金額が不足しています。"); }
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		return true;
	}

	public void acceptPayment(Drink selectDrink) {
		this.amountInserted -= selectDrink.getPrice();
		System.out.println("投入金額は" + amountInserted + "円です。");
	}

	public ArrayList<Coin> returnCoin() {
		try {
			if(this.isNotInserted()) { throw new Exception("返却するコインがありません。"); }
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		ArrayList<Coin> change = new ArrayList<Coin>();
		IntStream.range(0, this.amountInserted / 100).forEach(i -> change.add(Coin.ONE_HUNDRED));
		this.amountInserted = 0;
		System.out.println("コインを返却しました。");
		System.out.println("投入金額は" + amountInserted + "円です。");
		return change;
	}

	private Boolean isIncorrect(Coin insertCoin) {
		return insertCoin == null;
	}

	private Boolean isReachThousand(Coin insertCoin) {
		return this.amountInserted + insertCoin.getAmount() > 1000;
	}

	private  Boolean isNotInserted() {
		return this.amountInserted == 0;
	}
}
