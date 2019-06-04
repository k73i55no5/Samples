package k73i55no5.refactorers.student190520.domain;

import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;

public interface MainRepository {

	void addRecord(Object record);
	void editRecord(int index, Object record);
	void get();
	void removeRecord(int index);
	void setEntity(AbstractListModel model);
	void store();
}
