package chinahadoop.p4.commonstore;

public class StoreHouse {

	private volatile int base = 0;
	private volatile int top = 0;
	private Product[] products = new Product[10];
	private Object pushLock = new Object();
	private Object popLock = new Object();

	public void push(Product product) {
		synchronized (pushLock) {
			System.out.println("push:" + top + "===" + products.length);
			while (top == products.length) { // 仓库已满，等待消费
				try {
					System.out.println("仓库已满，正在等待消费。。。");
					pushLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("top:" + top);
			products[top] = product;
			top++;
		}
		synchronized (popLock) {
			popLock.notifyAll();
		}
	}

	public Product pop() {
		Product product = null;
		synchronized (popLock) {
			System.out.println("pop:" + top + "===" + base);
			while (top == base) { // 仓库为空，不能消费
				try {
					System.out.println("仓库已空，正在等待生产。。。");
					popLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			top--;
			product = products[top];
			products[top] = null;
		}
		synchronized (pushLock) {
			pushLock.notifyAll();
		}
		return product;
	}

	// 创建storeHouse 单例对象
	private static StoreHouse storeHouse = null;

	public static StoreHouse getInstance() {
		synchronized (StoreHouse.class) {
			if (null == storeHouse) {
				storeHouse = new StoreHouse();
			}

		}
		return storeHouse;
	}
}
