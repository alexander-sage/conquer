package utils;

import java.text.DecimalFormat;

public class Formats {
	public static String normal(double d){
		return new DecimalFormat("#0.00").format(d);
	}
}
