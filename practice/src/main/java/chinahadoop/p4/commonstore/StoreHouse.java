package chinahadoop.p4.commonstore;


public class StoreHouse {

    private int base = 0;
    private int top = 0;
    private Product[] products = new Product[10];

    public synchronized void  push(Product product){
        System.out.println("push:"+top+"==="+products.length);
        while (top == products.length){  //仓库已满，等待消费
            try {
                System.out.println("仓库已满，正在等待消费。。。");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("top:"+top);
        products[top] = product;
        top++;
        this.notifyAll();
    }

    public synchronized Product pop(){
        Product product = null;
        System.out.println("pop:"+top+"==="+base);
        while (top == base){   //仓库为空，不能消费
            try {
                System.out.println("仓库已空，正在等待生产。。。");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        top --;
        product = products[top];
        products[top] = null;
        this.notifyAll();
        return product;
    }


    //创建storeHouse 单例对象
    private  static StoreHouse storeHouse = null;

    public static StoreHouse getInstance(){
        synchronized (StoreHouse.class){
            if (null == storeHouse){
                storeHouse = new StoreHouse();
            }

        }
        return storeHouse;
    }
}
