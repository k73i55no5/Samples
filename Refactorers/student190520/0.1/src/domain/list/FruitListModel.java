package k73i55no5.refactorers.student190520.domain.list;

import java.util.ArrayList;
import java.util.List;

import k73i55no5.api.util.Cast;
import k73i55no5.refactorers.student190520.domain.Fruit;
import k73i55no5.refactorers.student190520.domain.MeasureWord;

public final class FruitListModel extends AbstractListModel {

	private static List<Object> defaults = new ArrayList<>() {{
		add(new Fruit("りんご", new MeasureWord("個")));
		add(new Fruit("みかん", new MeasureWord("個")));
		add(new Fruit("バナナ", new MeasureWord("本")));
		add(new Fruit("ぶどう", new MeasureWord( "房")));
		add(new Fruit("ドリアン", new MeasureWord("個")));
		add(new Fruit("いちご", new MeasureWord("個")));
	}};
	private static FruitListModel currentModel = new FruitListModel();

	public FruitListModel() {
		super(defaults, "student190520_f.txt");
	}

	public static AbstractListModel getCurrentModel() {
		return currentModel;
	}

	@Override public void setCurrentModel(AbstractListModel currentModel) {
		FruitListModel.currentModel = Cast.from(currentModel);
	}
}
