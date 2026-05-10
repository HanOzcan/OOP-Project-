package HR;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JFrame {

    private Admin currentAdmin;

    public AdminGUI(Admin admin) {
        this.currentAdmin = admin;

        setTitle("Gazi Hastanesi - Admin Paneli");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JLabel lbl_welcome = new JLabel("Hoşgeldiniz, " + currentAdmin.getAd() + " " + currentAdmin.getSoyad());
        add(lbl_welcome);

        JButton btn_listele  = new JButton("Doktorları Listele");
        JButton btn_ekle     = new JButton("Doktor Ekle");
        JButton btn_sil      = new JButton("Doktor Sil");
        JButton btn_hastaEkle = new JButton("Hasta Ekle");
        JButton btn_randevular = new JButton("Aktif Randevular");
        JButton btn_logout   = new JButton("Çıkış Yap");

        add(btn_listele);
        add(btn_ekle);
        add(btn_sil);
        add(btn_hastaEkle);
        add(btn_randevular);
        add(btn_logout);

        // Doktorları Listele
        btn_listele.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Doctor d : VeriMerkezi.getInstance().getDoktorListesi()) {
                sb.append(d.getAd()).append(" ").append(d.getSoyad())
                  .append(" - ").append(d.getBolum()).append("\n");
            }
            String sonuc = sb.length() > 0 ? sb.toString() : "Kayıtlı doktor yok.";
            JOptionPane.showMessageDialog(this, sonuc, "Doktor Listesi", JOptionPane.INFORMATION_MESSAGE);
        });

        // Doktor Ekle
        btn_ekle.addActionListener(e -> {
            JTextField fld_tc    = new JTextField();
            JTextField fld_ad    = new JTextField();
            JTextField fld_soyad = new JTextField();
            JTextField fld_sifre = new JTextField();
            JTextField fld_bolum = new JTextField();

            Object[] fields = {
                "TC:", fld_tc, "Ad:", fld_ad, "Soyad:", fld_soyad,
                "Şifre:", fld_sifre, "Bölüm:", fld_bolum
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Doktor Ekle", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                boolean basarili = currentAdmin.doktorEkle(
                    fld_tc.getText(), fld_ad.getText(), fld_soyad.getText(),
                    fld_sifre.getText(), fld_bolum.getText()
                );
                if (basarili) {
                    JOptionPane.showMessageDialog(this, "Doktor başarıyla eklendi.");
                } else {
                    JOptionPane.showMessageDialog(this, "Hata: Alanlar boş bırakılamaz veya TC kayıtlı(TC 11haneli olmalı)",
                        "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Doktor Sil
        btn_sil.addActionListener(e -> {
            String tc = JOptionPane.showInputDialog(this, "Silinecek doktorun TC'si:");
            if (tc != null && !tc.trim().isEmpty()) {
                boolean basarili = currentAdmin.doktorSil(tc.trim());
                if (basarili) {
                    JOptionPane.showMessageDialog(this, "Doktor başarıyla silindi.");
                } else {
                    JOptionPane.showMessageDialog(this, "Hata: Bu TC'ye sahip doktor bulunamadı!",
                        "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btn_randevular.addActionListener(e -> {
            List<Appointment> randevular = VeriMerkezi.getInstance().getRandevuListesi();
            if (randevular == null || randevular.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aktif randevu bulunmamaktadır.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Appointment r : randevular) {
                sb.append(r.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Aktif Randevular", JOptionPane.INFORMATION_MESSAGE);
        });
        btn_hastaEkle.addActionListener(e -> {
            JTextField fld_tc    = new JTextField();
            JTextField fld_ad    = new JTextField();
            JTextField fld_soyad = new JTextField();
            JTextField fld_sifre = new JTextField();

            Object[] fields = {
                "TC:", fld_tc, "Ad:", fld_ad,
                "Soyad:", fld_soyad, "Şifre:", fld_sifre
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Hasta Ekle", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                boolean basarili = currentAdmin.hastaEkle(
                    fld_tc.getText(), fld_ad.getText(),
                    fld_soyad.getText(), fld_sifre.getText()
                );
                if (basarili) {
                    JOptionPane.showMessageDialog(this, "Hasta başarıyla eklendi.");
                } else {
                    JOptionPane.showMessageDialog(this, "Hata: Alanlar boş bırakılamaz veya TC kayıtlı (TC 11 haneli olmalı)",
                        "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Çıkış
        btn_logout.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        setVisible(true);
    }
}