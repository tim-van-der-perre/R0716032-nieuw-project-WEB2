package ui.controller;

import domain.db.BezoekersDB;
import domain.model.Bezoeker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    BezoekersDB bezoekersDB = new BezoekersDB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "home";
        String destination;

        if (request.getParameter("command")!= null){
            command = request.getParameter("command");
        }

        switch (command){
            case "home":
                destination = home(request,response);
                break;
            case "overzicht":
                destination = sorteeroptie(request, response);
                break;
            case "reservatieOverzicht":
                destination = wisselOverzicht(request,response,"reservatie");
                break; 
            case "chronologischOverzicht":
                destination = wisselOverzicht(request,response,"chronologisch");
                break;
                
            case "voegToe":
                destination = voegToe(request, response);
                break;
            case "zoek" :
                destination = zoek(request, response);
                break;
            case "ProbeerTeVerwijderen" :
                destination = "verwijder.jsp";
                break;
            case "verwijderDefinitief" :
                destination = verwijderDefinitief(request, response);
                break;
            case "ProbeerTeUpdaten" :
                destination = "update.jsp";
                break;
            case "updateDefinitief" :
                destination = updateDefinitief(request, response);
                break;
            default :
                destination = overzicht(request,response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String sorteeroptie(HttpServletRequest request, HttpServletResponse response) {
        String destination;
        Cookie cookie = getCookieWithKey(request, "sorteerOptie");

        if (cookie == null || cookie.getValue().equals("reservatie")){
            destination = overzicht(request,response);
        } else {
            destination = chronologischOverzicht(request, response);
        }
        return destination;
    }

    private String wisselOverzicht(HttpServletRequest request, HttpServletResponse response, String sorteerOptie) {
        String destination;
        Cookie c = new Cookie("sorteerOptie", sorteerOptie);
        response.addCookie(c);

        if (sorteerOptie == null || sorteerOptie.equals("reservatie")){
            request.setAttribute("requestCookie", "reservatie");
            destination = overzicht(request, response);
        }
        else {
            request.setAttribute("requestCookie", "chronologisch");
            destination = chronologischOverzicht(request, response);
        }
        return destination;
    }

    private String chronologischOverzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("bezoekers", bezoekersDB.getBezoekersChronologisch());
        return "overzicht.jsp";
    }


    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("bezoekers", bezoekersDB.getBezoekers());
        return "overzicht.jsp";
    }


    private String updateDefinitief(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String naam = request.getParameter("naam");

        Bezoeker oudeBezoeker = bezoekersDB.zoek(naam);

        Bezoeker nieuweBezoeker = new Bezoeker();
        nieuweBezoeker.setNaam(naam);
        setHoeveelheidMensen(nieuweBezoeker, request, errors);
        setVerblijfLengte(nieuweBezoeker, request, errors);
        setStartDatum(nieuweBezoeker, request, errors);

        if (errors.size() == 0) {
            try {
                    bezoekersDB.verwijder(bezoekersDB.zoek(naam));
                    bezoekersDB.voegToe(nieuweBezoeker);
                    return sorteeroptie(request, response);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
                bezoekersDB.voegToe(oudeBezoeker);
            }
        }
        request.setAttribute("errors", errors);
        return "update.jsp";
    }


    private String verwijderDefinitief(HttpServletRequest request, HttpServletResponse response) {
        String naam = request.getParameter("naam");
        bezoekersDB.verwijder(bezoekersDB.zoek(naam));
        return overzicht(request, response);
    }

    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String naam = request.getParameter("bezoekersNaam");
        if (naam.isEmpty()){
            errors.add("Er is geen naam ingevuld om te zoeken.");
            request.setAttribute("errors", errors);
            return "zoek.jsp";
            }
        ArrayList<Bezoeker>gezochten = new ArrayList<>();
        for (Bezoeker b: bezoekersDB.getBezoekers()){
            if (b.getNaam().equalsIgnoreCase(naam)){
                gezochten.add(b);
            }
        }
        request.setAttribute("gevondenBezoekers", gezochten);
        logboekVoorSessies(request, response, naam);
        return "gevonden.jsp";
    }

    private void logboekVoorSessies(HttpServletRequest request, HttpServletResponse response, String naam) {
        HttpSession session = request.getSession();
        if (session.getAttribute("logboek") == null){
            ArrayList<String>logboek = new ArrayList<>();
            logboek.add(naam);
            session.setAttribute("logboek", logboek);
        }
        else{
            ArrayList<String> logboek = (ArrayList<String>)session.getAttribute("logboek");
            logboek.add(naam);
            session.setAttribute("logboek", logboek);
        }
    }

    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        Bezoeker nieuweBezoeker = new Bezoeker();
        setNaam(nieuweBezoeker, request, errors);
        setHoeveelheidMensen(nieuweBezoeker, request, errors);
        setVerblijfLengte(nieuweBezoeker, request, errors);
        setStartDatum(nieuweBezoeker, request, errors);

        if (errors.size() == 0) {
            try {
                bezoekersDB.voegToe(nieuweBezoeker);
                return sorteeroptie(request, response);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "voegToe.jsp";
    }

    private void setStartDatum(Bezoeker nieuweBezoeker, HttpServletRequest request, ArrayList<String> errors) {
        String startDatum = request.getParameter("startDatum");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localeDatum = LocalDate.parse(startDatum, formatter);
            nieuweBezoeker.setStartDatum(localeDatum);
            request.setAttribute("datumPreviousValue", localeDatum);
        }
        catch (NullPointerException | DateTimeParseException NPE){
            errors.add("Datum is leeg");
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }
    private void setVerblijfLengte(Bezoeker nieuweBezoeker, HttpServletRequest request, ArrayList<String> errors) {
        String verblijfLengte = request.getParameter("verblijfLengte");
        try {
            nieuweBezoeker.setVerblijfLengte(Integer.parseInt(verblijfLengte));
            request.setAttribute("verblijfLengtePreviousValue", verblijfLengte);
        } catch (IllegalArgumentException exc) {
            errors.add("lengte van verblijf is leeg");
        }
    }

    private void setHoeveelheidMensen(Bezoeker nieuweBezoeker, HttpServletRequest request, ArrayList<String> errors) {
        String hoeveelMensen = request.getParameter("hoeveelMensen");
        try {
            nieuweBezoeker.setHoeveelMensen(Integer.parseInt(hoeveelMensen));
            request.setAttribute("hoeveelMensenPreviousValue", hoeveelMensen);
        } catch (IllegalArgumentException exc) {
            errors.add("hoeveelheid mensen is leeg");
        }
    }

    private void setNaam(Bezoeker nieuweBezoeker, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");
        try {
            nieuweBezoeker.setNaam(naam);
            request.setAttribute("naamPreviousValue", naam);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private String home(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("volgendeBezoeker", bezoekersDB.berekenVolgendeBezoeker());
        return "index.jsp";
    }
    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }
}
