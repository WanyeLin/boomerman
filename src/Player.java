import java.awt.*;
import java.awt.event.*;

public class Player extends Map implements KeyListener{
	
	private static final long serialVersionUID = -5132485444377286445L;
	//��ͼ�ϰ���
	private MapArr mapRelt;
	//ը������ ���ڼ���Ƿ�ײ��ը�� �������
	private MapArr deathRect;

	//��Ϸ��������
	//�����ͼx����
	public static int xPos=6;
		//�����ͼy����
	public static int yPos=6;
	//�������x����
	public static int getXPos(){
		return xPos;
	}
	//�������y����
	public static int getYPos(){
		return yPos;
	}
	//�ƶ����꣨��һ�������꣩
	private int x=0;
	private int y=0;
	//�Ƿ���ײ
	private boolean rectflag=false;

	//��������
	public static boolean death=false;
	//�����ƶ�����
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	//���������ͼ
	public void paint(Graphics g){
		super.paint(g);
		deathRect=new MapArr();	
		//ȫ�ּ��̼���
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {  
	        @Override  
	        public void eventDispatched(AWTEvent event) {  
	            // TODO Auto-generated method stub  
	            if(((KeyEvent)event).getID()==KeyEvent.KEY_PRESSED){  
	            	int key=((KeyEvent)event).getKeyCode();
	        		movePressed(key);
	            } 
	            if(((KeyEvent)event).getID()==KeyEvent.KEY_RELEASED){  
	            	int key=((KeyEvent)event).getKeyCode();
	        		moveReleased(key);
	            }  
	        }  
	    }, AWTEvent.KEY_EVENT_MASK);  
		
		
		//�ж������Ƿ�ӵ�ҩˮ
		for(int i=0;i<deathRect.getMapRect6().size();i++){
			if(isCollisionNow(deathRect.getMapRect6().get(i))){
				int x=(int)(deathRect.getMapRect6().get(i).getX())/30;
				int y=(int)(deathRect.getMapRect6().get(i).getY())/30;
				MapArr.Map[y][x]=0;
				Boom.boomlevel+=1;
				GameMainUI.boomlevel.setText("������"+Boom.boomlevel);
			}
		}
		//�ж������Ƿ���ը��������ײ
		for(int i=0;i<deathRect.getMapRect7().size();i++){
			if(isCollisionNow(deathRect.getMapRect7().get(i))){
				death=true;
				break;
			}
		}
		//��������λ��
		Image imageplayer=getToolkit().getImage("player.png");
		g.drawImage(imageplayer, 5*yPos, 5*xPos, null);
		//�����ƶ�
		playerMove();
	}
	
	//�����ƶ�����
	public void playerMove(){
		if(up==true){
			if(xPos>6){//�߽���
				mapRelt=new MapArr();
					x=getXPos()-1;
					y=getYPos();
					rectflag=false;
					//����Ƿ�ײǽ
					for(int i=0;i<mapRelt.getMapRectList().size();i++){
						if(IsCollision(mapRelt.getMapRectList().get(i))){
							rectflag=true;//��ײ
							break;
						}
					}
					if(rectflag==false){
						xPos-=1;
					}
			}
		}
		if(down==true){
			if(xPos<108){
				mapRelt=new MapArr();
					x=getXPos()+1;
					y=getYPos();
					rectflag=false;
					for(int i=0;i<mapRelt.getMapRectList().size();i++){
						if(IsCollision(mapRelt.getMapRectList().get(i))){
							rectflag=true;//��ײ
							break;
						}
					}
					if(rectflag==false){
						xPos+=1;
					}
			}
		}
		if(right==true){
			if(yPos<108){
				mapRelt=new MapArr();
					y=getYPos()+1;
					x=getXPos();
					rectflag=false;
					for(int i=0;i<mapRelt.getMapRectList().size();i++){
						if(IsCollision(mapRelt.getMapRectList().get(i))){
							rectflag=true;//��ײ
							break;
						}
					}
					if(rectflag==false){
						yPos+=1;
					}
			}
		}
		if(left==true){
			if(yPos>6){
				mapRelt=new MapArr();
					rectflag=false;
					x=getXPos();
					y=getYPos()-1;
					for(int i=0;i<mapRelt.getMapRectList().size();i++){
						if(IsCollision(mapRelt.getMapRectList().get(i))){
							rectflag=true;//��ײ
							break;
						}
					}
					if(rectflag==false){
						yPos-=1;
					}
			}
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	//�����ƶ������������·���
	public void movePressed(int keycode){
		
		switch(keycode){
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		}
	}
	//�����ƶ�������������
	public void moveReleased(int keycode){
		switch(keycode){
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		}
	}
	
		//������һ������rectlangle����  ������ײ���
		public Rectangle getPlayerRec(){
			return new Rectangle(this.y*5, this.x*5, 30, 30);
		}
		//����Ŀǰ����rectlangle����  ������ײ���
		public Rectangle getPlayerRecNow(int x,int y){
			return new Rectangle(y*5, x*5, 30, 30);
		}
		
		//�������ϰ�����ײ���
		public boolean IsCollision(Rectangle map){
			return this.getPlayerRec().intersects(map);
		}
		//��ǰ�����Ƿ���ը����ײ
		public boolean isCollisionNow(Rectangle boom){
			return this.getPlayerRecNow(getXPos(),getYPos()).intersects(boom);
		}
		//���ڼ���Ƿ��������ײ������ײ����
		public static Rectangle isCollisionMonster(){
			return new Rectangle(getYPos()*5, getXPos()*5, 30, 30);
		}
}
