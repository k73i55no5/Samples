package k73i55no5.refactorers.student190520.infra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.DefaultListModel;

final class MainOrm {

	private String path = "student190520.txt";

	private MainOrm() {}

	@SuppressWarnings("unchecked")
	DefaultListModel<Object> deserialize() {
		DefaultListModel<Object> model = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			model = (DefaultListModel<Object>) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			// 本来は省略すべきでないが、ここでは省略。
		}
		return model;
	}

	void setPath(String path) {
		this.path = path;
	}

	void serialize(DefaultListModel<Object> model) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(model);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			// 本来は省略すべきでないが、ここでは省略。
		}
	}

	static MainOrm getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainOrm INSTANCE = new MainOrm();
	}
}
