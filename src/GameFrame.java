import javax.swing.*;

//游戏界面类 将地图 人物添加到窗体
public class GameFrame extends JPanel{
	private static final long serialVersionUID = -3885877146330269125L;
	public GameFrame() {
		setLayout(null);
		allPanel allpan = new allPanel();
		allpan.setBounds(0, 0, 600, 600);
		add(allpan);
	}
	
}
