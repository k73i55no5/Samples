package vending180219;

import java.util.Scanner;

import vending180219.vending.Vending;

class Supplier {

	private Scanner s = new Scanner(System.in);

	Supplier(Vending v) {
		while (true) {
			System.out.println("操作を入力して下さい。");
			System.out.println("[1]飲料補充、[2]コイン補充、[0]終了");
			switch (s.nextInt()) {
			case 0: System.exit(0);
			case 1: refillDrink(v); break;
			case 2: refillCoin(v); break;
			}
		}
	}

	private void refillDrink(Vending v) {
		v.refillDrink();
	}

	private void refillCoin(Vending v) {
		v.refillCoin();
	}
}
