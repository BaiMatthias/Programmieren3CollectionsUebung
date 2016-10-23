package MavenUebung_Programmieren3_Baidinger.MavenProject_Baidinger.bankprojekt.verarbeitung;

/**
 * Exception, die auftritt, wenn ein Konto nicht gen�gende Deckung f�r eine
 * gew�nschte Aktion aufweist
 * @author Doro
 *
 */
@SuppressWarnings("serial")
public class KontoNichtGedecktException extends RuntimeException {
	
	/**
	 * erstellt eine KontoNichtGedecktException, wenn auf das Konto mit der angegebenen
	 * Kontonummer zugegriffen werden sollte, das nicht gen�gend Deckung f�r den angegebenen
	 * Betrag aufweist
	 * @param kontonummer Nummer des Kontos, auf das zugegriffen wird
	 * @param betrag gew�nschter Betrag beim Zugriff
	 */
	public KontoNichtGedecktException(long kontonummer, double betrag)
	{
		super("Das Konto mit der Nummer " + kontonummer + " weist nicht gen�egend Deckung f�r den Betrag von " + betrag + " auf.");
	}
}
