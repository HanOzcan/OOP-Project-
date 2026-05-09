package HR;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
	
	//hastaya ozgu dinamik liste
	private List<Appointment> randevuListesi;
	
	public Patient(String tc, String ad, String soyad, String sifre) {
        super(tc, ad, soyad, sifre);//ust sınıfın constructerını cagırır
        this.randevuListesi = new ArrayList<>();
    }
	
public void randevuAl(Doctor doktor, String tarih) throws AppointmentException {
        
        for (Appointment mevcutRandevu : doktor.getDoktorRandevulari()) {
          
            if (mevcutRandevu.getTarih().equals(tarih)) {
                throw new AppointmentException("ÇAKIŞMA HATASI: Dr. " + doktor.getSoyad() +  " için " + tarih + " saatinde zaten dolu bir randevu var!");
            }
        }
        
        String tamHastaAdi = this.getAd() + " " + this.getSoyad();
        String tamDoktorAdi = "Dr. " + doktor.getAd() + " " + doktor.getSoyad();
        
        Appointment yeniRandevu = new Appointment(tamHastaAdi, tamDoktorAdi, tarih);
        
      
        this.randevuListesi.add(yeniRandevu);      
        doktor.randevuEkle(yeniRandevu);         
        
        System.out.println("Başarılı: Randevunuz onaylandı! Randevu No: " + yeniRandevu.getRandevuID());
}
	public boolean randevuIptal(int id) {
        for (int i = 0; i < randevuListesi.size(); i++) {
            if (randevuListesi.get(i).getRandevuID() == id) {
                randevuListesi.remove(i);
                System.out.println("Randevu No " + id + " başarıyla iptal edildi.");
                return true;
            }
        }
        System.out.println("Hata: " + id + " numaralı randevu bulunamadı.");
        return false;
    }
	public List<Appointment> getRandevuListesi() {
        return randevuListesi;
    }
	
	public boolean login(String tc, String sifre) {//tc ve şifre eslesmesı
        return this.getTc().equals(tc) && this.getSifre().equals(sifre);
    }
	
	public void showUserInfo() {
		//interfaceden gelen metodun doldurulması
        System.out.println("----- Hasta Profili -----");
        System.out.println("Ad Soyad: "+ getAd() + " "+ getSoyad());
        System.out.println("TC: "+ getTc());
        System.out.println("Aktif Randevu Sayısı: " + randevuListesi.size());
    }
	
	
	
	
	
	

	

}
