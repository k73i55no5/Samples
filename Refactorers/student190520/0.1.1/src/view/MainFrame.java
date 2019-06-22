package k73i55no5.refactorers.student190520.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

final class MainFrame extends JFrame {

	private MainFrame() {
		super("テスト");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		add(MainPanel.getInstance(), BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}

	static MainFrame getInstance() { return Holder.INSTANCE; }
	private static class Holder {
		static final MainFrame INSTANCE = new MainFrame();
	}
}
