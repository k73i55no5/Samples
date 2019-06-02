package k73i55no5.refactorers.student190520;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.UIManager;

import k73i55no5.refactorers.student190520.view.MainController;

final class Main {

	private Main() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		MainController.getInstance();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new Main());
	}
}
