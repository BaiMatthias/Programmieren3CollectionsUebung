package bankprojekt.verarbeitung;

import java.util.Date;

/**
 * ein Sparbuch
 * @author Doro
 *
 */
public class Sparbuch extends Konto {
	/**
	 * Zinssatz, mit dem das Sparbuch verzinst wird. 0,03 entspricht 3%
	 */
	private double zinssatz;
	
	/**
	 * Monatlich erlaubter Gesamtbetrag für Abhebungen
	 */
	public static final double ABHEBESUMME = 2000;
	
	/**
	 * Betrag, der im aktuellen Monat bereits abgehoben wurde
	 */
	private double bereitsAbgehoben = 0;
	/**
	 * Monat und Jahr der letzten Abhebung
	 */
	private Date zeitpunkt = new Date();
	
	public Sparbuch() {
		zinssatz = 0.03;
	}

	public Sparbuch(Kunde inhaber, long kontonummer) {
		super(inhaber, kontonummer);
		zinssatz = 0.03;
	}
	
	@Override
	public String toString()
	{
    	String ausgabe = "-- SPARBUCH --" + System.lineSeparator() +
    	super.toString()
    	+ "Zinssatz: " + this.zinssatz * 100 +"%" + System.lineSeparator();
    	return ausgabe;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean abheben (double betrag) throws GesperrtException{
		if (betrag < 0 ) {
			throw new IllegalArgumentException();
		}
		if(this.isGesperrt())
		{
			GesperrtException e = new GesperrtException(this.nummer);
			throw e;
		}
		Date heute = new Date();
		if(heute.getMonth() != zeitpunkt.getMonth() || heute.getYear() != zeitpunkt.getYear())
		{
			this.bereitsAbgehoben = 0;
		}
		if (getKontostand() - betrag >= 0.50 && 
				 bereitsAbgehoben + betrag <= Sparbuch.ABHEBESUMME)
		{
			kontostand = kontostand - betrag;
			bereitsAbgehoben += betrag;
			zeitpunkt = heute;
			kontoaktionList.add(new Kontoaktion("Betrag wurde abgehoben", betrag));
			return true;
		}
		else
			return false;
	}

}
