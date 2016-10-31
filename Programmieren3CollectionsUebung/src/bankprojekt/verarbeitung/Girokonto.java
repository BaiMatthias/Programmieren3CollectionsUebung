package bankprojekt.verarbeitung;

/**
 * Ein Girokonto
 * @author Doro
 *
 */
public class Girokonto extends Konto {
	/**
	 * Wert, bis zu dem das Konto �berzogen werden darf
	 */
	private double dispo;

	/**
	 * erzeugt ein Standard-Girokonto
	 */
	public Girokonto()
	{
		super(Kunde.MUSTERMANN, 99887766);
		this.dispo = 500;
	}
	
	/**
	 * erzeugt ein Girokonto mit den angegebenen Werten
	 * @param inhaber Kontoinhaber
	 * @param nummer Kontonummer
	 * @param dispo Dispo
	 * @throws IllegalArgumentException wenn der inhaber null ist
	 */
	public Girokonto(Kunde inhaber, long nummer, double dispo)
	{
		super(inhaber, nummer);
		this.dispo = Math.max(0, dispo);
	}
	
	/**
	 * liefert den Dispo
	 * @return Dispo von this
	 */
	public double getDispo() {
		return dispo;
	}

	/**
	 * setzt den Dispo neu
	 * @param dispo muss gr��er sein als 0
	 */
	public void setDispo(double dispo) {
		if(dispo >= 0)
		{
			this.dispo = dispo;
		}
	}
	
    /**
     * vermindert den Kontostand um den angegebenen Betrag, falls das Konto nicht gesperrt ist.
     * Am Empf�ngerkonto wird keine �nderung vorgenommen, da davon ausgegangen wird, dass dieses sich
     * bei einer anderen Bank befindet.
     * @param betrag double
     * @param empfaenger String
     * @param nachKontonr int
     * @param nachBlz int
     * @param verwendungszweck String
     * @return boolean
     * @throws GesperrtException wenn das Konto gesperrt ist
     * @throws KontoNichtGedecktException wenn das Konto nicht gedeckt ist
     * @throws IllegalArgumentException wenn der Betrag negativ ist
     */
    public boolean ueberweisungAbsenden(double betrag, String empfaenger, long nachKontonr, long nachBlz, String verwendungszweck) throws GesperrtException 
    {
      if (this.isGesperrt())
            throw new GesperrtException(this.nummer);
        if (betrag < 0)
        {
                betrag = 0;
        }
        if (getKontostand() - betrag >= - dispo)
        {
            kontostand = getKontostand() - betrag;
            kontoaktionList.add(new Kontoaktion("�berweisung wurde von Konto "+ nummer +" mit Verwendungszweck " +verwendungszweck+" abgesendet",betrag));
            return true;
        }
        else
        {
        	throw new KontoNichtGedecktException(this.nummer, betrag);
        }
    }

    /**
     * erh�ht den Kontostand um den angegebenen Betrag
     * @param betrag double
     * @param vonName String
     * @param vonKontonr int
     * @param vonBlz int
     * @param verwendungszweck String
     */
    public void ueberweisungEmpfangen(double betrag, String vonName, long vonKontonr, long vonBlz, String verwendungszweck)
    {
        this.kontostand += betrag;
        kontoaktionList.add(new Kontoaktion("�berweisung wurde von Konto "+ vonKontonr +" mit Verwendungszweck " +verwendungszweck+" empfangen",betrag));
    }
    
    @Override
    public String toString()
    {
    	String ausgabe = "-- GIROKONTO --" + System.lineSeparator() +
    	super.toString()
    	+ "Dispo: " + this.dispo + System.lineSeparator();
    	return ausgabe;
    }

	@Override
	public boolean abheben(double betrag) throws GesperrtException{
		if (betrag < 0 ) {
			throw new IllegalArgumentException();
		}
		if(this.isGesperrt())
		{
			GesperrtException e = new GesperrtException(this.nummer);
			throw e;
		}
		if (getKontostand() - betrag >= - dispo)
		{
			kontostand = kontostand - betrag;
			 kontoaktionList.add(new Kontoaktion("Betrag wurde abgehoben", betrag));
			return true;
		}
		else
			return false;
	}

}
