package vending180219;

import java.util.ArrayList;
import java.util.Scanner;

import vending180219.coin.Coin;
import vending180219.drink.Drink;

public class Me {

	private Scanner s = new Scanner(System.in);
	private Vending vending = new Vending();

	public static void main(String[] args) {
		new Me();
	}

	private Me() {
		while (true) {
			System.out.println("操作を入力して下さい。");
			System.out.println("[1]コイン投入、[2]飲料購入、[3]コイン返却、[0]終了");
			switch (s.nextInt()) {
			case 0:
				System.exit(0);
			case 1:
				insertCoin();
				break;
			case 2:
				selectDrink();
				break;
			case 3:
				selectReturn();
				break;
			}
		}
	}

	private void insertCoin() {
		System.out.println("投入硬貨を入力して下さい。");
		System.out.println("[1]100円、[2]500円、[3]10円、[他]一つ前に戻る");
		switch (s.nextInt()) {
		case 1:
			vending.requestStoreCoin(Coin.ONE_HUNDRED);
			break;
		case 2:
			vending.requestStoreCoin(Coin.FIVE_HUNDRED);
			break;
		case 3:
			vending.requestStoreCoin(null);
			break;
		}
	}

	private void selectDrink() {
		System.out.println("購入する飲料を入力して下さい。");
		System.out.println("[1]コーラ、[2]ダイエットコーラ、[3]お茶、[4]ぬるぽ、[他]一つ前に戻る");
		switch (s.nextInt()) {
		case 1:
			if (vending.requestPermitPurchase()) { vending.requestOfferDrink(Drink.COLA); };
			break;
		case 2:
			if (vending.requestPermitPurchase()) { vending.requestOfferDrink(Drink.DIET_COLA); };
			break;
		case 3:
			if (vending.requestPermitPurchase()) { vending.requestOfferDrink(Drink.TEA); };
			break;
		case 4:
			if (vending.requestPermitPurchase()) { vending.requestOfferDrink(null); };
			break;
		}
	}

	private ArrayList<Coin> selectReturn() {
		return vending.requestReturnCoin();
	}
}
