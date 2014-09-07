package chinahadoop.deadlock;

/**
 * Hello world!
 *
 */
public class App {
	
	static Object obj1 = new Object(), obj2 = new Object();
	
	static class ThreadA implements Runnable {
		@Override
		public void run() {
			synchronized(obj1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(obj2) {
					System.out.println("Should not be here");
				}
			}
		}
	}

	
	static class ThreadB implements Runnable {
		@Override
		public void run() {
			synchronized(obj2) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(obj1) {
					System.out.println("Should not be here");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(new ThreadA()).start();
		new Thread(new ThreadB()).start();
	}

}
