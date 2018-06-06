package vending180219.drink;

public enum Drink {
	COLA("コーラ", 100),
	DIET_COLA("ダイエットコーラ", 100),
	TEA("お茶", 100);

	private String name;
	private int price;

	private Drink(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}
}
