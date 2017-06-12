package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.slashie.libjcsi.CSIColor;
import utils.StringUtils;

public class ColorString implements CharSequence{

	private String value;
	private int color;
	
	public ColorString(String value, int color){
		this.value = value;
		this.color = color;
	}
	
	public ColorString(String value, CSIColor color){
		this.value = value;
		this.color = CSIColor.getCodeFromColor(color);
	}
	
	public String getValue(){
		return value;
	}
	
	public int getColor(){
		return color;
	}
	
	public CSIColor getCSIColor(){
		return CSIColor.getColorFromCode(color);
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public void setColor(CSIColor color){
		this.color = CSIColor.getCodeFromColor(color);
	}
	
	public void setValue(String value){
		this.value= value;
	}
	
	
	@Override
	public char charAt(int arg0) {
		return value.charAt(arg0);
	}

	@Override
	public int length() {
		return value.length();
	}

	@Override
	public CharSequence subSequence(int arg0, int arg1) {
		return value.subSequence(arg0,arg1);
	}
	
	
	public static List<ColorString> colorizeList(List<String> stringList, CSIColor color){
		List<ColorString> output = new ArrayList<>();
		CSIColor c = Optional.of(color).orElse(CSIColor.WHITE);
		for(String s: stringList){
			output.add(new ColorString(s, c));
		}
		return output;
	}

	public ColorString trimTo(int width) {
		if(width<value.length()){
			return new ColorString(value.substring(0, width), color);
		}
		else{
			return this;
		}
	}

	public ColorString spaceAndtrimTo(int width) {
		if(width<value.length()){
			return trimTo(width);
		}
		else{
			return new ColorString(value+StringUtils.repeated(" ", width-value.length()), color);
		}
	}

	public static ColorString colorize(String repeated, CSIColor color) {
		return new ColorString(repeated, color);
	}

	public static ColorString of(String content, CSIColor color) {
		return new ColorString(content, color);
	}
}
