
package QuanLyBanHangOnlline;
import java.util.*;
public class Product {
        private String id;
        private String ten;
        private int soluong;
        private int soluongmua;
        private int gia;       
        Scanner sc = new Scanner(System.in);
    public Product(String id, String ten, int soluong, int gia) {
        this.id = id;
        this.ten = ten;
        this.soluong = soluong;    
        this.gia = gia;
    }

    public Product(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }   
    public void truhang(int soluong){
        this.soluong-=soluong;
    }
    public void themhang(int soluong){
        this.soluong+=soluong;
    }
    public int TongTien(){
        return this.gia * this.soluongmua;
    }
    public String toString(){
        return String.format("%-15s%-20s%-10s%-10s",id,ten,soluong,gia);
    }
    public boolean hangton(){
        return soluong > 0;
    }
    public void InutIFProduct(){
        System.out.print("Nhap ID: ");
        this.id = sc.nextLine();
        System.out.print("Nhap Ten: ");
        this.ten=sc.nextLine();
        System.out.print("Nhap so luong: ");
        this.soluong=sc.nextInt();
        System.out.print("Nhap gia: ");
        this.gia=sc.nextInt();
    }
}
