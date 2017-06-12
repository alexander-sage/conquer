package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.textcomponents.MenuBox;
import net.slashie.libjcsi.textcomponents.MenuItem;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
 
public class HelloDungeon{
 
	public static ConsoleSystemInterface csi = null;
	
	public static void main(String[] args){
		Properties text = new Properties();
		text.setProperty("fontSize","20");
		text.setProperty("font", "Courier");
		
		try{
			csi = new WSwingConsoleInterface("My little Java Roguelike - Programming is fun", text);
		}
		catch (ExceptionInInitializerError eiie) {
			System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
			eiie.printStackTrace();
			System.exit(-1);
		}
		
		MenuBox m = new MenuBox(csi);
		m.setBounds(6, 4,  10,  10);
		List<MenuItem> l= new ArrayList<>();
		

			
		m.setMenuItems(l);
		m.draw();
			
		
		
		
		
		
		
	}
	
	/*private static void moveAround(){
		int x = 0;
		int y = 0;
		boolean stop = false;
		
		while(!stop){
			//csi.cls();
			csi.print(x,y, '@', CSIColor.WHITE);
			csi.refresh();
			CharKey dir = csi.inkey();
			if(dir.isUpArrow()&& (y-1 >= 0)){
				y--;
			}
			if(dir.isDownArrow() && (y+1 < 25)){
				y++;
			}
			if(dir.isLeftArrow() && (x-1 >= 0)){
				x--;
			}
			if(dir.isRightArrow() && (x+1 < 80)){
				x++;
			}
			if(dir.code == CharKey.Q){
				stop = true;
			}
		}
	}*/
}