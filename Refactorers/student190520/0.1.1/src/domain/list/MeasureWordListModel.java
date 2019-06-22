package k73i55no5.refactorers.student190520.domain.list;

import java.util.ArrayList;
import java.util.List;

import k73i55no5.api.util.Cast;
import k73i55no5.refactorers.student190520.domain.MeasureWord;

public final class MeasureWordListModel extends AbstractListModel {

	private static List<Object> defaults = new ArrayList<>() {{
		add(new MeasureWord("個"));
		add(new MeasureWord("本"));
		add(new MeasureWord("房"));
	}};
	private static MeasureWordListModel currentModel = new MeasureWordListModel();

	public MeasureWordListModel() {
		super(defaults, "student190520_m.txt");
	}

	public static AbstractListModel getCurrentModel() {
		return currentModel;
	}

	@Override public void setCurrentModel(AbstractListModel currentModel) {
		MeasureWordListModel.currentModel = Cast.from(currentModel);
	}
}
