package vending180219.cashbox;

import java.io.PrintWriter;
import java.util.List;

import vending180219.io.ORMapper;

class CashBoxORMapper extends ORMapper {

	void saveStoredMoney(StoredMoney sm) {
		List<String> lines = this.readAllLines();
		PrintWriter pw = this.newPrintWriter();
		int i[] = {0};
		lines.stream().forEach(line -> {
			if (i[0] == 2) pw.println(sm.countBy()); else pw.println(line);
			i[0]++;
		});
		pw.close();
	}

	void saveMoneyInHand(int change) {
		List<String> lines = this.readAllLines();
		PrintWriter pw = this.newPrintWriter();
		int i[] = {0};
		lines.stream().forEach(line -> {
			if (i[0] == 0) {
				int moneyInHand = Integer.parseInt(line);
				pw.println(moneyInHand += change);
			} else {
				pw.println(line);
			}
			i[0]++;
		});
		pw.close();
	}
}
