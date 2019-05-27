package k73i55no5.refactorers.student190520.domain;

import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

public interface MainService {

	JScrollPane getJScrollPaneOfList();
	ActionListener showStudentList();
	ActionListener showFruitList();
	ActionListener showMeasureWordList();
}
