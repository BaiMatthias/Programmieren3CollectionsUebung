package MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung;

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
	 * Wenn das Konto gesperrt ist (gesperrt = true), k�nnen keine Aktionen daran mehr vorgenommen werden,
	 * die zum Schaden des Kontoinhabers w�ren (abheben, Inhaberwechsel)
	 */
	private boolean gesperrt;
	
	/**
	 * die W�hrung, in der der Kontostand gerade angegeben wird.
	 */
	private Waehrung aktuelleWaehrung = Waehrung.EUR;
	
	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
	 * der anf�ngliche Kontostand wird auf 0 gesetzt.
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
	}
	
	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public Konto() {
		this(Kunde.MUSTERMANN, 1234567);
	}
	
	/**
	 * liefert die W�hrung, in der das Konto gef�hrt wird
	 * @return W�hrung von this
	 */
	public Waehrung getAktuelleWaehrung() {
		return this.aktuelleWaehrung;
	}

	/**
	 * setzt die aktuelle W�hrung des Kontos und rechnet 
	 * den aktuellen Kontostand entsprechend um
	 * @param aktuelleWaehrung neue W�hrung
	 */
	public void setAktuelleWaehrung(Waehrung aktuelleWaehrung) {
		if (this.aktuelleWaehrung != aktuelleWaehrung)
		{
			this.kontostand = this.kontostand * 
					aktuelleWaehrung.getWechselkurs()
					/this.aktuelleWaehrung.getWechselkurs();
		}
		this.aktuelleWaehrung = aktuelleWaehrung;
	}

	/**
	 * liefert den Kontoinhaber zur�ck
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
			&& !this.gesperrt)         //und das Konto nicht gesperrt ist
			    this.inhaber = kinh;

	}
	
	/**
	 * liefert den aktuellen Kontostand
	 * @return   double
	 */
	public double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zur�ck
	 * @return   long
	 */
	public long getKontonummer() {
		return nummer;
	}

	/**
	 * liefert zur�ck, ob das Konto gesperrt ist oder nicht
	 * @return
	 */
	public boolean isGesperrt() {
		return gesperrt;
	}
	
	/**
	 * Erh�ht den Kontostand um den eingezahlten Betrag.
	 *
	 * @param betrag double
	 * @throws IllegalArgumentException wenn der stand negativ ist 
	 */
	public void einzahlen(double betrag) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		kontostand = kontostand + betrag;
	}
	
	/**
	 * Gibt eine Zeichenkettendarstellung der Kontodaten zur�ck.
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
	 * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr m�glich.
	 */
	public void sperren() {
		this.gesperrt = true;
	}

	/**
	 * entsperrt das Konto, alle Kontoaktionen sind wieder m�glich.
	 */
	public void entsperren() {
		this.gesperrt = false;
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
	 * @return formatierter Kontostand mit 2 Nachkommastellen und W�hrungssymbol �
	 */
	public String getKontostandFormatiert()
	{
		return String.format("%10.2f " + this.getAktuelleWaehrung(), this.kontostand);
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
