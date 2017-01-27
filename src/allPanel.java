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
	 * ��������ʾ�������   �ж���Ϸ���ս�� �������ж���
	 */
	private static final long serialVersionUID = -3822802805738853186L;
	//�������� ͨ��һ�μ�1
	public static int monsterNumber=3;
	//�ж���Ϸ�Ƿ����
	public static boolean isnotOver=true;
	//��������
	public static ArrayList<Monster> MonsterList;
	//��Ϸʤ�����߽�������
	JDialog victory;
	JDialog gameover;
	
	public allPanel(){
		this.setFocusable(true);
		MonsterList=new ArrayList<Monster>();
		//��ʼ����ӹ���
		addMonster();
		Thread allp=new Thread(this);
		allp.start();
		
	}
	public void paint(Graphics g){
		super.paint(g);
		Image imageMonster=getToolkit().getImage("monster.png");
		//���ƹ���  
		for(int i=0;i<MonsterList.size();i++){
			if(MonsterList.get(i).isLive==true){//�������Ƿ���
			g.drawImage(imageMonster, MonsterList.get(i).getyMonster()*5, MonsterList.get(i).getxMonster()*5, 30, 30, null);
			}else{
				MonsterList.remove(i);//�����Ƴ�
			}
		}
		//�жϹ����Ƿ��������ײ
		for(int i=0;i<MonsterList.size();i++){
			if(MonsterList.get(i).returnMonster().intersects(Player.isCollisionMonster())){
				Player.death=true;
			}
		}
		 
	}
	//������Ϸ���߳�
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			if(isnotOver==true){
				repaint();
				//��Ϸʤ������ʧ�ܵĵ���
					if(MonsterList.size()==0){//��Ϸ����Ϊ0 ��Ϸʤ��
						victory();
						isnotOver=false;
					}
					if(Player.death==true){
						//����ը��
						Boom.boomExistence=false;
						Boom.timeCount=0;
						//��ʾ��Ϸʧ�ܴ���
						gameover();
						isnotOver=false;
					}
			}	
		}
	}
	//��Ϸʤ���ǵ��÷���
	private void victory() {
			
		victory = new JDialog();
		victory.setSize(516, 246);
		victory.setLocationRelativeTo(null);
		victory.setLayout(null);
		JLabel info = new JLabel(new ImageIcon("win.jpg"));
		info.setBounds(0, 0, 501, 176);
		JLabel gameinfo = new JLabel("���յ÷֣�"+Boom.scroll);
		gameinfo.setBounds(90, 176, 100, 30);
		JButton butun1=new JButton("��һ�ؿ�");
		butun1.setBounds(0, 176, 90, 30);
		butun1.addActionListener(this);
		victory.add(butun1);
		victory.add(info);
		victory.add(gameinfo);
		victory.setVisible(true);
	}
	//��Ϸʧ��ʱ����
	private void gameover() {

		gameover = new JDialog();
		gameover.setSize(480, 300);
		gameover.setLocationRelativeTo(null);
		gameover.setLayout(null);
		JLabel info2 = new JLabel(new ImageIcon("gameover.jpg"));
		info2.setBounds(0, 0, 465, 230);
		JLabel gameinfo2 = new JLabel("���յ÷֣�"+Boom.scroll);
		gameinfo2.setBounds(100, 230, 90, 30);
		gameover.add(info2);
		gameover.add(gameinfo2);
		JButton butun2=new JButton("����һ��");
		butun2.setBounds(0, 230, 90, 30);
		butun2.addActionListener(this);
		gameover.add(butun2);
		gameover.setVisible(true);
	}
	//��ӹ���
	public static void addMonster(){
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(MapArr.Map[i][j]==4){
				Monster mon=new Monster(i*6, j*6);//���������6�ı���  ���Ҵ���6������6֮��Ϊ��ͼ��ʵ����ֵ
				Thread mont=new Thread(mon);
				MapArr.Map[i][j]=0;
				mont.start();
				MonsterList.add(mon);
				}
			}
		}
	}
	
	//��ť�¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("����һ��")){
			GameMainUI.gameinit();
			gameover.dispose();
		}
		if(e.getActionCommand().equals("��һ�ؿ�")){
			monsterNumber+=1;//ͨ�� ����������1
			GameMainUI.gameinit();
			victory.dispose();
		}
	}
}
