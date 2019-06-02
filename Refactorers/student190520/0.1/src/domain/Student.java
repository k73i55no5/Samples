package k73i55no5.refactorers.student190520.domain;

class Student {
	private static final String LINE = "もってこいやぁ！"; // セリフ
	private String name; // 生徒の名前
	private Fruit fruit; // 好きな果物（果物クラスのオブジェクト）
	private int quantity; // 食べたい量

	Student(String name, FruitConstants fruit, int quantity) {
		this.name = name;
		this.fruit = fruit.get();
		this.quantity = quantity;
	}

	// 生徒の名前、好きな果物、食べたい量を表す文字列を半角コンマ区切りで出力
	String getInfo() { return String.join(", ", name, fruit.name(), String.valueOf(quantity)); }
	// 好きな果物を食べたい量（単位付き）だけ持ってくるよう要求するメッセージを出力
	String require() {
		return name + "：" + fruit.name() + quantity + fruit.measureWord() + LINE;
	}

	void setFavoritefruit(Fruit fruit) { this.fruit = fruit; }
	void setQuantity(int quantity) { this.quantity = quantity; }
}