package k73i55no5.refactorers.student190520.domain.list;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

public abstract class AbstractListModel extends DefaultListModel<Object> {

	// モデルのパス
	private final String path;

	AbstractListModel(List<Object> list, String path) {
		this.path = path;
		// TODO 自作クラスのオブジェクトについては、ラムダ式の変数名を省略させない。
		list.stream().forEach(o -> addElement(o));
	}

	public String getListPath() { return path; }

	public abstract void setCurrentModel(AbstractListModel currentModel);

	public ComboBoxModel<Object> toComboBoxModel() {
		return new JComboBox<>(toArray()).getModel();
	}
}
