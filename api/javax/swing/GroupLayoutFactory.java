package k73i55no5.api.javax.swing;

import java.awt.Container;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComponent;

public final class GroupLayoutFactory {

	public static GroupLayout createDialogLayout(Container host, JComponent[][] components) {
		GroupLayout layout = new GroupLayout(host);
		// コンポーネント間のギャップ設定
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// グループ作成
		int ny = components.length;
		int nx = components[0].length;
		// 水平方向のグループ
		SequentialGroup hg = layout.createSequentialGroup();
		for (int x = 0; x < nx; x++) {
			ParallelGroup pg = layout.createParallelGroup();
			for (int y = 0; y < ny; y++) {
				JComponent comp = components[y][x];
				Optional.ofNullable(comp).ifPresent(pg::addComponent);
			}
			hg.addGroup(pg);
		}
		layout.setHorizontalGroup(hg);
		// 垂直方向のグループ
		SequentialGroup vg = layout.createSequentialGroup();
		for (int y = 0; y < ny; y++) {
			ParallelGroup pg = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
			for (int x = 0; x < nx; x++) {
				JComponent comp = components[y][x];
				Optional.ofNullable(comp).ifPresent(pg::addComponent);
			}
			vg.addGroup(pg);
		}
		layout.setVerticalGroup(vg);
		return layout;
	}
}
