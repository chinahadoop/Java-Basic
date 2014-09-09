package chinahadoop.p4.commonstore;


public class Test {
   public static void main(String args[]){
       StoreHouse storeHouse = StoreHouse.getInstance();
       Producer pro1 = new Producer("java",storeHouse);
       Thread pth1 = new Thread(pro1);
       pth1.start();

       Producer pro2 = new Producer("scala",storeHouse);
       Thread pth2 = new Thread(pro2);
       pth2.start();


       Consumer cs1 = new Consumer("同学a",storeHouse);
       Thread cth1 = new Thread(cs1);
       cth1.start();

       Consumer cs2 = new Consumer("同学b",storeHouse);
       Thread cth2 = new Thread(cs2);
       cth2.start();

   }
}
