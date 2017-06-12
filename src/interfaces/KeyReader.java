package interfaces;

import net.slashie.libjcsi.CharKey;

public class KeyReader {
	public static boolean isUp(CharKey input){
		return input.isUpArrow() || isSameIgnoreCase(input, CharKey.w);
	}
	
	public static boolean isDown(CharKey input){
		return input.isDownArrow() || isSameIgnoreCase(input, CharKey.s);
	}
	
	public static boolean isLeft(CharKey input){
		return input.isLeftArrow() || isSameIgnoreCase(input, CharKey.A);
	}
	
	public static boolean isRight(CharKey input){
		return input.isRightArrow() || isSameIgnoreCase(input, CharKey.D);
	}
	
	public static boolean isSame(CharKey a, int b){
		return a.code == b;
	}
	
	public static boolean isSameIgnoreCase(CharKey a, int b){
		
		if(a.isAlphaNumeric() && a.code<CharKey.N0 && b>=CharKey.a && b<CharKey.N0){
		
			return((a.code-b)%26 == 0);
		}
		else{
			return a.code == b;
		}
	}
}
