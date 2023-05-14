package QuanLyBanHangOnlline;

import java.util.*;

public class Customer extends Account {

    private String id;
    private String name;
    private String sdt;
    private String address;
    private ArrayList<Order> orders;
    private boolean ktra;
    private Cart cart;
    Scanner sc = new Scanner(System.in);

    public Customer(String id, String name, String sdt, String address, ArrayList<Order> orders) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
        this.address = address;
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isKtra() {
        return ktra;
    }

    public Customer() {
        this.orders = new ArrayList<>();
        this.cart = new Cart();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTk() {
        return tk;
    }

    @Override
    public void setTk(String tk) {
        this.tk = tk;
    }

    @Override
    public String getMk() {
        return mk;
    }

    @Override
    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void DisplayInfoCustomer() {
        System.out.println("Ma Khach Hang: " + this.id);
        System.out.println("Ten Khach Hang: " + this.name);
        System.out.println("Dia Chi: " + this.address);
        System.out.println("So Dia Chi: " + this.sdt);
    }

    public void InputInformation() {
        sc.nextLine();
        System.out.print("Nhap ID:");
        this.id = sc.nextLine();
        System.out.print("Nhap Ten: ");
        this.name = sc.nextLine();
        System.out.print("Nhap Dia Chi: ");
        this.address = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        this.sdt = sc.nextLine();
        this.ktra = true;
    }

    public void ThemProduct(ArrayList<Product> products) {
        //Khoi tao collection map de tim kiem san pham voi do phuc tap la O1
        Map<String, Product> mapProduct = new HashMap<>();
        //them all san pham vao map
        for (Product a : products) {
            mapProduct.put(a.getId(), a);
        }
        System.out.print("\u001B[34mNhap ma san pham can mua: ");
        //Tao bien ID de them
        String ID = sc.nextLine();
        // Neu tim thay Id san pham
        if (mapProduct.containsKey(ID)) {
            System.out.print("\u001B[34mNhap so luong: ");
            int soluong = sc.nextInt();
            //lay san pham trung ID
            Product a = mapProduct.get(ID);
            ///So sanh so luong mua vo nho hon so luong san pham
            if (soluong <= a.getSoluong() && a.hangton()) {
                ///neu khach hang da them SP da co trong cart thi chi can cap nhat so luong
                if (this.getCart().getProducts().contains(a)) {
                    //Nếu Số lượng ban khách mua ban đầu + SL khách muốn thêm < SL San Pham
                    if (soluong + a.getSoluongmua() <= a.getSoluong()) {
                        // Thi Thêm Số lường khách mua thêm + số lượng mua ban đầu
                        a.setSoluongmua(soluong + a.getSoluongmua());
                    } else {
                        System.err.println("\u001B[31m========== KHONG DU SO LUONG SAN PHAM   ==================");
                    }
                } else {
                    a.setSoluongmua(soluong);
                    this.getCart().addProduct(a);
                }
            } else if (soluong > a.getSoluong()) {
                System.err.println("\u001B[31m================ KHONG DU SO LUONG =================");
            }

        } else {
            System.err.println("\u001B[31mSan pham khong ton tai.");
        }
    }

    public void RemoveProduct() {
        if (this.getCart().getProducts().isEmpty()) {
            System.err.println("\u001B[31m============= GIO HANG TRONG ===================");
        } else {
            System.out.print("\u001B[34mNhap ID san pham can xoa: ");
            sc.nextLine();
            String ID = sc.nextLine();
            for (int i = 0; i < this.getCart().getProducts().size(); i++) {
                Product a = this.getCart().getProducts().get(i);
                if (a.getId().equals(ID)) {
                    this.getCart().removeProduct(a);
                    break;
                }
            }
        }
    }

    public void HienthiPDinCart() {
        if (this.getCart().getProducts().isEmpty()) {
            System.out.println("\u001B[31m============= KHONG CO SAN PHAM TRONG GIO HANG ==============");
        } else {
            System.out.println("\u001B[34m============== SAN PHAM CO TRONG GIO HANG ==============");
            System.out.println(String.format("%-15s%-15s%-15s%-10s%-10s", "Ma SP", "Ten SP", "So Luong", " Gia", "Thanh Tien"));
            for (Product b : this.getCart().getProducts()) {
                System.out.println(String.format("%-15s%-17s%-13s%-13s%-13s", b.getId(), b.getTen(), b.getSoluongmua(), b.getGia(), b.TongTien()));
            }
            System.out.println("Tong tien la: " + this.getCart().TongtienInCart());
            //////// Them san pham trong gio hang vao don hang
        }
    }

    public void ChotDon() {
        ///Khoi tao collection Map
        Map<String, Product> mapCart = new HashMap<>();
        //Them cac san pham trong gio hang vao map
        for (Product c : this.getCart().getProducts()) {
            mapCart.put(c.getId(), c);
        }
        /// nếu co hang hàng có trong map
        if (!mapCart.isEmpty()) {
            Order order = new Order();
            Random random = new Random();
            int r = random.nextInt(10);
            order.setId("ODR00" + r);
            for (String i : mapCart.keySet()) {
                Product a = mapCart.get(i);
                //thêm sản phẩm trong giỏ hàng vào đơn hàng để thanh toán
                order.getProducts().add(a);
                // thanh toán xong rồi thì xoá sản phẩm trong đơn hàng
                this.getCart().removeProduct(a);
                // Cập nhật số lương Sản  Phẩm khi khách hàng mua hàng thành công
                a.truhang(a.getSoluongmua());
            }
            this.getOrders().add(order);
            if (!this.isKtra()) {
                this.InputInformation();
            }
            System.out.println("\u001B[1;32m======== CHOT DON THANH CONG =========");
            //                      //in ra san phẩm để bán
            //                      System.out.println(String.format("%-15s%-17s%-13s%-10s","Ma SP","Ten SP","So Luong"," Gia"));
            //                      for(Product x : products){
            //                          System.out.println(x);
        } else {
            System.out.println("\u001B[31m============= SAN PHAM KHONG TON TAI =================");
        }
    }

    public void BuyHistory() {
        System.out.println("\u001B[34m============= LICH SU MUA HANG ===============");
        if (!this.getOrders().isEmpty()) {
            this.DisplayInfoCustomer();
            int tong = 0;
            for (Order a : this.getOrders()) {
                System.out.println(a);
                for (Product c : a.getProducts()) {
                    System.out.println("\u001B[35mSAN PHAM DA MUA: ");
                    System.out.println("Ma San Pham: " + c.getId());
                    System.out.println("Ten San Pham: " + c.getTen());
                    System.out.println("So Luong Mua: " + c.getSoluongmua());
                    System.out.println("Gia San Pham: " + c.getGia());
                    System.out.println("Tong Tien: " + c.TongTien());
                    tong += c.TongTien();
                }
                System.out.println("TONG SO TIEN DA MUA TAT CA DON HANG  LA: " + tong);
            }

        } else {
            System.out.println("\u001B[31m========== KHONG CO DON HANG ==============");
        }
    }

    public void Search(ArrayList<Product> Products) {
        Map<String, Product> mapProduct = new HashMap<>();
        for (Product x : Products) {
            mapProduct.put(x.getId(), x);
        }
        System.out.print("Nhap ma san pham: ");
        String ID = sc.nextLine();
        if (mapProduct.containsKey(ID)) {
            Product a = mapProduct.get(ID);
            System.out.println(String.format("%-15s%-17s%-13s%-10s", "Ma SP", "Ten SP", "So Luong", " Gia"));

            System.out.println(a);
        } else {
            System.out.println("San pham khong ton tai.");
        }
    }
}
