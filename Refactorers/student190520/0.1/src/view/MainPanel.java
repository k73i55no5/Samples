package k73i55no5.refactorers.student190520.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import k73i55no5.refactorers.student190520.domain.MainService;
import k73i55no5.refactorers.student190520.domain.MainServiceImpl;

public final class MainPanel extends JPanel {

	enum Button {
		STUDENT("生徒リスト", mainService.showStudentList()),
		FRUIT("果物リスト", mainService.showFruitList()),
		MEASURE_WORD("単位リスト", mainService.showMeasureWordList()),;

		private JButton button;

		private Button(String text, ActionListener listener) {
			JButton button = new JButton(text);
			button.addActionListener(listener);
			this.button = button;
		}

		JButton get() { return button; }
	}

	private static MainService mainService = MainServiceImpl.getInstance();

	private MainPanel() {
		super(new BorderLayout());
		JPanel west = new JPanel() {{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBorder(new EmptyBorder(5, 5, 5, 0));
			Stream.of(Button.class.getEnumConstants())
			.map(Button::get)
			.forEach(this::add);
		}};
		JPanel center = new JPanel() {{
			add(mainService.getJScrollPaneOfList());
		}};
		add(west, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
	}

	public static MainPanel getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainPanel INSTANCE = new MainPanel();
	}
}
