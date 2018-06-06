package vending180219.storage;

import java.io.PrintWriter;
import java.util.List;

import vending180219.io.ORMapper;

class StorageORMapper extends ORMapper {

	void savePurchasedDrink(Drink selectedDrink) {
		List<String> lines = this.readAllLines();
		PrintWriter pw = this.newPrintWriter();
		int i[] = {0};
		lines.stream().forEach(line -> {
			if (i[0] == 1) pw.println(selectedDrink.getName()); else pw.println(line);
			i[0]++;
		});
		pw.close();
	}

	void saveStoredDrink(StoredDrink sd) {
		List<String> lines = this.readAllLines();
		PrintWriter pw = this.newPrintWriter();
		int i[] = {0};
		lines.stream().forEach(line -> {
			if (i[0] == 3) pw.println(sd.countBy()); else pw.println(line);
			i[0]++;
		});
		pw.close();
	}

	void saveMoneyInHand(Drink selectedDrink) {
		List<String> lines = this.readAllLines();
		PrintWriter pw = this.newPrintWriter();
		int i[] = {0};
		lines.stream().forEach(line -> {
			if (i[0] == 0) {
				int moneyInHand = Integer.parseInt(line);
				pw.println(moneyInHand -= selectedDrink.getPrice());
			} else {
				pw.println(line);
			}
			i[0]++;
		});
		pw.close();
	}

	void displayHistory() throws Exception {
		List<String> lines = this.readAllLines();
		if (lines.get(1).equals("")) throw new Exception("飲料の購入履歴がありません。");
		System.out.println("購入履歴：" + lines.get(1));
	}
}
