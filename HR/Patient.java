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
	
	// getter and setter methods
	public List<String> getGecmisRandevular() {
        return gecmisRandevular;
    }

    public void setGecmisRandevular(List<String> gecmisRandevular) {
        this.gecmisRandevular = gecmisRandevular;
    }
	
	@Override
	public boolean login(String tc, String sifre) {//tc ve şifre eslesmesı
        return this.getTc().equals(tc) && this.getSifre().equals(sifre);
    }
	
	@Override
	public void showUserInfo() {
		//interfaceden gelen metodun doldurulması
        System.out.println("----- Hasta Profili -----");
        System.out.println("Ad Soyad: "+ getAd() + " "+ getSoyad());
        System.out.println("TC: "+ getTc());
    }
	
	// randevu fonksiyonlarının dönüş değerine göre arayüzde işlemler yapılacak
	public boolean randevuAl(String tarih, String saat, Doctor doktor) {
        if (doktor == null || tarih.isEmpty() || saat.isEmpty()) {
            return false;
        }
        // Randevu metnini oluştur ve listeye ekle
        String randevuDetayi = tarih + " " + saat + " - Dr. " + doktor.getAd() + " (" + doktor.getBolum() + ")";
        this.gecmisRandevular.add(randevuDetayi);
        return true;
    }
	
	public boolean randevuIptalEt(String tarih, String saat) {
        String arananZaman = tarih + " " + saat;
        for (int i = 0; i < gecmisRandevular.size(); i++) {
            if (gecmisRandevular.get(i).startsWith(arananZaman)) {
                gecmisRandevular.remove(i);
                return true; // Sildiyse true dön
            }
        }
        return false; // Bulamadıysa false dön
    }

}
