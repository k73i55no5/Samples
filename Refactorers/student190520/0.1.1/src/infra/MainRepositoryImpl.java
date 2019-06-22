package k73i55no5.refactorers.student190520.infra;

import java.beans.PropertyChangeListener;
import java.util.Optional;

import k73i55no5.api.event.MyPropertyChangeSupport;
import k73i55no5.refactorers.student190520.domain.MainRepository;
import k73i55no5.refactorers.student190520.domain.list.AbstractListModel;
import k73i55no5.refactorers.student190520.domain.properties.Event;

public final class MainRepositoryImpl implements MainRepository {

	// Entity
	private static AbstractListModel model;
	// O/R Mapper
	private static MainOrm mainOrm = MainOrm.getInstance();

	private static MyPropertyChangeSupport pcs;

	private MainRepositoryImpl() {}

	@Override public void addRecord(Object record) {
		model.addElement(record);
		pcs.firePropertyChange(Event.MODEL_UPDATED);
	}

	@Override public void editRecord(int index, Object record) {
		model.set(index, record);
		pcs.firePropertyChange(Event.MODEL_UPDATED);
	}

	@Override public void get() {
		mainOrm.setPath(model.getListPath());
		model = (AbstractListModel) mainOrm.deserialize();
		model.setCurrentModel(model);
		pcs.firePropertyChange(Event.MODEL_DESERIALIZED, model);
	}

	@Override public void removeRecord(int index) {
		model.removeElementAt(index);
		pcs.firePropertyChange(Event.MODEL_UPDATED);
	}

	@Override public void setEntity(AbstractListModel model) {
		MainRepositoryImpl.model = model;
	}

	@Override public void store() {
		mainOrm.setPath(model.getListPath());
		mainOrm.serialize(model);
	}

	public void addPropertyChangeListenerToPcs(PropertyChangeListener listener) {
		pcs = Optional.ofNullable(pcs).orElse(new MyPropertyChangeSupport(this));
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListenerfromPcs(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public static MainRepositoryImpl getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainRepositoryImpl INSTANCE = new MainRepositoryImpl();
	}
}
