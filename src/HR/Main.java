package HR;

public class Main {

	public static void main(String[] args) {
		System.out.println("_____ RandevuPlus Sistemine Hos Geldiniz ____");
		
		//userFactory ile nesne uretimi
		Person hasta1 = UserFactory.createUser("PATIENT", "123456789001", "Tuğçe", "Kalaylı", "sifresifre", null);
		Person doktor1 = UserFactory.createUser("Doctor", "777777777777", "Sevval", "Araf", "7442", "Göz");
		
		
		System.out.println("Giriş kontrolleri yapılıyor...");
		
		//login metodu testi
		if(hasta1 !=null && hasta1.login("123456789001", "sifresifre")){
			System.out.println("Giriş başarılı: " +hasta1.getAd() +" sisteme giriş yaptı");
		}else {
			System.out.println("Hata: Hasta girişi başarısız");
		}
		
		if(hasta1!=null) {
			hasta1.showUserInfo();
		}
		
		if(doktor1!=null) {
			doktor1.showUserInfo();
		}
		
		System.out.println("\n**********************************");
		

	}

}
