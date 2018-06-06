package vending180219.cashbox;

import vending180219.storage.Drink;

public class CashBoxImpl implements CashBox {

	private CashBoxORMapper orm = new CashBoxORMapper();
	private StoredMoney sm = new StoredMoney();
	private InsertedAmount ia = new InsertedAmount();

	public void storeMoney(Money insertedMoney) throws Exception {
		if(this.isIncorrect(insertedMoney)) throw new Exception("投入されたコインの種類が正しくありません。");
		if(this.sm.isExceededCapacity()) throw new Exception("これ以上コインを投入できません。");
		this.ia.addAmount(insertedMoney);
		this.sm.storeMoney(insertedMoney);
		this.ia.displayInsertedAmount();
		this.orm.saveStoredMoney(this.sm);
	}

	public void permitPurchase() throws Exception {
		if(this.ia.isNotInserted()) throw new Exception("投入金額が不足しています。");
	}

	public void acceptPayment(Drink selectedDrink) {
		this.ia.acceptPayment(selectedDrink);
	}

	public void returnCoin() throws Exception {
		if(this.ia.isNotInserted()) { throw new Exception("返却するコインがありません。"); }
		if(this.ia.isNotEnough(this.sm)) { throw new Exception("返却するコインが足りません。"); }
		this.sm.returnCoin(ia.returnCoin(this.orm));
		System.out.println("コインを返却しました。");
		this.ia.displayInsertedAmount();
		this.orm.saveStoredMoney(this.sm);
	}

	public void refillCoin() {
		this.sm.refillCoin();
		System.out.println("100円硬貨を補充しました。");
	}

	private Boolean isIncorrect(Money insertedMoney) {
		return insertedMoney == null;
	}
}
