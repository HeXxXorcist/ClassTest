package classtest;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class ArrayTest {
	private static final int ARRAY_LENGTH = 2000000;
	private final int test[];
	private final Map<Integer, Integer> key;
	
	public ArrayTest() {
		this(ARRAY_LENGTH);
	}

	public ArrayTest(int length) {
		SecureRandom random = new SecureRandom();
		test = new int[length];
		for(int i = 0; i < length; i++)
			test[i] = random.nextInt();
		key = buildKeyMap();
	}
	
	public ArrayTest(String type) {
		switch (type) {
		case "small":
			test = new int[]{1,1,1,1,1,1,1,2,2,2,3,3,0};
			key = buildKeyMap();
			break;
		default:
			test = new int[ARRAY_LENGTH];
			key = buildKeyMap();
			break;
		}
	}
		
	public int getDesiredInt(Compare comparison) {
		Integer mostRepeatedValue = null;
		for (Map.Entry<Integer, Integer> e : key.entrySet()) {
			if(mostRepeatedValue == null) {
				mostRepeatedValue = new Integer(e.getKey());
			}
			else {
				if(comparison.compare(key.get(mostRepeatedValue), e.getValue()))
					mostRepeatedValue = e.getKey();
			}
//			System.out.println("key: " + e.getKey() + " value: " + e.getValue());			
		}
		return mostRepeatedValue;
	}

	private Map<Integer, Integer> buildKeyMap() {
		Map<Integer, Integer> key = new HashMap<Integer, Integer>();
		for(int i = 0; i < test.length; i++) {
			if (key.containsKey(test[i])) {
				key.replace(test[i], key.get(test[i]) + 1);
			} else {
				key.put(test[i], 1);
			}
		}
		return key;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ArrayTest array = new ArrayTest();
		System.out.println("Creación del arreglo: "
		+ (System.currentTimeMillis() - startTime) + " milisegundos");
		
		startTime = System.currentTimeMillis();
		int mostRepeatedValue = array.getDesiredInt((a,b) -> a<b);
		System.out.println("Mayor: " + mostRepeatedValue + " calculado en: "
		+ (System.currentTimeMillis() - startTime) + " milisegundos");

		startTime = System.currentTimeMillis();
		int leastRepeatedValue = array.getDesiredInt((a,b) -> a>b);
		System.out.println("Menor: " + leastRepeatedValue + " calculado en: "
		+ (System.currentTimeMillis() - startTime) + " milisegundos");
	}
}
