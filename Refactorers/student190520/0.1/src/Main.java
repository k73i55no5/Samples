package k73i55no5.refactorers.student190520;

public class Main {

	public static void main(String[] args) {
		String message = Student.TANAKA.require();
		System.out.println(message);

//		Stream.of(Student.class.getEnumConstants())
//		.map(student -> student.require())
//		.forEach(System.out::println);
	}
}

enum Student {
	ANDO("安藤さん", Fruit.APPLE, 5),
	ITO("伊藤くん", Fruit.MIKAN, 8),
	ENDO("遠藤さん", Fruit.STRAWBERRY, 10),
	SATO("佐藤さん", Fruit.BANANA, 10),
	SUZUKI("鈴木くん", Fruit.GRAPE, 4),
	TANAKA("田中さん", Fruit.DORIAN, 7),
	TESHIGAWARA("勅使河原くん", Fruit.DORIAN, 35),;

	private static final String LINE = "もってこいやぁ！"; // セリフ
	private final String name; // 生徒の名前
	private Fruit fruit; // 好きな果物（果物クラスのオブジェクト）
	private int quantity; // 食べたい量

	Student(String name, Fruit fruit, int quantity) {
		this.name = name;
		this.fruit = fruit;
		this.quantity = quantity;
	}

	// 好きな果物を食べたい量（単位付き）だけ持ってくるよう要求するメッセージを出力
	String require() {
		return name + "：" + fruit + quantity + fruit.measureWord() + LINE;
	}

	void setFavoritefruit(Fruit fruit) { this.fruit = fruit; }
	void setQuantity(int quantity) { this.quantity = quantity; }
}

enum Fruit {
	APPLE("りんご", MeasureWord.KO),
	MIKAN("みかん", MeasureWord.KO),
	BANANA("バナナ", MeasureWord.HON),
	GRAPE("ぶどう", MeasureWord.HUSA),
	DORIAN("ドリアン", MeasureWord.KO),
	STRAWBERRY("いちご", MeasureWord.KO),;

	private final String name; // 果物の名前
	private final MeasureWord measureWord; // 果物の単位（単位クラスのオブジェクト）

	Fruit(String name, MeasureWord measureWord) {
		this.name = name;
		this.measureWord = measureWord;
	}

	@Override public String toString() { return name; }
	MeasureWord measureWord() { return measureWord; }
}

enum MeasureWord {
	KO("個"),
	HON("本"),
	HUSA("房"),;

	private final String name;

	private MeasureWord(String name) { this.name = name; }
	@Override public String toString() { return name; }
}