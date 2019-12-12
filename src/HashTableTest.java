import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashTableTest {
	
	public static void main(String[] args) {
		
		// [C]-1
		HashTable<String, String> ht = new HashTable<String, String>(16);
 		ht.put("Korea", "Seoul");
 		ht.put("France", "Paris");
 		ht.put("Germany", "Berlin");
 		ht.put("USA", "Washington. D. C.");
 		ht.put("Japan", "Tokyo");
 		ht.put("China", "Beijing");
 		ht.printHT();
 		
 		// [C]-2
//		HashTable<String, String> ht = new HashTable<String, String>(8);
// 		BufferedReader fin = null;
//		String line;
//		
//		try {
//			fin = new BufferedReader(new FileReader("src/cap.txt"));
//			while ((line = fin.readLine()) != null) {
//				ht.put(line.split("\t")[0], line.split("\t")[1]);
//			}
//			fin.close();
//		}catch(IOException e){
//			System.out.println("File Error!");
//			System.exit(1);
//		}
//		ht.printHT();
//		System.out.println(ht.get("South Korea"));
//		System.out.println(ht.get("United Arab Emirates"));
	}

}
