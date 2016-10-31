package bankprojekt.verarbeitung;

/**
 * Exception, die auftritt, wenn ein Konto nicht genügende Deckung für eine
 * gewünschte Aktion aufweist
 * @author Doro
 *
 */
@SuppressWarnings("serial")
public class KontoNichtGedecktException extends RuntimeException {
	
	/**
	 * erstellt eine KontoNichtGedecktException, wenn auf das Konto mit der angegebenen
	 * Kontonummer zugegriffen werden sollte, das nicht genügend Deckung für den angegebenen
	 * Betrag aufweist
	 * @param kontonummer Nummer des Kontos, auf das zugegriffen wird
	 * @param betrag gewünschter Betrag beim Zugriff
	 */
	public KontoNichtGedecktException(long kontonummer, double betrag)
	{
		super("Das Konto mit der Nummer " + kontonummer + " weist nicht genüegend Deckung für den Betrag von " + betrag + " auf.");
	}
}
