package k73i55no5.refactorers.student190520.domain;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public final class MainServiceImpl implements MainService {

	private static JList<String> list = new JList<>();
	private static DefaultListModel<String> model;

	private MainServiceImpl() {}

	@Override public JScrollPane getJScrollPaneOfList() {
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(320, 240));
		return scrollPane;
	}

	@Override public ActionListener showStudentList() {
		return e -> {
			model = new DefaultListModel<>();
			Stream.of(StudentConstants.class.getEnumConstants())
			.forEach(student -> model.addElement(student.getInfo()));
			list.setModel(model);
		};
	}

	@Override public ActionListener showFruitList() {
		return e -> {
			model = new DefaultListModel<>();
			Stream.of(FruitConstants.class.getEnumConstants())
			.forEach(fruit -> model.addElement(fruit.getInfo()));
			list.setModel(model);
		};
	}

	@Override public ActionListener showMeasureWordList() {
		return e -> {
			model = new DefaultListModel<>();
			Stream.of(MeasureWord.class.getDeclaredFields())
			.filter(f -> f.getType() == MeasureWord.class)
			.forEach(f -> {
				try {
					model.addElement(((MeasureWord) f.get(null)).name());
				} catch (Exception ex) {
					// 本来は省略すべきでないが、ここでは省略。
				}
			});
			list.setModel(model);
		};
	}

	public static MainServiceImpl getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainServiceImpl INSTANCE = new MainServiceImpl();
	}
}
