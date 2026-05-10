package HR;

public class Main {

	public static void main(String[] args) {
		System.out.println("_____ RandevuPlus Sistemine Hos Geldiniz ____");
		
		//userFactory ile nesne uretimi
		Person hasta1 = UserFactory.createUser("PATIENT", "123456789001", "Tuğçe", "Kalaylı", "sifresifre", null);
		Person doktor1 = UserFactory.createUser("Doctor", "777777777777", "Sevval", "Araf", "7442", "Göz");
		if (VeriMerkezi.getInstance().getDoktorListesi().isEmpty()) {		//admin,doctor ve hastalar listelerini dolduran kısım
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "11111111111", "Ahmet",   "Yılmaz",  "1234", "Kardiyoloji"));
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "22222222222", "Ayşe",    "Kaya",    "1234", "Nöroloji"));
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "33333333333", "Mehmet",  "Demir",   "1234", "Ortopedi"));
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "44444444444", "Fatma",   "Çelik",   "1234", "Pediatri"));
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "55555555555", "Ali",     "Şahin",   "1234", "Göz Hastalıkları"));
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "66666666666", "Zeynep",  "Arslan",  "1234", "Dermatoloji"));
		    VeriMerkezi.getInstance().doktorEkle((Doctor) UserFactory.createUser("DOCTOR", "77777777778", "Mustafa", "Koç",     "1234", "Dahiliye"));
		    VeriMerkezi.getInstance().verileriKaydet();
		}

		if (VeriMerkezi.getInstance().getAdminListesi().isEmpty()) {
		    VeriMerkezi.getInstance().adminEkle((Admin) UserFactory.createUser("ADMIN", "88888888888", "Süleyman", "Öztürk", "admin123", "Süper Admin"));
		    VeriMerkezi.getInstance().verileriKaydet();
		}

		if (VeriMerkezi.getInstance().getHastaListesi().isEmpty()) {
		    VeriMerkezi.getInstance().hastaEkle((Patient) UserFactory.createUser("PATIENT", "99999999991", "Elif",  "Yıldız", "1234", null));
		    VeriMerkezi.getInstance().hastaEkle((Patient) UserFactory.createUser("PATIENT", "99999999992", "Can",   "Aydın",  "1234", null));
		    VeriMerkezi.getInstance().hastaEkle((Patient) UserFactory.createUser("PATIENT", "99999999993", "Selin", "Polat",  "1234", null));
		    VeriMerkezi.getInstance().hastaEkle((Patient) UserFactory.createUser("PATIENT", "99999999994", "Burak", "Güneş",  "1234", null));
		    VeriMerkezi.getInstance().verileriKaydet();
		}
		
		System.out.println("Giriş kontrolleri yapılıyor...");
		
		//login metodu testi
		if(hasta1 !=null && hasta1.login("123456789001", "sifresifre")){
			System.out.println("Giriş başarılı: " +hasta1.getAd() +" sisteme giriş yaptı");
		}else {
			System.out.println("Hata: Hasta girişi başarısız");
		}
		
		if (hasta1 instanceof Patient && doktor1 instanceof Doctor) {
			Patient gercekHasta = (Patient) hasta1;
			Doctor gercekDoktor = (Doctor) doktor1;

			try {
		
				gercekHasta.randevuAl(gercekDoktor, "25.05.2026 10:30");
				
				System.out.println("Aynı saate tekrar randevu alınmaya çalışılıyor...");
				gercekHasta.randevuAl(gercekDoktor, "25.05.2026 10:30");

			} catch (AppointmentException e) {
			
				System.out.println("SİSTEM UYARISI YAKALANDI -> " + e.getMessage());
			}
			
			System.out.println("\n=== DOKTOR EKRANI ===");
			gercekDoktor.randevulariListele();
			gercekDoktor.showUserInfo();
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
