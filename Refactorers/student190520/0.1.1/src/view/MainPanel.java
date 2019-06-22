package k73i55no5.refactorers.student190520.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import k73i55no5.api.event.MyPropertyChangeSupport;
import k73i55no5.refactorers.student190520.domain.properties.List;
import k73i55no5.refactorers.student190520.domain.properties.Mode;

final class MainPanel extends JPanel {

	enum Button {
		STUDENT(List.STUDENT.title(), e -> pcs.firePropertyChange(List.STUDENT)),
		FRUIT(List.FRUIT.title(), e -> pcs.firePropertyChange(List.FRUIT)),
		MEASURE_WORD(List.MEASURE_WORD.title(), e -> pcs.firePropertyChange(List.MEASURE_WORD)),
		LOAD(Mode.LOAD.title(), e -> pcs.firePropertyChange(Mode.LOAD)),
		SAVE(Mode.SAVE.title(), e -> pcs.firePropertyChange(Mode.SAVE)),;

		private JButton button;

		private Button(String text, ActionListener listener) {
			JButton button = new JButton(text);
			button.addActionListener(listener);
			this.button = button;
		}

		JButton get() { return button; }
	}

	private static MyPropertyChangeSupport pcs;

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

	public void addPropertyChangeListenerToPcs(PropertyChangeListener listener) {
		pcs = Optional.ofNullable(pcs).orElse(new MyPropertyChangeSupport(this));
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListenerfromPcs(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	static MainPanel getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainPanel INSTANCE = new MainPanel();
	}
}
