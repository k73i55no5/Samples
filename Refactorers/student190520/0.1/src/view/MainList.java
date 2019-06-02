package k73i55no5.refactorers.student190520.view;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import k73i55no5.refactorers.student190520.domain.MainServiceImpl;

final class MainList extends JList<Object> implements PropertyChangeListener {

	private MainList() {
		// イベントの監視対象を追加
		MainServiceImpl.getInstance().addPropertyChangeListenerToPcs(this);
	}

	JScrollPane getJScrollPane() {
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(320, 240));
		return scrollPane;
	}

	@SuppressWarnings("unchecked")
	@Override public void propertyChange(PropertyChangeEvent e) {
		DefaultListModel<Object> value = (DefaultListModel<Object>) e.getNewValue();
		if (e.getPropertyName().equals("setModelToList")) setModel(value);
	}

	static MainList getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainList INSTANCE = new MainList();
	}
}
