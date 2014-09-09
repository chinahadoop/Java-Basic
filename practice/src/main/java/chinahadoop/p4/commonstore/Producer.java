package chinahadoop.p4.commonstore;


public class Producer implements Runnable {

    private String producerName;
    private StoreHouse storeHouse;


    public Producer(String producerName,StoreHouse storeHouse){
        this.producerName = producerName;
        this.storeHouse = storeHouse;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
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
        int i = 0;
        while (true){
            i++;
            Product pro = new Product(i);
            storeHouse.push(pro);
            System.out.println(getProducerName()+" 生产了："+pro);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
