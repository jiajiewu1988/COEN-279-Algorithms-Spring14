package assign1;

/**
1000000 - 1001000 : 
isPrime1 : Average Operation: 74983.99/ Max: 1000997/ # of Prime: 75
isPrime2 : Average Operation: 37492.71/ Max: 500499/ # of Prime: 75
isPrime3 : Average Operation: 49.345654/ Max: 499/ # of Prime: 75
isPrime4 : Average Operation: 18.36963/ Max: 168/ # of Prime: 75

10000000 - 10001000 : 
isPrime1 : Average Operation: 609488.75/ Max: 10000991/ # of Prime: 61
isPrime2 : Average Operation: 304745.12/ Max: 5000496/ # of Prime: 61
isPrime3 : Average Operation: 128.86613/ Max: 1580/ # of Prime: 61
isPrime4 : Average Operation: 38.987015/ Max: 446/ # of Prime: 61

100000000 - 100001000 : 
isPrime1 : Average Operation: 5394764.0/ Max: 100000967/ # of Prime: 54
isPrime2 : Average Operation: 2697382.8/ Max: 50000484/ # of Prime: 54
isPrime3 : Average Operation: 336.36963/ Max: 4999/ # of Prime: 54
isPrime4 : Average Operation: 86.596405/ Max: 1229/ # of Prime: 54

1000000000 - 1000001000 : 
isPrime1 : Average Operation: 4.8951392E7/ Max: 1000000991/ # of Prime: 49
isPrime2 : Average Operation: 2.4475698E7/ Max: 500000496/ # of Prime: 49
isPrime3 : Average Operation: 934.6613/ Max: 15810/ # of Prime: 49
isPrime4 : Average Operation: 207.87112/ Max: 3401/ # of Prime: 49
 *
 *
 * @author jerry
 *
 */

public class AssignmentOne {
	private static final int TEN_POWER_SIX = (int) Math.pow(10.0, 6.0);
	private static final int TEN_POWER_SEVEN = (int) Math.pow(10.0, 7.0);
	private static final int TEN_POWER_EIGHT = (int) Math.pow(10.0, 8.0);
	private static final int TEN_POWER_NINE = (int) Math.pow(10.0, 9.0);
	private static final int RANGE = 1000;
	private static final int PRIME_ARRAY_LENGTH = 6542;
	private static int[] primeArray = new int[PRIME_ARRAY_LENGTH];
	
	private static final long[] trialModuloCount = new long[4];
	private static final long[] trialModuloMax = new long[4];
	private static final int[] primeCount = new int[4];
	
	public static boolean isPrime1(int num) {
		//negative integers, 0, and 1 are not primes
		if (num <= 1) return false;
		int d = 2;
		int modCount = 0;
		
		while (d < num) {
			trialModuloCount[0]++;
			modCount++;
			if (num % d == 0){
				if (modCount > trialModuloMax[0]) trialModuloMax[0] = modCount;
				return false;
			}
			d++;
		}
		
		if (modCount > trialModuloMax[0]) trialModuloMax[0] = modCount;
		return true;
	}
	
	public static boolean isPrime2(int num) {
		if (num <= 1) return false;
		if (num == 2) return true; //2 is prime
		int modCount = 0;
		modCount++;
		if (modCount > trialModuloMax[1]) trialModuloMax[1] = modCount;
		trialModuloCount[1]++;
		if (num % 2 == 0) return false;
		int d = 3;
		
		
		while (d < num) {
			trialModuloCount[1]++;
			modCount++;
			if (num % d == 0) {
				if (modCount > trialModuloMax[1]) trialModuloMax[1] = modCount;
				return false;
			}
			d += 2;
		}
		
		if (modCount > trialModuloMax[1]) trialModuloMax[1] = modCount;
		return true;
	}
	
	public static boolean isPrime3(int num) {
		if (num <= 1) return false;
		if (num == 2) return true; // 2 is prime
		int modCount = 0;
		if (modCount > trialModuloMax[2]) trialModuloMax[2] = modCount;
		trialModuloCount[2]++;
		if (num % 2 == 0) return false;
		
		int d = 3;
		while (d * d <= num) {
			trialModuloCount[2]++;
			modCount++;
			if (num % d == 0) { 
				if (modCount > trialModuloMax[2]) trialModuloMax[2] = modCount;
				return false;
			}
			d += 2;
		}
		
		if (modCount > trialModuloMax[2]) trialModuloMax[2] = modCount;
		return true;
	}
	
	public static boolean isPrime4(int num) {
		if (num <= 1) return false;
		int i = 0;
		int end = (int) Math.floor(Math.sqrt((double) num));
		int modCount = 0;
		
		while (primeArray[i] <= end) {
			trialModuloCount[3]++;
			modCount++;
			if (num % primeArray[i] == 0) {
				if (modCount > trialModuloMax[3]) trialModuloMax[3] = modCount;
				return false;
			}
			i += 1;
		}
		
		if (modCount > trialModuloMax[3]) trialModuloMax[3] = modCount;
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
			trialModuloMax[i] = 0;
			primeCount[i] = 0;
		}
		
		for (int i = num; i <= num + RANGE; i++) {
			if(isPrime1(i)) primeCount[0]++;
			if(isPrime2(i)) primeCount[1]++;
			if(isPrime3(i)) primeCount[2]++;
			if(isPrime4(i)) primeCount[3]++;
		}	
		System.out.print(num + " - " + (num+1000) + " : \n");
		for (int i = 0; i < trialModuloCount.length; i++) {
			System.out.println("isPrime" + (i+1) + " : Average Operation: " + (float) ((float) trialModuloCount[i]/1001)
					+ "/ Max: " + trialModuloMax[i] + "/ # of Prime: " + primeCount[i]);
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
