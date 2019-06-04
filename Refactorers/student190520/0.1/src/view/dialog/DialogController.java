package k73i55no5.refactorers.student190520.view.dialog;

import java.util.EnumMap;

import k73i55no5.refactorers.student190520.domain.MainRepository;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.properties.List;
import k73i55no5.refactorers.student190520.domain.properties.Mode;
import k73i55no5.refactorers.student190520.infra.MainRepositoryImpl;

public final class DialogController {

	// 表示すべきダイアログのパネル
	private static AbstractInputDialogPanel currentDialogPanel;
	// リストで選択されている項目のインデックス
	private static int currentItemIndexOfList;

	private DialogController() {}

	// 表示すべきダイアログのパネルを設定
	public void setDialogPanel(List list) {
		currentDialogPanel =  new EnumMap<List, AbstractInputDialogPanel>(List.class) {{
			put(List.STUDENT, StudentInputDialogPanel.getInstance());
			put(List.FRUIT, FruitInputDialogPanel.getInstance());
			put(List.MEASURE_WORD, MeasureWordInputDialogPanel.getInstance());
		}}.get(list);
	}

	public void setItemIndexOfList(int index) {
		currentItemIndexOfList = index;
	}

	public void setValuesToDialogPanel(Object record) {
		currentDialogPanel.set(record);
	}

	public void showInputDialog(Mode mode) {
		// 追加モードならば、ダイアログの入力値をすべてクリアする。
		if (mode == Mode.ADD) currentDialogPanel.clear();
		// リポジトリにリストモデルの操作を命令
		MainRepository mainRepository = MainRepositoryImpl.getInstance();
		AbstractListModel model = currentDialogPanel.getListModel();
		mainRepository.setEntity(model);
		// ダイアログを表示
		currentDialogPanel.showInputDialog(mode)
		.ifPresent(record -> {
			new EnumMap<Mode, Runnable>(Mode.class) {{
				put(Mode.ADD, () -> mainRepository.addRecord(record));
				put(Mode.EDIT, () -> mainRepository.editRecord(currentItemIndexOfList, record));
			}}.get(mode).run();
		});
	}

	public static DialogController getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final DialogController INSTANCE = new DialogController();
	}
}
