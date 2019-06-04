package k73i55no5.refactorers.student190520.domain;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name; // 生徒の名前
	private Fruit fruit; // 好きな果物（果物クラスのオブジェクト）
	private int quantity; // 食べたい量

	public Student(String name, Fruit fruit, int quantity) {
		this.name = name;
		this.fruit = fruit;
		this.quantity = quantity;
	}

	public Fruit getFavoriteFruit() { return fruit; };
	public int getQuantityToEat() { return quantity; }
	public String name() { return name; }

	// 生徒の名前、好きな果物、食べたい量を表す文字列を半角コンマ区切りで出力
	@Override public String toString() {
		return String.join(", ", name, fruit.name(), String.valueOf(quantity));
	};
}