package org.example;

import java.time.LocalDate;

public class Main {
    public static boolean moisA31Jours(int numeroMois) {
        return (numeroMois == 1 || numeroMois == 3 || numeroMois == 5 ||
                numeroMois == 7 || numeroMois == 8 || numeroMois == 10 || numeroMois == 12);
    }

    public static boolean fevrierA28ou29Jours(int annee) {
        return (annee % 4 == 0 && (annee % 100 != 0 || annee % 400 == 0));
    }

    public static LocalDate getDateEcheance(ModeReglement modeReglement, LocalDate dateDeFacture) {
        LocalDate dateEcheance = dateDeFacture;
        if (modeReglement.isEstReception()) {
            dateEcheance = dateDeFacture;
        } else {
            if (modeReglement.getPrecesionParMoins() > 0) {
                if (modeReglement.getPrecesionParMoins() == 30) {
                    if (dateEcheance.getMonthValue() == 12) {
                        dateEcheance = dateEcheance.withMonth(1);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    } else if (moisA31Jours(dateEcheance.getMonthValue()) && dateEcheance.getMonthValue() != 12) {
                        dateEcheance = dateEcheance.plusMonths(1);
                        dateEcheance = dateEcheance.withDayOfMonth(30);
                    } else if (dateEcheance.getMonthValue() == 1 && fevrierA28ou29Jours(dateEcheance.getYear())) {
                        dateEcheance = dateEcheance.withMonth(2);
                        dateEcheance = dateEcheance.withDayOfMonth(29);
                    } else if (dateEcheance.getMonthValue() == 1 && !fevrierA28ou29Jours(dateEcheance.getYear())) {
                        dateEcheance = dateEcheance.withMonth(2);
                        dateEcheance = dateEcheance.withDayOfMonth(28);
                    } else if (!moisA31Jours(dateEcheance.getMonthValue()) && dateEcheance.getMonthValue() != 12) {
                        dateEcheance = dateEcheance.plusMonths(1);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    }
                }
                if (modeReglement.getPrecesionParMoins() == 60) {
                    if (dateEcheance.getMonthValue() == 11) {
                        dateEcheance = dateEcheance.withMonth(1);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    }
                    else if (dateEcheance.getMonthValue() == 12 && fevrierA28ou29Jours(dateEcheance.getYear() + 1)) {
                        dateEcheance = dateEcheance.withMonth(2);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(29);
                    }
                    else if (dateEcheance.getMonthValue() == 12 && !fevrierA28ou29Jours(dateEcheance.getYear() + 1)) {
                        dateEcheance = dateEcheance.withMonth(2);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(28);
                    }
                    else if (moisA31Jours(dateEcheance.getMonthValue())) {
                        dateEcheance = dateEcheance.plusMonths(2);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    }
                    else {
                        dateEcheance = dateEcheance.plusMonths(2);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    }
                }
                if (modeReglement.getPrecesionParMoins() == 90) {
                    if (dateEcheance.getMonthValue() == 10) {
                        dateEcheance = dateEcheance.withMonth(1);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    } else if (dateEcheance.getMonthValue() == 11 && fevrierA28ou29Jours(dateEcheance.getYear() + 1)) {
                        dateEcheance = dateEcheance.withMonth(2);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(29);
                    } else if (dateEcheance.getMonthValue() == 11 && !fevrierA28ou29Jours(dateEcheance.getYear() + 1)) {
                        dateEcheance = dateEcheance.withMonth(2);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(28);
                    } else if (dateEcheance.getMonthValue() == 12) {
                        dateEcheance = dateEcheance.withMonth(3);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    } else if (moisA31Jours(dateEcheance.getMonthValue())) {
                        dateEcheance = dateEcheance.plusMonths(3);
                        dateEcheance = dateEcheance.withDayOfMonth(30);
                    } else {
                        dateEcheance = dateEcheance.plusMonths(3);
                        dateEcheance = dateEcheance.withDayOfMonth(31);
                    }
                }
            }
                if (modeReglement.getPrecesionParJourValeur() > 0) {
                    /* le type de precision par jour net */
                    if (modeReglement.getPrecesionParJourType().equals("JN")) {
                        dateEcheance = dateEcheance.plusDays(modeReglement.getPrecesionParJourValeur());
                    }
                    /* le type de precision par jour net et fin de mois */
                    else if (modeReglement.getPrecesionParJourType().equals("JNFM")) {
                        if(dateEcheance.getMonthValue() == 12 && (dateEcheance.getDayOfMonth() + modeReglement.getPrecesionParJourValeur())>31) {
                            dateEcheance = dateEcheance.withMonth(1);
                            dateEcheance = dateEcheance.withDayOfMonth(31);
                            dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        }
                        else if(dateEcheance.getMonthValue() == 1 && (dateEcheance.getDayOfMonth() + modeReglement.getPrecesionParJourValeur())>31 && fevrierA28ou29Jours(dateEcheance.getYear())){
                            dateEcheance = dateEcheance.withMonth(2);
                            dateEcheance = dateEcheance.withDayOfMonth(29);
                        }else if(dateEcheance.getMonthValue() == 1 && (dateEcheance.getDayOfMonth() + modeReglement.getPrecesionParJourValeur())>31 && !fevrierA28ou29Jours(dateEcheance.getYear())){
                            dateEcheance = dateEcheance.withMonth(2);
                            dateEcheance = dateEcheance.withDayOfMonth(28);
                        }
                        else if(moisA31Jours(dateEcheance.getMonthValue()) && (dateEcheance.getDayOfMonth() + modeReglement.getPrecesionParJourValeur())>31 ){
                            dateEcheance = dateEcheance.plusMonths(1);
                            dateEcheance = dateEcheance.withDayOfMonth(30);
                        }else if (!moisA31Jours(dateEcheance.getMonthValue()) && (dateEcheance.getDayOfMonth() + modeReglement.getPrecesionParJourValeur())>30){
                            dateEcheance = dateEcheance.plusMonths(1);
                            dateEcheance = dateEcheance.withDayOfMonth(31);
                        }else if(moisA31Jours(dateEcheance.getMonthValue()) ){
                            dateEcheance = dateEcheance.withDayOfMonth(31);
                        }else if(dateEcheance.getMonthValue() == 2 && fevrierA28ou29Jours(dateEcheance.getYear())) {
                            dateEcheance = dateEcheance.withDayOfMonth(29);
                        }else if(dateEcheance.getMonthValue() == 2 && !fevrierA28ou29Jours(dateEcheance.getYear())){
                            dateEcheance = dateEcheance.withDayOfMonth(28);
                        }else {
                            dateEcheance = dateEcheance.withDayOfMonth(30);
                        }
                    }
                    /* le type de precision par fin de mois plus jour nets */
                    else if(modeReglement.getPrecesionParJourType().equals("FMJN")){
                        if(moisA31Jours(dateEcheance.getMonthValue())) {
                            dateEcheance = dateEcheance.withDayOfMonth(31);
                        } else if (dateEcheance.getMonthValue() == 2 && fevrierA28ou29Jours(dateEcheance.getYear())) {
                            dateEcheance = dateEcheance.withDayOfMonth(29);
                        }else if(dateEcheance.getMonthValue() == 2 &&!fevrierA28ou29Jours(dateEcheance.getYear())){
                            dateEcheance = dateEcheance.withDayOfMonth(28);
                        }else{
                            dateEcheance = dateEcheance.withDayOfMonth(30);
                        }
                        if(dateEcheance.getMonthValue() == 12){
                            dateEcheance = dateEcheance.withMonth(1);
                            dateEcheance = dateEcheance.withDayOfMonth(modeReglement.getPrecesionParJourValeur());
                            dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        }else{
                            dateEcheance = dateEcheance.plusMonths(1);
                            dateEcheance = dateEcheance.withDayOfMonth(modeReglement.getPrecesionParJourValeur());
                        }
                    }
                }
                /* mode reglement le jour de mois */
                if(modeReglement.getLeJourMois()>0){
                    if(dateEcheance.getDayOfMonth() > modeReglement.getLeJourMois() && dateEcheance.getMonthValue() == 12){
                        dateEcheance = dateEcheance.withMonth(1);
                        dateEcheance = dateEcheance.withYear(dateEcheance.getYear() + 1);
                        dateEcheance = dateEcheance.withDayOfMonth(modeReglement.getLeJourMois());
                    }
                    else if(dateEcheance.getDayOfMonth() > modeReglement.getLeJourMois()){
                        dateEcheance = dateEcheance.plusMonths(1);
                        dateEcheance = dateEcheance.withDayOfMonth(modeReglement.getLeJourMois());
                    }else {
                        dateEcheance = dateEcheance.withDayOfMonth(modeReglement.getLeJourMois());
                    }
                }

        }
        return dateEcheance;
    }
        public static void main (String[]args){
            ModeReglement modeReglement = new ModeReglement(false,0,0,"",29);
            LocalDate dateFacture = LocalDate.of(2023,12,27);
            LocalDate dateEcheance = getDateEcheance(modeReglement,dateFacture);
            System.out.println("dateEcheance : "+ dateEcheance);
        }

}
