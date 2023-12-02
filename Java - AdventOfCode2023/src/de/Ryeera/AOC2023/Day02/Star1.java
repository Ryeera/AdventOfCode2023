package de.Ryeera.AOC2023.Day02;

import java.util.List;

import de.Ryeera.AOC2023.AOCUtils;

public class Star1 {

	public static void main(String[] args) {
		int maxRed = 12;
		int maxGreen = 13;
		int maxBlue = 14;
		
		int sum = 0;
		
		List<String> lines = AOCUtils.readFile("/de/Ryeera/AOC2023/Day02/input.txt");
		
		outer:
		for (String line : lines) {
			int gameID = Integer.parseInt(line.substring(5, line.indexOf(':')));
			String[] rounds = line.substring(line.indexOf(':')+2).split("; ");
			for (String round : rounds) {
				String[] picks = round.split(", ");
				for (String pick : picks) {
					String[] sep = pick.split(" ");
					int count = Integer.parseInt(sep[0]);
					String color = sep[1];
					if (color.equals("red") && count > maxRed) {
						continue outer;
					}
					if (color.equals("green") && count > maxGreen) {
						continue outer;
					}
					if (color.equals("blue") && count > maxBlue) {
						continue outer;
					}
				}
			}
			sum += gameID;
		}
		
		System.out.println(sum);
	}
}
