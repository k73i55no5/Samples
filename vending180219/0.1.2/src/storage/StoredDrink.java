package vending180219.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StoredDrink {

	private List<Drink> drinks = new ArrayList<Drink>();

	public StoredDrink() {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get("logcard.txt"), StandardCharsets.UTF_8);
			br.readLine();
			br.readLine();
			br.readLine();
			List<String> noOfStoredMoney  = Arrays.asList(br.readLine().split(","));
			IntStream.range(1, Integer.valueOf(noOfStoredMoney.get(0)) + 1).forEach(i -> this.drinks.add(Drink.COLA));
			IntStream.range(1, Integer.valueOf(noOfStoredMoney.get(1)) + 1).forEach(i -> this.drinks.add(Drink.DIET_COLA));
			IntStream.range(1, Integer.valueOf(noOfStoredMoney.get(2)) + 1).forEach(i -> this.drinks.add(Drink.TEA));
		} catch (IOException e) { e.printStackTrace(); }
	}

	void offerDrink(Drink selectedDrink) {
		this.drinks.remove(selectedDrink);
	}

	void refillDrink() {
		this.drinks.clear();
		IntStream.range(1, 10 + 1).forEach(i -> this.drinks.add(Drink.COLA));
		IntStream.range(1, 10 + 1).forEach(i -> this.drinks.add(Drink.DIET_COLA));
		IntStream.range(1, 10 + 1).forEach(i -> this.drinks.add(Drink.TEA));
	}

	int count(Drink storedDrink) {
		return (int) this.drinks.stream().filter(drink -> drink.equals(storedDrink)).count();
	}

	String countBy() {
		return String.join(",", String.valueOf(this.count(Drink.COLA)), String.valueOf(this.count(Drink.DIET_COLA)), String.valueOf(this.count(Drink.TEA)));
	}

	Boolean isEmpty(Drink selectedDrink) {
		return this.drinks.indexOf(selectedDrink) == -1;
	}
}
