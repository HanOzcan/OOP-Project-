package HR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class VeriMerkezi {

    private static VeriMerkezi instance;

    private List<Patient> hastaListesi;
    private List<Doctor> doktorListesi;
    private List<Admin> adminListesi;
    private List<Appointment> randevuListesi;

    private final String HASTA_DOSYASI   = "hastalar.json";
    private final String DOKTOR_DOSYASI  = "doktorlar.json";
    private final String ADMIN_DOSYASI   = "adminler.json";
    private final String RANDEVU_DOSYASI = "randevular.json";

    private Gson gson;

    private VeriMerkezi() {
        gson = new Gson();
        this.hastaListesi  = verileriYukle(HASTA_DOSYASI,  new TypeToken<ArrayList<Patient>>(){});
        this.doktorListesi = verileriYukle(DOKTOR_DOSYASI, new TypeToken<ArrayList<Doctor>>(){});
        this.adminListesi  = verileriYukle(ADMIN_DOSYASI,  new TypeToken<ArrayList<Admin>>(){});
        this.randevuListesi = verileriYukle(RANDEVU_DOSYASI, new TypeToken<ArrayList<Appointment>>(){});
        randevulariDagit(); // Yüklenen randevuları hasta ve doktorlara dağıt
    }

    public static VeriMerkezi getInstance() {
        if (instance == null) {
            instance = new VeriMerkezi();
        }
        return instance;
    }

    // Randevuları hasta ve doktor nesnelerine dağıtan metot
    private void randevulariDagit() {
        for (Appointment randevu : randevuListesi) {
            try {
                String[] hastaParts = randevu.getHastaAdi().split(" ");
                if (hastaParts.length < 2) continue;

                for (Patient hasta : hastaListesi) {
                    if (hasta.getAd().equals(hastaParts[0]) &&
                        hasta.getSoyad().equals(hastaParts[1])) {
                        hasta.randevuListesiInit();
                        hasta.getRandevuListesi().add(randevu);
                        break;
                    }
                }
                for (Doctor doktor : doktorListesi) {
                    String tamAd = "Dr. " + doktor.getAd() + " " + doktor.getSoyad();
                    if (tamAd.equals(randevu.getDoktorAdi())) {
                        doktor.doktorRandevulariInit();
                        doktor.getDoktorRandevulari().add(randevu);
                        break;
                    }
                }
            } catch (Exception e) {
                System.err.println("Randevu dağıtım hatası: " + e.getMessage());
            }
        }
    }

    public void verileriKaydet() {
        dosyayaYaz(HASTA_DOSYASI,   hastaListesi);
        dosyayaYaz(DOKTOR_DOSYASI,  doktorListesi);
        dosyayaYaz(ADMIN_DOSYASI,   adminListesi);
        dosyayaYaz(RANDEVU_DOSYASI, randevuListesi); // Randevular ayrı kaydediliyor
    }
    private <T> List<T> verileriYukle(String dosyaYolu, TypeToken<?> typeToken) {
        File dosya = new File(dosyaYolu);
        if (!dosya.exists() || dosya.length() == 0) return new ArrayList<>();
        try (Reader reader = new FileReader(dosyaYolu)) {
            List<T> yuklenenListe = gson.fromJson(reader, typeToken.getType());
            return (yuklenenListe != null) ? yuklenenListe : new ArrayList<>();
        } catch (com.google.gson.JsonSyntaxException e) {
            JOptionPane.showMessageDialog(null,
                "Dosya bozuk, temizleniyor: " + dosyaYolu,
                "Veri Hatası", JOptionPane.WARNING_MESSAGE);
            dosya.delete();
            return new ArrayList<>();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Okuma hatası: " + dosyaYolu + "\n" + e.getMessage(),
                "Dosya Hatası", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    // Merkezi randevu ekleme — hasta ve doktor listelerini de günceller
    public void randevuEkle(Appointment randevu, Patient hasta, Doctor doktor) {
        if (randevuListesi == null) randevuListesi = new ArrayList<>();
        hasta.randevuListesiInit();
        doktor.doktorRandevulariInit();

        randevuListesi.add(randevu);
        hasta.getRandevuListesi().add(randevu);
        doktor.getDoktorRandevulari().add(randevu);
        verileriKaydet();
    }

    public List<Appointment> getRandevuListesi() { return randevuListesi; }

    private <T> void dosyayaYaz(String dosyaYolu, List<T> liste) {
        try (Writer writer = new FileWriter(dosyaYolu)) {
            gson.toJson(liste, writer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Kaydetme hatası: " + dosyaYolu + "\n" + e.getMessage(),
                "Dosya Hatası", JOptionPane.ERROR_MESSAGE);
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