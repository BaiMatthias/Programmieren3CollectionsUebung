import java.sql.Date;
import java.time.LocalDateTime;

import bankprojekt.verarbeitung.Bank;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.KontoNichtExistentException;
import bankprojekt.verarbeitung.Kunde;

/**
 * Testprogramm für Konten
 * @author Doro
 *
 */
public class Kontentest {

	/**
	 * Testprogramm für Konten
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		
		Kunde k1 = new Kunde("Max","Mustermann","Musterstr.3",  new Date(3242));
		Kunde k2 = new Kunde("Martina","Mustermann","Musterstr.20",  new Date(124567));
		
		//Bank anlegen
		Bank b = new Bank(500100);
		// 3 Girokonten und 2 Sparbuecher anlegen und Kontonummer ausgeben
		b.girokontoErstellen(k1);
		System.out.println(b.getHoechsteKontonummer());
		b.girokontoErstellen(k1);
		System.out.println(b.getHoechsteKontonummer());
		b.girokontoErstellen(k2);
		System.out.println(b.getHoechsteKontonummer());
		b.sparbuchErstellen(k1);
		System.out.println(b.getHoechsteKontonummer());
		b.sparbuchErstellen(k2);
		System.out.println(b.getHoechsteKontonummer());
		
		
		// Liste aller Konten
		System.out.println(b.getAlleKonten());
				
		//Geld einzahlen
		b.geldEinzahlen(1, 10000);
		b.geldEinzahlen(2, 10000);
		b.geldEinzahlen(3, 10000);
		b.geldEinzahlen(4, 10000);
		b.geldEinzahlen(5, 10000);
		
		//Nicht existierendes Konto einzahlen
		try{
			b.geldEinzahlen(20, 10000);
		}
		catch(KontoNichtExistentException kex){
			kex.printStackTrace();
			System.out.println("Einzahlung nicht erfolgreich");
		}
		
		
		// Liste aller Konten
		System.out.println(b.getAlleKonten());
		
		//Geld abheben
		if(b.geldAbheben(3, 5000)){
			System.out.println("Abheben erfolgreich");
		}
		else{
			System.out.println("Abheben nicht erfolgreich");
		}
		
		if(b.geldAbheben(5, 3000)){
			System.out.println("Abheben erfolgreich");
		}
		else{
			System.out.println("Abheben nicht erfolgreich");
		}
		
		
		// Mehr Geld abheben, als verfügbar ist
		if(b.geldAbheben(4, 20000)){
			System.out.println("Abheben erfolgreich");
		}
		else{
			System.out.println("Abheben nicht erfolgreich");
		}
		
		// Liste aller Konten
		System.out.println(b.getAlleKonten());
		
		//Geld ueberweisen
		if(b.geldUeberweisen(1, 2, 2500, "Autokredit")){
			System.out.println("Ueberweisung erfolgreich");
		}
		else{
			System.out.println("Ueberweisung nicht erfolgreich");
		}
		
		//Geld von Girokonto auf Sparkbuch ueberweisen
		try{
			
		
		if(b.geldUeberweisen(3, 4, 1500, "Rente sparen")){
			System.out.println("Ueberweisung erfolgreich");
		}
		else{
			
		}
		}
		catch(ClassCastException cce){
			System.out.println("Ueberweisung nicht erfolgreich");
		}
		
		
		// Liste aller Konten
		System.out.println(b.getAlleKonten());
		
		System.out.println(Konto.getKontoauszug());
		// Zum Testen
		Konto.alteEintraegeLoeschen(LocalDateTime.now().plusDays(4));
		System.out.println(Konto.getKontoauszug());
	}

}
