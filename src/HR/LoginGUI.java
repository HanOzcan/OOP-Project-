package HR;

import javax.swing.*;   // temel components
import java.awt.*;      //boyut
import java.awt.event.*; // tıklama olayları

public class LoginGUI extends JFrame {
    // encapsulation
    private JComboBox<String> cmb_userType;
    private JTextField fld_tc;
    private JPasswordField fld_pass;
    private JButton btn_login;

    public LoginGUI() {
        
        setTitle("Gazi Hastanesi - Giriş"); // baslık
        setSize(300, 350);  //boyut
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //çarpıya basınca kapansın
        setLocationRelativeTo(null); //ekranın ortasında açılır
        
        
        setLayout(new GridLayout(8, 1,5, 5)); // grid ızgara 8 satır 1 sütun

        //Arayüz elemanları
        add(new JLabel(" Giriş Türü:"));
        cmb_userType = new JComboBox<>(new String[]{"Hasta", "Doktor"});
        add(cmb_userType);

        add(new JLabel(" TC Kimlik No:"));
        fld_tc = new JTextField();
        add(fld_tc);

        add(new JLabel(" Şifre:"));
        fld_pass = new JPasswordField();
        add(fld_pass);

        add(new JLabel("")); // Butonun üstünde boşluk için
        btn_login = new JButton("Giriş Yap");
        add(btn_login);

        // Giriş Mantığı
     // LoginGUI.java içindeki btn_login ActionListener kısmı

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tc = fld_tc.getText();
                String pass = new String(fld_pass.getPassword());
                String secim = cmb_userType.getSelectedItem().toString();
                
                Person user = null;

                // 1. Veri merkezinden gerçek kullanıcıyı bul
                if (secim.equals("Hasta")) {
                    user = VeriMerkezi.getInstance().getHastaByTc(tc);
                } else {
                    user = VeriMerkezi.getInstance().getDoktorByTc(tc);
                }

                // 2. Kullanıcı bulundu mu ve şifre doğru mu kontrol et
                if (user != null && user.login(tc, pass)) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı! Hoşgeldiniz, " + user.getAd());
                    
                    if (user instanceof Patient) {
                        new PatientGUI((Patient) user); // Mevcut hastayı panele gönder
                        dispose(); 
                    } else if (user instanceof Doctor) {
                        // new DoctorGUI((Doctor) user); // İleride eklenecek doktor paneli
                        dispose();
                    }
                } else {
                    // Liste boşsa veya bilgiler hatalıysa bu mesaj çıkar
                    JOptionPane.showMessageDialog(null, "Hatalı TC veya Şifre!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // pencere görünür yapıldı
        setVisible(true);
    }

    // Programın başlangıcı
    public static void main(String[] args) {
        // standart başlatma kodu
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}