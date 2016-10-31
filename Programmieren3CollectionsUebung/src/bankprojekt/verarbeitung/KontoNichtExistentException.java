package bankprojekt.verarbeitung;

@SuppressWarnings("serial")
public class KontoNichtExistentException extends RuntimeException{
	
	/**
	 * Erstellt eine KontoNichtExistentException, wenn eine Kontonummer verwendet wird,
	 * die mit keinem Konto verknuepft ist
	 * @param kontonummer Die Kontonummer, auf die versucht wurde, zuzugreifen
	 */
	public KontoNichtExistentException(long kontonummer){
		
		super("Das Konto mit der Kontonummer " + kontonummer + " existiert nicht!");
		
	}

}
