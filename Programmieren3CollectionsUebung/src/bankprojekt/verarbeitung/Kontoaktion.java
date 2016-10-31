package bankprojekt.verarbeitung;
import java.time.LocalDateTime;

public class Kontoaktion {

	
/**
 * Eine Klasse, die eine Kontoaktion darstellt. Diese speichert den Text, den Betrag 
 * und das Datum der Aktion.
 * Als Datum wird LocalDateTime verwendet, da Date veraltet ist und nicht
 * zur Verwendung empfohlen wird.
 * @author Matthias Baidinger
 */
	private String text;
	private double betrag;
	private LocalDateTime datum;
	
	/*
	 * Dieser Konstruktor wird bei Geldbewegungen verwendet
	 * @param text Die Beschreibung der Aktion
	 * @param betrag Der Betrag, der mit der Aktion gehandelt wurde
	 */
	public Kontoaktion(String text, double betrag){
		this.text = text;
		this.betrag = betrag;
		this.datum = LocalDateTime.now();
		
	}
	
	/*
	 * Dieser Konstruktor soll bei Aktionen verwendet werden, die eine
	 * Information liefern, der Kontostand bleibt unberührt
	 * @param text Die Beschreibung der Aktion
	 */
	public Kontoaktion(String text){
		this.text = text;
		this.betrag = 0;
		this.datum = LocalDateTime.now();
	}
	
	public String getText(){
		return this.text;
	}
	public double getBetrag(){
		return this.betrag;
	}
	public LocalDateTime getDatum(){
		return this.datum;
	}
	
}
