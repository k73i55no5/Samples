package k73i55no5.api.event;

import java.beans.PropertyChangeEvent;

import k73i55no5.api.util.Cast;
import k73i55no5.api.util.PropertyKey;

public class MyPropertyChangeEvent {

	private final PropertyChangeEvent evt;

	public MyPropertyChangeEvent(PropertyChangeEvent evt) {
		this.evt = evt;
	}

	public PropertyKey getKey() {
		return Cast.from(evt.getOldValue());
	}

	public <T> T getValue() {
		return Cast.from(evt.getNewValue());
	}
}
