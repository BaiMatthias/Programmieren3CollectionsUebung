package MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;

/**
 * <p>
 * Kunde einer Bank
 * </p>
 * 
 * @author Dorothea Hubrich
 * @version 1.0
 */
public class Kunde implements Comparable<Kunde>{
	/**
	 * Ein Musterkunde
	 */
	public static final Kunde MUSTERMANN = new Kunde("Max", "Mustermann", "zuhause", new Date());
	/**
	 * englische oder deutsche Anrede, je nach den Systemeinstellungen
	 */
	public static String ANREDE;

	/**
	 * der Vorname
	 */
	private String vorname;
	/**
	 * Der Nachname
	 */
	private String nachname;
	/**
	 * Die Adresse
	 */
	private String adresse;
	/**
	 * Geburtstag
	 */
	private Date geburtstag;

	/**
	 * erzeugt einen Standardkunden
	 */
	public Kunde() {
		this("Max", "Mustermann", "Adresse", new Date());
	}

	/**
	 * Erzeugt einen Kunden mit den �bergebenen Werten
	 * 
	 * @param vorname
	 * @param nachname
	 * @param adresse
	 * @param gebdat
	 */
	public Kunde(String vorname, String nachname, String adresse, Date gebdat) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
		this.geburtstag = gebdat;
	}

	/**
	 * Erzeugt einen Kunden mit den �bergebenen Werten
	 * 
	 * @param vorname
	 * @param nachname
	 * @param adresse
	 * @param gebdat
	 *            im Format tt.mm.yy oder tt.mm.yyyy
	 * @throws ParseException wenn das Format des �bergebenen Datums nicht korrekt ist
	 */
	public Kunde(String vorname, String nachname, String adresse, String gebdat) throws ParseException {
		this(vorname, nachname, adresse, DateFormat.getDateInstance(DateFormat.SHORT).parse(gebdat));
	}

	/**
	 * gibt alle Daten des Kunden aus
	 */
	@Override
	public String toString() {
		String ausgabe;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		ausgabe = this.vorname + " " + this.nachname + System.getProperty("line.separator");
		ausgabe += this.adresse + System.getProperty("line.separator");
		ausgabe += df.format(this.geburtstag) + System.getProperty("line.separator");
		return ausgabe;
	}

	/**
	 * vollst�ndiger Name des Kunden in der Form "Nachname, Vorname"
	 * 
	 * @return
	 */
	public String getName() {
		return this.nachname + ", " + this.vorname;
	}

	/**
	 * Adresse des Kunden
	 * 
	 * @return
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * setzt die Adresse auf den angegebenen Wert
	 * 
	 * @param adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Nachname des Kunden
	 * 
	 * @return
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * setzt den Nachnamen auf den angegebenen Wert
	 * 
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Vorname des Kunden
	 * 
	 * @return
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * setzt den Vornamen auf den angegebenen Wert
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Geburtstag des Kunden
	 * 
	 * @return
	 */
	public Date getGeburtstag() {
		return geburtstag;
	}

	static {
		if (Locale.getDefault().getCountry().equals("DE")) {
			Kunde.ANREDE = "Lieber Kunde";
		} else {
			Kunde.ANREDE = "Dear Customer";
		}

	}

	
	public int compareTo(Kunde arg0) {
		return this.getName().compareTo(arg0.getName());
	}
}