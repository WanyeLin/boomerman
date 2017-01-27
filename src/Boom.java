import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//��Ϸը����
public class Boom extends Player implements KeyListener{
	//ը�������ȼ� ���2 ���5����ը�������йأ�
	public static int boomlevel =2;
	//����
	public static int scroll=0;
	//ը������
	private int xBoom;
	private int yBoom;
	//��ը��Χ
	private int xBoomPos;
	private int yBoomPos;
	//�Ƿ���ը��
	public static boolean boomExistence=false;
	//ը����ʱ��
	public static int timeCount=0;
	//������ �Ƿ������
	private boolean up=false;
	private boolean down=false;
	private boolean right=false;
	private boolean left=false;
	//����Ƿ��Ѿ����ù�ը����Ч
	private boolean isShowBoom=false;
	//���췽��
	public Boom(){
		addKeyListener(this);
	}
	
	public int getxBoomPos() {
		return xBoomPos=xBoom;
	}
	
	public int getyBoomPos() {
		return yBoomPos=yBoom;
	}


	public void paint(Graphics g){
		super.paint(g);
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {  
	        @Override  
	        public void eventDispatched(AWTEvent event) {  
	            // TODO Auto-generated method stub  
	            if(((KeyEvent)event).getID()==KeyEvent.KEY_TYPED){  
	            	if(boomExistence==false){//���ը�������� �ſ��Է���ը��
	        			int spance = ((KeyEvent)event).getKeyChar();
	        			if(spance==32){
	        				xBoom=Player.getXPos()/6;
	        				yBoom=Player.getYPos()/6;
	        				boomExistence=true;
	        			}
	        		}
	            } 
	            
	        }  
	    }, AWTEvent.KEY_EVENT_MASK);  
		Image imageboom=getToolkit().getImage("boom.png");
		if(boomExistence==true){//���ը������
			timeCount++;
			if(timeCount<40){
			g.drawImage(imageboom, 30*yBoom, 30*xBoom, 30, 30, null);
			}
			if(timeCount>=40&&timeCount<50){
				if(isShowBoom==false){
					showBoom();
				}
			}
			if(timeCount==50){//��ʼ��ը������
				boomInit();
			}
		}
	}


	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}



	//��ʾ��ը��Ч �Լ�����Ƿ���Ա�ը
	public void showBoom(){
		isShowBoom=true;
		MapArr.Map[getxBoomPos()][getyBoomPos()]=7;
		for(int i=0;i<boomlevel;i++){
			if(up==false){//����ը����Χ
				int xExplosionRange=getxBoomPos()-1-i;
				if(xExplosionRange<0){
					xExplosionRange=0;
				}
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==0||MapArr.Map[xExplosionRange][getyBoomPos()]==2){
					if(MapArr.Map[xExplosionRange][getyBoomPos()]==0){//��ը�������յ�
						MapArr.Map[xExplosionRange][getyBoomPos()]=7;
					}
					if(MapArr.Map[xExplosionRange][getyBoomPos()]==2){//��ը�����Դݻ�
						MapArr.Map[xExplosionRange][getyBoomPos()]=0;
						scroll+=10;//�ӷ�
						GameMainUI.scroll.setText("������"+Boom.scroll);
						up=true;
					}
					
				}
				//��ը���������ɴݻ�ǽ
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
					if(boomlevel>3){
						if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
							MapArr.Map[xExplosionRange][getyBoomPos()]=0;
							scroll+=10;//�ӷ�
							GameMainUI.scroll.setText("������"+Boom.scroll);
							up=true;
						}
					}
						up=true;
				}
			}
			if(down==false){
				int xExplosionRange=getxBoomPos()+1+i;
				if(xExplosionRange>19){
					xExplosionRange=19;
				}
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==0||MapArr.Map[xExplosionRange][getyBoomPos()]==2){
					if(MapArr.Map[xExplosionRange][getyBoomPos()]==0){
						MapArr.Map[xExplosionRange][getyBoomPos()]=7;
					}
					if(MapArr.Map[xExplosionRange][getyBoomPos()]==2){
						MapArr.Map[xExplosionRange][getyBoomPos()]=0;
						scroll+=10;//�ӷ�
						GameMainUI.scroll.setText("������"+Boom.scroll);
						down=true;
					}
				}
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
					if(boomlevel>3){
						if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
							MapArr.Map[xExplosionRange][getyBoomPos()]=0;
							scroll+=10;//�ӷ�
							GameMainUI.scroll.setText("������"+Boom.scroll);
							down=true;
						}
					}
					down=true;
				}
			}
			if(right==false){
				int yExplosionRange=getyBoomPos()+1+i;
				if(yExplosionRange>19){
					yExplosionRange=19;
				}
				if(MapArr.Map[getxBoomPos()][yExplosionRange]==0||MapArr.Map[getxBoomPos()][yExplosionRange]==2){
					if(MapArr.Map[getxBoomPos()][yExplosionRange]==0){
						MapArr.Map[getxBoomPos()][yExplosionRange]=7;
					}
					if(MapArr.Map[getxBoomPos()][yExplosionRange]==2){
						MapArr.Map[getxBoomPos()][yExplosionRange]=0;
						scroll+=10;//�ӷ�
						GameMainUI.scroll.setText("������"+Boom.scroll);
						right=true;
					}
				}
				if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
					if(boomlevel>3){
						if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
							MapArr.Map[getxBoomPos()][yExplosionRange]=0;
							scroll+=10;//�ӷ�
							GameMainUI.scroll.setText("������"+Boom.scroll);
							right=true;
						}
					}
					right=true;
				}
			}
			if(left==false){
				int yExplosionRange=getyBoomPos()-1-i;
				if(yExplosionRange<0){
					yExplosionRange=0;
				}
				if(MapArr.Map[getxBoomPos()][yExplosionRange]==0||MapArr.Map[getxBoomPos()][yExplosionRange]==2){
					if(MapArr.Map[getxBoomPos()][yExplosionRange]==0){
						MapArr.Map[getxBoomPos()][yExplosionRange]=7;
					}
					if(MapArr.Map[getxBoomPos()][yExplosionRange]==2){
						MapArr.Map[getxBoomPos()][yExplosionRange]=0;
						scroll+=10;//�ӷ�
						GameMainUI.scroll.setText("������"+Boom.scroll);
						left=true;
					}
				}
				if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
					if(boomlevel>3){
						if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
							MapArr.Map[getxBoomPos()][yExplosionRange]=0;
							scroll+=10;//�ӷ�
							GameMainUI.scroll.setText("������"+Boom.scroll);
							left=true;
						}
					}
					left=true;
				}
			}
			
		}
	}
	//��ʼ��ը��
	public void boomInit(){
		boomExistence=false;
		timeCount=0;
		up=false;
		down=false;
		right=false;
		left=false;
		isShowBoom=false;
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(MapArr.Map[i][j]==7){
					MapArr.Map[i][j]=0;
				}
			}
		}
	}
}
