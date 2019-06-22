package k73i55no5.refactorers.student190520.view.dialog;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import k73i55no5.api.util.Cast;
import k73i55no5.refactorers.student190520.domain.MeasureWord;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.list.MeasureWordListModel;

final class MeasureWordInputDialogPanel extends AbstractInputDialogPanel {

	private static JTextField name = new JTextField();

	private static final JComponent[][] COMPONENTS = {
		{ new JLabel("単位名"), name },
	};

	private MeasureWordInputDialogPanel() {
		super(COMPONENTS);
	}

	@Override void clear() {
		name.setText("");
	}

	@Override Object createRecord() {
		String name = MeasureWordInputDialogPanel.name.getText();
		return new MeasureWord(name);
	}

	@Override AbstractListModel getListModel() {
		return MeasureWordListModel.getCurrentModel();
	}

	@Override Object removeRecord() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override void set(Object record) {
		MeasureWord measureWord = Cast.from(record);
		name.setText(measureWord.name());
	}

	@Override void updateComboBoxModel() {} // 実装予定なし（コンボボックス未想定のため）

	static MeasureWordInputDialogPanel getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MeasureWordInputDialogPanel INSTANCE = new MeasureWordInputDialogPanel();
	}
}