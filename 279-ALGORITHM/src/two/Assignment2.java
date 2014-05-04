package two;
import java.util.Random;


public class Assignment2 {
	private static final int ARRAY_LENGTH = 5000000;
	
	public static int[] generatingArray(int length) {
		int[] a = new int[length];
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			a[i] = r.nextInt();
		}
		return a;
	}
	
	public static int[] generatingArray(int length, int uniqueCount) {
		int[] a = new int[length];
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			a[i] = r.nextInt(uniqueCount);
		}
		return a;
	}
	
	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void quickSort(int[] a, int start, int end) {
		if (start < end) {
			int mid = partition(a, start, end);
			quickSort(a, start, mid - 1);
			quickSort(a, mid + 1, end);
		}
	}
	
	public static void quickSort2(int[] a, int start, int end, int insertion_sort_threshold) {
		if ((end - start) < insertion_sort_threshold) {
			insertionSort(a, start, end);
		} else {
			int mid = partition(a, start, end);
			quickSort2(a, start, mid - 1, insertion_sort_threshold);
			quickSort2(a, mid + 1, end, insertion_sort_threshold);
		}
	}
	
	public static void quickSort3(int[] a, int start, int end) {
		if (start < end) {
			int[] mid = partition3(a, start, end);
			quickSort3(a, start, mid[0] - 1);
			quickSort3(a, mid[1], end);
		}
	}
	
	public static int partition(int[] a, int start, int end) {
		int p = a[end];
		int topLow = start - 1;
		for (int i = start; i < end; i++) {
			if (a[i] <= p) {
				topLow++;
				int temp = a[i];
				a[i] = a[topLow];
				a[topLow] = temp;
			}
		}
		topLow += 1;
		int temp = a[end];
		a[end] = a[topLow];
		a[topLow] = temp;
		return topLow;
	}
	
	public static int[] partition3(int[] a, int start, int end) {
		int p = a[end];
		int topLow = start - 1;
		int topEqual = topLow;
		for (int i = start; i < end; i++) {
			if (a[i] < p) {
				topLow++;
				topEqual++;
				int temp = a[i];
				a[i] = a[topEqual];
				a[topEqual] = a[topLow];
				a[topLow] = temp;
			} else if (a[i] == p) {
				topEqual++;
				int temp = a[i];
				a[i] = a[topEqual];
				a[topEqual] = temp;
			}
		}
		topEqual += 1;
		int temp = a[end];
		a[end] = a[topEqual];
		a[topEqual] = temp;
		int[] result = {topLow + 1, topEqual};
		return result;
	}
	
	public static void insertionSort(int[] a, int start, int end) {
		for (int j = start + 1; j <= end; j++) {
			int key = a[j];
			int i = j - 1;
			while (i > start && a[i] > key) {
				a[i+1] = a[i];
				i = i - 1;
			}
			a[i+1] = key;
		}
	}
	
	public static void main(String[] args) {
		
		int[] a1 = new int[ARRAY_LENGTH];
		int[] a2 = new int[ARRAY_LENGTH];
		long[] qsTimeUsed = new long[60];
		long[] insertTimeUsed = new long[60];
		for (int i = 0; i < qsTimeUsed.length; i++) {
			qsTimeUsed[0] = 0;
			insertTimeUsed[0] = 0;
		}
		
		//first quick sort test
		/*
		for (int i = 5; i <= 300; i += 5) {
			a1 = generatingArray(ARRAY_LENGTH);
			long qsStart = System.currentTimeMillis();
			for (int j = 0; j + i < ARRAY_LENGTH; j += i) {
				quickSort(a1, j, j + i - 1);
			}
			long qsEnd = System.currentTimeMillis();
			qsTimeUsed[i/5 - 1] = qsEnd - qsStart;
			
			a2 = generatingArray(ARRAY_LENGTH);
			long instStart = System.currentTimeMillis();
			for (int j = 0; j + i < ARRAY_LENGTH; j += i) {
				int[] subarray = new int[i];
				for (int k = 0; k < i; k++) {
					subarray[k] = a2[j + k];
				}
				insertionSort(subarray);
			}
			long instEnd = System.currentTimeMillis();
			insertTimeUsed[i/5 - 1] = instEnd - instStart;
		}
		*/
		
		//2nd quickSort
		/*
		for (int i = 1; i <= 200; i++) {
			int INSERTION_SORT_THRESHOLD = i;
			int[] a3 = generatingArray(ARRAY_LENGTH);
			long start = System.currentTimeMillis();
			quickSort2(a3, 0, a3.length - 1, INSERTION_SORT_THRESHOLD);
			long end = System.currentTimeMillis();
			System.out.println((end - start));
		}
		*/
//		for (int i = 0; i < 60; i++) {
//			//System.out.print((i+1) * 5 + " ");
//			System.out.println(insertTimeUsed[i]);
//		}
		
		//3rd qs
		int[] qsArray = generatingArray(10000000, (int) Math.pow(10.0, 7.0));
		long start = System.currentTimeMillis();
		quickSort(qsArray, 0, qsArray.length - 1);
		long end = System.currentTimeMillis();
		System.out.println("qs result " + (end - start));
		
		int[] qs3Array = generatingArray(10000000, (int) Math.pow(10.0, 7.0));
		start = System.currentTimeMillis();
		quickSort3(qs3Array, 0, qs3Array.length - 1);
		end = System.currentTimeMillis();
		System.out.println("qs3 result " + (end - start));
		
		for (int i = 1; i < qs3Array.length; i++) {
			if (qs3Array[i] < qs3Array[i-1]) {
				System.out.println("qs3 array not sorted");
				break;
			}
		}
		
		System.out.println("end");
	}
}
