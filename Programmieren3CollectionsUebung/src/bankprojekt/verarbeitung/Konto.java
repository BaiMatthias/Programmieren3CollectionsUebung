package bankprojekt.verarbeitung;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto 
{
	/** 
	 * der Kontoinhaber
	 */
	private Kunde inhaber;

	/**
	 * die Kontonummer
	 */
	public final long nummer;

	/**
	 * der aktuelle Kontostand
	 */
	protected double kontostand;

	/**
	 * Wenn das Konto gesperrt ist (gesperrt = true), können keine Aktionen daran mehr vorgenommen werden,
	 * die zum Schaden des Kontoinhabers wären (abheben, Inhaberwechsel)
	 */
	private boolean gesperrt;
	
	/**
	 * die Währung, in der der Kontostand gerade angegeben wird.
	 */
	private Waehrung aktuelleWaehrung = Waehrung.EUR;
	
	/*
	 * Die gespeicherten Kontoaktionen
	 */
	protected static List<Kontoaktion> kontoaktionList = new ArrayList<Kontoaktion>();
	
	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
	 * der anfängliche Kontostand wird auf 0 gesetzt.
	 *
	 * @param inhaber Kunde
	 * @param kontonummer long
	 * @throws IllegalArgumentException wenn der Inhaber null
	 */
	public Konto(Kunde inhaber, long kontonummer) {
		if(inhaber == null)
			throw new IllegalArgumentException("Inhaber darf nicht null sein!");
		this.inhaber = inhaber;
		this.nummer = kontonummer;
		this.kontostand = 0;
		this.gesperrt = false;
		kontoaktionList.add(new Kontoaktion("Neues Konto mit der Kontonummer " + this.nummer + " wurde angelegt"));
	}
	
	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public Konto() {
		this(Kunde.MUSTERMANN, 1234567);
	}
	
	/**
	 * liefert die Währung, in der das Konto geführt wird
	 * @return Währung von this
	 */
	public Waehrung getAktuelleWaehrung() {
		return this.aktuelleWaehrung;
	}
	
	

	/**
	 * setzt die aktuelle Währung des Kontos und rechnet 
	 * den aktuellen Kontostand entsprechend um
	 * @param aktuelleWaehrung neue Währung
	 */
	public void setAktuelleWaehrung(Waehrung aktuelleWaehrung) {
		if (this.aktuelleWaehrung != aktuelleWaehrung)
		{
			this.kontostand = this.kontostand * 
					aktuelleWaehrung.getWechselkurs()
					/this.aktuelleWaehrung.getWechselkurs();
		}
		this.aktuelleWaehrung = aktuelleWaehrung;
		kontoaktionList.add(new Kontoaktion("Das Konto mit der Kontonr. " + this.nummer + " wurde auf die Währung " + aktuelleWaehrung + " umgestellt"));
	}

	/**
	 * liefert den Kontoinhaber zurück
	 * @return   Kunde
	 */
	public final Kunde getInhaber() {
		return this.inhaber;
	}
	
	/**
	 * setzt den Kontoinhaber
	 * @param kinh   neuer Kontoinhaber
	 */
	public final void setInhaber(Kunde kinh){
		if (kinh != null 
			&& !this.gesperrt)      {
			//und das Konto nicht gesperrt ist
			 this.inhaber = kinh;
			 kontoaktionList.add(new Kontoaktion("Neuer Inhaber wurde gesetzt"));
		}
			   
		
	}
	
	/**
	 * liefert den aktuellen Kontostand
	 * @return   double
	 */
	public double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zurück
	 * @return   long
	 */
	public long getKontonummer() {
		return nummer;
	}

	/**
	 * liefert zurück, ob das Konto gesperrt ist oder nicht
	 * @return
	 */
	public boolean isGesperrt() {
		return gesperrt;
	}
	
	/**
	 * Erhöht den Kontostand um den eingezahlten Betrag.
	 *
	 * @param betrag double
	 * @throws IllegalArgumentException wenn der stand negativ ist 
	 */
	public void einzahlen(double betrag) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		kontostand = kontostand + betrag;
		kontoaktionList.add(new Kontoaktion("Betrag wurde eingezahlt", betrag));
	}
	
	/**
	 * Gibt eine Zeichenkettendarstellung der Kontodaten zurück.
	 */
	@Override
	public String toString() {
		String ausgabe;
		ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
				+ System.getProperty("line.separator");
		ausgabe += "Inhaber: " + this.inhaber;
		ausgabe += "Aktueller Kontostand: " + this.kontostand + this.getAktuelleWaehrung();
		ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
		return ausgabe;
	}

	/**
	 * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
	 *
	 * @param betrag double
	 * @throws GesperrtException wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException wenn der betrag negativ ist 
	 */
	public abstract boolean abheben(double betrag) throws GesperrtException;
	
	/**
	 * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr möglich.
	 */
	public void sperren() {
		this.gesperrt = true;
		 kontoaktionList.add(new Kontoaktion("Konto wurde gesperrt"));
		
	}

	/**
	 * entsperrt das Konto, alle Kontoaktionen sind wieder möglich.
	 */
	public void entsperren() {
		this.gesperrt = false;
		 kontoaktionList.add(new Kontoaktion("Konto wurde entsperrt"));
	}
	
	
	/**
	 * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
	 * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
	 */
	public String getGesperrtText()
	{
		if (this.gesperrt)
		{
			return "GESPERRT";
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * liefert die ordentlich formatierte Kontonummer
	 * @return auf 10 Stellen formatierte Kontonummer
	 */
	public String getKontonummerFormatiert()
	{
		return String.format("%10d", this.nummer);
	}
	
	/**
	 * liefert den ordentlich formatierten Kontostand
	 * @return formatierter Kontostand mit 2 Nachkommastellen und Währungssymbol €
	 */
	public String getKontostandFormatiert()
	{
		return String.format("%10.2f " + this.getAktuelleWaehrung(), this.kontostand);
	}
	/**
	 * Liefert einen String, der alle Kontobewegungen enthaelt
	 * @return String mit den Kontobewegungen
	 */
	public static String getKontoauszug(){
		StringBuilder sb = new StringBuilder();
		for(Kontoaktion ka : kontoaktionList){
			sb.append(ka.getDatum() + " "+ ka.getText() + "  " +ka.getBetrag());
			sb.append("\n");
		}
		return sb.toString();
	}
	/**
	 * Loescht alle Eintraege, die vor dem übergegeben Datum stattfanden
	 * Wenn die erste Aktion in der Liste schon nach dem übergegebenen Datum stattfand
	 * passiert nix
	 * Wenn alle Aktionen vor dem übergegebenen Datum stattfanden, wird die gesamte Liste geloescht
	 * @param vor 
	 */
	public static void alteEintraegeLoeschen(LocalDateTime vor){
		int index = -1;
		for(int i = 0; i < kontoaktionList.size();i++){
			if(vor.isBefore(kontoaktionList.get(i).getDatum())){
				index = i;
			}
			else if(i == kontoaktionList.size()-1){ // Am Ende der Liste angelangt, Liste loeschen
				kontoaktionList.clear();
				continue;
			}
		}
		if(index >= 0){
			kontoaktionList.subList(0, index).clear();
		}
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(this == other)
			return true;
		if(other == null)
			return false;
		if(this.getClass() != other.getClass())
			return false;
		if(this.nummer == ((Konto)other).nummer)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
	}
}
