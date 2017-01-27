import java.awt.Rectangle;

public class Monster implements Runnable{
	//������ ������˶�
	//�жϹ����Ƿ�����
	boolean isLive=true;
	//ȡ�ù����˶�����
	private int Direct;
	//�����ʼ����  ��������Ŵ�6�������� ����120*120��
	private int xMonster;
	private int yMonster;
	//���췽��
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
	
	//���߳��ƶ�����
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		 while (true) {
			monsterMoveDirect();//ȡ���˶�����
		        try {
		            Thread.sleep(50);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        monsterMove(Direct);//�����ƶ�����
		        
		        //�жϹ����Ƿ�����
		        if(isLive==false){
		            //�������˳��߳�
		        	Boom.scroll+=100;
					GameMainUI.scroll.setText("������"+Boom.scroll);
		            break;
		        }
		 }
	}
	//����Ƿ�ײ��ը��
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
	//���ڼ���Ƿ���ը����ײ�ķ���
	private boolean isCollisionNow(Rectangle boom){
		return this.getMonsterRecNow(getxMonster(),getyMonster()).intersects(boom);
	}
	
	private Rectangle getMonsterRecNow(int x, int y) {
		// TODO �Զ����ɵķ������
		return new Rectangle(y*5, x*5,30, 30);
	}
	//���ص�ǰλ�� �����������ײ  
	public Rectangle returnMonster(){
		return new Rectangle(getyMonster()*5, getxMonster()*5, 30, 30);
	}
	//���������������
	public void monsterMoveDirect(){
		 for(int j=0;j==0;){
			 Direct =  (int)(Math.random()*4);
			 //�������
			 if(Direct==0){//�жϸ÷����Ƿ����ͨ��
				 int xMonsterMap=getxMonster()/6-1;//������һ���������е�λ�ã��Ŵ�6����(�����С120*120)
				 if(MapArr.Map[xMonsterMap][getyMonster()/6]==0){
					 j=1;
				 }
			 }
		      //�������
			 if(Direct==1){
				 int yMonsterMap=getyMonster()/6+1;
				 if(MapArr.Map[getxMonster()/6][yMonsterMap]==0){
					 j=1;
				 }
			 }
			 //�������
			 if(Direct==2){
				 int xMonsterMap=getxMonster()/6+1;
				 if(MapArr.Map[xMonsterMap][getyMonster()/6]==0){
					 j=1;
				 }
			 }
			 //�������
			 if(Direct==3){
				 int yMonsterMap=getyMonster()/6-1;
				 if(MapArr.Map[getxMonster()/6][yMonsterMap]==0){
					 j=1;
				 }
			 }
		 }
	}
	//�����ƶ�����
	public void monsterMove(int Direct){
		   switch (Direct) {
	        case 0:
	            for(int i=0;i<6;i++){ //���������ƶ�
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
