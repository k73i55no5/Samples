package k73i55no5.refactorers.student190520.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import k73i55no5.api.event.MyPropertyChangeEvent;
import k73i55no5.api.event.MyPropertyChangeSupport;
import k73i55no5.api.util.Cast;
import k73i55no5.api.util.PropertyKey;
import k73i55no5.refactorers.student190520.domain.properties.Event;
import k73i55no5.refactorers.student190520.domain.properties.Mode;
import k73i55no5.refactorers.student190520.infra.MainRepositoryImpl;

final class MainList extends JList<Object> implements PropertyChangeListener {

	enum MenuItem {
		ADD("追加", e -> pcs.firePropertyChange(Mode.ADD)),
		EDIT("編集", e -> pcs.firePropertyChange(Mode.EDIT)),
		REMOVE("削除", e -> pcs.firePropertyChange(Mode.REMOVE)),;

		JMenuItem item;

		MenuItem(String text, ActionListener listener) {
			JMenuItem item = new JMenuItem(text);
			item.addActionListener(listener);
			this.item = item;
		}

		JMenuItem get() { return item; }
	}

	private static MyPropertyChangeSupport pcs;

	private MainList() {
		// イベントの監視対象を追加
		MainRepositoryImpl.getInstance().addPropertyChangeListenerToPcs(this);
		// リストのポップアップメニューを初期化
		JPopupMenu popup = new JPopupMenu() {{
			Stream.of(MenuItem.class.getEnumConstants())
			.map(MenuItem::get)
			.forEach(this::add);
		}};
		// setComponentPopupMenuでは、ポップアップメニューが表示されない。
		JList<Object> list = this;
		addMouseListener(new MouseAdapter() {
			@Override public void mousePressed(MouseEvent e) { showPopup(e); }
			@Override public void mouseReleased(MouseEvent e) { showPopup(e); }
			public void showPopup(MouseEvent e) {
				// JListの項目が選択されており、かつ、	右クリックした場合
				if (!list.isSelectionEmpty() && e.isPopupTrigger()) {
					setSelectedIndex(locationToIndex(e.getPoint()));
					popup.show(list, e.getX(), e.getY());
				}
			}
		});
	}

	@Override public void propertyChange(PropertyChangeEvent evt) {
		MyPropertyChangeEvent mye = new MyPropertyChangeEvent(evt);
		PropertyKey key = mye.getKey();
		if (key == Event.MODEL_DESERIALIZED) {
			DefaultListModel<Object> model = Cast.from(mye.getValue());
			setModel(model);
		}
	}

	// 既存のaddPropertyChangeListenerをオーバーライドさせると不具合が起きる。
	public void addPropertyChangeListenerToPcs(PropertyChangeListener listener) {
		// クラス初期化時のぬるぽ防止用
		pcs = Optional.ofNullable(pcs).orElse(new MyPropertyChangeSupport(this));
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListenerfromPcs(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	JScrollPane getJScrollPane() {
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(320, 240));
		return scrollPane;
	}

	static MainList getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainList INSTANCE = new MainList();
	}
}
