package de.Ryeera.AOC2023.Day04;

import java.util.List;

import de.Ryeera.AOC2023.AOCUtils;

public class Star2 {

	public static void main(String[] args) {
		List<String> lines = AOCUtils.readFile("/de/Ryeera/AOC2023/Day04/input.txt");
		ScratchCard[] cards = new ScratchCard[lines.size()];
		for (int i = 0; i < lines.size(); i++) {
			cards[i] = new ScratchCard(lines.get(i));
		}
		int sum = 0;
		for (int i = 0; i < cards.length; i++) {
			do {
				sum++;
				int score = cards[i].getPoints();
				for (int j = 1; j <= score; j++) {
					if (i+j >= cards.length) break;
					cards[i+j].add();
				}
			} while (!cards[i].remove());
		}
		System.out.println(sum);
	}
}
