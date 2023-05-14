
package QuanLyBanHangOnlline;
import java.util.*;
public class Cart {
    private ArrayList<Product> products = new ArrayList<>();
    public Cart() {
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product){
        this.products.add(product);
    }
    public void removeProduct(Product product){
        this.products.remove(product);
    }
    public ArrayList<Product> getProducts(){
        return this.products;
    }
    public int TongtienInCart(){
        int tong =0;
        for(Product x : products){
            tong += x.getGia() * x.getSoluongmua();
        }
        return tong;
    }
        
}
