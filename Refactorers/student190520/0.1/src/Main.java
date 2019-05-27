package k73i55no5.refactorers.student190520;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.UIManager;

import k73i55no5.refactorers.student190520.view.MainFrame;

final class Main extends JPanel {

	private Main() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		MainFrame.getInstance().setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new Main());
	}
}
