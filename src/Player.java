import java.awt.*;
import java.awt.event.*;

public class Player extends Map implements KeyListener{
	
	private static final long serialVersionUID = -5132485444377286445L;
	//地图障碍物
	private MapArr mapRelt;
	//炸弹集合 用于检测是否撞上炸弹 检测死亡
	private MapArr deathRect;

	//游戏人物属性
	//人物地图x坐标
	public static int xPos=6;
		//人物地图y坐标
	public static int yPos=6;
	//获得人物x坐标
	public static int getXPos(){
		return xPos;
	}
	//获得人物y坐标
	public static int getYPos(){
		return yPos;
	}
	//移动坐标（下一步的坐标）
	private int x=0;
	private int y=0;
	//是否碰撞
	private boolean rectflag=false;

	//人物死亡
	public static boolean death=false;
	//人物移动方向
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	//绘制人物地图
	public void paint(Graphics g){
		super.paint(g);
		deathRect=new MapArr();	
		//全局键盘监听
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
		
		
		//判断人物是否加到药水
		for(int i=0;i<deathRect.getMapRect6().size();i++){
			if(isCollisionNow(deathRect.getMapRect6().get(i))){
				int x=(int)(deathRect.getMapRect6().get(i).getX())/30;
				int y=(int)(deathRect.getMapRect6().get(i).getY())/30;
				MapArr.Map[y][x]=0;
				Boom.boomlevel+=1;
				GameMainUI.boomlevel.setText("威力："+Boom.boomlevel);
			}
		}
		//判断人物是否与炸弹发生碰撞
		for(int i=0;i<deathRect.getMapRect7().size();i++){
			if(isCollisionNow(deathRect.getMapRect7().get(i))){
				death=true;
				break;
			}
		}
		//绘制人物位置
		Image imageplayer=getToolkit().getImage("player.png");
		g.drawImage(imageplayer, 5*yPos, 5*xPos, null);
		//人物移动
		playerMove();
	}
	
	//人物移动方法
	public void playerMove(){
		if(up==true){
			if(xPos>6){//边界检测
				mapRelt=new MapArr();
					x=getXPos()-1;
					y=getYPos();
					rectflag=false;
					//检测是否撞墙
					for(int i=0;i<mapRelt.getMapRectList().size();i++){
						if(IsCollision(mapRelt.getMapRectList().get(i))){
							rectflag=true;//碰撞
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
							rectflag=true;//碰撞
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
							rectflag=true;//碰撞
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
							rectflag=true;//碰撞
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
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	//人物移动方法按键按下方法
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
	//人物移动方法按键弹起
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
	
		//返回下一步人物rectlangle对象  用于碰撞检测
		public Rectangle getPlayerRec(){
			return new Rectangle(this.y*5, this.x*5, 30, 30);
		}
		//返回目前人物rectlangle对象  用于碰撞检测
		public Rectangle getPlayerRecNow(int x,int y){
			return new Rectangle(y*5, x*5, 30, 30);
		}
		
		//人物与障碍物碰撞检测
		public boolean IsCollision(Rectangle map){
			return this.getPlayerRec().intersects(map);
		}
		//当前人物是否与炸弹碰撞
		public boolean isCollisionNow(Rectangle boom){
			return this.getPlayerRecNow(getXPos(),getYPos()).intersects(boom);
		}
		//用于检测是否与怪物碰撞返回碰撞对象
		public static Rectangle isCollisionMonster(){
			return new Rectangle(getYPos()*5, getXPos()*5, 30, 30);
		}
}
