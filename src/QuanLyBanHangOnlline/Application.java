package QuanLyBanHangOnlline;
import java.util.*;
public class Application {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        //khoi tao Map de lua tru user va admin
        Map<String, Account> map = new HashMap<>();
        Account DUY = new Admin("duy", "123");
        map.put(DUY.getTk(), DUY);
        Account THANG = new Admin("thang", "123");
        map.put(THANG.getTk(), THANG);
        Account NGUYEN = new Admin("nguyen", "123");
        map.put(NGUYEN.getTk(), NGUYEN);
        Scanner sc = new Scanner(System.in);
        products.add(new Product("SP001", "Quan Jean", 5, 5000));
        products.add(new Product("SP002", "Ao So Mi", 6, 3000));
        products.add(new Product("SP003", "Non", 4, 2000));
        while (true) {
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");         
            System.out.print("\u001B[35mNhap lua chon: ");
            int luachon = sc.nextInt();
            if (luachon == 1) {
                System.out.println("\u001B[34m====== Dang Nhap =======");
                System.out.print("Nhap tai khoan: ");
                sc.nextLine();
                String username = sc.nextLine();
                System.out.print("Nhap mat khau: ");
                String passwork = sc.nextLine();
                if (map.containsKey(username)) {
                    Account x = map.get(username);
                    if (x.getMk().equals(passwork)) {
                        if (x instanceof Customer) {
                            while (true) {
                                System.out.println("\u001B[34m=========== Dang Nhap Thanh Cong. =============");
                                System.out.println(String.format("%-15s%-17s%-13s%-10s", "Ma SP", "Ten SP", "So Luong", " Gia"));
                                for (Product prd : products) {
                                    System.out.println(prd);
                                }
                                System.out.println("1. Them san pham vao gio hang.");
                                System.out.println("2. Xoa san pham trong gio hang.");
                                System.out.println("3. Tim kiem san pham.");
                                System.out.println("4. Hien thi san pham trong gio hang.");
                                System.out.println("5. Chot Don !!!");
                                System.out.println("6. Xem lich su mua hang.");
                                System.out.println("7. Dang Xuat.");
                                System.out.println();
                                System.out.print("\u001B[34mNhap lua chon cua khach hang: ");
                                int luachon1 = sc.nextInt();
                                //////////////// THEM SAN PHAM
                                if (luachon1 == 1) {
                                   ((Customer) x).ThemProduct(products);
                                } else if (luachon1 == 2) {
                                    ((Customer) x).RemoveProduct();                             
                                }else if(luachon1==3){
                                    ((Customer) x).Search(products);
                                }else if (luachon1 == 4) {
                                   ((Customer) x).HienthiPDinCart();
                                } else if (luachon1 == 5) {
                                   ((Customer) x).ChotDon();
                                } else if (luachon1 == 6) {
                                  ((Customer) x).BuyHistory();
                                } else if (luachon1 == 7) {
                                    break;
                                }
                            }
                        } else if (x instanceof Admin) {
                            while (true) {
                                System.out.println("1. Them san pham.");
                                System.out.println("2. Xoa san pham.");
                                System.out.println("3. Xem Menu san pham");
                                System.out.println("4. Update san pham.");
                                System.out.println("5. Tim kiem san pham theo ID.");
                                System.out.println("6. Xem thong tin mua hang cua khach hang.");                             
                                System.out.println("7. Sap xep San Pham tang dan theo ID.");
                                System.out.println("8. Dang Xuat.");
                                System.out.print("\u001B[34mNhap lua chon cua Admin: ");
                                int luachonAD = sc.nextInt();
                                if (luachonAD == 1) {                                   
                                    ((Admin) x).AddProduct( products);
                                } else if (luachonAD == 2) {
                                    ((Admin) x).RemoveProduct(products);
                                } else if (luachonAD == 3) {
                                    ((Admin) x).Xemthongtin(products);
                                }else if(luachonAD ==4){
                                    ((Admin) x).Update(products);                                                                  
                                }else if(luachonAD==5){                                  
                                    ((Admin) x).Timkiem(products);
                                }else if (luachonAD == 6) {
                                    ((Admin) x).HienthiCustomer(map);
                                } else if (luachonAD == 7) {
                                    ((Admin) x).Sort(products);
                                } else if (luachonAD == 8) {
                                    break;
                                }
                            }
                        }

                    } else {
                        System.out.println("\u001B[31mSAI TAI KHOAN HOAC MAT KHAU !!!");
                    }
                    /////////////////////////
                } else {
                    System.out.println("\u001B[31m SAI TAI KHOAN HOAC MAT KHAU !!!");
                }
            } else if (luachon == 2) {
                System.out.println("\u001B[34m=========== Dang Ky ============");
                System.out.print("Nhap tai khoan: ");
                sc.nextLine();
                String username = sc.nextLine();
                if (map.containsKey(username)) {
                    System.out.println("\u001B[31mTAI KHOAN DA TON TAI !!!");
                } else {
                    System.out.print("Nhap mat khau: ");
                    String passwork = sc.nextLine();
                    Account cus = new Customer();
                    cus.setTk(username);
                    cus.setMk(passwork);
                    map.put(cus.getTk(), cus);
                }

            }
        }
    }

}
