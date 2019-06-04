package k73i55no5.refactorers.student190520.domain.properties;

import k73i55no5.api.lang.enumerate.ETitle;
import k73i55no5.api.util.PropertyKey;

public enum List implements ETitle, PropertyKey {
	STUDENT("生徒リスト"),
	FRUIT("果物リスト"),
	MEASURE_WORD("単位リスト"),;

	String title;

	List(String title) { this.title = title; }
	@Override public String title() { return title; }
}