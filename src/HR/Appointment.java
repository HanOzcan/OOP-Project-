package HR;

public class Appointment {
	private int randevuID;
    private String hastaAdi;
    private String doktorAdi;
    private String tarih;
    
    private static int idCounter = 1000;

    public Appointment(String hastaAdi, String doktorAdi, String tarih) {
        this.randevuID = idCounter++; // ID atama
        this.hastaAdi = hastaAdi;
        this.doktorAdi = doktorAdi;
        this.tarih = tarih;
    }
    
    public int getRandevuID() {
    	return randevuID; 
    	}
    public String getHastaAdi() { 
    	return hastaAdi;
    	}
    public String getDoktorAdi() { 
    	return doktorAdi; 
    	}
    public String getTarih() {
    	return tarih; 
    	}
    
    @Override
    public String toString() {
        return "Randevu No: " + randevuID + " | Hasta: " + hastaAdi + " | Dr: " + doktorAdi + " | Tarih: " + tarih;
    }

}
