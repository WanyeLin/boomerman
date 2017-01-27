import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;
import javax.swing.*;


//游戏窗体   开始游戏和重新游戏初始化
public class GameMainUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -2901340197707284288L;
	public static JLabel scroll;
	public static JLabel boomlevel;
	public JLabel bgimagela;
	//菜单栏
	private MenuBar mb;
	private Menu game;
	private MenuItem gamestart;
	private MenuItem newgame;
	private GameFrame gf;
	private MenuItem quit;
	//首页图片容器
	JPanel panel;
	public static boolean gameIsStart=false;
	//构造方法
	public GameMainUI(){
		setGameMainUI();
		mb=new MenuBar();
		game=new Menu("游戏菜单");
		gamestart=new MenuItem("游戏开始");
		newgame=new MenuItem("重新开始");
		quit=new MenuItem("退出游戏");
		gamestart.addActionListener(this);
		newgame.addActionListener(this);
		quit.addActionListener(this);
		game.add(gamestart);
		game.add(newgame);
		game.add(quit);
		mb.add(game);
		setMenuBar(mb);
		scroll=new JLabel("分数："+Boom.scroll);
		scroll.setBounds(0, 600, 60, 20);
		boomlevel=new JLabel("威力："+Boom.boomlevel);
		boomlevel.setBounds(540, 600, 60, 20);
		add(scroll);
		add(boomlevel);
		addWindowListener(new close());
	}
	
	//设置窗口主界面
	private void setGameMainUI() {
		setLayout(null);
		setTitle("炸弹人");
		setSize(605, 670);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		panel = new JPanel();
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon("bgimage.jpg");//创建图片对象
		label.setIcon(img);
		panel.add(label);
		panel.setBounds(0, 145, 600, 293);
		getContentPane().add(panel);
		
	}
	//关闭窗口
		class close extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(gameIsStart==false){
				if(e.getActionCommand().equals("游戏开始")){
					gf=new GameFrame();
					gf.setBounds(0, 0, 600, 600);
					add(gf);
					this.repaint();
					gameIsStart=true;
				}
			}
			if(e.getActionCommand().equals("重新开始")){
				gameinit();
			}
			if(e.getActionCommand().equals("退出游戏")){
				System.exit(0);
			}
		}
		//游戏初始化
		public static void gameinit(){
			//初始化人物位置
			Player.xPos=6;
			Player.yPos=6;
			//清空怪物
			allPanel.MonsterList.clear();
			//地图初始化
			MapArr.mapInitialization();
			//将怪物添加到地图
			allPanel.addMonster();
			//初始化游戏和人物信息
			allPanel.isnotOver=true;
			Player.death=false;
			//初始化得分和炸弹
			Boom.scroll=0;
			GameMainUI.scroll.setText("分数："+Boom.scroll);
			Boom.boomlevel=2;
			GameMainUI.boomlevel.setText("威力："+Boom.boomlevel);
			
		}
}
