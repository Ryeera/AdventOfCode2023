package de.Ryeera.AOC2023.Day01;

import java.util.List;

import de.Ryeera.AOC2023.AOCUtils;

public class CalibrationValue {

	public static void main(String[] args) {
		List<String> lines = AOCUtils.readFile("/de/Ryeera/AOC2023/Day01/CalibrationValue.txt");
		int sum = 0;
		for (String s : lines) {
			s = s.replace("one", "one1one");
			s = s.replace("two", "two2two");
			s = s.replace("three", "three3three");
			s = s.replace("four", "four4four");
			s = s.replace("five", "five5five");
			s = s.replace("six", "six6six");
			s = s.replace("seven", "seven7seven");
			s = s.replace("eight", "eight8eight");
			s = s.replace("nine", "nine9nine");
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (AOCUtils.isDigit(c)) {
					sum += Character.getNumericValue(c)*10;
					break;
				}
			}
			for (int i = s.length()-1; i >= 0 ; i--) {
				char c = s.charAt(i);
				if (AOCUtils.isDigit(c)) {
					sum += Character.getNumericValue(c);
					break;
				}
			}
		}
		System.out.println(sum);
	}
}
