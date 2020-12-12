package domain.db;

import domain.model.Bezoeker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BezoekersDB {
    ArrayList<Bezoeker> bezoekers;

    public BezoekersDB() {
        bezoekers = new ArrayList<>();

        Bezoeker tim = new Bezoeker("Tim", 2, 7, LocalDate.of(2020,10,25));
        Bezoeker elisa = new Bezoeker("Elisa", 2, 5, LocalDate.of(2021,1,1));
        Bezoeker natalie = new Bezoeker("Natalie", 4, 4, LocalDate.of(2020,11,20));
        Bezoeker papa = new Bezoeker("Papa", 1, 2, LocalDate.of(2020,12,8));

        voegToe(tim);
        voegToe(elisa);
        voegToe(natalie);
        voegToe(papa);
    }

    public ArrayList<Bezoeker> getBezoekers() {
        return bezoekers;
    }

    public ArrayList<Bezoeker> getBezoekersChronologisch(){
        ArrayList<Bezoeker> chronologisch = new ArrayList<>();
        for (Bezoeker b: getBezoekers()){
            chronologisch.add(b);
        }
        Collections.sort(chronologisch, new Comparator<Bezoeker>() {
            @Override
            public int compare(Bezoeker bezoeker1, Bezoeker bezoeker2) {
                return Integer.valueOf(bezoeker1.getStartDatum().compareTo(bezoeker2.getStartDatum()));
            }
        });
        return chronologisch;
    }

    public void voegToe(Bezoeker bezoeker){
        if (bezoeker == null)throw new IllegalArgumentException("bezoeker om toe te voegen is null");

        else{
            for (Bezoeker b: bezoekers) {
                if ((bezoeker.getStartDatum().isBefore(b.berekenEindDatum()) || bezoeker.getStartDatum().isEqual(b.berekenEindDatum()))
                        && (b.getStartDatum().isBefore(bezoeker.berekenEindDatum()) || b.getStartDatum().isEqual(bezoeker.berekenEindDatum()))) {
                    throw new IllegalArgumentException("datum van deze bezoeker overlapt met bestaande bezoeker");
                }
            }
        }
        bezoekers.add(bezoeker);
    }
    public void verwijder(Bezoeker bezoeker){
        bezoekers.remove(bezoeker);
    }

    public Bezoeker zoek(String naam){
        if (naam.isEmpty())throw new IllegalArgumentException("Vul een naam in.");
        else {
            Bezoeker bezoeker = null;
            for (Bezoeker b: bezoekers){
                if (naam.equalsIgnoreCase(b.getNaam())){
                    bezoeker = b;
                }
            }
            if (bezoeker == null)throw new IllegalArgumentException("Bezoeker is niet te vinden in de database");
            return bezoeker;
        }
    }
    public Bezoeker berekenVolgendeBezoeker(){
        Bezoeker volgende = null;
        for (Bezoeker b : bezoekers){
            if (volgende == null || b.getStartDatum().isBefore(volgende.getStartDatum())){
                volgende = b;
            }
        }
        return volgende;
    }

}
