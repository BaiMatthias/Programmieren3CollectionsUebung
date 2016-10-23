package MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung;

import java.util.Calendar;

import MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung.GesperrtException;
import MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung.Girokonto;
import MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung.Konto;
import MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung.Kunde;
import MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung.Sparbuch;
import MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung.Waehrung;

/**
 * Testprogramm f�r Konten
 * @author Doro
 *
 */
public class Kontentest {

	/**
	 * Testprogramm f�r Konten
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(76, Calendar.JULY, 13);
		Kunde ich = new Kunde("Dorothea", "Hubrich", "zuhause", cal.getTime());

		Konto meinGiro = new Girokonto(ich, 1234, 1000.0);
		meinGiro.einzahlen(50);
		System.out.println(meinGiro);
		//((Girokonto)meinGiro).ueberweisungAbsenden();
		
		Konto meinSpar = new Sparbuch(ich, 9876);
		meinSpar.einzahlen(50);
		try
		{
			boolean hatGeklappt = meinSpar.abheben(70);
			System.out.println("Abhebung hat geklappt: " + hatGeklappt);
			System.out.println(meinSpar.toString());
		}
		catch (GesperrtException e)
		{
			System.out.println("Zugriff auf gesperrtes Konto - Polizei rufen!");
		}
		
		int a = 100;
		int b = a;  //Achtung: Kopien!!!
		a += 50;
		System.out.println(b);
		
		Konto aKonto = new Girokonto();
		aKonto.einzahlen(100);
		Konto bKonto = aKonto;  //Achtung: Referenzen!!!!
		aKonto.einzahlen(50);
		System.out.println(bKonto);
		System.out.println("---------------------------");
		
		Waehrung w = Waehrung.BGN;
		System.out.println(w + " " + w.name() + " " + w.ordinal());
		System.out.println(w.getWechselkurs());
		
		System.out.println("Es sind folgende Kontoarten verfügbar:");
		for(Kontoart k : Kontoart.values()){
			
			System.out.println(k.toString() + ": " + k.getInfotext());
		}
		
		
	}

}
