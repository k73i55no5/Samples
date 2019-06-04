package k73i55no5.refactorers.student190520.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;

import k73i55no5.api.event.MyPropertyChangeEvent;
import k73i55no5.api.util.Cast;
import k73i55no5.api.util.PropertyKey;
import k73i55no5.refactorers.student190520.domain.MainRepository;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.list.FruitListModel;
import k73i55no5.refactorers.student190520.domain.list.MeasureWordListModel;
import k73i55no5.refactorers.student190520.domain.list.StudentListModel;
import k73i55no5.refactorers.student190520.domain.properties.List;
import k73i55no5.refactorers.student190520.domain.properties.Mode;
import k73i55no5.refactorers.student190520.infra.MainRepositoryImpl;
import k73i55no5.refactorers.student190520.view.dialog.DialogController;

public final class MainController implements PropertyChangeListener {

	// View
	private static MainList list = MainList.getInstance();
	// Controller
	private static DialogController dialogController = DialogController.getInstance();
	// Repository
	private static MainRepository mainRepository = MainRepositoryImpl.getInstance();
	// 入出力すべきリストのモデル
	private static AbstractListModel currentListModel = new StudentListModel();

	private MainController() {
		// イベントの監視対象を追加
		MainPanel.getInstance().addPropertyChangeListenerToPcs(this);
		list.addPropertyChangeListenerToPcs(this);
		// メイン画面を表示
		MainFrame.getInstance().setVisible(true);
	}

	@Override public void propertyChange(PropertyChangeEvent evt) {
		MyPropertyChangeEvent mye = new MyPropertyChangeEvent(evt);
		PropertyKey key = mye.getKey();
		if (key instanceof Mode) showInputDialog(Cast.from(key));
		else if (key instanceof List) setModelAndDialogPanel(Cast.from(key));
	}

	// 読込モードならば、直列化したリストのモデルを復元
	private void loadListModel() {
		mainRepository.setEntity(currentListModel);
		mainRepository.get();
	}

	// 削除モードならば、リストで選択されている項目を削除する。
	private void removeRecord() {
		mainRepository.setEntity(Cast.from(list.getModel()));
		mainRepository.removeRecord(list.getSelectedIndex());
	}

	// 書込モードならば、リストのモデルを直列化する。
	private void saveListModel() {
		mainRepository.setEntity(currentListModel);
		mainRepository.store();
	}

	// 入出力すべきリストのモデルを設定し、表示すべきダイアログのパネルを設定する。
	private void setModelAndDialogPanel(List list) {
		currentListModel = new EnumMap<List, AbstractListModel>(List.class) {{
			put(List.STUDENT, StudentListModel.getCurrentModel());
			put(List.FRUIT, FruitListModel.getCurrentModel());
			put(List.MEASURE_WORD, MeasureWordListModel.getCurrentModel());
		}}.get(list);
		MainController.list.setModel(currentListModel);
		dialogController.setDialogPanel(list);
	}

	// 編集モードならば、編集対象のレコードをリストから取得し、ダイアログにレコードの各値をセットしておく。
	private void setRecordValues() {
		dialogController.setItemIndexOfList(list.getSelectedIndex());
		dialogController.setValuesToDialogPanel(list.getSelectedValue());
	}

	// モードによってレコードやリストのモデルに対する操作を振り分けた後、ダイアログを表示する。
	private void showInputDialog(Mode mode) {
		switch (mode) {
			case ADD: break;
			case EDIT: setRecordValues(); break;
			case REMOVE: removeRecord(); return;
			case LOAD: loadListModel(); return;
			case SAVE: saveListModel(); return;
		}
		dialogController.showInputDialog(mode);
	}

	public static MainController getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainController INSTANCE = new MainController();
	}
}
