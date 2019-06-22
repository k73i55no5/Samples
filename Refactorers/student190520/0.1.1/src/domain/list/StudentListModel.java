package k73i55no5.refactorers.student190520.domain.list;

import java.util.ArrayList;
import java.util.List;

import k73i55no5.api.util.Cast;
import k73i55no5.refactorers.student190520.domain.Fruit;
import k73i55no5.refactorers.student190520.domain.MeasureWord;
import k73i55no5.refactorers.student190520.domain.Student;

public final class StudentListModel extends AbstractListModel {

	private static List<Object> defaults = new ArrayList<>() {{
		add(new Student("安藤さん", new Fruit("りんご", new MeasureWord("個")), 5));
		add(new Student("伊藤くん", new Fruit("みかん", new MeasureWord("個")), 8));
		add(new Student("遠藤さん", new Fruit("いちご", new MeasureWord("個")), 10));
		add(new Student("佐藤さん", new Fruit("バナナ", new MeasureWord("本")), 10));
		add(new Student("鈴木くん", new Fruit("ぶどう", new MeasureWord( "房")), 4));
		add(new Student("田中さん", new Fruit("ドリアン", new MeasureWord("個")), 7));
		add(new Student("勅使河原くん", new Fruit("ドリアン", new MeasureWord("個")), 35));
	}};
	private static StudentListModel currentModel = new StudentListModel();

	public StudentListModel() {
		super(defaults, "student190520_s.txt");
	}

	public static AbstractListModel getCurrentModel() {
		return currentModel;
	}

	@Override public void setCurrentModel(AbstractListModel currentModel) {
		StudentListModel.currentModel = Cast.from(currentModel);
	}
}
