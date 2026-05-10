package HR;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {
    private JComboBox<String> cmb_userType;
    private JTextField fld_tc;
    private JPasswordField fld_pass;
    private JButton btn_login;

    public LoginGUI() {
        setTitle("Gazi Hastanesi - Giriş");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 5, 5));

        add(new JLabel(" Giriş Türü:"));
        cmb_userType = new JComboBox<>(new String[]{"Hasta", "Doktor", "Admin"});
        add(cmb_userType);

        add(new JLabel(" TC Kimlik No:"));
        fld_tc = new JTextField();
        add(fld_tc);

        add(new JLabel(" Şifre:"));
        fld_pass = new JPasswordField();
        add(fld_pass);

        add(new JLabel(""));
        btn_login = new JButton("Giriş Yap");
        add(btn_login);

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tc   = fld_tc.getText();
                String pass = new String(fld_pass.getPassword());
                String secim = cmb_userType.getSelectedItem().toString();

                Person user = null;

                if (secim.equals("Hasta")) {
                    user = VeriMerkezi.getInstance().getHastaByTc(tc);
                } else if (secim.equals("Doktor")) {
                    user = VeriMerkezi.getInstance().getDoktorByTc(tc);
                } else {
                    user = VeriMerkezi.getInstance().getAdminByTc(tc);
                }

                if (user != null && user.login(tc, pass)) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı! Hoşgeldiniz, " + user.getAd());
                    if (user instanceof Patient) {
                        new PatientGUI((Patient) user);
                        dispose();
                    } else if (user instanceof Doctor) {
                        new DoctorGUI((Doctor) user);
                        dispose();
                    } else if (user instanceof Admin) {
                        new AdminGUI((Admin) user);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Hatalı TC veya Şifre!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}