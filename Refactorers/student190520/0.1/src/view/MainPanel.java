package k73i55no5.refactorers.student190520.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import k73i55no5.refactorers.student190520.domain.properties.ListToShow;

final class MainPanel extends JPanel {

	enum Button {
		STUDENT("生徒リスト", e -> firePropertyChange(ListToShow.STUDENT)),
		FRUIT("果物リスト", e -> firePropertyChange(ListToShow.FRUIT)),
		MEASURE_WORD("単位リスト", e -> firePropertyChange(ListToShow.MEASURE_WORD)),;

		private JButton button;

		private Button(String text, ActionListener listener) {
			JButton button = new JButton(text);
			button.addActionListener(listener);
			this.button = button;
		}

		JButton get() { return button; }
	}

	private static PropertyChangeSupport pcs;

	private MainPanel() {
		super(new BorderLayout());
		// UIを初期化
		JPanel west = new JPanel() {{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBorder(new EmptyBorder(5, 5, 5, 0));
			Stream.of(Button.class.getEnumConstants())
			.map(Button::get)
			.forEach(this::add);
		}};
		JPanel center = new JPanel() {{
			add(MainList.getInstance().getJScrollPane());
		}};
		add(west, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
	}

	// 既存のaddPropertyChangeListenerをオーバーライドさせると不具合が起きることがある。
	public void addPropertyChangeListenerToPcs(PropertyChangeListener listener) {
		// クラス初期化時のぬるぽ防止用
		pcs = Optional.ofNullable(pcs).orElse(new PropertyChangeSupport(this));
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListenerfromPcs(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	private static void firePropertyChange(ListToShow listToShow) {
		pcs.firePropertyChange(ListToShow.class.getSimpleName(), "", listToShow);
	}

	static MainPanel getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainPanel INSTANCE = new MainPanel();
	}
}
