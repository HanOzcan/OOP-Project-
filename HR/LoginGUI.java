package HR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    
    // 1. Encapsulation: Tüm görsel bileşenler private yapıldı
    private JPanel w_pane;
    private JTextField fld_tc;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JComboBox<String> cmb_userType; // Hasta/Doktor seçimi için

    public LoginGUI() {
        // Pencere Ayarları
        setTitle("RandevuPlus - Giriş Ekranı");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Ana Panel
        w_pane = new JPanel();
        w_pane.setLayout(null);
        w_pane.setBackground(Color.WHITE);

        // Başlık
        JLabel lbl_title = new JLabel("RandevuPlus'a Hoş Geldiniz", SwingConstants.CENTER);
        lbl_title.setBounds(50, 20, 300, 30);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 18));
        w_pane.add(lbl_title);

        // Kullanıcı Tipi Seçimi (Factory Pattern için gerekli)
        JLabel lbl_type = new JLabel("Giriş Türü:");
        lbl_type.setBounds(50, 70, 100, 30);
        w_pane.add(lbl_type);

        String[] userTypes = {"Hasta", "Doktor"};
        cmb_userType = new JComboBox<>(userTypes);
        cmb_userType.setBounds(150, 70, 180, 30);
        w_pane.add(cmb_userType);

        // TC Kimlik Alanı
        JLabel lbl_tc = new JLabel("TC Kimlik No:");
        lbl_tc.setBounds(50, 120, 100, 30);
        w_pane.add(lbl_tc);

        fld_tc = new JTextField();
        fld_tc.setBounds(150, 120, 180, 30);
        w_pane.add(fld_tc);

        // Şifre Alanı
        JLabel lbl_pass = new JLabel("Şifre:");
        lbl_pass.setBounds(50, 170, 100, 30);
        w_pane.add(lbl_pass);

        fld_pass = new JPasswordField();
        fld_pass.setBounds(150, 170, 180, 30);
        w_pane.add(fld_pass);

        // Giriş Butonu
        btn_login = new JButton("Giriş Yap");
        btn_login.setBounds(50, 230, 280, 40);
        btn_login.setFont(new Font("Arial", Font.BOLD, 14));
        w_pane.add(btn_login);

        // 2. Butona Tıklanma Olayı (Factory ve OOP Bağlantısı)
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tc = fld_tc.getText();
                String pass = new String(fld_pass.getPassword());
                String selectedType = cmb_userType.getSelectedItem().toString();

                if (tc.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen TC ve Şifre alanlarını boş bırakmayınız!", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Factory pattern kullanımı: Seçime göre nesne üretilir
                    String factoryType = selectedType.equals("Hasta") ? "PATIENT" : "Doctor";
                    
                    // Şimdilik test amaçlı sahte isimler veriyoruz. 
                    // İleride Tunahan'ın JSON dosyasından gerçek veriler çekilecek.
                    Person user = UserFactory.createUser(factoryType, tc, "TestAd", "TestSoyad", pass, "Dahiliye");

                    // Polymorphism: Hem hasta hem doktor için aynı login metodu çalışır
                    if (user != null && user.login(tc, pass)) {
                        JOptionPane.showMessageDialog(null, "Giriş Başarılı!\nHoşgeldiniz: " + user.getAd());
                        user.showUserInfo(); // Konsola polimorfik yazdırır
                        
                        // TODO: Burada Hasta Paneli veya Doktor Paneli açılacak
                    } else {
                        JOptionPane.showMessageDialog(null, "Hatalı Giriş!", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        add(w_pane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}