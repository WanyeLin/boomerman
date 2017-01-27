import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//游戏炸弹类
public class Boom extends Player implements KeyListener{
	//炸弹威力等级 最低2 最高5（与炸弹数量有关）
	public static int boomlevel =2;
	//分数
	public static int scroll=0;
	//炸弹坐标
	private int xBoom;
	private int yBoom;
	//爆炸范围
	private int xBoomPos;
	private int yBoomPos;
	//是否有炸弹
	public static boolean boomExistence=false;
	//炸弹计时器
	public static int timeCount=0;
	//方向检测 是否检测完毕
	private boolean up=false;
	private boolean down=false;
	private boolean right=false;
	private boolean left=false;
	//检测是否已经设置过炸弹特效
	private boolean isShowBoom=false;
	//构造方法
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
	            	if(boomExistence==false){//如果炸弹不存在 才可以放置炸弹
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
		if(boomExistence==true){//如果炸弹存在
			timeCount++;
			if(timeCount<40){
			g.drawImage(imageboom, 30*yBoom, 30*xBoom, 30, 30, null);
			}
			if(timeCount>=40&&timeCount<50){
				if(isShowBoom==false){
					showBoom();
				}
			}
			if(timeCount==50){//初始化炸弹数据
				boomInit();
			}
		}
	}


	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}



	//显示爆炸特效 以及检测是否可以爆炸
	public void showBoom(){
		isShowBoom=true;
		MapArr.Map[getxBoomPos()][getyBoomPos()]=7;
		for(int i=0;i<boomlevel;i++){
			if(up==false){//控制炸弹范围
				int xExplosionRange=getxBoomPos()-1-i;
				if(xExplosionRange<0){
					xExplosionRange=0;
				}
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==0||MapArr.Map[xExplosionRange][getyBoomPos()]==2){
					if(MapArr.Map[xExplosionRange][getyBoomPos()]==0){//当炸弹遇到空地
						MapArr.Map[xExplosionRange][getyBoomPos()]=7;
					}
					if(MapArr.Map[xExplosionRange][getyBoomPos()]==2){//当炸弹可以摧毁
						MapArr.Map[xExplosionRange][getyBoomPos()]=0;
						scroll+=10;//加分
						GameMainUI.scroll.setText("分数："+Boom.scroll);
						up=true;
					}
					
				}
				//当炸弹遇到不可摧毁墙
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
					if(boomlevel>3){
						if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
							MapArr.Map[xExplosionRange][getyBoomPos()]=0;
							scroll+=10;//加分
							GameMainUI.scroll.setText("分数："+Boom.scroll);
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
						scroll+=10;//加分
						GameMainUI.scroll.setText("分数："+Boom.scroll);
						down=true;
					}
				}
				if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
					if(boomlevel>3){
						if(MapArr.Map[xExplosionRange][getyBoomPos()]==3){
							MapArr.Map[xExplosionRange][getyBoomPos()]=0;
							scroll+=10;//加分
							GameMainUI.scroll.setText("分数："+Boom.scroll);
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
						scroll+=10;//加分
						GameMainUI.scroll.setText("分数："+Boom.scroll);
						right=true;
					}
				}
				if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
					if(boomlevel>3){
						if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
							MapArr.Map[getxBoomPos()][yExplosionRange]=0;
							scroll+=10;//加分
							GameMainUI.scroll.setText("分数："+Boom.scroll);
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
						scroll+=10;//加分
						GameMainUI.scroll.setText("分数："+Boom.scroll);
						left=true;
					}
				}
				if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
					if(boomlevel>3){
						if(MapArr.Map[getxBoomPos()][yExplosionRange]==3){
							MapArr.Map[getxBoomPos()][yExplosionRange]=0;
							scroll+=10;//加分
							GameMainUI.scroll.setText("分数："+Boom.scroll);
							left=true;
						}
					}
					left=true;
				}
			}
			
		}
	}
	//初始化炸弹
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
