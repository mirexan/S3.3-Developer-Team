package com.itAcademy.agenda.common.utils;

import com.itAcademy.agenda.common.exception.InvalidInputException;
import java.util.Scanner;

public class ConsoleInputUtils {
	private static final Scanner SCANNER = new Scanner(System.in);
	private ConsoleInputUtils() {}
	public static void closeScanner() {
		SCANNER.close();
	}
	public static String readString(String message){
		System.out.print(message);
		return SCANNER.nextLine();
	}
	public static int readInt(String message) throws InvalidInputException{
		System.out.print(message);
		try{
			String input = SCANNER.nextLine().trim();
			return Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			throw new InvalidInputException("Invalid Input. Integer number expected",e);
		}
	}

}
