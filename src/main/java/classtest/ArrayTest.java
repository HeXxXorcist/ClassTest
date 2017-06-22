package classtest;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class ArrayTest {
	private static final int ARRAY_LENGTH = 2000000;
	private final int test[];
	
	public ArrayTest() {
		this(ARRAY_LENGTH);
	}

	public ArrayTest(int length) {
		SecureRandom random = new SecureRandom();
		test = new int[length];
		for(int i = 0; i < length; i++)
			test[i] = random.nextInt();
	}
	
	public ArrayTest(String type) {
		switch (type) {
		case "small":
			test = new int[]{1,1,1,1,1,1,1,2,2,2,3,3,0};
			break;
		default:
			test = new int[ARRAY_LENGTH];
			break;
		}
	}
	
	public int mostRepeatedInt() {
		Map<Integer, Integer> key = new HashMap<Integer, Integer>();
		for(int i = 0; i < test.length; i++) {
			if (key.containsKey(test[i])) {
				key.replace(test[i], key.get(test[i]) + 1);
			} else {
				key.put(test[i], 1);
			}
		}
		
		Integer mostRepeatedValue = null;
		for (Map.Entry<Integer, Integer> e : key.entrySet()) {
			if(mostRepeatedValue == null) {
				mostRepeatedValue = new Integer(e.getKey());
			}
			else {
				if(key.get(mostRepeatedValue) < e.getValue())
					mostRepeatedValue = e.getKey();
			}
//			System.out.println("key: " + e.getKey() + " value: " + e.getValue());			
		}
		return mostRepeatedValue;
	}

	public int leastRepeatedInt() {
		Map<Integer, Integer> key = new HashMap<Integer, Integer>();
		for(int i = 0; i < test.length; i++) {
			if (key.containsKey(test[i])) {
				key.replace(test[i], key.get(test[i]) + 1);
			} else {
				key.put(test[i], 1);
			}
		}
		
		Integer leastRepeatedValue = null;
		for (Map.Entry<Integer, Integer> e : key.entrySet()) {
			if(leastRepeatedValue == null)
				leastRepeatedValue = e.getKey();
			else {
				if(key.get(leastRepeatedValue) > e.getValue())
					leastRepeatedValue = e.getKey();
			}
//			System.out.println("key: " + e.getKey() + " value: " + e.getValue());
		}
		return leastRepeatedValue;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ArrayTest array = new ArrayTest();
		System.out.println("Creación del arreglo: "
		+ (System.currentTimeMillis() - startTime) + " milisegundos");
		
		startTime = System.currentTimeMillis();
		int mostRepeatedValue = array.mostRepeatedInt();
		System.out.println("Mayor: " + mostRepeatedValue + " calculado en: "
		+ (System.currentTimeMillis() - startTime) + " milisegundos");

		startTime = System.currentTimeMillis();
		int leastRepeatedValue = array.leastRepeatedInt();
		System.out.println("Menor: " + leastRepeatedValue + " calculado en: "
		+ (System.currentTimeMillis() - startTime) + " milisegundos");
	}
}
