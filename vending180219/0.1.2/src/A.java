package vending180219;

import java.util.Scanner;

import vending180219.cashbox.Money;
import vending180219.storage.Drink;
import vending180219.vending.Vending;

class A {

	private Scanner s = new Scanner(System.in);

	A(Vending v) {
		while (true) {
			System.out.println("操作を入力して下さい。");
			System.out.println("[1]貨幣投入、[2]飲料購入、[3]コイン返却、[4]購入履歴照会、[0]終了");
			switch (s.nextInt()) {
			case 0: System.exit(0);
			case 1: insertMoney(v); break;
			case 2: selectDrink(v); break;
			case 3: selectReturn(v); break;
			case 4: displayHistory(v); break;
			}
		}
	}

	private void insertMoney(Vending v) {
		System.out.println("投入金額を入力して下さい。");
		System.out.println("[1]100円、[2]500円、[3]1,000円、[4]10円、[他]一つ前に戻る");
		switch (s.nextInt()) {
		case 1: v.insertMoney(Money.ONE_HUNDRED); break;
		case 2: v.insertMoney(Money.FIVE_HUNDRED); break;
		case 3: v.insertMoney(Money.ONE_THOUSAND); break;
		case 4: v.insertMoney(null); break;
		}
	}

	private void selectDrink(Vending v) {
		System.out.println("購入する飲料を入力して下さい。");
		System.out.println("[1]コーラ、[2]ダイエットコーラ、[3]お茶、[4]ぬるぽ、[他]一つ前に戻る");
		switch (s.nextInt()) {
		case 1: v.selectDrink(Drink.COLA); break;
		case 2: v.selectDrink(Drink.DIET_COLA); break;
		case 3: 	v.selectDrink(Drink.TEA); break;
		case 4: v.selectDrink(null); break;
		}
	}

	private void selectReturn(Vending v) {
		v.selectReturn();
	}

	private void displayHistory(Vending v) {
		v.displayHistory();
	}
}
