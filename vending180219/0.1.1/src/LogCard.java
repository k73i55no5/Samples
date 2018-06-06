package vending180219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import vending180219.drink.Drink;
import vending180219.money.Money;

public class LogCard {

	static String filePath = "logcard.txt";

	public static void saveMoneyInHand(Drink selectDrink) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("logcard.txt"), StandardCharsets.UTF_8));
			int i[] = {0};
			lines.stream().forEach(line -> {
				if (i[0] == 0) {
					int moneyInHand = Integer.parseInt(line);
					pw.println(moneyInHand -= selectDrink.getPrice());
				} else {
					pw.println(line);
				}
				i[0]++;
			});
			pw.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void saveMoneyInHand(int change) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("logcard.txt"), StandardCharsets.UTF_8));
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
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void saveMoneyStored(List<Money> storedMoney) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("logcard.txt"), StandardCharsets.UTF_8));
			int i[] = {0};
			lines.stream().forEach(line -> {
				if (i[0] == 2) {
					String cntOneHundred = String.valueOf(storedMoney.stream().filter(money -> money.equals(Money.ONE_HUNDRED)).count());
					String cntFiveHundred = String.valueOf(storedMoney.stream().filter(money -> money.equals(Money.FIVE_HUNDRED)).count());
					String cntOneThousand = String.valueOf(storedMoney.stream().filter(money -> money.equals(Money.ONE_THOUSAND)).count());
					pw.println(cntOneHundred + "," + cntFiveHundred + "," + cntOneThousand);
				} else {
					pw.println(line);
				}
				i[0]++;
			});
			pw.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void saveDrinkPurchased(Drink selectDrink) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("logcard.txt"), StandardCharsets.UTF_8));
			int i[] = {0};
			lines.stream().forEach(line -> {
				if (i[0] == 1) {
					pw.println(selectDrink.getName());
				} else {
					pw.println(line);
				}
				i[0]++;
			});
			pw.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void saveDrinkStored(List<Drink> storedDrinks) {
		try {
			List<String> lines = Files.readAllLines(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("logcard.txt"), StandardCharsets.UTF_8));
			int i[] = {0};
			lines.stream().forEach(line -> {
				if (i[0] == 3) {
					String cntCola = String.valueOf(storedDrinks.stream().filter(drink -> drink.equals(Drink.COLA)).count());
					String cntDietCola = String.valueOf(storedDrinks.stream().filter(drink -> drink.equals(Drink.DIET_COLA)).count());
					String cntTea = String.valueOf(storedDrinks.stream().filter(drink -> drink.equals(Drink.TEA)).count());
					pw.println(cntCola + "," + cntDietCola + "," + cntTea);
				} else {
					pw.println(line);
				}
				i[0]++;
			});
			pw.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void displayLog() throws Exception {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			br.readLine();
			String purchasedDrink = br.readLine();
			if (purchasedDrink.equals("")) throw new Exception("飲料の購入履歴がありません。");
			System.out.println("購入履歴：" + purchasedDrink);
		} catch (IOException e) { e.printStackTrace(); }
	}
}
