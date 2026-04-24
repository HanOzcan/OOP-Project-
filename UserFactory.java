package HR;

public class UserFactory {
	
	//parametrelere gore dogru kullanıcı nesnesini üretir
public static Person createUser(String type, String tc, String ad, String soyad, String sifre, String extra) {
        
        if (type.equalsIgnoreCase("PATIENT")) {
            return new Patient(tc, ad, soyad, sifre);
        }else if(type.equalsIgnoreCase("Doctor")){
        	return new Doctor(tc, ad, soyad, sifre, extra);
        }
        return null;	
     }    

}
