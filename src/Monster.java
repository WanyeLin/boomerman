import java.awt.Rectangle;

public class Monster implements Runnable{
	//怪物类 怪物的运动
	//判断怪物是否死亡
	boolean isLive=true;
	//取得怪物运动方向
	private int Direct;
	//怪物初始坐标  （将数组放大6倍的数组 坐标120*120）
	private int xMonster;
	private int yMonster;
	//构造方法
	public int getxMonster() {
		return xMonster;
	}

	public void setxMonster(int xMonster) {
		this.xMonster = xMonster;
	}
	
	public int getyMonster() {
		return yMonster;
	}

	public void setyMonster(int yMonster) {
		this.yMonster = yMonster;
	}
	
	public Monster(int x,int y){
		setxMonster(x);
		setyMonster(y);
	}
	
	//多线程移动方法
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		 while (true) {
			monsterMoveDirect();//取得运动方向
		        try {
		            Thread.sleep(50);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        monsterMove(Direct);//调用移动方法
		        
		        //判断怪物是否死亡
		        if(isLive==false){
		            //死亡，退出线程
		        	Boom.scroll+=100;
					GameMainUI.scroll.setText("分数："+Boom.scroll);
		            break;
		        }
		 }
	}
	//检测是否撞上炸弹
	private boolean isCollisionboom(){
		MapArr deathRect=new MapArr();	
		for(int i=0;i<deathRect.getMapRect7().size();i++){
			if(isCollisionNow(deathRect.getMapRect7().get(i))){
				isLive=false;
				break;
			}
		}
		return isLive;
		
	}
	//用于检测是否与炸弹相撞的方法
	private boolean isCollisionNow(Rectangle boom){
		return this.getMonsterRecNow(getxMonster(),getyMonster()).intersects(boom);
	}
	
	private Rectangle getMonsterRecNow(int x, int y) {
		// TODO 自动生成的方法存根
		return new Rectangle(y*5, x*5,30, 30);
	}
	//返回当前位置 用于与玩家碰撞  
	public Rectangle returnMonster(){
		return new Rectangle(getyMonster()*5, getxMonster()*5, 30, 30);
	}
	//产生怪物随机方向
	public void monsterMoveDirect(){
		 for(int j=0;j==0;){
			 Direct =  (int)(Math.random()*4);
			 //如果向上
			 if(Direct==0){//判断该方向是否可以通过
				 int xMonsterMap=getxMonster()/6-1;//怪物下一步在数组中的位置（放大6倍）(数组大小120*120)
				 if(MapArr.Map[xMonsterMap][getyMonster()/6]==0){
					 j=1;
				 }
			 }
		      //如果向右
			 if(Direct==1){
				 int yMonsterMap=getyMonster()/6+1;
				 if(MapArr.Map[getxMonster()/6][yMonsterMap]==0){
					 j=1;
				 }
			 }
			 //如果向下
			 if(Direct==2){
				 int xMonsterMap=getxMonster()/6+1;
				 if(MapArr.Map[xMonsterMap][getyMonster()/6]==0){
					 j=1;
				 }
			 }
			 //如果向左
			 if(Direct==3){
				 int yMonsterMap=getyMonster()/6-1;
				 if(MapArr.Map[getxMonster()/6][yMonsterMap]==0){
					 j=1;
				 }
			 }
		 }
	}
	//怪物移动方法
	public void monsterMove(int Direct){
		   switch (Direct) {
	        case 0:
	            for(int i=0;i<6;i++){ //正在向上移动
	                setxMonster(getxMonster() - 1);
	                isCollisionboom();
	                try {
	                    Thread.sleep(150);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            break;
	        case 1:
	            for(int i=0;i<6;i++){
	                setyMonster(getyMonster() + 1);
	                isCollisionboom();
	                try {
	                    Thread.sleep(150);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            break;
	        case 2:
	            for(int i=0;i<6;i++){
	                setxMonster(getxMonster() + 1);
	                isCollisionboom();
	                try {
	                    Thread.sleep(150);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            break;
	        case 3:
	            for(int i=0;i<6;i++){
	                setyMonster(getyMonster() - 1);
	                isCollisionboom();
	                try {
	                    Thread.sleep(150);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            break;
	        default:
	            break;
	        }
	}
}
