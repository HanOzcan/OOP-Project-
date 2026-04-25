package HR;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VeriMerkezi {
    // Singleton nesnesi
    private static VeriMerkezi instance;
    
    // Veri listeleri (Kapsulleme kuralına uygun)
    private List<Patient> hastaListesi;
    private List<Doctor> doktorListesi;
    private List<Appointment> randevuListesi;
    
    // Dosya yolları
    private final String HASTA_DOSYASI = "hastalar.json";
    private final String DOKTOR_DOSYASI = "doktorlar.json";
    private final String RANDEVU_DOSYASI = "randevular.json";
    private Gson gson;

    // Private Constructor: Sadece bu sınıf içinden erişilir
    private VeriMerkezi() {
        gson = new Gson();
        // Program basladıgında dosyaları oku, yoksa bos liste olustur
        this.hastaListesi = verileriYukle(HASTA_DOSYASI, new TypeToken<ArrayList<Patient>>(){});
        this.doktorListesi = verileriYukle(DOKTOR_DOSYASI, new TypeToken<ArrayList<Doctor>>(){});
    }

    // Singleton erisim metodu
    public static VeriMerkezi getInstance() {
        if (instance == null) {
            instance = new VeriMerkezi();
        }
        return instance;
    }

    // --- GENEL KAYDETME VE YUKLEME METODLARI ---

    public void verileriKaydet() {
        dosyayaYaz(HASTA_DOSYASI, hastaListesi);
        dosyayaYaz(DOKTOR_DOSYASI, doktorListesi);
    }

    private <T> void dosyayaYaz(String dosyaYolu, List<T> liste) {
        try (Writer writer = new FileWriter(dosyaYolu)) {
            gson.toJson(liste, writer);
        } catch (IOException e) {
            System.err.println("Hata: Veriler kaydedilemedi (" + dosyaYolu + ") - " + e.getMessage());
        }
    }

    private <T> List<T> verileriYukle(String dosyaYolu, TypeToken<?> typeToken) {
        try (Reader reader = new FileReader(dosyaYolu)) {
            List<T> yuklenenListe = gson.fromJson(reader, typeToken.getType());
            return (yuklenenListe != null) ? yuklenenListe : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Dosya yoksa temiz bir baslangıc yap
        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // --- VERI ERISIM METODLARI ---

    public void hastaEkle(Patient yeniHasta) {
    	// Listede aynı TC var mı kontrol et
        boolean varMi = false;
        for (Patient p : hastaListesi) {
            if (p.getTc().equals(yeniHasta.getTc())) {
                varMi = true;
                break;
            }
        }

        if (!varMi) {
            this.hastaListesi.add(yeniHasta);
            verileriKaydet();
            System.out.println("Yeni hasta kaydedildi.");
        } else {
            System.out.println("Hata: Bu TC numarasıyla zaten bir hasta kayıtlı!");
        }
    }

    public void doktorEkle(Doctor doktor) {
        this.doktorListesi.add(doktor);
        verileriKaydet();
    }

    public List<Patient> getHastaListesi() {
        return hastaListesi;
    }

    public List<Doctor> getDoktorListesi() {
        return doktorListesi;
    }
 

    public Patient getHastaByTc(String tc) {
        for (Patient p : hastaListesi) {
            if (p.getTc().equals(tc)) {
                return p;
            }
        }
        return null; // Hasta bulunamadı
    }

    public Doctor getDoktorByTc(String tc) {
        for (Doctor d : doktorListesi) {
            if (d.getTc().equals(tc)) {
                return d;
            }
        }
        return null; // Doktor bulunamadı
    }
}