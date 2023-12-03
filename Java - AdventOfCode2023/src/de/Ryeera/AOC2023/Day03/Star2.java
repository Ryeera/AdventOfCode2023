package de.Ryeera.AOC2023.Day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.Ryeera.AOC2023.AOCUtils;

public class Star2 {

	public static void main(String[] args) {
		List<String> lines = AOCUtils.readFile("/de/Ryeera/AOC2023/Day03/input.txt");
		int sum = 0;
		Map<Integer,List<Integer>> gears = new HashMap<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i) + ".";
			int numStart = -1;
			for (int j = 0; j < line.length(); j++) {
				char c = line.charAt(j);
				if (AOCUtils.isDigit(c) && numStart == -1) {
					numStart = j;
				} else if ((!AOCUtils.isDigit(c)) && numStart != -1) {
					int number = Integer.parseInt(line.substring(numStart, j));
					for (int l = i-1; l <= i+1; l++) {
						if (l < 0 || l >= lines.size()) continue;
						String line2 = lines.get(l) + ".";
						for (int k = numStart-1; k <= j; k++) {
							if (k < 0) continue;
							char s = line2.charAt(k);
							if (s == '*') {
								if (!gears.containsKey(l*line2.length()+k)) {
									gears.put(l*line2.length()+k, new ArrayList<>());
								}
								gears.get(l*line2.length()+k).add(number);
							}
						}
					}
					numStart = -1;
				}
			}
		}
		for (List<Integer> list : gears.values()) {
			if (list.size() == 2) {
				sum += list.get(0)*list.get(1);
			}
		}
		System.out.println(sum);
	}
}
