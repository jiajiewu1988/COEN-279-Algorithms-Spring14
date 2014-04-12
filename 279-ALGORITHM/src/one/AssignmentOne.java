package one;

public class AssignmentOne {
	private static final int TEN_POWER_SIX = (int) Math.pow(10.0, 6.0);
	private static final int TEN_POWER_SEVEN = (int) Math.pow(10.0, 7.0);
	private static final int TEN_POWER_EIGHT = (int) Math.pow(10.0, 8.0);
	private static final int TEN_POWER_NINE = (int) Math.pow(10.0, 9.0);
	private static final int RANGE = 1000;
	private static final int PRIME_ARRAY_LENGTH = 6542;
	private static int[] primeArray = new int[PRIME_ARRAY_LENGTH];
	
	private static final long[] trialModuloCount = new long[4];
	
	public static boolean isPrime1(int num) {
		//negative integers, 0, and 1 are not primes
		if (num <= 1) return false;
		int d = 2;
		
		while (d <= num) {
			trialModuloCount[0]++;
			if (num % d == 0)
				return false;
			d += 1;
		}
		return true;
	}
	
	public static boolean isPrime2(int num) {
		if (num <= 1) return false;
		if (num == 2) return true; //2 is prime
		if (num % 2 == 0) return false;
		int d = 3;
		
		while (d < num) {
			trialModuloCount[1]++;
			if (num % d == 0) 
				return false;
			d += 2;
		}
		return true;
	}
	
	public static boolean isPrime3(int num) {
		if (num <= 1) return false;
		if (num == 2) return true; // 2 is prime
		if (num % 2 == 0) return false;
		
		int d = 3;
		while (d * d <= num) {
			trialModuloCount[2]++;
			if (num % d == 0) 
				return false;
			d += 2;
		}
		
		return true;
	}
	
	public static boolean isPrime4(int num) {
		if (num <= 1) return false;
		int i = 0;
		int end = (int) Math.floor(Math.sqrt((double) num));
		
		while (primeArray[i] <= end) {
			trialModuloCount[3]++;
			if (num % primeArray[i] == 0) 
				return false;
			i += 1;
		}
		
		return true;
	}
	
	public static void buildPrimeArray() {
		int limit = (int) Math.pow(2.0, 31.0);
		int position = 0;
		for (int i = 1; i <= limit; i++) {
			if (isPrime3(i)) {
				primeArray[position++] = i;
				if (position >= PRIME_ARRAY_LENGTH) break;
			}
		}
	}
	
	public static void doStatistics(int num) {
		for (int i = 0; i < trialModuloCount.length; i++) { //Initialize Count
			trialModuloCount[i] = 0;
		}
		for (int i = num; i <= num + RANGE; i++) {
			isPrime1(i);
			isPrime2(i);
			isPrime3(i);
			isPrime4(i);
		}	
		System.out.print(num + " - " + (num+1000) + " : \n");
		for (int i = 0; i < trialModuloCount.length; i++) {
			System.out.println("isPrime" + (i+1) + " : " + trialModuloCount[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		buildPrimeArray();
		doStatistics(TEN_POWER_SIX);
		doStatistics(TEN_POWER_SEVEN);
		doStatistics(TEN_POWER_EIGHT);
		doStatistics(TEN_POWER_NINE);
	}
	
}
