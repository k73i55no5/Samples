package k73i55no5.api.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.stream.Stream;

public class MyProperties extends Properties {

	private String name;

	public MyProperties(String name) {
		load(name);
	}

	public String getMyProperty(PropertyKey key) {
		load(name);
		return getProperty(key.toString());
	}

	public String[] getMyProperties(PropertyKey... keys) {
		load(name);
		return Stream.of(keys)
			.map(e -> getMyProperty(e))
			.toArray(String[]::new);
	}

	public int getMyPropertyAsInt(PropertyKey key) {
		return Integer.parseInt(getMyProperty(key));
	}

	public void setMyProperty(PropertyKey key, Object value) {
		setProperty(key.toString(), value.toString());
		store();
	}

	public void setMyProperties(PropertyKey[] keys, String[] values) {
		Iterator<String> iterator = Arrays.asList(values).iterator();
		Stream.of(keys).forEach(e -> {
			setProperty(e.toString(), iterator.next());
			store();
		});
	}

	private void load(String name) {
		try {
			load(new FileInputStream(name));
		} catch (IOException e) {
			// TODO 未実装
		}
		this.name = name;
	}

	private void store() {
		try {
			store(new FileOutputStream(name), null);
		} catch (IOException e) {
			// TODO 未実装
		}
	}
}
