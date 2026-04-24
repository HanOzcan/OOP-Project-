package HR;

public class Doctor extends Person {
	
	private String bolum;//brans bilgisi
	
	public Doctor(String tc, String ad, String soyad,String sifre, String bolum) {
		super(tc, ad, soyad, sifre);
		this.bolum=bolum;
	}
	
	public boolean login(String tc, String sifre) {
        return this.getTc().equals(tc) && this.getSifre().equals(sifre);
    }
	
	public void showUserInfo() {
        System.out.println("--- Doktor Profili ---");
        System.out.println("Dr. " + getAd() + " " + getSoyad());
        System.out.println("Branş: " + bolum);
    }
	
	public void setBolum(String bolum) {
		this.bolum=bolum;
	}
	
	public String getBolum() {
		return this.bolum;
	}

}
