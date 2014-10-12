package chinahadoop.p1;

import java.io.*;
import java.util.List;

public class Read {

	public List<String> lineList;

	public Read(List<String> lineList) {
		this.lineList = lineList;
	}

	public void readFile(String readPath) throws IOException {
		BufferedReader br = null;
		try {
			FileInputStream fin = new FileInputStream(readPath);
			Reader r = new InputStreamReader(fin, "GBK");
			br = new BufferedReader(r);
			String line;
			while ((line = br.readLine()) != null) {
				lineList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
		}
	}
}
