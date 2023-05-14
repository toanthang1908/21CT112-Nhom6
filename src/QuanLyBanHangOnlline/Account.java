
package QuanLyBanHangOnlline;
import java.util.*;
public class Account {
    protected String tk;
    protected String mk;

           
    public Account(String tk, String mk) {
        this.tk = tk;
        this.mk = mk;
    }  
    public Account() {
        
    }
    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

   
    
}
