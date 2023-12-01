package de.Ryeera.AOC2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AOCUtils {

	private static List<Integer> primes = new ArrayList<Integer>();
	
	private static boolean isPolygonal(int n){
		if(isTriangular(n))System.out.println(n + " is triangular!");
		if(isSquare(n))System.out.println(n + " is square!");
		if(isPentagonal(n))System.out.println(n + " is pentagonal!");
		if(isHexagonal(n))System.out.println(n + " is hexagonal!");
		if(isHeptagonal(n))System.out.println(n + " is heptagonal!");
		if(isOctagonal(n))System.out.println(n + " is ocatagonal!");
		return isTriangular(n) || isSquare(n) || isPentagonal(n) || isHexagonal(n) || isHeptagonal(n) || isOctagonal(n);
	}
	
	/**
	 * Concatenates two integers.
	 * @param i1 the integer that will stand at the beginning of the resulting number
	 * @param i2 the integer that will stand at the end of the resulting number
	 * @return The concatenated integers
	 */
	public static long concat(long i1, long i2){
		return Long.parseLong(String.valueOf(i1) + String.valueOf(i2));
	}
	
	/**
	 * Two numbers have the same digits if they are of the same length and contain the same digits exactly as often.
	 * @param i1 The first number to be compared
	 * @param i2 The second number to be compared
	 * @return whether the two numbers contain the same digits
	 */
	public static boolean hasSameDigits(int i1, int i2){
		int[] ia1 = toDigits(i1);
		int[] ia2 = toDigits(i2);
		if(ia1.length != ia2.length)return false;
		Map<Integer, Integer> c1 = new HashMap<>();
		Map<Integer, Integer> c2 = new HashMap<>();
		for(int i : ia1){
			if(c1.containsKey(i))c1.put(i, c1.get(i)+1);
			else c1.put(i, 1);
		}
		for(int i : ia2){
			if(c2.containsKey(i))c2.put(i, c2.get(i)+1);
			else c2.put(i, 1);
		}
		for(int i : c1.keySet()){
			if(c1.get(i) != c2.get(i))return false;
		}
		return true;
	}
	
	/**
	 * Converts a String representation of a number to its digits in the for of an array.
	 * Non-digits are skipped and represented in the resulting array as -1.
	 * @param s The String to be converted
	 * @return An array containing all digits in order
	 */
	public static int[] toDigits(String s){
		int[] returns = new int[s.length()];
		char[] digits = s.toCharArray();
		for(int i = 0; i < returns.length; i++){
			try{
				returns[i] = Integer.parseInt(String.valueOf(digits[i]));
			}catch(NumberFormatException e){
				returns[i] = -1;
			}
		}
		return returns;
	}
	
	/**
	 * Converts an integer to an array containing its digits.
	 * @param n The number to be converted
	 * @return An array containing all digits in order.
	 */
	public static int[] toDigits(long n){
		return toDigits(String.valueOf(n));
	}
	
	public static int consecutivePrimeSumTermCount(int n){
		int longest = 0;
		for(int start = 1; nthPrime(start) <= n; start++){
			int sum = 0;
			int terms = 0;
			for(int i = start; sum <= n; i++){
				sum += nthPrime(i);
				terms++;
				if(sum == n)break;
			}
			if(sum == n && terms > longest){
				longest = terms;
			}
		}
		return longest;
	}
	
	/**
	 * Returns the nth prime. 
	 * @param n The id of the prime to get
	 * @return the nth prime.
	 */
	public static int nthPrime(int n){
		if(primes.size() >= n)return primes.get(n-1);
		for(int i = (primes.size() == 0 ? 2 : primes.get(primes.size()-1) + 1); primes.size() < n; i++){
			if(isPrime(i))primes.add(i);
		}
		return primes.get(n-1);
	}
	
	/**
	 * Calculates and formats the date to the format dd.MM.yy HH:mm:ss from the
	 * given time in milliseconds.
	 *
	 * @param millisecs
	 *            The time to be converted
	 * @return a formatted string containing the converted date
	 */
	public static String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}

	/**
	 * Converts a given time in milliseconds to a more readable format. This
	 * method is especially for times longer than a day. For shorter times, use
	 * {@link #calcTime(long)}
	 *
	 * @see #calcTime(long)
	 * @param millis
	 *            The milliseconds to be converted
	 * @return a String in a more readable format. Goes up to years.
	 */
	public static String calcLongTime(double millis) {
		long years = (long) (millis / 31536000000.0);
		millis -= years * 31536000000.0;
		long days = (long) (millis / 86400000.0);
		millis -= days * 86400000.0;
		long hours = (long) (millis / 3600000.0);
		millis -= hours * 3600000.0;
		long minutes = (long) (millis / 60000.0);
		millis -= minutes * 60000.0;
		long seconds = (long) (millis / 1000.0);
		millis -= seconds * 1000.0;

		return years + " years, " + days + " days, " + hours + ":" + minutes + ":" + seconds + " and " + millis + " ms";
	}

	/**
	 * Converts a given time in milliseconds to a more readable format. This
	 * method is especially for times shorter than a day. For longer times, use
	 * {@link #calcLongTime(double)}
	 *
	 * @see #calcLongTime(double)
	 * @param millisecs
	 *            The milliseconds to be converted
	 * @return a String in a more readable format. Goes up to hours.
	 */
	public static String calcTime(long millisecs) {
		int seconds = (int) (millisecs / 1000) % 60;
		int minutes = (int) (millisecs / (1000 * 60) % 60);
		int hours = (int) (millisecs / (1000 * 60 * 60) % 24);
		int millis = (int) (millisecs % 1000);
		return hours + " hours, " + minutes + " minutes, " + seconds + " seconds and " + millis + " milliseconds";
	}

	/**
	 * Calculates the sum of all digits of the given number.
	 * @param n The number to be parsed
	 * @return The sum of all digits of the number
	 */
	public static int digitalSum(BigInteger n) {
		int returns = 0;
		for (char c : n.toString().toCharArray()) {
			returns += Integer.parseInt(String.valueOf(c));
		}
		return returns;
	}

	/**
	 * Calculates the sum of all digits of the given number.
	 * @param n The number to be parsed
	 * @return The sum of all digits of the number
	 */
	public static int digitalSum(long n) {
		return digitalSum(new BigInteger(String.valueOf(n)));
	}

	/**
	 * Calculates and returns all distinct prime factors of the given number.
	 * This method is the same as {@link #primeFactors(int)} but the resulting
	 * set will contain each primefactor only once. The prime factorization of
	 * an integer is the multiset of primes those product is the integer.
	 *
	 * @param n
	 *            the number of which the distinct primefactors should be
	 *            calculated
	 * @return a list containing all prime-factors of this number
	 * @see #primeFactors(int)
	 */
	public static Set<Integer> distinctPrimeFactors(int n) {
		Set<Integer> factors = new HashSet<>();
		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}
		return factors;
	}

	/**
	 * Calculates the factorial of the given number. The factorial is the
	 * product of all natural numbers below the given number.<br>
	 * E.g. 3! = 3 * 2 * 1 = 6<br>
	 * For bigger integers (e.g. n>20!) use {@link #bigfactorial(int)}
	 *
	 * @param n
	 *            The number to be factorialized
	 * @return The factorial of the given number
	 */
	public static long factorial(int n) {
		if (n == 0)
			return 1;
		long returns = n;
		for (int i = n - 1; i > 1; i--) {
			returns *= i;
		}
		return returns;
	}
	
	/**
	 * Returns the factorial of the given number.  The factorial is the
	 * product of all natural numbers below the given number.<br>
	 * E.g. 3! = 3 * 2 * 1 = 6 <br>
	 * This method must be used for numbers, whose factorial exceeds the integer limit. This number is 21.
	 * @param n The number to be factorialized
	 * @return The factorial of the given number
	 */
	public static BigInteger bigfactorial(int n){
		if (n == 0)
			return new BigInteger("1");
		BigInteger returns = new BigInteger(String.valueOf(n));
		for (int i = n - 1; i > 1; i--) {
			returns = returns.multiply(new BigInteger(String.valueOf(i)));
		}
		return returns;
	}

	/**
	 * A number n is abundant if the sum of its proper divisors exceeds the
	 * number itself.
	 *
	 * @param n
	 *            the number to be checked
	 * @return whether the number is abundant or not
	 */
	public static boolean isAbundant(int n) {
		if (n < 12)
			return false;
		int s = 0;
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0)
				s += i;
		}
		return s > n;
	}

	/**
	 * Checks whether a number n can be displayed as the sum of two abundant
	 * numbers.
	 *
	 * @param n
	 *            the number to be checked
	 * @return whether the number can be displayed as the sum of two abundant
	 *         numbers
	 */
	public static boolean isAbundantSum(int n) {
		if (n < 24)
			return false;
		for (int i = 12; i <= n - 12; i++) {
			if (!isAbundant(i))
				continue;
			if (isAbundant(n - i))
				return true;
		}
		return false;
	}

	/**
	 * A sequence of numbers is an arithmetic sequence if the distance from each
	 * number to the next higher number is constant.
	 *
	 * @param sequence
	 *            the sequence to check
	 * @return whether this sequence is arithmetic or not
	 */
	public static boolean isArithmeticSequence(List<Integer> sequence) {
		if (sequence.size() < 2)
			return false;
		if (sequence.size() == 2)
			return true;
		Collections.sort(sequence);
		int dist = sequence.get(1) - sequence.get(0);
		if (dist == 0)
			return false;
		for (int i = 1; i < sequence.size(); i++) {
			if (sequence.get(i) - sequence.get(i - 1) != dist)
				return false;
		}
		return true;
	}

	/**
	 * A number n is a circular prime if all rotations of the digits of this
	 * number are also prime.
	 *
	 * @param n
	 *            The number to be checked
	 * @return Whether this number is a circular prime
	 */
	public static boolean isCircularPrime(int n) {
		if (!isPrime(n))
			return false;
		char[] digits = String.valueOf(n).toCharArray();
		for (int i = 0; i < digits.length - 1; i++) {
			char buffer = digits[0];
			for (int i2 = 0; i2 < digits.length - 1; i2++) {
				digits[i2] = digits[i2 + 1];
			}
			digits[digits.length - 1] = buffer;
			if (!isPrime(Integer.parseInt(String.valueOf(digits))))
				return false;
		}
		return true;
	}

	/**
	 * A number n is curious if the sum of the factorials of its digits is equal
	 * to the number itself.
	 *
	 * @param n
	 *            The number to be checked
	 * @return Whether this number is curious
	 */
	public static boolean isCurious(int n) {
		char[] digitsc = String.valueOf(n).toCharArray();
		int[] digits = new int[digitsc.length];
		for (int i = 0; i < digitsc.length; i++) {
			digits[i] = Integer.parseInt(String.valueOf(digitsc[i]));
		}
		int sum = 0;
		for (int i : digits)
			sum += factorial(i);
		return sum == n;
	}

	/**
	 * A number is fully pandigital if it contains the digits 0 through 9
	 * exactly once.
	 *
	 * @param n
	 *            The number to be checked
	 * @return whether this number is pandigital or not
	 */
	public static boolean isFullyPandigital(long n) {
		if (n > 9876543210L)
			return false;
		String num = String.valueOf(n);
		for (int i = 0; i <= 9; i++) {
			if (!num.contains(String.valueOf(i)))
				return false;
		}
		return true;
	}

	/**
	 * A number is hexagonal if it can be generated using the formula n(2n-1)
	 * @param n The number to be checked
	 * @return whether this number is hexagonal
	 */
	public static boolean isHexagonal(long n) {
		for (long i = (long) ((Math.sqrt(8*n+1)+1)/4); i * (2 * i - 1) <= n; i++) {
			if (i * (2 * i - 1) == n)
				return true;
		}
		return false;
	}

	/**
	 * A String is a palindrome if it can be reversed without changing it. Thus,
	 * it can be read forwards and backwards the same.
	 *
	 * @param s
	 *            the string to be checked
	 * @return whether the string is a palindrome or not
	 */
	public static boolean isPalindrome(String s) {
		String reverse = "";

		for (int i = s.length() - 1; i >= 0; i--)
			reverse += s.charAt(i);

		return s.equals(reverse);
	}

	/**
	 * A number is pandigital if it contains the digits 1 through 9 exactly
	 * once.
	 *
	 * @param n
	 *            The number to be checked
	 * @return whether this number is pandigital or not
	 */
	public static boolean isPandigital(int n) {
		if (n > 987654321)
			return false;
		String num = String.valueOf(n);
		for (int i = 1; i <= num.length(); i++) {
			if (!num.contains(String.valueOf(i)))
				return false;
		}
		return true;
	}

	/**
	 * A number n is partly pandigital if its digits only contain each digit
	 * from 1 to 9 at most once.
	 *
	 * @param n
	 *            the number to be checked
	 * @return whether this number is partly pandigital
	 */
	public static boolean isPartlyPandigital(int n) {
		String num = String.valueOf(n);
		if (num.contains("0") || n > 987654321)
			return false;
		for (int i = 1; i <= 9; i++) {
			int sum = 0;
			char c = String.valueOf(i).charAt(0);
			for (int i2 = 0; i2 < num.length(); i2++) {
				if (num.charAt(i2) == c)
					sum++;
			}
			if (sum > 1)
				return false;
		}
		return true;
	}

	/**
	 * A number is pentagonal if it can be generated using the formula n(3n-1)/2
	 * @param n The number to be checked
	 * @return whether this number is pentagonal
	 */
	public static boolean isPentagonal(long n) {
		for (long i = (long) ((Math.sqrt(24*n+1)+1)/6); getPentagonal(i) <= n; i++) {
			if (getPentagonal(i) == n)
				return true;
		}
		return false;
	}
	
	public static long getPentagonal(long n){
		return n*(3*n-1)/2;
	}

	/**
	 * A number is prime if it and only if it has two proper divisors. These are
	 * always the number itself and one.<br>
	 * One is not a prime since it only has one proper divisor.<br>
	 * This is the most efficient universal method for checking for primes.
	 *
	 * @param n
	 *            The number to be checked
	 * @return whether this number is prime or not
	 */
	public static boolean isPrime(long n) {
		if (n <= 1)
			return false;
		else if (n == 2)
			return true;
		else if (n % 2 == 0)
			return false;
		else if (n == 3)
			return true;
		else if (n % 3 == 0)
			return false;
		else if (n == 5)
			return true;
		else if (n % 5 == 0)
			return false;
		else {
			double r = Math.floor(Math.sqrt(n));
			long f = 5;
			while (f <= r) {
				if (n % f == 0)
					return false;
				if (n % (f + 2) == 0)
					return false;
				f += 6;
			}
			return true;
		}
	}

	/**
	 * A triangle is a right triangle if the angle opposite to the side c is 90�
	 *
	 * @param a
	 *            one of the other sides
	 * @param b
	 *            one of the other sides
	 * @param c
	 *            the hypotenuse
	 * @return Whether this triangle has a right angle
	 */
	public static boolean isRightTriangle(int a, int b, int c) {
		return a * a + b * b == c * c;
	}

	/**
	 * A word is a triangle word if the sum of the placements of the single letters is a triangle number.
	 * @param word The word to be checked
	 * @return whether this word is a triangle word
	 * @see #isTriangular(long)
	 */
	public static boolean isTriangleWord(String word) {
		int val = wordValue(word);
		for (int i = 1; val >= 0.5 * i * (i + 1.0); i++) {
			if (val == 0.5 * i * (i + 1.0))
				return true;
		}
		return false;
	}

	/**
	 * A number is triangular if it can be generated using the formula n(2n-1)
	 * @param n The number to be checked
	 * @return whether this number is triangular
	 */
	public static boolean isTriangular(long n) {
		for (long i = (long) ((Math.sqrt(8*n+1)-1)/2); i * (i + 1) / 2 <= n; i++) {
			if (i * (i + 1) / 2 == n)
				return true;
		}
		return false;
	}
	
	/**
	 * A number is square if it can be generated using the formula n^2
	 * @param n The number to be checked
	 * @return whether this number is square
	 */
	public static boolean isSquare(long n){
		for (long i = (long) Math.sqrt(n); i*i <= n; i++) {
			if (i * i == n)
				return true;
		}
		return false;
	}
	
	/**
	 * A number is heptagonal if it can be generated using the formula n(5n-3)/2
	 * @param n The number to be checked
	 * @return whether this number is triangular
	 */
	public static boolean isHeptagonal(long n){
		for (long i = (long) ((Math.sqrt(40*n+9)+3)/10); i*(5*i-3)/2 <= n; i++) {
			if (i*(5*i-3)/2 == n)
				return true;
		}
		return false;
	}
	
	/**
	 * A number is octagonal if it can be generated using the formula n(3n-2)
	 * @param n The number to be checked
	 * @return whether this number is octagonal
	 */
	public static boolean isOctagonal(long n){
		for (long i = (long) ((Math.sqrt(3*n+1)+1)/3); i*(3*i-2) <= n; i++) {
			if (i*(3*i-2) == n)
				return true;
		}
		return false;
	}

	/**
	 * A number n is a truncatable prime if you can remove single digits one
	 * after another from each end of the number and the number remains prime.
	 *
	 * @param n
	 *            the number to be checked
	 * @return whether this number is a truncatable prime or not
	 */
	public static boolean isTruncatablePrime(long n) {
		if (!isPrime(n))
			return false;
		String s = String.valueOf(n);
		char[] digits = s.toCharArray();
		String num = "";
		for (char digit : digits) {
			num += String.valueOf(digit);
			if (!isPrime(Long.parseLong(num)))
				return false;
		}
		num = "";
		for (int i = digits.length - 1; i >= 0; i--) {
			num = digits[i] + num;
			if (!isPrime(Long.parseLong(num)))
				return false;
		}
		return true;
	}
	
//	public boolean isCyclic(int[] n){
//		return	toDigits(n[0])[2] == toDigits(n[1])[0] && toDigits(n[0])[3] == toDigits(n[1])[1] && 
//				toDigits(n[1])[2] == toDigits(n[2])[0] && toDigits(n[1])[3] == toDigits(n[2])[1] && 
//				toDigits(n[2])[2] == toDigits(n[3])[0] && toDigits(n[2])[3] == toDigits(n[3])[1] && 
//				toDigits(n[3])[2] == toDigits(n[4])[0] && toDigits(n[3])[3] == toDigits(n[4])[1] && 
//				toDigits(n[4])[2] == toDigits(n[0])[0] && toDigits(n[4])[3] == toDigits(n[0])[1];
//	}

	/**
	 * Returns the placement of the letter in the alphabet.
	 * @param c the character to be parsed
	 * @return the value, e.g. the placement in the alphabet of this letter
	 */
	public static int letterValue(char c) {
		if (c == 'a' || c == 'A')
			return 1;
		if (c == 'b' || c == 'B')
			return 2;
		if (c == 'c' || c == 'C')
			return 3;
		if (c == 'd' || c == 'D')
			return 4;
		if (c == 'e' || c == 'E')
			return 5;
		if (c == 'f' || c == 'F')
			return 6;
		if (c == 'g' || c == 'G')
			return 7;
		if (c == 'h' || c == 'H')
			return 8;
		if (c == 'i' || c == 'I')
			return 9;
		if (c == 'j' || c == 'J')
			return 10;
		if (c == 'k' || c == 'K')
			return 11;
		if (c == 'l' || c == 'L')
			return 12;
		if (c == 'm' || c == 'M')
			return 13;
		if (c == 'n' || c == 'N')
			return 14;
		if (c == 'o' || c == 'O')
			return 15;
		if (c == 'p' || c == 'P')
			return 16;
		if (c == 'q' || c == 'Q')
			return 17;
		if (c == 'r' || c == 'R')
			return 18;
		if (c == 's' || c == 'S')
			return 19;
		if (c == 't' || c == 'T')
			return 20;
		if (c == 'u' || c == 'U')
			return 21;
		if (c == 'v' || c == 'V')
			return 22;
		if (c == 'w' || c == 'W')
			return 23;
		if (c == 'x' || c == 'X')
			return 24;
		if (c == 'y' || c == 'Y')
			return 25;
		if (c == 'z' || c == 'Z')
			return 26;
		return -1;
	}

	private static String parseNumber(String num) {
		String returns = "";
		if (num.length() == 3) {
			if (num.substring(0, 1).equals("0")) {
				num = num.substring(1, 3);
			} else {
				String hundreds = num.substring(0, 1);
				if (hundreds.equals("1"))
					returns += "one";
				else if (hundreds.equals("2"))
					returns += "two";
				else if (hundreds.equals("3"))
					returns += "three";
				else if (hundreds.equals("4"))
					returns += "four";
				else if (hundreds.equals("5"))
					returns += "five";
				else if (hundreds.equals("6"))
					returns += "six";
				else if (hundreds.equals("7"))
					returns += "seven";
				else if (hundreds.equals("8"))
					returns += "eight";
				else if (hundreds.equals("9"))
					returns += "nine";
				returns += " hundred";
				if (!num.substring(1, 3).equals("00"))
					returns += " and ";
				num = num.substring(1, 3);
			}
		}
		if (num.length() == 2) {
			if (num.substring(0, 1).equals("0")) {
				num = num.substring(1, 2);
			} else {
				String tens = num.substring(0, 1);
				if (tens.equals("1")) {
					String singles = num.substring(1, 2);
					if (singles.equals("0"))
						returns += "ten";
					else if (singles.equals("1"))
						returns += "eleven";
					else if (singles.equals("2"))
						returns += "twelve";
					else if (singles.equals("3"))
						returns += "thirteen";
					else if (singles.equals("4"))
						returns += "fourteen";
					else if (singles.equals("5"))
						returns += "fifteen";
					else if (singles.equals("6"))
						returns += "sixteen";
					else if (singles.equals("7"))
						returns += "seventeen";
					else if (singles.equals("8"))
						returns += "eighteen";
					else if (singles.equals("9"))
						returns += "nineteen";
					num = "";
				} else {
					String singles = num.substring(1, 2);
					if (tens.equals("2"))
						returns += "twenty";
					else if (tens.equals("3"))
						returns += "thirty";
					else if (tens.equals("4"))
						returns += "forty";
					else if (tens.equals("5"))
						returns += "fifty";
					else if (tens.equals("6"))
						returns += "sixty";
					else if (tens.equals("7"))
						returns += "seventy";
					else if (tens.equals("8"))
						returns += "eighty";
					else if (tens.equals("9"))
						returns += "ninety";
					if (!singles.equals("0"))
						returns += "-";
					num = num.substring(1, 2);
				}
			}
		}
		if (num.length() == 1) {
			String singles = num;
			if (singles.equals("1"))
				returns += "one";
			else if (singles.equals("2"))
				returns += "two";
			else if (singles.equals("3"))
				returns += "three";
			else if (singles.equals("4"))
				returns += "four";
			else if (singles.equals("5"))
				returns += "five";
			else if (singles.equals("6"))
				returns += "six";
			else if (singles.equals("7"))
				returns += "seven";
			else if (singles.equals("8"))
				returns += "eight";
			else if (singles.equals("9"))
				returns += "nine";
		}
		return returns;
	}

	/**
	 * Generates a list from the array of integers containing all possible lists
	 * which can be generated from these numbers.
	 *
	 * @param num
	 *            The array of numbers to be permuted
	 * @return A list containing all possible lists
	 */
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<Integer>());
		for (int element : num) {
			ArrayList<ArrayList<Integer>> current = new ArrayList<>();
			for (ArrayList<Integer> l : result) {
				for (int j = 0; j < l.size() + 1; j++) {
					l.add(j, element);
					ArrayList<Integer> temp = new ArrayList<>(l);
					current.add(temp);
					l.remove(j);
				}
			}
			result = new ArrayList<>(current);
		}
		return result;
	}
	
	public static HashSet<Long> permuteDigits(long n) {
		HashSet<Long> returns = new HashSet<>();
		int[] digits = toDigits(n);
		ArrayList<ArrayList<Integer>> permutations = permute(digits);
		for(ArrayList<Integer> l : permutations){
			returns.add(toNumber(l));
		}
		return returns;
	}
	
	public static long toNumber(ArrayList<Integer> list){
		long returns = 0;
		for(int i = 0; i < list.size(); i++){
			returns += list.get(list.size()-i-1) * pow(10, i);
		}
		return returns;
	}

	/**
	 * Returns n^exp. Uses only whole numbers for increased accuracy.
	 *
	 * @param n
	 *            The base of the equation
	 * @param exp
	 *            The exponent
	 * @return n^exp
	 */
	public static long pow(long n, int exp) {
		if(exp == 0)return 1;
		long returns = n;
		for (int i = 1; i < exp; i++) {
			returns *= n;
		}
		return returns;
	}

	/**
	 * Calculates and returns all prime factors of the given number. The prime
	 * factorization of an integer is the multiset of primes those product is
	 * the integer.
	 *
	 * @param n
	 *            the number of which the primefactors should be calculated
	 * @return a list containing all prime-factors of this number
	 * @see #distinctPrimeFactors(int) for a set containing each primefactor
	 *      only once
	 */
	public static List<Integer> primeFactors(int n) {
		List<Integer> factors = new ArrayList<>();
		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}
		return factors;
	}

	/**
	 * Returns a number that is the input-number spelled backwards
	 *
	 * @param n
	 *            the number to be reversed
	 * @return the reversed number
	 */
	public static int reverseNumber(int n) {
		int reverse = 0;
		while (n != 0) {
			reverse *= 10;
			reverse += n % 10;
			n = n / 10;
		}
		return reverse;
	}
	
	public static BigInteger reverseNumber(BigInteger n){
		String num = n.toString();
		String returns = "";
		for(int i = num.length()-1; i >= 0; i--){
			returns += String.valueOf(num.charAt(i));
		}
		return new BigInteger(returns);
	}

	/**
	 * Converts a number to a string in a way how the number would be spoken
	 *
	 * @param val
	 *            the number to be converted
	 * @return the converted string
	 */
	public static String toWords(BigInteger val) {
		String num = "";
		if (val.compareTo(new BigInteger("0")) < 0) {
			val = val.negate();
			num += "Minus ";
		}
		String string = val.toString();
		List<String> nums = new ArrayList<>();
		while (!string.equals("")) {
			if (string.length() < 3) {
				nums.add(string.substring(0, string.length()));
				string = "";
			} else {
				String add = string.substring(string.length() - 3, string.length());
				nums.add(add);
				string = string.substring(0, string.length() - 3);
			}
		}
		if (nums.size() < 43) {
			for (int i = nums.size() - 1; i >= 0; i--) {
				num += parseNumber(nums.get(i));
				if (i == 1)
					num += " Thousand\n";
				else if (i == 2)
					num += " Million\n";
				else if (i == 3)
					num += " Billion\n";
				else if (i == 4)
					num += " Trillion\n";
				else if (i == 5)
					num += " Quadrillion\n";
				else if (i == 6)
					num += " Quintillion\n";
				else if (i == 7)
					num += " Sextillion\n";
				else if (i == 8)
					num += " Septillion\n";
				else if (i == 9)
					num += " Octillion\n";
				else if (i == 10)
					num += " Nonillion\n";
				else if (i == 11)
					num += " Decillion\n";
				else if (i == 12)
					num += " Undecillion\n";
				else if (i == 13)
					num += " Duodecillion\n";
				else if (i == 14)
					num += " Tredecillion\n";
				else if (i == 15)
					num += " Quattuordecillion\n";
				else if (i == 16)
					num += " Quindecillion\n";
				else if (i == 17)
					num += " Sexdecillion\n";
				else if (i == 18)
					num += " Septendecillion\n";
				else if (i == 19)
					num += " Octodecillion\n";
				else if (i == 20)
					num += " Novemdecillion\n";
				else if (i == 21)
					num += " Vigintillion\n";
				else if (i == 22)
					num += " Unvigintillion\n";
				else if (i == 23)
					num += " Duovigintillion\n";
				else if (i == 24)
					num += " Tresvigintillion\n";
				else if (i == 25)
					num += " Quattuorvigintillion\n";
				else if (i == 26)
					num += " Quinquavigintillion\n";
				else if (i == 27)
					num += " Sesvigintillion\n";
				else if (i == 28)
					num += " Septemvigintillion\n";
				else if (i == 29)
					num += " Octovigintillion\n";
				else if (i == 30)
					num += " Novemvigintillion\n";
				else if (i == 31)
					num += " Trigintillion\n";
				else if (i == 32)
					num += " Untrigintillion\n";
				else if (i == 33)
					num += " Duotrigintillion\n";
				else if (i == 34)
					num += " Trestrigintillion\n";
				else if (i == 35)
					num += " Quattuortrigintillion\n";
				else if (i == 36)
					num += " Quinquatrigintillion\n";
				else if (i == 37)
					num += " Sestrigintillion\n";
				else if (i == 38)
					num += " Septentrigintillion\n";
				else if (i == 39)
					num += " Octotrigintillion\n";
				else if (i == 40)
					num += " Noventrigintillion\n";
				else if (i == 41)
					num += " Quadragintillion\n";
			}
		} else {
			num = "number is too big!";
		}
		return num;
	}

	/**
	 * Converts a number to a string in a way how the number would be spoken
	 *
	 * @param num
	 *            the number to be converted
	 * @return the converted string
	 */
	public static String toWords(long num) {
		return toWords(BigInteger.valueOf(num));
	}

	/**
	 * Calculates the value of the word by adding all placements of the single letters in the alphabet.
	 * @param word The word to be parsed
	 * @return The value of the word
	 * @see #letterValue(char)
	 */
	public static int wordValue(String word) {
		int returns = 0;
		for (char c : word.toCharArray()) {
			returns += letterValue(c);
		}
		return returns;
	}
	
	public static BigInteger nCr(int n, int r){
		return bigfactorial(n).divide((bigfactorial(r).multiply(bigfactorial(n-r))));
	}
	
	/**
	 * A set of numbers is cyclic if all numbers are of the same length,
	 * the length is even and the second half of a number is the first half of the next number.
	 * @param nums The set to be checked
	 * @return whether the set is cyclic
	 */
	public static boolean isCyclic(int[] nums){
		int length = String.valueOf(nums[0]).length();
		if(length%2 == 1)return false;
		for(int num : nums){
			if(String.valueOf(num).length() != length)return false;
		}
		int prev = nums[nums.length-1];
		for(int i = 0; i < nums.length; i++){
			String prevs = String.valueOf(prev);
			String num = String.valueOf(nums[i]);
			if(!prevs.substring(length/2).equals(num.substring(0, length/2)))return false;
			prev = nums[i];
		}
		return true;
	}
	
	/**
	 * A number is a cube if the cube root of this number is an integer.
	 * @param input The number to be checked
	 * @return whether the number is a cube
	 */
	public static boolean isCube(long input) {
	    double cubeRoot = Math.cbrt(input);
	    return Math.round(cubeRoot) == cubeRoot;
	}
	
	public static List<String> readFile(String resourcePath) {
		BufferedReader txtReader = new BufferedReader(new InputStreamReader(AOCUtils.class.getResourceAsStream(resourcePath)));
		List<String> returns = new ArrayList<>();
		try {
			for (String x = txtReader.readLine(); x != null; x = txtReader.readLine()) {
				returns.add(x);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returns;
	}
	
	public static boolean isDigit(char c) {
		char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
		for (char a : digits) {
			if (c == a) return true;
		}
		return false;
	}
}