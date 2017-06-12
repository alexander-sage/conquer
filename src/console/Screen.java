package console;

import java.util.Properties;

import interfaces.PaneFactory;
import interfaces.menus.MenuFactory;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class Screen {
	private final ConsoleSystemInterface window;
	public final Printer printer;
	
	public Screen(){
		Properties text = new Properties();
		text.setProperty("fontSize","20");
		text.setProperty("font", "Courier");
		
		window = new WSwingConsoleInterface("This is a title!", text);
		printer = new Printer(window);
	}
	
	public CharKey inkey(){
		return window.inkey();
	}

	public void refresh() {
		window.refresh();
	}

}
