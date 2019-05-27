package k73i55no5.refactorers.student190520.domain;

enum StudentConstants {
	ANDO(new Student("安藤さん", FruitConstants.APPLE, 5)),
	ITO(new Student("伊藤くん", FruitConstants.MIKAN, 8)),
	ENDO(new Student("遠藤さん", FruitConstants.STRAWBERRY, 10)),
	SATO(new Student("佐藤さん", FruitConstants.BANANA, 10)),
	SUZUKI(new Student("鈴木くん", FruitConstants.GRAPE, 4)),
	TANAKA(new Student("田中さん", FruitConstants.DORIAN, 7)),
	TESHIGAWARA(new Student("勅使河原くん", FruitConstants.DORIAN, 35)),;

	private Student student;

	private StudentConstants(Student student) { this.student = student; }
	String getInfo() { return student.getInfo(); }
	String require() { return student.require(); }
}