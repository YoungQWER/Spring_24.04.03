package ex03;

import java.io.FileWriter;
import java.io.IOException;

public class SimpleWrite {

	public static void main(String[] args) {
		
		try {
			FileWriter out = new FileWriter("c:/upload/data.txt");
			out.write('A');
			out.write('B');
			out.write("asasdDsd");
			out.write("--");
			out.write("korea", 1, 1);
			out.write('\n');
			out.write("korea");
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
