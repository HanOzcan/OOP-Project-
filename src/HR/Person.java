package HR;

public abstract class Person implements IReportable {
	private String TC;
	private String Ad;
	private String Soyad;
	private String Sifre;
	
	public Person() {
	    // GSON ve alt sınıflar için boş constructor
	}
	//nesne olusturmak ıcın constructer
	public Person(String tc, String ad, String soyad, String sifre) {
		this.TC = tc;
		this.Ad=ad;
		this.Soyad=soyad;
		this.Sifre=sifre;
		
	}
	//verilere kontrollu erısım ıcın get set metodları
	public void setTc(String tc) {
		this.TC=tc;
	}
	public String getTc() {
		return this.TC;
	}
	
	public void setAd(String ad) {
		this.Ad=ad;
	}
	public String getAd() {
		return this.Ad;
	}
	
	public void setSoyad(String soyad) {
		this.Soyad = soyad; 
	}
	public String getSoyad() {
		return this.Soyad; 
	}
	
	public void setSifre(String sifre) { 
		this.Sifre = sifre;
	}
	public String getSifre() {
    	return Sifre; 
	}	
	
	//abstract metod 
	public abstract boolean login(String TC,String Sifre);
	
	
	

}
