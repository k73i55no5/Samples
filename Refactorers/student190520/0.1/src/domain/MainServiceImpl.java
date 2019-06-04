package k73i55no5.refactorers.student190520.domain;

public final class MainServiceImpl implements MainService {

	private MainServiceImpl() {}

	public static MainServiceImpl getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainServiceImpl INSTANCE = new MainServiceImpl();
	}
}

