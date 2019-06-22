package k73i55no5.refactorers.student190520.domain;

import java.io.Serializable;

import k73i55no5.api.util.Cast;

public class Fruit implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name; // 果物の名前
	private MeasureWord measureWord; // 果物の単位（単位クラスのオブジェクト）

	public Fruit(String name, MeasureWord measureWord) {
		this.name = name;
		this.measureWord = measureWord;
	}

	// 果物の名前と単位名がそれぞれ等しければ、オブジェクトを等価とみなす。
	@Override public boolean equals(Object obj) {
		Fruit fruit = Cast.from(obj);
		if (!name().equals(fruit.name())) return false;
		if (!measureWord().name().equals(fruit.measureWord().name())) return false;
		return true;
	}

	@Override public String toString() { return name(); }
	public String name() { return name; }
	public MeasureWord measureWord() { return measureWord; }
}