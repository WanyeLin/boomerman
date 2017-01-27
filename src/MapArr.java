import java.awt.Rectangle;
import java.util.ArrayList;

import org.w3c.dom.css.Counter;

public class MapArr {
	private ArrayList<Rectangle> MapRectList;
	private ArrayList<Rectangle> MapRect6;
	private ArrayList<Rectangle> MapRect7;
	/**��ά���鶨���ͼ
	*���ϰ�0 
	*��Χǽ1
	*���ƻ�ǽ2
	*�����ƻ�ǽ3
	*��ʼ����ص�4�����ﱻ���ú󽫻ָ�Ϊ0��
	*ը����������6
	*��ը����Ч7�������ֶ����ã�
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
		//��ʼ������ص�
		for(int i=0;i<allPanel.monsterNumber;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=4;
			//��ֹ��ʼ������Χǽ��
			if(MapArr.Map[x+1][y]!=1)
			MapArr.Map[x+1][y]=0;
			if(MapArr.Map[x-1][y]!=1)
			MapArr.Map[x-1][y]=0;
		}
		MapArr.Map[1][1]=0;
		MapArr.Map[1][2]=0;
		MapArr.Map[2][1]=0;
		//��ʼ��ҩˮ
		for(int i=0;i<3;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=6;
		}
	}
	//�����ϷRectangle����
	public ArrayList<Rectangle> getMapRectList() {
		return MapRectList;
	}

	public ArrayList<Rectangle> getMapRect6() {
		return MapRect6;
	}

	public ArrayList<Rectangle> getMapRect7() {
		return MapRect7;
	}
	//���췽��
	public MapArr(){
		MapRectList=new ArrayList<Rectangle>();
		MapRect6=new ArrayList<Rectangle>();
		MapRect7=new ArrayList<Rectangle>();
		
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(MapArr.Map[i][j]==1||MapArr.Map[i][j]==2||MapArr.Map[i][j]==3){//����ϰ�����ײ
					MapRectList.add(new Rectangle(j*30, i*30, 30, 30));
				}
				if(MapArr.Map[i][j]==7){//��ӱ�ը��Ч��ײ
					MapRect7.add(new Rectangle(j*30, i*30, 30, 30));
				}
				if(MapArr.Map[i][j]==6){//���ҩˮ��ײ
					MapRect6.add(new Rectangle(j*30, i*30, 30, 30));
				}
			}
		}
	}
	//�ֶ���ʼ����ͼ
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
		//��ʼ������ص�
		for(int i=0;i<allPanel.monsterNumber;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=4;
			if(MapArr.Map[x+1][y]!=1)
				MapArr.Map[x+1][y]=0;
			if(MapArr.Map[x-1][y]!=1)
				MapArr.Map[x-1][y]=0;
			
		}
		//����λ�ó�ʼ����ֹ��ͼ
		MapArr.Map[1][1]=0;
		MapArr.Map[1][2]=0;
		MapArr.Map[2][1]=0;
		//��ʼ��ҩˮ
		for(int i=0;i<3;i++){
			int x=(int)(1+Math.random()*18);
			int y=(int)(1+Math.random()*18);
			MapArr.Map[x][y]=6;
		}
	}
	
}
