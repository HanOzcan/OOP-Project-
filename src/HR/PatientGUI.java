package HR;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PatientGUI extends JFrame {

    private Patient currentPatient;

    public PatientGUI(Patient hasta) {
        this.currentPatient = hasta;

        setTitle("Gazi Hastanesi - Hasta Paneli");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JLabel lbl_welcome = new JLabel("Hoşgeldiniz, Sayın " + currentPatient.getAd() + " " + currentPatient.getSoyad());
        add(lbl_welcome);

        JButton btn_randevuAl = new JButton("Yeni Randevu Al");
        JButton btn_logout    = new JButton("Çıkış Yap");

        add(btn_randevuAl);
        add(btn_logout);

        // Randevu Al — Patient.randevuAl ile eşlendi
        btn_randevuAl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Doctor> doktorlar = VeriMerkezi.getInstance().getDoktorListesi();

                if (doktorlar.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kayıtlı doktor bulunamadı.");
                    return;
                }

                // Doktor seçimi
                String[] doktorAdlari = new String[doktorlar.size()];
                for (int i = 0; i < doktorlar.size(); i++) {
                    doktorAdlari[i] = doktorlar.get(i).getAd() + " " + doktorlar.get(i).getSoyad()
                            + " - " + doktorlar.get(i).getBolum();
                }

                String secim = (String) JOptionPane.showInputDialog(
                    null, "Doktor seçiniz:", "Randevu Al",
                    JOptionPane.PLAIN_MESSAGE, null, doktorAdlari, doktorAdlari[0]
                );
                if (secim == null) return;

                Doctor secilenDoktor = doktorlar.get(java.util.Arrays.asList(doktorAdlari).indexOf(secim));

                // Tarih seçimi
                String tarih = JOptionPane.showInputDialog(null, "Tarih giriniz (örn: 2026-05-10):");
                if (tarih == null || tarih.trim().isEmpty()) return;

                // Boş slotları getir
                List<String> bosSlotlar = secilenDoktor.getBosSlotlar(tarih.trim());

                if (bosSlotlar.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Seçilen tarihte müsait saat kalmamıştır.");
                    return;
                }

                // Boş slotları dropdown olarak göster
                String[] slotDizisi = bosSlotlar.toArray(new String[0]);
                String secilenSlot = (String) JOptionPane.showInputDialog(
                    null, "Müsait saatler:", "Saat Seçiniz",
                    JOptionPane.PLAIN_MESSAGE, null, slotDizisi, slotDizisi[0]
                );
                if (secilenSlot == null) return;

                // Randevu al
                try {
                    currentPatient.randevuAl(secilenDoktor, secilenSlot);
                    JOptionPane.showMessageDialog(null, "Randevunuz başarıyla oluşturuldu.");
                } catch (AppointmentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Çıkış — değişmedi
        btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });

        setVisible(true);
    }
}