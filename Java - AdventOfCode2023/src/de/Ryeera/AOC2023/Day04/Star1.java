package de.Ryeera.AOC2023.Day04;

import java.util.List;

import de.Ryeera.AOC2023.AOCUtils;

public class Star1 {

	public static void main(String[] args) {
		List<String> lines = AOCUtils.readFile("/de/Ryeera/AOC2023/Day04/input.txt");
		int sum = 0;
		for (String line : lines) {
			ScratchCard card = new ScratchCard(line);
			sum += card.getValue();
		}
		System.out.println(sum);
	}
}
