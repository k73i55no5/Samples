package vending180219.drink;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Storage {
	private ArrayList<Drink> drinks = new ArrayList<Drink>();

	public Storage() {
		IntStream.range(1, 4).forEach(i -> this.drinks.add(Drink.COLA));
		IntStream.range(1, 4).forEach(i -> this.drinks.add(Drink.DIET_COLA));
		IntStream.range(1, 4).forEach(i -> this.drinks.add(Drink.TEA));
	}

	public Boolean decrement(Drink selectDrink) {
		try {
			if(this.isIncorrect(selectDrink)) { throw new Exception("選択された飲料の種類が正しくありません。"); }
			if(this.isEmpty(selectDrink)) { throw new Exception(selectDrink.getName() + "の在庫がありません。"); }
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		this.drinks.remove(selectDrink);
		return true;
	}

	private Boolean isIncorrect(Drink selectDrink) {
		return selectDrink == null;
	}

	private Boolean isEmpty(Drink selectDrink) {
		return this.drinks.indexOf(selectDrink) == -1;
	}
}
