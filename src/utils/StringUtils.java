package utils;

import java.util.List;

public class StringUtils {
	
	private StringUtils(){}
	
	/**
	 * Returns a string consisting of the base repeated N times.
	 * 
	 * @param base  The string to be repeated
	 * @param count  The number of times to repeat
	 * @return  
	 * @throws IllegalArgumentException  if count < 0
	 * 
	 * 
	 * Examples: 
	 * 		repeated(" ", 5) //returns "     "
	 * 		"ba"+repeated("na",2) //retunrs "Banana"
	 */
	public static String repeated(String base, int count) throws IllegalArgumentException{
		if(count<0){
			throw new IllegalArgumentException();
		}
		StringBuilder output = new StringBuilder();
		for(int i=0; i<count; i++){
			output.append(base);
		}
		return output.toString();
	}
	
	/**
	 * Returns a string contianing the elements in values, separated by a delimiter.
	 * @param delimiter  The string interjected between each value.  If the string is empty, this method essentially concatinates an array of strings.
	 * @param values  The strings to be separated by the delimiter
	 * @return delimited string
	 * 
	 * examples:
	 * 		delimitedBy(", ", myFruitList) //returns "apple, orange, banana, kiwi"
	 * 		delimitedBy(" and also ", myTaskList) //returns "clean and also sleep and also eat"
	 */
	
	public static String delimitedBy(String delimiter, String[] values){
		StringBuilder output = new StringBuilder(values[0]);
		for(int i=1; i<values.length; i++){
			output.append(delimiter+values[i]);
		}
		return output.toString();
	}
	
	
	/**
	 * Returns the elements of VALUES, separated by commas (without spaces)
	 * @param values
	 * @return A comma separated list
	 */
	public static String commaSeparated(String[] values){
		return delimitedBy(",", values);
	}
	
	
	/**
	 * Returns the elements of VALUES, separated by spaces.
	 * @param values
	 * @return
	 */
	public static String spaceSeparated(String[] values){
		return delimitedBy(" ",values);
	}
	
	
	/**
	 * Returns a substring consisting of the first SIZE characters of the string input.
	 * Returns the string itself if the trimming size is larger than the string.
	 * @param size
	 * @param s
	 * @return
	 */
	public static String trimTo(int size, String s){
		if(s.length()<=size){
			return s;
		}
		else{
			return s.substring(0, size);
		}
	}

	
	/**
	 * Pads a string with spaces, up to SIZE.
	 * @param size
	 * @param s
	 * @return
	 */
	public static String fillIn(int size, String s) {
		if(s.length()>size){
			return s;
		}
		else{
			return s+repeated(" ", size-s.length());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(fillIn(14, "Hello")+"|");
	}

	public static String delimitedBy(String delimiter, List<String> values) {
		StringBuilder output = new StringBuilder(values.get(0));
		for(int i=1; i<values.size(); i++){
			output.append(delimiter+values.get(i));
		}
		return output.toString();
	}
}
