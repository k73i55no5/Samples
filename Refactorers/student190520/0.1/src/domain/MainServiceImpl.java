package k73i55no5.refactorers.student190520.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;

public final class MainServiceImpl implements MainService {

	private static PropertyChangeSupport pcs;

	private MainServiceImpl() {}

	@Override public void showStudentList() {
		DefaultListModel<String> model = new DefaultListModel<>();
		Stream.of(StudentConstants.class.getEnumConstants())
		.forEach(student -> model.addElement(student.getInfo()));
		pcs.firePropertyChange("setModelToList", "", model);
	}

	@Override public void showFruitList() {
		DefaultListModel<String> model = new DefaultListModel<>();
		Stream.of(FruitConstants.class.getEnumConstants())
		.forEach(fruit -> model.addElement(fruit.getInfo()));
		pcs.firePropertyChange("setModelToList", "", model);
	}

	@Override public void showMeasureWordList() {
		DefaultListModel<String> model = new DefaultListModel<>();
		Stream.of(MeasureWord.class.getDeclaredFields())
		.filter(f -> f.getType() == MeasureWord.class)
		.forEach(f -> {
			try {
				model.addElement(((MeasureWord) f.get(null)).name());
			} catch (Exception ex) {
				// 本来は省略すべきでないが、ここでは省略。
			}
		});
		pcs.firePropertyChange("setModelToList", "", model);
	}

	public void addPropertyChangeListenerToPcs(PropertyChangeListener listener) {
		pcs = Optional.ofNullable(pcs).orElse(new PropertyChangeSupport(this));
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListenerfromPcs(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public static MainServiceImpl getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainServiceImpl INSTANCE = new MainServiceImpl();
	}
}