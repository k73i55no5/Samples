package vending180219.drink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import vending180219.LogCard;

public class Storage {
	private List<Drink> drinks = new ArrayList<Drink>();

	public Storage() {
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

	public void decrement(Drink selectDrink) throws Exception {
		if(this.isIncorrect(selectDrink)) throw new Exception("選択された飲料の種類が正しくありません。");
		if(this.isEmpty(selectDrink)) throw new Exception(selectDrink.getName() + "の在庫がありません。");
		this.drinks.remove(selectDrink);
		System.out.println(selectDrink.getName() + "を購入しました。");
		this.requestSaveDrinkPurchased(selectDrink);
		this.requestSaveDrinkStored(this.drinks);
	}

	public void requestSaveDrinkPurchased(Drink selectDrink) {
		LogCard.saveDrinkPurchased(selectDrink);
	}

	public void requestSaveDrinkStored(List<Drink> storedDrinks) {
		LogCard.saveDrinkStored(storedDrinks);
	}

	private Boolean isIncorrect(Drink selectDrink) {
		return selectDrink == null;
	}

	private Boolean isEmpty(Drink selectDrink) {
		return this.drinks.indexOf(selectDrink) == -1;
	}
}
