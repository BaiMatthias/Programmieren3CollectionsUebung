package bankprojekt.verarbeitung;

/**
 * Währung mit Umrechnungskurs zum Euro
 * @author Doro
 *
 */
public enum Waehrung {
	/**
	 * Bulgarische Leva	
	 */
	BGN(1.95583), 
	/**
	 * Litauische Litas
	 */
	LTL(3.4528),
	/**
	 * Konvertible Mark Bosnien-Herzegowina
	 */
	KM(1.95583), 
	/**
	 * Euro
	 */
	EUR(1);
	
	/**
	 * erstellt eine neue Währung mit dem angegebenen Kurs
	 * @param kurs
	 */
	private Waehrung(double kurs)
	{
		this.wechselkurs = kurs;
	}
	
	/** 
	 * 1 Euro entspricht wechselkurs Einheiten von this
	 */
	private double wechselkurs;

	/**
	 * liefert den Wechselkurs; 1 Euro entspricht wechselkurs Einheiten von this
	 * @return
	 */
	public double getWechselkurs() {
		return wechselkurs;
	}
	

}
