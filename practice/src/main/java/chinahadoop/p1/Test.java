package chinahadoop.p1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String args[]) throws IOException {
		List<String> lineList = new ArrayList<String>();
		Read r = new Read(lineList);
		r.readFile("test.txt");
		Write w = new Write(lineList);
		w.writeFile("test2.txt");
	}

}
