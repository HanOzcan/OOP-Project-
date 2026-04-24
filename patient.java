package HR;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
	
	//hastaya ozgu dinamik liste
	private List<String> gecmisRandevular;
	
	public Patient(String tc, String ad, String soyad, String sifre) {
        super(tc, ad, soyad, sifre);//ust sınıfın constructerını cagırır
        this.gecmisRandevular = new ArrayList<>();
    }
	
	public boolean login(String tc, String sifre) {//tc ve şifre eslesmesı
        return this.getTc().equals(tc) && this.getSifre().equals(sifre);
    }
	
	
	public void showUserInfo() {
		//interfaceden gelen metodun doldurulması
        System.out.println("----- Hasta Profili -----");
        System.out.println("Ad Soyad: "+ getAd() + " "+ getSoyad());
        System.out.println("TC: "+ getTc());
    }
	
	

}
