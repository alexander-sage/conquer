package interfaces.outputs;

import java.util.Deque;
import java.util.LinkedList;

import interfaces.BorderedWindow;
import interfaces.Pane;
import interfaces.PaneFactory;
import net.slashie.libjcsi.CSIColor;
import utils.StringUtils;

public class MessageShower  implements BorderedWindow{
	private final Pane pane;
	private LinkedList<String> messages;
	
	public static MessageShower standard(){
		return new MessageShower();
	}
	
	private MessageShower(){
		this(PaneFactory.getStandardMessagePane());
	}
	
	public MessageShower(Pane pane){
		this.pane = pane;
		this.messages = new LinkedList<>();
		
		
	}
	
	
	
	public MessageShower drawBorder(){
		pane.drawBorder();
		return this;
	}
	
	public MessageShower write(String message){
		messages.addFirst(message);
		if(messages.size()>this.size()){
			messages.removeLast();
		}
		pane.setContent(messages, CSIColor.WHITE).display();
		System.out.println(message);
		return this;
	}
	
	
	int size(){
		return pane.getHeight();
	}
}
