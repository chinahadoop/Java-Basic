package chinahadoop.p4.commonstore;


public class Consumer implements Runnable {

    private String consumerName;
    private StoreHouse storeHouse;


    public Consumer(String consumerName, StoreHouse storeHouse){
        this.consumerName = consumerName;
        this.storeHouse = storeHouse;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public StoreHouse getStoreHouse() {
        return storeHouse;
    }

    public void setStoreHouse(StoreHouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    @Override
    public void run() {
        exec();

    }

    public void exec(){
        while (true){
            System.out.println(this.getConsumerName()+" 消费了："+storeHouse.pop());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
