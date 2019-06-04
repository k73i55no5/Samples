package k73i55no5.refactorers.student190520.domain;

import java.io.Serializable;

import k73i55no5.api.util.Cast;

public class MeasureWord implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name; // 単位名

	public MeasureWord(String name) { this.name = name; }

	// 単位名が等しければ、オブジェクトを等価とみなす。
	@Override public boolean equals(Object obj) {
		MeasureWord measureWord = Cast.from(obj);
		return name().equals(measureWord.name());
	}

	@Override public String toString() { return name(); }
	public String name() { return name; }
}