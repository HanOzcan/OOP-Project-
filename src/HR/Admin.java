package HR;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Person{

	private String yetkiSeviyesi;
	private String departman;
	
	private List<String> doktorlar;
	
	//parametreli constructor
	public Admin(String TC, String ad, String soyad, String şifre, String yetkiSeviyesi, String departman) {
	super(TC,ad,soyad,şifre);	
	setYetkiSeviyesi(yetkiSeviyesi);
	setDepartman(departman);
	}
	
	public Admin() {}	//default boş constructor
	
	public void doktorlarıListele() {}	//Doctor listesindeki elemanları 
	
	public void doktorEkle()//Doctor nesnesi alacak 
			{}
	
	public void doktorSil()//Doctor nesnei alacak 
			{}
	
	public void aktifRandevuListele() //Randevu listesine ulaşacak
			{}
	@Override
	public void showUserInfo() {
        System.out.println("--- Admin Profili ---");
        System.out.println("Dr. " + super.getAd() + " " + super.getSoyad());
        System.out.println("Branş: " + this.departman);
        System.out.println("Unvan: " + this.yetkiSeviyesi);
    }
		
	@Override
	public boolean login(String TC, String şifre) {	//Admin giriş sistemi hazırlanmadı
		return false;
	}

	//yetkiSeviyesi getter
	public String getYetkiSeviyesi() {
		return this.yetkiSeviyesi;
	}
	//yetkiSeviyesi setter
	public void setYetkiSeviyesi(String yetkiSeviyesi) {
		this.yetkiSeviyesi = yetkiSeviyesi;
	}
	//departman getter
	public String getDeparman() {
		return this.departman;
	}
	//departman setter
	public void setDepartman(String yetkiSeviyesi) {
		this.departman = departman;
	}
}