import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class allPanel extends Boom implements Runnable,ActionListener{
	/**
	 * 将怪物显示在面板上   判断游戏最终结果 集中所有东西
	 */
	private static final long serialVersionUID = -3822802805738853186L;
	//怪物数量 通关一次加1
	public static int monsterNumber=3;
	//判断游戏是否结束
	public static boolean isnotOver=true;
	//怪物数组
	public static ArrayList<Monster> MonsterList;
	//游戏胜利或者结束窗口
	JDialog victory;
	JDialog gameover;
	
	public allPanel(){
		this.setFocusable(true);
		MonsterList=new ArrayList<Monster>();
		//初始化添加怪物
		addMonster();
		Thread allp=new Thread(this);
		allp.start();
		
	}
	public void paint(Graphics g){
		super.paint(g);
		Image imageMonster=getToolkit().getImage("monster.png");
		//绘制怪物  
		for(int i=0;i<MonsterList.size();i++){
			if(MonsterList.get(i).isLive==true){//看怪物是否存活
			g.drawImage(imageMonster, MonsterList.get(i).getyMonster()*5, MonsterList.get(i).getxMonster()*5, 30, 30, null);
			}else{
				MonsterList.remove(i);//死亡移除
			}
		}
		//判断怪物是否与玩家碰撞
		for(int i=0;i<MonsterList.size();i++){
			if(MonsterList.get(i).returnMonster().intersects(Player.isCollisionMonster())){
				Player.death=true;
			}
		}
		 
	}
	//运行游戏多线程
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if(isnotOver==true){
				repaint();
				//游戏胜利或者失败的弹窗
					if(MonsterList.size()==0){//游戏怪物为0 游戏胜利
						victory();
						isnotOver=false;
					}
					if(Player.death==true){
						//消除炸弹
						Boom.boomExistence=false;
						Boom.timeCount=0;
						//显示游戏失败窗口
						gameover();
						isnotOver=false;
					}
			}	
		}
	}
	//游戏胜利是调用方法
	private void victory() {
			
		victory = new JDialog();
		victory.setSize(516, 246);
		victory.setLocationRelativeTo(null);
		victory.setLayout(null);
		JLabel info = new JLabel(new ImageIcon("win.jpg"));
		info.setBounds(0, 0, 501, 176);
		JLabel gameinfo = new JLabel("最终得分："+Boom.scroll);
		gameinfo.setBounds(90, 176, 100, 30);
		JButton butun1=new JButton("下一关卡");
		butun1.setBounds(0, 176, 90, 30);
		butun1.addActionListener(this);
		victory.add(butun1);
		victory.add(info);
		victory.add(gameinfo);
		victory.setVisible(true);
	}
	//游戏失败时调用
	private void gameover() {

		gameover = new JDialog();
		gameover.setSize(480, 300);
		gameover.setLocationRelativeTo(null);
		gameover.setLayout(null);
		JLabel info2 = new JLabel(new ImageIcon("gameover.jpg"));
		info2.setBounds(0, 0, 465, 230);
		JLabel gameinfo2 = new JLabel("最终得分："+Boom.scroll);
		gameinfo2.setBounds(100, 230, 90, 30);
		gameover.add(info2);
		gameover.add(gameinfo2);
		JButton butun2=new JButton("再试一次");
		butun2.setBounds(0, 230, 90, 30);
		butun2.addActionListener(this);
		gameover.add(butun2);
		gameover.setVisible(true);
	}
	//添加怪物
	public static void addMonster(){
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(MapArr.Map[i][j]==4){
				Monster mon=new Monster(i*6, j*6);//坐标必须是6的倍数  并且大于6；除以6之后为地图真实坐标值
				Thread mont=new Thread(mon);
				MapArr.Map[i][j]=0;
				mont.start();
				MonsterList.add(mon);
				}
			}
		}
	}
	
	//按钮事件
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("再试一次")){
			GameMainUI.gameinit();
			gameover.dispose();
		}
		if(e.getActionCommand().equals("下一关卡")){
			monsterNumber+=1;//通关 怪物数量加1
			GameMainUI.gameinit();
			victory.dispose();
		}
	}
}
