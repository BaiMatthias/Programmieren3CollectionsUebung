package MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung;

/**
 * Ein Enum, welches Kontoarten mit einer Beschreibung enthält
 * @author Matthias Baidinger
 */
public enum Kontoart {

	
	Sparbuch("Ein Konto, welches zum Jahresende verzinst wird. Der Zugriff auf das Guthaben ist eingeschränkt."),
	Girokonto("Ein Konto mit flexiblen Zugriff, allerdings auch mit geringeren Zinsen."),
	Depotkonto("Ein Konto, um Wertpapiere zu deponieren.");
	
	// Variable für den Infotext
	private String infotext;
	
	/**
	 * Erstellt ein Enum des Typs Kontoart
	 * @param infotext Infotext, der die Kontoart beschreibt.
	 */
	private Kontoart(String infotext){
		this.infotext = infotext;
	}
	
	/**
	 * Gibt den Infotext der Kontoart zurück
	 * @return Infotext der Kontoart
	 */
	public String getInfotext(){
		return this.infotext;
	}
}
