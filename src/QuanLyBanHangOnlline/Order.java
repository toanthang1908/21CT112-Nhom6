
package QuanLyBanHangOnlline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Order {
    private String id;
    private String date ;
    private ArrayList<Product> products;
   
    public Order(String id, String date, ArrayList<Product> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }

    public Order() {       
        this.products = new ArrayList<>();     
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        this.date=formattedDateTime;
        
    }

    public String getId() {     
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public String toString(){
        return String.format("%-25s%-17s","Ma Don Hang: "+getId(),"\nThoi Gian Dat Hang : "+date);
    }
}
