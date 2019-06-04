package k73i55no5.refactorers.student190520.view.dialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;
import java.util.Optional;
import java.util.function.Supplier;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import k73i55no5.api.event.MyPropertyChangeEvent;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.properties.Event;
import k73i55no5.refactorers.student190520.domain.properties.Mode;
import k73i55no5.refactorers.student190520.infra.MainRepositoryImpl;

abstract class AbstractInputDialogPanel extends JPanel implements PropertyChangeListener {

	AbstractInputDialogPanel(JComponent[][] components) {
		// イベントの監視対象を追加
		MainRepositoryImpl.getInstance().addPropertyChangeListenerToPcs(this);
		// ダイアログに用いるレイアウト（パネル）の初期化
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		// コンポーネント間のギャップ設定
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// グループ作成
		int ny = components.length;
		int nx = components[0].length;
		// 水平方向のグループ
		SequentialGroup hg = layout.createSequentialGroup();
		for (int x = 0; x < nx; x++) {
			ParallelGroup pg = layout.createParallelGroup();
			for (int y = 0; y < ny; y++) {
				JComponent c = components[y][x];
				if (c != null) {
					pg.addComponent(c);
				}
			}
			hg.addGroup(pg);
		}
		layout.setHorizontalGroup(hg);
		// 垂直方向のグループ
		SequentialGroup vg = layout.createSequentialGroup();
		for (int y = 0; y < ny; y++) {
			ParallelGroup pg = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
			for (int x = 0; x < nx; x++) {
				JComponent c = components[y][x];
				if (c != null) {
					pg.addComponent(c);
				}
			}
			vg.addGroup(pg);
		}
		layout.setVerticalGroup(vg);
	}

	abstract void clear();

	abstract Object createRecord();

	abstract AbstractListModel getListModel();

	abstract Object removeRecord();

	@Override public void propertyChange(PropertyChangeEvent evt) {
		MyPropertyChangeEvent mye = new MyPropertyChangeEvent(evt);
		if (mye.getKey() == Event.MODEL_UPDATED) updateComboBoxModel();
	}

	abstract void set(Object record);

	// ダイアログからの入力を受け取り、オブジェクトを生成する。
	Optional<Object> showInputDialog(Mode mode) {
		// モードによる挙動の振り分け
		Supplier<Object> action = () -> {
			return new EnumMap<Mode, Supplier<Object>>(Mode.class) {{
				put(Mode.ADD, () -> createRecord());
				put(Mode.EDIT, () -> createRecord());
				put(Mode.REMOVE, () -> removeRecord());
			}}.get(mode).get();
		};
		// ダイアログの表示
		int option = JOptionPane.showConfirmDialog(
			null,
			this,
			mode.title(),
			JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.INFORMATION_MESSAGE);
		return (option == JOptionPane.YES_OPTION) ? Optional.of(action.get()) : Optional.empty();
	}

	abstract void updateComboBoxModel();
}