package k73i55no5.refactorers.student190520.domain;

class Fruit {
	private String name; // 果物の名前
	private MeasureWord measureWord; // 果物の単位（単位クラスのオブジェクト）

	Fruit(String name, MeasureWord measureWord) {
		this.name = name;
		this.measureWord = measureWord;
	}

	// 果物の名前、果物の単位を表す文字列を半角コンマ区切りで出力
	String getInfo() { return String.join(", ", name(), measureWord()); }
	String name() { return name; }
	String measureWord() { return measureWord.name(); }
}