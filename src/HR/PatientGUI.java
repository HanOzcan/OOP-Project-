package HR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientGUI extends JFrame {
    
    // Kapsülleme: Login'den gelen hasta
    private Patient currentPatient;

    public PatientGUI(Patient hasta) {
        this.currentPatient = hasta;

        // Pencere ayarları
        setTitle("Gazi Hastanesi - Hasta Paneli");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        // FlowLayout: Elemanları yan yana dizer.
        setLayout(new FlowLayout());

        // Person'dan gelen getAd() ile ismi ekrana yazdırıyoruz
        JLabel lbl_welcome = new JLabel("Hoşgeldiniz, Sayın " + currentPatient.getAd() + " " + currentPatient.getSoyad());
        add(lbl_welcome); 

        // Taslak butonlar
        JButton btn_randevuAl = new JButton("Yeni Randevu Al");
        add(btn_randevuAl);

        JButton btn_logout = new JButton("Çıkış Yap");
        add(btn_logout);

        // Çıkış yapınca giriş ekranına dön
        btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginGUI(); 
                dispose(); // bu pencereyi kapat
            }
        });

        // Randevu butonu (şimdilik sadece uyarı mesajı )
        btn_randevuAl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Randevu sistemi yakında eklenecek!");
            }
        });
        
        setVisible(true); // en sonda tüm ayarlardan sonra olmalı
    }
}