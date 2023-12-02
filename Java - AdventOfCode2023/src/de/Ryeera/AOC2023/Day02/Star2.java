package de.Ryeera.AOC2023.Day02;

import java.util.List;

import de.Ryeera.AOC2023.AOCUtils;

public class Star2 {

	public static void main(String[] args) {
		int sum = 0;
		
		List<String> lines = AOCUtils.readFile("/de/Ryeera/AOC2023/Day02/input.txt");
		
		for (String line : lines) {
			String[] rounds = line.substring(line.indexOf(':')+2).split("; ");
			int maxRed = 0;
			int maxGreen = 0;
			int maxBlue = 0;
			for (String round : rounds) {
				String[] picks = round.split(", ");
				for (String pick : picks) {
					String[] sep = pick.split(" ");
					int count = Integer.parseInt(sep[0]);
					String color = sep[1];
					if (color.equals("red") && maxRed < count) {
						maxRed = count;
					}
					if (color.equals("green") && maxGreen < count) {
						maxGreen = count;
					}
					if (color.equals("blue") && maxBlue < count) {
						maxBlue = count;
					}
				}
			}
			sum += maxRed*maxGreen*maxBlue;
		}
		
		System.out.println(sum);
	}
}
