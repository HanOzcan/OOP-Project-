package HR;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DoctorGUI extends JFrame {
    private Doctor currentDoctor;

    public DoctorGUI(Doctor doctor) {
        this.currentDoctor = doctor;
        setTitle("Gazi Hastanesi - Doktor Paneli");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JLabel lbl_welcome = new JLabel("Hoşgeldiniz Dr. " + currentDoctor.getAd() + " " + currentDoctor.getSoyad());
        JLabel lbl_bolum   = new JLabel("Branş: " + currentDoctor.getBolum());
        add(lbl_welcome);
        add(lbl_bolum);

        JButton btn_hastalariListele = new JButton("Hastaları Listele");
        JButton btn_randevular       = new JButton("Randevuları Gör");
        JButton btn_logout           = new JButton("Çıkış Yap");

        add(btn_hastalariListele);
        add(btn_randevular);
        add(btn_logout);

        // Hastaları Listele — değişmedi
        btn_hastalariListele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Patient p : VeriMerkezi.getInstance().getHastaListesi()) {
                    sb.append(p.getAd()).append(" ").append(p.getSoyad())
                      .append(" - TC: ").append(p.getTc()).append("\n");
                }
                String sonuc = sb.length() > 0 ? sb.toString() : "Kayıtlı hasta bulunamadı.";
                JOptionPane.showMessageDialog(null, sonuc, "Hasta Listesi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Randevuları Gör — dolduruldu
        btn_randevular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDoctor.doktorRandevulariInit();
                List<Appointment> randevular = currentDoctor.getDoktorRandevulari();

                if (randevular.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aktif randevunuz bulunmamaktadır.");
                    return;
                }

                StringBuilder sb = new StringBuilder();
                for (Appointment r : randevular) {
                    sb.append(r.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Randevularım", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Çıkış — değişmedi
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                dispose();
            }
        });

        setVisible(true);
    }
}