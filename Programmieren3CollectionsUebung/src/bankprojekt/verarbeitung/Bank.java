package bankprojekt.verarbeitung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Repräsentiert eine Bank, die Konten und Kunden verwaltet
 * @author Matthias Baidinger
 *
 */
public class Bank {

	// private fields
	private long bankleitzahl;
	private long hoechsteKontonummer;
	private Map<Long, Konto> kontoMap; 
	
	public long getBankleitzahl(){
		return this.bankleitzahl;
	}
	public long getHoechsteKontonummer(){
		return this.hoechsteKontonummer;
	}

	
	public Bank(long bankleitzahl){
		this.bankleitzahl = bankleitzahl;
		this.hoechsteKontonummer  = 0;
		this.kontoMap = new HashMap<Long, Konto>();
	}
	
	
	// public methods
	
	/**
	 * Erstellt ein neues Girokonto mit automatisch vergebener Kontonummer
	 * Kontonummer wird erstellt durch die Addition mit 1
	 * @param inhaber Der Inhaber des Kontos
	 * @return Die Kontonummer des Kontos
	 */
	public long girokontoErstellen(Kunde inhaber){
		Konto k = new Girokonto(inhaber, hoechsteKontonummer +1, 100);
		this.kontoMap.put(k.getKontonummer(), k);
		this.hoechsteKontonummer = k.getKontonummer();
		
		return this.hoechsteKontonummer;
	}
	/**
	 * Erstellt ein neues Sparbuch mit automatisch vergebener Kontonummer
	 * Kontonummer wird erstellt durch die Addition mit 1
	 * @param inhaber Der Inhaber des Kontos
	 * @return Die Kontonummer des Kontos
	 */
	public long sparbuchErstellen(Kunde inhaber){
		
		Konto k = new Sparbuch(inhaber, hoechsteKontonummer +1);
		this.kontoMap.put(k.getKontonummer(), k);
		this.hoechsteKontonummer = k.getKontonummer();
		return this.hoechsteKontonummer;
		
	}
	
	/**
	 * Erstellt einen Text, der alle Konten dieser Bank enthält
	 * @return Der Text, der alle Konten der Bank auflistet
	 */
	public String getAlleKonten(){
		StringBuilder sb = new StringBuilder();
	    for(long nummer : this.kontoMap.keySet()){
	    	sb.append("-----------------");
	    	sb.append(this.kontoMap.get(nummer).getInhaber().toString());
	    	sb.append(this.kontoMap.get(nummer).getKontonummer());
	    	sb.append("\n");
	    	sb.append(this.kontoMap.get(nummer).getKontostand());
	    	sb.append("\n");
	    	
	    }
	    return sb.toString();
		
	}
	/** 
	 * Erstellt eine Liste, die alle Kontonummern enthält
	 *
	 * @return Eine Liste mit allen vergebenen Kontonummern
	 */
	public List<Long> getAlleKontonummern(){
		List<Long> kontonummerList = new ArrayList<Long>();
		for(long nummer : this.kontoMap.keySet()){
			kontonummerList.add(nummer);
		}
		return kontonummerList;
	}
	/**
	 * Hebt Geld vom angegeben Konto ab
	 * @param von Die Kontonummer des Kontos, von dem das Geld abgehoben werden soll
	 * @param betrag Der Betrag, der vom Konto abgehoben werden soll
	 * @return Gibt true zurück, falls die Abhebung erfolgreich war, gibt false zurück, wenn nicht
	 * @throws GesperrtException 
	 */
	public boolean geldAbheben(long von, double betrag){
		try{
			
			if(!this.kontoMap.containsKey(von)){
				throw new KontoNichtExistentException(von);
			}
			if(this.kontoMap.get(von).abheben(betrag)){
				return true;
			}
			return false;
			
		} catch (GesperrtException e) {
			e.printStackTrace();
			return false;
		}
		catch(IllegalArgumentException iae){
			iae.printStackTrace();
			return false;
		}
	}
	/**
	 * Zahlt Geld auf das angegebene Konto ein
	 * @param auf Die Kontonummer des Kontos, auf das das Geld eingezahlt werden soll
	 * @param betrag Der Betrag, der auf das Konto eingezahlt werden soll
	 */
	public void geldEinzahlen(long auf, double betrag){
		if(!this.kontoMap.containsKey(auf)){
			throw new KontoNichtExistentException(auf);
		}
		this.kontoMap.get(auf).einzahlen(betrag);
	}
	/**
	 * Loescht das angegegebene Konto
	 * @param nummer Die Kontonummer des Konto, das gelöscht werden soll
	 * @return gibt true zurück, falls die Loeschung erfolgreich war, gibt false zurück, wenn nicht
	 */
	public boolean kontoLoeschen(long nummer){

		if(!this.kontoMap.containsKey(nummer)){
			throw new KontoNichtExistentException(nummer);
		}
		return this.kontoMap.remove(nummer, this.kontoMap.get(nummer));
	}
	
	/**
	 * Gibt den Kontostand des Kontos zurück
	 * @param nummer Die Kontonummer des Kontos, von welchem der Kontostand zurückgegeben werden soll
	 * @return Der Kontostand
	 */
	public double getKontostand(long nummer){
		if(!this.kontoMap.containsKey(nummer)){
			throw new KontoNichtExistentException(nummer);
		}
		return this.kontoMap.get(nummer).getKontostand();
	}
	/**
	 * Ueberweist Geld von einem Konto auf ein anderes unter Angabe des Verwendungszwecks
	 * @param vonKontonr Die Kontonummer des Kontos, von dem das Geld gesendet wird
	 * @param nachKontonr Die Kontonummer des Kontos, welches das Geld empfängt
	 * @param betrag Der Betrag, der ueberwiesen werden soll
	 * @param verwendungszweck Der Verwendungszweck des Betrages
	 * @return true, wenn Ueberweisung erfolgreich, false, wenn nicht
	 */
	public boolean geldUeberweisen(long vonKontonr, long nachKontonr, double
			 betrag, String verwendungszweck){
		
		try {
			if(!this.kontoMap.containsKey(vonKontonr)){
				throw new KontoNichtExistentException(vonKontonr);
			}
			if(!this.kontoMap.containsKey(nachKontonr)){
				throw new KontoNichtExistentException(nachKontonr);
			}
			if(this.kontoMap.get(vonKontonr) instanceof Girokonto && this.kontoMap.get(nachKontonr) instanceof Girokonto){
				Girokonto gksender = (Girokonto)this.kontoMap.get(vonKontonr);
				gksender.ueberweisungAbsenden(betrag, 
						this.kontoMap.get(nachKontonr).getInhaber().getName() + " "+this.kontoMap.get(nachKontonr).getInhaber().getNachname(),
						nachKontonr, this.bankleitzahl, verwendungszweck);
				Girokonto gkempfaenger = (Girokonto)this.kontoMap.get(nachKontonr);
				gkempfaenger.ueberweisungEmpfangen(betrag, this.kontoMap.get(vonKontonr).getInhaber().getName() + " "+this.kontoMap.get(vonKontonr).getInhaber().getNachname(),
						vonKontonr, this.bankleitzahl, verwendungszweck);
				return true;
			}
			
		} catch (GesperrtException e) {
			
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
}
 