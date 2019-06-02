package k73i55no5.refactorers.student190520.domain;

enum FruitConstants {
	APPLE(new Fruit("りんご", MeasureWord.KO)),
	MIKAN(new Fruit("みかん", MeasureWord.KO)),
	BANANA(new Fruit("バナナ", MeasureWord.HON)),
	GRAPE(new Fruit("ぶどう", MeasureWord.HUSA)),
	DORIAN(new Fruit("ドリアン", MeasureWord.KO)),
	STRAWBERRY(new Fruit("いちご", MeasureWord.KO)),;

	private Fruit fruit;

	private FruitConstants(Fruit fruit) { this.fruit = fruit; }
	Fruit get() { return fruit; }
	String getInfo() { return fruit.getInfo(); }
}