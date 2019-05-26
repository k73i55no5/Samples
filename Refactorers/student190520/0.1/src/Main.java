package k73i55no5.refactorers.student190520;

public class Main {

	public static void main(String[] args) {
		String message = StudentConstants.TANAKA.require();
		System.out.println(message);
		
//		Stream.of(StudentConstants.class.getEnumConstants())
//		.map(student -> student.require())
//		.forEach(System.out::println);
	}
}

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

	// 好きな果物を食べたい量（単位付き）だけ持ってくるよう要求するメッセージを出力
	String require() {
		return name + "：" + fruit.name() + quantity + fruit.measureWord() + LINE;
	}

	void setFavoritefruit(Fruit fruit) { this.fruit = fruit; }
	void setQuantity(int quantity) { this.quantity = quantity; }
}

class Fruit {
	private String name; // 果物の名前
	private MeasureWord measureWord; // 果物の単位（単位クラスのオブジェクト）

	Fruit(String name, MeasureWord measureWord) {
		this.name = name;
		this.measureWord = measureWord;
	}

	String name() { return name; }
	String measureWord() { return measureWord.name(); }
}

class MeasureWord {
	static final MeasureWord KO = new MeasureWord("個");
	static final MeasureWord HON = new MeasureWord("本");
	static final MeasureWord HUSA = new MeasureWord( "房");
	private String name;

	private MeasureWord(String name) { this.name = name; }
	String name() { return name; }
}

enum StudentConstants {
	ANDO(new  Student("安藤さん", FruitConstants.APPLE, 5)),
	ITO(new Student("伊藤くん", FruitConstants.MIKAN, 8)),
	ENDO(new Student("遠藤さん", FruitConstants.STRAWBERRY, 10)),
	SATO(new Student("佐藤さん", FruitConstants.BANANA, 10)),
	SUZUKI(new Student("鈴木くん", FruitConstants.GRAPE, 4)),
	TANAKA(new Student("田中さん", FruitConstants.DORIAN, 7)),
	TESHIGAWARA(new Student("勅使河原くん", FruitConstants.DORIAN, 35)),;

	private Student student;

	private StudentConstants(Student student) { this.student = student; }
	String require() { return student.require(); }
}

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
}
