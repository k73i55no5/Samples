package k73i55no5.refactorers.student190520.view.dialog;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import k73i55no5.api.util.Cast;
import k73i55no5.refactorers.student190520.domain.Fruit;
import k73i55no5.refactorers.student190520.domain.MeasureWord;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.list.FruitListModel;
import k73i55no5.refactorers.student190520.domain.list.MeasureWordListModel;

final class FruitInputDialogPanel extends AbstractInputDialogPanel {

	private static JTextField name = new JTextField();
	private static JComboBox<Object> measureWord =
		new JComboBox<>(MeasureWordListModel.getCurrentModel().toComboBoxModel());

	private static final JComponent[][] COMPONENTS = {
		{ new JLabel("果物の名前"), name },
		{ new JLabel("果物の単位"), measureWord },
	};

	private FruitInputDialogPanel() {
		super(COMPONENTS);
	}

	@Override void clear() {
		name.setText("");
		measureWord.setSelectedIndex(0);
	}

	@Override Object createRecord() {
		String name = FruitInputDialogPanel.name.getText();
		MeasureWord measureWord = Cast.from(FruitInputDialogPanel.measureWord.getSelectedItem());
		return new Fruit(name, measureWord);
	}

	@Override AbstractListModel getListModel() {
		return FruitListModel.getCurrentModel();
	}

	@Override Object removeRecord() {
		return null;
	}

	@Override void set(Object record) {
		Fruit fruit = Cast.from(record);
		name.setText(fruit.name());
		measureWord.setSelectedItem(fruit.measureWord());
	}

	@Override void updateComboBoxModel() {
		measureWord.setModel(MeasureWordListModel.getCurrentModel().toComboBoxModel());
	}

	static FruitInputDialogPanel getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final FruitInputDialogPanel INSTANCE = new FruitInputDialogPanel();
	}
}