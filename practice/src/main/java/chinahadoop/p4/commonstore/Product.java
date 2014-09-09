package chinahadoop.p4.commonstore;


public class Product {
    private int id;

    public Product(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}
