package vending180219.storage;

public class StorageImpl implements Storage {

	private StorageORMapper orm = new StorageORMapper();
	private StoredDrink sd = new StoredDrink();

	public void offerDrink(Drink selectedDrink) throws Exception {
		if(this.isIncorrect(selectedDrink)) throw new Exception("選択された飲料の種類が正しくありません。");
		if(this.sd.isEmpty(selectedDrink)) throw new Exception(selectedDrink.getName() + "の在庫がありません。");
		this.sd.offerDrink(selectedDrink);
		System.out.println(selectedDrink.getName() + "を購入しました。");
		this.orm.savePurchasedDrink(selectedDrink);
		this.orm.saveStoredDrink(this.sd);
	}

	public void saveMoneyInHand(Drink selectedDrink) {
		this.orm.saveMoneyInHand(selectedDrink);
	}

	public void displayHistory() throws Exception {
		this.orm.displayHistory();
	}

	public void refillDrink() {
		this.sd.refillDrink();
		System.out.println("飲料を補充しました。");
	}

	private Boolean isIncorrect(Drink selectDrink) {
		return selectDrink == null;
	}
}
