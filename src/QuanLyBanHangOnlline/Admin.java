package QuanLyBanHangOnlline;

import java.util.*;

public class Admin extends Account {

    Scanner sc = new Scanner(System.in);

    public Admin(String tk, String mk) {
        super(tk, mk);
    }

    public Admin() {
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

    public void AddProduct(ArrayList<Product> Products) {
        Product product = new Product();
        product.InutIFProduct();
        Products.add(product);
    }

    public void Update(ArrayList<Product> Products) {
        System.out.print("Nhap ID San pham can Update: ");
        String ID = sc.nextLine();
        System.out.print("Nhap so luong: ");
        int soluong = sc.nextInt();
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i).getId().equals(ID)) {
                Products.get(i).setSoluong(soluong);
                break;
            }
        }
    }

    public void RemoveProduct(ArrayList<Product> Products) {
        System.out.print("Nhap ID san pham can xoa: ");
        String ID = sc.nextLine();
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i).getId().equals(ID)) {
                Products.remove(Products.get(i));
            }
        }
    }

    public void Xemthongtin(ArrayList<Product> Products) {
        System.out.println(String.format("%-15s%-17s%-13s%-10s", "Ma SP", "Ten SP", "So Luong", " Gia"));

        for (Product x : Products) {
            System.out.println(x);
        }
    }

    public void Timkiem(ArrayList<Product> Products) {
        Map<String, Product> mapProduct = new HashMap<>();
        for (Product x : Products) {
            mapProduct.put(x.getId(), x);
        }
        System.out.print("Nhap ID san pham: ");
        String ID = sc.nextLine();
        if (mapProduct.containsKey(ID)) {
            Product a = mapProduct.get(ID);
            System.out.println(String.format("%-15s%-17s%-13s%-10s", "Ma SP", "Ten SP", "So Luong", " Gia"));
            System.out.println(a);
        } else {
            System.out.println("San pham khong ton tai.");
        }
    }

    public void HienthiCustomer(Map<String, Account> map) {
        System.out.println("=============== LICH SU MUA HANG CUA TAT CA KHACH HANG ===============");
        int tong=0;
        for (String Id : map.keySet()) {
            System.out.println("");
            Account x = map.get(Id);           
            if (x instanceof Customer) {
                if (!((Customer) x).getOrders().isEmpty()) {
                    ((Customer) x).DisplayInfoCustomer();
                    for (Order a : ((Customer) x).getOrders()) {
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
                    }
                } 
            }
            
        }
        System.out.println("TONG DOANH THU LA: "+tong);
    }

    public void Sort(ArrayList<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product a, Product b) {
                return a.getId().compareTo(b.getId());
            }
        });
    }
}
