package k73i55no5.refactorers.student190520.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import k73i55no5.refactorers.student190520.domain.MainService;
import k73i55no5.refactorers.student190520.domain.MainServiceImpl;
import k73i55no5.refactorers.student190520.domain.properties.ListToShow;

public final class MainController implements PropertyChangeListener {

	// Model
	private static MainService mainService = MainServiceImpl.getInstance();

	private MainController() {
		// イベントの監視対象を追加
		MainPanel.getInstance().addPropertyChangeListenerToPcs(this);
		// メイン画面を表示
		MainFrame.getInstance().setVisible(true);
	}

	@Override public void propertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName().equals(ListToShow.class.getSimpleName())) {
			new HashMap<ListToShow, Runnable>() {{
				put(ListToShow.STUDENT, () -> mainService.showStudentList());
				put(ListToShow.FRUIT, () -> mainService.showFruitList());
				put(ListToShow.MEASURE_WORD, () -> mainService.showMeasureWordList());
			}}.getOrDefault(e.getNewValue(), () -> {}).run();
		}
	}

	public static MainController getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainController INSTANCE = new MainController();
	}
}
