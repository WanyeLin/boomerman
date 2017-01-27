import java.awt.Rectangle;
import java.util.ArrayList;

import org.w3c.dom.css.Counter;

public class MapArr {
	private ArrayList<Rectangle> MapRectList;
	private ArrayList<Rectangle> MapRect6;
	private ArrayList<Rectangle> MapRect7;
	/**二维数组定义地图
	*无障碍0 
	*外围墙1
	*可破坏墙2
	*不可破坏墙3
	*初始怪物地点4（怪物被设置后将恢复为0）
	*炸弹威力道具6
	*爆炸火花特效7（不可手动设置）
	*/
	public static int Map[][]=new int[20][20];
	
	static{
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(i==0||i==19|j==0|j==19){
					MapArr.Map[i][j]=1;
					continue;
				}
				int Mapinit=(int)(0+Math.random()*4);
				if(Mapinit==1){
					MapArr.Map[i][j]=0;
					continue;
				}
				MapArr.Map[i][j]=Mapinit;
			}
		}
		//初始化怪物地点
		for(int i=0;i<allPanel.monsterNumber;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=4;
			//防止初始化掉外围墙体
			if(MapArr.Map[x+1][y]!=1)
			MapArr.Map[x+1][y]=0;
			if(MapArr.Map[x-1][y]!=1)
			MapArr.Map[x-1][y]=0;
		}
		MapArr.Map[1][1]=0;
		MapArr.Map[1][2]=0;
		MapArr.Map[2][1]=0;
		//初始化药水
		for(int i=0;i<3;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=6;
		}
	}
	//添加游戏Rectangle对象
	public ArrayList<Rectangle> getMapRectList() {
		return MapRectList;
	}

	public ArrayList<Rectangle> getMapRect6() {
		return MapRect6;
	}

	public ArrayList<Rectangle> getMapRect7() {
		return MapRect7;
	}
	//构造方法
	public MapArr(){
		MapRectList=new ArrayList<Rectangle>();
		MapRect6=new ArrayList<Rectangle>();
		MapRect7=new ArrayList<Rectangle>();
		
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(MapArr.Map[i][j]==1||MapArr.Map[i][j]==2||MapArr.Map[i][j]==3){//添加障碍物碰撞
					MapRectList.add(new Rectangle(j*30, i*30, 30, 30));
				}
				if(MapArr.Map[i][j]==7){//添加爆炸特效碰撞
					MapRect7.add(new Rectangle(j*30, i*30, 30, 30));
				}
				if(MapArr.Map[i][j]==6){//添加药水碰撞
					MapRect6.add(new Rectangle(j*30, i*30, 30, 30));
				}
			}
		}
	}
	//手动初始化地图
	public static void mapInitialization(){
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(i==0||i==19|j==0|j==19){
					MapArr.Map[i][j]=1;
					continue;
				}
				int Mapinit=(int)(0+Math.random()*4);
				if(Mapinit==1){
					MapArr.Map[i][j]=0;
					continue;
				}
				MapArr.Map[i][j]=Mapinit;
			}
		}
		//初始化怪物地点
		for(int i=0;i<allPanel.monsterNumber;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=4;
			if(MapArr.Map[x+1][y]!=1)
				MapArr.Map[x+1][y]=0;
			if(MapArr.Map[x-1][y]!=1)
				MapArr.Map[x-1][y]=0;
			
		}
		//人物位置初始化防止卡图
		MapArr.Map[1][1]=0;
		MapArr.Map[1][2]=0;
		MapArr.Map[2][1]=0;
		//初始化药水
		for(int i=0;i<3;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=6;
		}
	}
	
}
