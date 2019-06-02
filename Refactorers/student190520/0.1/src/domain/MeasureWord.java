package k73i55no5.refactorers.student190520.domain;

class MeasureWord {
	static final MeasureWord KO = new MeasureWord("個");
	static final MeasureWord HON = new MeasureWord("本");
	static final MeasureWord HUSA = new MeasureWord( "房");
	private String name;

	private MeasureWord(String name) { this.name = name; }
	String name() { return name; }
}