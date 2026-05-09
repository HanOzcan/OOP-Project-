package HR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VeriMerkezi {

    private static VeriMerkezi instance;

    private List<Patient> hastaListesi;
    private List<Doctor> doktorListesi;
    private List<Admin> adminListesi;        // Admin listesi eklendi
    private List<Appointment> randevuListesi;

    private final String HASTA_DOSYASI   = "hastalar.json";
    private final String DOKTOR_DOSYASI  = "doktorlar.json";
    private final String ADMIN_DOSYASI   = "adminler.json";  // Admin dosyası eklendi
    private final String RANDEVU_DOSYASI = "randevular.json";

    private Gson gson;

    private VeriMerkezi() {
        gson = new Gson();
        this.hastaListesi  = verileriYukle(HASTA_DOSYASI,  new TypeToken<ArrayList<Patient>>(){});
        this.doktorListesi = verileriYukle(DOKTOR_DOSYASI, new TypeToken<ArrayList<Doctor>>(){});
        this.adminListesi  = verileriYukle(ADMIN_DOSYASI,  new TypeToken<ArrayList<Admin>>(){});  // Admin yüklendi
    }

    public static VeriMerkezi getInstance() {
        if (instance == null) {
            instance = new VeriMerkezi();
        }
        return instance;
    }

    public void verileriKaydet() {
        dosyayaYaz(HASTA_DOSYASI,  hastaListesi);
        dosyayaYaz(DOKTOR_DOSYASI, doktorListesi);
        dosyayaYaz(ADMIN_DOSYASI,  adminListesi);  // Admin kaydediliyor
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
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // --- HASTA ---
    public void hastaEkle(Patient yeniHasta) {
        for (Patient p : hastaListesi) {
            if (p.getTc().equals(yeniHasta.getTc())) {
                System.out.println("Hata: Bu TC numarasıyla zaten bir hasta kayıtlı!");
                return;
            }
        }
        this.hastaListesi.add(yeniHasta);
        verileriKaydet();
        System.out.println("Yeni hasta kaydedildi.");
    }

    public List<Patient> getHastaListesi() { return hastaListesi; }

    public Patient getHastaByTc(String tc) {
        for (Patient p : hastaListesi) {
            if (p.getTc().equals(tc)) return p;
        }
        return null;
    }

    // --- DOKTOR ---
    public void doktorEkle(Doctor doktor) {
        this.doktorListesi.add(doktor);
        verileriKaydet();
    }

    public List<Doctor> getDoktorListesi() { return doktorListesi; }

    public Doctor getDoktorByTc(String tc) {
        for (Doctor d : doktorListesi) {
            if (d.getTc().equals(tc)) return d;
        }
        return null;
    }

    // --- ADMİN ---
    public void adminEkle(Admin admin) {
        for (Admin a : adminListesi) {
            if (a.getTc().equals(admin.getTc())) {
                System.out.println("Hata: Bu TC numarasıyla zaten bir admin kayıtlı!");
                return;
            }
        }
        this.adminListesi.add(admin);
        verileriKaydet();
        System.out.println("Yeni admin kaydedildi.");
    }

    public List<Admin> getAdminListesi() { return adminListesi; }

    public Admin getAdminByTc(String tc) {
        for (Admin a : adminListesi) {
            if (a.getTc().equals(tc)) return a;
        }
        return null;
    }
}