package k73i55no5.api.event;

import java.beans.PropertyChangeSupport;

import k73i55no5.api.util.PropertyKey;

public class MyPropertyChangeSupport extends PropertyChangeSupport {

	public MyPropertyChangeSupport(Object sourceBean) {
		super(sourceBean);
	}

	// 元クラス（親クラス）の同メソッドの第二引数をキーオブジェクトとして扱う。
	public void firePropertyChange(PropertyKey key) {
		firePropertyChange(null, key, null);
	}

	// 元クラス（親クラス）の同メソッドの第二引数をキーオブジェクトとして、第三引数をバリューオブジェクトとして扱う。
	public void firePropertyChange(PropertyKey key, Object value) {
		firePropertyChange(null, key, value);
	}
}
