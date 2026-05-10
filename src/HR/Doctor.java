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
	public void doktorRandevulariInit() {
	    if (doktorRandevulari == null) doktorRandevulari = new ArrayList<>();
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
	
	public List<String> getBosSlotlar(String tarih) {
	    if (doktorRandevulari == null) doktorRandevulari = new ArrayList<>();
	    List<String> tumSlotlar = new ArrayList<>();
	    int[] saatler = {9, 10, 11, 12, 13, 14, 15, 16};
	    int[] dakikalar = {0, 30};

	    for (int saat : saatler) {
	        for (int dakika : dakikalar) {
	            String slot = tarih + " " + String.format("%02d:%02d", saat, dakika);
	            tumSlotlar.add(slot);
	        }
	    }

	    // Dolu slotları çıkar
	    for (Appointment randevu : doktorRandevulari) {
	        tumSlotlar.remove(randevu.getTarih());
	    }

	    return tumSlotlar;
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
