package k73i55no5.refactorers.student190520.view.dialog;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import k73i55no5.api.util.Cast;
import k73i55no5.refactorers.student190520.domain.Fruit;
import k73i55no5.refactorers.student190520.domain.Student;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.list.FruitListModel;
import k73i55no5.refactorers.student190520.domain.list.StudentListModel;

final class StudentInputDialogPanel extends AbstractInputDialogPanel {

	private static JTextField name = new JTextField();
	private static JComboBox<Object> fruit =
		new JComboBox<>(FruitListModel.getCurrentModel().toComboBoxModel());
	private static JTextField quantity = new JTextField();

	private static final JComponent[][] COMPONENTS = {
		{ new JLabel("生徒の名前"), name },
		{ new JLabel("好きな果物"), fruit },
		{ new JLabel("食べたい量"), quantity },
	};

	private StudentInputDialogPanel() {
		super(COMPONENTS);
	}

	@Override void clear() {
		name.setText("");
		fruit.setSelectedIndex(0);
		quantity.setText("");
	}

	@Override Object createRecord() {
		String name = StudentInputDialogPanel.name.getText();
		Fruit fruit =Cast.from(StudentInputDialogPanel.fruit.getSelectedItem());
		int quantity = Integer.parseInt(StudentInputDialogPanel.quantity.getText());
		return new Student(name, fruit, quantity);
	}

	@Override AbstractListModel getListModel() {
		return StudentListModel.getCurrentModel();
	}

	@Override Object removeRecord() {
		return null;
	}

	@Override void set(Object record) {
		Student student = Cast.from(record);
		name.setText(student.name());
		fruit.setSelectedItem(student.getFavoriteFruit());
		quantity.setText(String.valueOf(student.getQuantityToEat()));
	}

	@Override void updateComboBoxModel() {
		fruit.setModel(FruitListModel.getCurrentModel().toComboBoxModel());
	}

	static StudentInputDialogPanel getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final StudentInputDialogPanel INSTANCE = new StudentInputDialogPanel();
	}
}