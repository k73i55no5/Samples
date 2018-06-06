package vending180219.cashbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class StoredMoney {

	private List<Money> money = new ArrayList<Money>();

	StoredMoney() {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			br.readLine();
			br.readLine();
			List<String> cntStoredMoney  = Arrays.asList(br.readLine().split(","));
			IntStream.range(1, Integer.valueOf(cntStoredMoney.get(0)) + 1).forEach(i -> this.money.add(Money.ONE_HUNDRED));
			IntStream.range(1, Integer.valueOf(cntStoredMoney.get(1)) + 1).forEach(i -> this.money.add(Money.FIVE_HUNDRED));
			IntStream.range(1, Integer.valueOf(cntStoredMoney.get(2))+ 1).forEach(i -> this.money.add(Money.ONE_THOUSAND));
		} catch (IOException e) { e.printStackTrace(); }
	}

	void storeMoney(Money insertedMoney) throws Exception {
		this.money.add(insertedMoney);
	}

	void returnCoin(int requiredOneHundred) {
		IntStream.range(0, requiredOneHundred).forEach(i -> this.money.remove(Money.ONE_HUNDRED));
	}

	void refillCoin() {
		IntStream.range(1, 10 - this.count(Money.ONE_HUNDRED) + 1).forEach(i -> this.money.add(Money.ONE_HUNDRED));
		System.out.println(this.money);
	}

	int count(Money storedMoney) {
		return (int) this.money.stream().filter(money -> money.equals(storedMoney)).count();
	}

	String countBy() {
		return String.join(",", String.valueOf(this.count(Money.ONE_HUNDRED)), String.valueOf(this.count(Money.FIVE_HUNDRED)), String.valueOf(this.count(Money.ONE_THOUSAND)));
	}

	Boolean isExceededCapacity() {
		return this.count(Money.ONE_HUNDRED) == 10;
	}
}
