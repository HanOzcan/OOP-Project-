package HR;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
	
	private String bolum;//brans bilgisi
	
	private List<Appointment> doktorRandevulari;
	
	public Doctor(String tc, String ad, String soyad,String sifre, String bolum) {
		super(tc, ad, soyad, sifre);
		this.bolum=bolum;
		this.doktorRandevulari = new ArrayList<>();
	}
	
	public void showUserInfo() {
        System.out.println("--- Doktor Profili ---");
        System.out.println("Dr. " + getAd() + " " + getSoyad());
        System.out.println("Branş: " + bolum);
        System.out.println("Aktif Randevu Sayısı: " + doktorRandevulari.size());
    }
	
	public void randevulariListele() {
        System.out.println("--- Dr. " + getAd() + " " + getSoyad() + " Randevuları ---");
        if (doktorRandevulari.isEmpty()) {
            System.out.println("Şu an bekleyen bir randevunuz bulunmuyor.");
        } else {
            for (Appointment randevu : doktorRandevulari) {
                // Burada senin yazdığın Appointment sınıfının toString metodu çalışır
                System.out.println(randevu);
            }
        }
    }
	
	public void randevuEkle(Appointment yeniRandevu) {
        this.doktorRandevulari.add(yeniRandevu);
    }
	
	public List<Appointment> getDoktorRandevulari() {
        return doktorRandevulari;
    }
	
	public boolean login(String tc, String sifre) {
        return this.getTc().equals(tc) && this.getSifre().equals(sifre);
    }
	
	public void setBolum(String bolum) {
		this.bolum=bolum;
	}
	
	public String getBolum() {
		return this.bolum;
	}

}
