package HR;
import java.util.*;

public class Admin extends Person {

    private String yetkiSeviyesi;
    private String departman;

    public Admin() {}

    public Admin(String TC, String ad, String soyad, String şifre, String yetkiSeviyesi, String departman) {
        super(TC, ad, soyad, şifre);
        setYetkiSeviyesi(yetkiSeviyesi);
        setDepartman(departman);
    }

    public void doktorlarıListele() {
        for (Doctor doctor : VeriMerkezi.getInstance().getDoktorListesi()) {
            System.out.println(doctor.getAd() + " " + doctor.getSoyad() + " " + doctor.getBolum());
        }
    }

    public boolean doktorEkle(String tc, String ad, String soyad, String sifre, String bolum) {
        if (ad == null || ad.isEmpty() || soyad == null || soyad.isEmpty() ||
                sifre == null || sifre.isEmpty() || bolum == null || bolum.isEmpty()
                || tc == null || tc.isEmpty()) {
            System.out.println("Lütfen tüm alanları doldurunuz!!");
            return false;
        }
        for (Doctor doctorTemp : VeriMerkezi.getInstance().getDoktorListesi()) {
            if (doctorTemp.getTc().equals(tc)) {
                return false;
            }
        }
        Doctor doctor = new Doctor(tc, ad, soyad, sifre, bolum);
        VeriMerkezi.getInstance().doktorEkle(doctor);
        VeriMerkezi.getInstance().verileriKaydet();
        return true;
    }

    public boolean doktorSil(String tc) {
        Iterator<Doctor> it = VeriMerkezi.getInstance().getDoktorListesi().iterator();
        while (it.hasNext()) {
            Doctor doctorTemp = it.next();
            if (doctorTemp.getTc().equals(tc)) {
                it.remove();
                System.out.println("Silme işlemi başarılı.");
                VeriMerkezi.getInstance().verileriKaydet();
                return true;
            }
        }
        System.out.println("Doktor bulunamadı.");
        return false;
    }

    public void aktifRandevuListele() {
    }

    @Override
    public void showUserInfo() {
        System.out.println("--- Admin Profili ---");
        System.out.println("Admin: " + super.getAd() + " " + super.getSoyad());
        System.out.println("Departman: " + this.departman);
        System.out.println("Yetki: " + this.yetkiSeviyesi);
    }

    @Override
    public boolean login(String TC, String şifre) {
        return this.getTc().equals(TC) && this.getSifre().equals(şifre);
    }

    public String getYetkiSeviyesi() { return this.yetkiSeviyesi; }
    public void setYetkiSeviyesi(String yetkiSeviyesi) { this.yetkiSeviyesi = yetkiSeviyesi; }
    public String getDepartman() { return this.departman; }
    public void setDepartman(String departman) { this.departman = departman; }
}