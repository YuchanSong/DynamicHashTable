public class HashTable <K, V> {

	private int m; // table size
	private int n; // number of items
	private K[] keys;
	private V[] values;
	
	public HashTable(int capacity) {
		m = capacity;
		n = 0;
		keys = (K[]) new Object[m];
		values = (V[]) new Object[m];
	}
	
	private int hashValue(K key) {
		return (key.hashCode() & 0x7fffffff) % m; // 해시코드가 음수일 때 양수로 저장하기 위해 사용(overflow 방지)
	}
	
	public void put(K key, V val) {
		
		int i = hashValue(key); // 가변되는 key의 해시값
		int pre_i = i; // key의 기존 해시값
		
		// put연산을 하기전에 load factor > 0.5인지 확인, 참이면 resize 실행
		if ((n + 1) * 2 >= m) resize(m * 2);
		
		//입력받은 key값이 해시테이블에 이미 있을 때 put을 실행하지 않고 value값을 갱신해준다.
		do {
			if (key.equals(keys[i])) {
				values[i] = val; 
				return;
			} 
			i = (i + 1) % m; // 찾는 값이 없으면 hashValue 증가
		} while (i != pre_i); // 해시테이블을 한 싸이클 돌면 반복문 빠져나옴 
		
		//key[i]에 이미 값이 들어가 있을 때(충돌시) 선형조사 실시
		while (keys[i] != null) i = (i + 1) % m;
		
		//key[i]에 값이 없다면 put 연산
		keys[i] = key;
		values[i] = val;
		n++; // 해시테이블의 아이템 수 증가
	}
	
	public V get(K key) {
		
		int i = hashValue(key); // 가변되는 key의 해시값
		int pre_i = i; // key의 기존 해시값

		// key가 keys[i]와 같을 때 values[i]를 return한다.
		do {
			if (key.equals(keys[i])) return values[i];
			i = (i + 1) % m; // 찾는 값이 없으면 hashValue 증가
		} while (i != pre_i); // 해시테이블을 한 싸이클 돌면 반복문 빠져나옴

		return null; // 찾는 값이 없을 땐 null을 반환한다
	}
	
	private void resize(int capacity) {
		// put에서 load factor > 0.5 => 배열 크기 2배, n&기존배열 초기화, 리해싱
		
		//1. 원래 key값과 value값을 저장할 변수 선언
		K[] cpKeys = (K[]) new Object[capacity];
		V[] cpValues = (V[]) new Object[capacity];
		
		//2. 새로운 배열에 keys,values 저장
		for (int i = 0; i < m; i++) {
			cpKeys[i] = keys[i];
			cpValues[i] = values[i];
		}
		
		//3. 배열 크기 증가, n&기존배열 초기화, 리해싱
		m = capacity;
		n = 0;
		keys = (K[]) new Object[m];
		values = (V[]) new Object[m];
		for (int i = 0; i < m; i++) {
			if (cpKeys[i] != null) // cpKeys의 값이 null이 아닌 경우에만 put연산
				put(cpKeys[i], cpValues[i]);
		}
		
	}
	
	public void printHT() {
		for (int i = 0; i < m; i++)
			System.out.println(i + ")  key : " + keys[i] + "\t\t\tvalue : " + values[i]);
	}
	
}
