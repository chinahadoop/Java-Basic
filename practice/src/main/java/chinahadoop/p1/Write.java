package chinahadoop.p1;

import java.io.*;
import java.util.List;

/**
 * Created by shen on 14-9-5.
 */
public class Write {

	public List<String> lineList;

	public Write(List<String> lineList) {
		this.lineList = lineList;
	}

	public void writeFile(String writePath) throws IOException {
		PrintWriter pw = null;
		try {
			FileOutputStream fout = new FileOutputStream(writePath);
			Writer w = new OutputStreamWriter(fout, "UTF-8");
			pw = new PrintWriter(w);
			for (int i = lineList.size() - 1; i >= 0; i--) {
				pw.println(lineList.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pw != null)
				try {
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw (new IOException(e));
				}
		}
	}
}
