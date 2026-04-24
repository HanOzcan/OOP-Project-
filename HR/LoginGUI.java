package HR;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    
    public LoginGUI() {
        setTitle("Gazi Hastanesi Otomasyonu - Giriş");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));


        JLabel label = new JLabel("Lütfen Sisteme Giriş Yapınız", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JTextField userField = new JTextField("Kullanıcı Adı");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Giriş Yap");

        loginBtn.setBackground(Color.WHITE);
        loginBtn.setFocusable(false);

        panel.add(label);
        panel.add(userField);
        panel.add(passField);
        panel.add(loginBtn);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}