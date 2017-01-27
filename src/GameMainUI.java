import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;
import javax.swing.*;


//��Ϸ����   ��ʼ��Ϸ��������Ϸ��ʼ��
public class GameMainUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -2901340197707284288L;
	public static JLabel scroll;
	public static JLabel boomlevel;
	public JLabel bgimagela;
	//�˵���
	private MenuBar mb;
	private Menu game;
	private MenuItem gamestart;
	private MenuItem newgame;
	private GameFrame gf;
	private MenuItem quit;
	//��ҳͼƬ����
	JPanel panel;
	public static boolean gameIsStart=false;
	//���췽��
	public GameMainUI(){
		setGameMainUI();
		mb=new MenuBar();
		game=new Menu("��Ϸ�˵�");
		gamestart=new MenuItem("��Ϸ��ʼ");
		newgame=new MenuItem("���¿�ʼ");
		quit=new MenuItem("�˳���Ϸ");
		gamestart.addActionListener(this);
		newgame.addActionListener(this);
		quit.addActionListener(this);
		game.add(gamestart);
		game.add(newgame);
		game.add(quit);
		mb.add(game);
		setMenuBar(mb);
		scroll=new JLabel("������"+Boom.scroll);
		scroll.setBounds(0, 600, 60, 20);
		boomlevel=new JLabel("������"+Boom.boomlevel);
		boomlevel.setBounds(540, 600, 60, 20);
		add(scroll);
		add(boomlevel);
		addWindowListener(new close());
	}
	
	//���ô���������
	private void setGameMainUI() {
		setLayout(null);
		setTitle("ը����");
		setSize(605, 670);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		panel = new JPanel();
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon("bgimage.jpg");//����ͼƬ����
		label.setIcon(img);
		panel.add(label);
		panel.setBounds(0, 145, 600, 293);
		getContentPane().add(panel);
		
	}
	//�رմ���
		class close extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(gameIsStart==false){
				if(e.getActionCommand().equals("��Ϸ��ʼ")){
					gf=new GameFrame();
					gf.setBounds(0, 0, 600, 600);
					add(gf);
					this.repaint();
					gameIsStart=true;
				}
			}
			if(e.getActionCommand().equals("���¿�ʼ")){
				gameinit();
			}
			if(e.getActionCommand().equals("�˳���Ϸ")){
				System.exit(0);
			}
		}
		//��Ϸ��ʼ��
		public static void gameinit(){
			//��ʼ������λ��
			Player.xPos=6;
			Player.yPos=6;
			//��չ���
			allPanel.MonsterList.clear();
			//��ͼ��ʼ��
			MapArr.mapInitialization();
			//��������ӵ���ͼ
			allPanel.addMonster();
			//��ʼ����Ϸ��������Ϣ
			allPanel.isnotOver=true;
			Player.death=false;
			//��ʼ���÷ֺ�ը��
			Boom.scroll=0;
			GameMainUI.scroll.setText("������"+Boom.scroll);
			Boom.boomlevel=2;
			GameMainUI.boomlevel.setText("������"+Boom.boomlevel);
			
		}
}
