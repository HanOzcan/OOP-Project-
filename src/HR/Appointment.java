package HR;

import java.util.UUID;

public class Appointment {
    private String id;
    private String hastaTc;   // Efe'nin Patient sınıfındaki TC
    private String doktorTc;  // Efe'nin Doctor sınıfındaki TC
    private String tarih;     // Efe'nin randevuAl metodundaki tarih
    private String saat;      // Efe'nin randevuAl metodundaki saat
    private String poliklinik; // Doktorun bölümü

    // GSON için boş constructor
    public Appointment() {}

    // Yeni randevu oluştururken kullanılacak constructor
    public Appointment(String hastaTc, String doktorTc, String poliklinik, String tarih, String saat) {
        this.id = UUID.randomUUID().toString(); // Benzersiz işlem numarası
        this.hastaTc = hastaTc;
        this.doktorTc = doktorTc;
        this.poliklinik = poliklinik;
        this.tarih = tarih;
        this.saat = saat;
    }

    // Getter ve Setter metotları
    public String getId() { return id; }
    public String getHastaTc() { return hastaTc; }
    public String getTarih() { return tarih; }
    public String getSaat() { return saat; }
}