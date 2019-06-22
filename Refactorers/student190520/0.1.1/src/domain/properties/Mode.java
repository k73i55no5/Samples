package k73i55no5.refactorers.student190520.domain.properties;

import k73i55no5.api.lang.enumerate.ETitle;
import k73i55no5.api.util.PropertyKey;

public enum Mode implements ETitle, PropertyKey {
	ADD("追加"),
	EDIT("編集"),
	REMOVE("削除"),
	SAVE("書込"),
	LOAD("読込"),;

	String title;

	Mode(String title) { this.title = title; }
	@Override public String title() { return title; }
}
