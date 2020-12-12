package domain.model;

import java.time.LocalDate;

public class Bezoeker {
    String naam;
    int hoeveelMensen;
    int verblijfLengte;
    LocalDate startDatum;

    public Bezoeker(){}
    public Bezoeker(String naam, int hoeveelMensen, int verblijfLengte, LocalDate startDatum){
        if (naam.isEmpty())throw new IllegalArgumentException("Naam is leeg");
        if (hoeveelMensen == 0)throw new IllegalArgumentException("hoeveelheid mensen mag niet 0 zijn");
        if (verblijfLengte == 0)throw new IllegalArgumentException("verblijf lengte mag niet 0 zijn");
        if (startDatum == null)throw new IllegalArgumentException("er is geen startdatum opgegeven");
        this.naam = naam;
        this.hoeveelMensen = hoeveelMensen;
        this.verblijfLengte=verblijfLengte;
        this.startDatum=startDatum;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam.isEmpty())throw new IllegalArgumentException("Naam is leeg");
        this.naam = naam;
    }

    public int getHoeveelMensen() {
        return hoeveelMensen;
    }

    public void setHoeveelMensen(int hoeveelMensen) {
        if (hoeveelMensen == 0)throw new IllegalArgumentException("hoeveelheid mensen is 0");
        this.hoeveelMensen = hoeveelMensen;
    }

    public int getVerblijfLengte() {
        return verblijfLengte;
    }

    public void setVerblijfLengte(int verblijfLengte) {
        if (verblijfLengte == 0)throw new IllegalArgumentException("lengte van verblijf is 0");
        this.verblijfLengte = verblijfLengte;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        if (startDatum == null)throw new IllegalArgumentException("er is geen startdatum opgegeven");
        this.startDatum = startDatum;
    }
    public LocalDate berekenEindDatum(){
        LocalDate einde = startDatum.plusDays(verblijfLengte);
        return einde;
    }
    public void updateBezoeker(int hoeveelMensen, int verblijfLengte, LocalDate startDatum){
        if (hoeveelMensen == 0)throw new IllegalArgumentException("hoeveelheid mensen is 0");
        if (verblijfLengte == 0)throw new IllegalArgumentException("lengte van verblijf is 0");
        if (startDatum == null)throw new IllegalArgumentException("er is geen startdatum opgegeven");
        this.hoeveelMensen = hoeveelMensen;
        this.verblijfLengte=verblijfLengte;
        this.startDatum=startDatum;
    }
}
