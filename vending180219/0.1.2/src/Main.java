package vending180219;

import vending180219.vending.Vending;
import vending180219.vending.VendingService;

public class Main {

	static Vending v = new VendingService();

	public static void main(String[] args) {
		if (args.length != 0) new Supplier(v); else new A(v);
	}
}
