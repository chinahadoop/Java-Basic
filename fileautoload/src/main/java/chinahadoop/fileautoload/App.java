package chinahadoop.fileautoload;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
	static Properties properties = new Properties();
	static String fileName;

	static class refreshThread implements Runnable {

		private Map<String, String> cachedValues;

		private void firstRoundOutput() {
			Set<String> keys = properties.stringPropertyNames();
			cachedValues = new HashMap<String, String>();
			for (String key : keys) {
				String value = properties.getProperty(key);
				System.out.println(key + " is " + value);
				cachedValues.put(key, value);
			}
		}

		private void outputIfUpdated() {
			Set<String> keys = properties.stringPropertyNames();
			for (String key : keys) {
				String oldValue = cachedValues.get(key);
				String newValue = properties.getProperty(key);
				if (!newValue.equals(oldValue)) {
					System.out.println(key + " is changed to " + newValue);
					cachedValues.put(key, newValue);
				}
			}
		}

		@Override
		public void run() {
			try {
				properties.load(new FileInputStream(fileName));
				if (cachedValues == null) {
					firstRoundOutput();
				} else {
					outputIfUpdated();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Command format: App <filename>");
			System.exit(1);
		}
		fileName = args[0];

		ScheduledExecutorService scheduler = Executors
				.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new refreshThread(), 0, 1000,
				TimeUnit.MILLISECONDS);
	}
}
