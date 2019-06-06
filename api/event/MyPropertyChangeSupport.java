package k73i55no5.api.event;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import k73i55no5.api.util.PropertyKey;

public final class MyPropertyChangeSupport {

	private final PropertyChangeSupport pcs;

	public MyPropertyChangeSupport(Object sourceBean) {
		pcs = new PropertyChangeSupport(sourceBean);
	}

	// 元クラスの同メソッドの第二引数をキーオブジェクトとして扱う。
	public void firePropertyChange(PropertyKey key) {
		pcs.firePropertyChange(null, key, null);
	}

	// 元クラスの同メソッドの第二引数をキーオブジェクトとして、第三引数をバリューオブジェクトとして扱う。
	public void firePropertyChange(PropertyKey key, Object value) {
		pcs.firePropertyChange(null, key, value);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
