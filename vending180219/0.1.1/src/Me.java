package vending180219;

import java.util.Scanner;

import vending180219.drink.Drink;
import vending180219.money.Money;

public class Me {

	private Scanner s = new Scanner(System.in);
	private Vending vending = new Vending();

	public static void main(String[] args) {
		new Me();
	}

	private Me() {
		while (true) {
			System.out.println("操作を入力して下さい。");
			System.out.println("[1]貨幣投入、[2]飲料購入、[3]コイン返却、[4]購入履歴照会、[0]終了");
			switch (s.nextInt()) {
			case 0: System.exit(0);
			case 1: insertMoney(); break;
			case 2: selectDrink(); break;
			case 3: selectReturn(); break;
			case 4: selectDisplayLog(); break;
			}
		}
	}

	private void insertMoney() {
		System.out.println("投入金額を入力して下さい。");
		System.out.println("[1]100円、[2]500円、[3]1,000円、[4]10円、[他]一つ前に戻る");
		switch (s.nextInt()) {
		case 1: this.vending.requestStoreMoney(Money.ONE_HUNDRED); break;
		case 2: this.vending.requestStoreMoney(Money.FIVE_HUNDRED); break;
		case 3: this.vending.requestStoreMoney(Money.ONE_THOUSAND); break;
		case 4: this.vending.requestStoreMoney(null); break;
		}
	}

	private void selectDrink() {
		System.out.println("購入する飲料を入力して下さい。");
		System.out.println("[1]コーラ、[2]ダイエットコーラ、[3]お茶、[4]ぬるぽ、[他]一つ前に戻る");
		try {
			switch (s.nextInt()) {
			case 1:
				this.vending.requestPermitPurchase();
				this.vending.requestOfferDrink(Drink.COLA);
				break;
			case 2:
				this.vending.requestPermitPurchase();
				this.vending.requestOfferDrink(Drink.DIET_COLA);
				break;
			case 3:
				this.vending.requestPermitPurchase();
				this.vending.requestOfferDrink(Drink.TEA);
				break;
			case 4:
				this.vending.requestPermitPurchase();
				this.vending.requestOfferDrink(null);
				break;
			}
		} catch (Exception e) { System.out.println(e.getLocalizedMessage()); }
	}

	private void selectReturn() {
		this.vending.requestReturnCoin();
	}

	private void selectDisplayLog() {
		this.vending.requestDisplayLog();
	}
}
