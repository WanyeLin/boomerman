import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

//地图界面绘制类
public class Map extends JPanel {

	private static final long serialVersionUID = 6324763307548353577L;
	
	public Map(){
		
	}
	//绘制初始地图
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d=(Graphics2D) g;
		Image imagebg=getToolkit().getImage("bg.jpg");
		g2d.drawImage(imagebg, 0, 0, null);
		Image imagewall1=getToolkit().getImage("wall1.jpg");
		Image imagewall2=getToolkit().getImage("wall2.gif");
		Image imagewall3=getToolkit().getImage("wall3.jpg");
		Image imagewall7=getToolkit().getImage("boom.gif");
		Image imagewallyaosui=getToolkit().getImage("yaosui.png");
		for(int i=0;i<MapArr.Map.length;i++){
			for(int j=0;j<MapArr.Map[i].length;j++){
				if(MapArr.Map[i][j]==1){
					g2d.drawImage(imagewall1, j*30, i*30, null);
				}
				if(MapArr.Map[i][j]==2){
					g2d.drawImage(imagewall2, j*30, i*30, null);
				}
				if(MapArr.Map[i][j]==3){
					g2d.drawImage(imagewall3, j*30, i*30, null);
				}
				if(MapArr.Map[i][j]==7){
					g2d.drawImage(imagewall7, j*30, i*30, null);
				}
				if(MapArr.Map[i][j]==6){
					g2d.drawImage(imagewallyaosui, j*30, i*30, null);
				}
			}
		}
		
	}
	
	
	
	
}
