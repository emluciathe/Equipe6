import java.util.Scanner;
import java.util.Vector;

/**
 * Equipe6
 */
public class Equipe6 {

    public static void main(String[] args) {
        System.out.print("Entrer votre nom d'utilisateur : ");
        try {
            Scanner input = new Scanner(System.in);
            User utilisateur = new User(input.nextLine());
            System.out.println("Quel film compter-vous regarder ?");
            System.out.println("\n Appuyer sur {ENTRER} avec input vide si fini");

            /* On demande à l'utilisateur d'entrer ses films à regarder */
            boolean newFilmIsAdded = true;
            while (newFilmIsAdded) {
                System.out.print(" - ");
                String intitule = input.nextLine();
                if (!intitule.equals("")) {
                    utilisateur.ajoute_film(intitule);
                } else {
                    newFilmIsAdded = false;
                }
            }

            /* 2 options : donner un avis ou indiquer qu'on a vu le film */
            boolean newChoiceToMake = true;
            while (newChoiceToMake) {
                System.out.println("Que voulez vous faire : 'compléter' un film ou donner un avis");
                System.out.print("Entrer 'C' ou 'A' sinon Q pour quitter : ");
                String choice = input.nextLine();
                String intitule;
                if (choice.equals("C") || choice.equals("c")) {
                    System.out.print("Quel film avez-vous fini ? ");
                    intitule = input.nextLine();
                    utilisateur.completeFilm(intitule);
                    if (utilisateur.rienAVoir()) {
                        System.out.println("C'est fait ! Vous avez tout vu...");
                    } else {
                        System.out.println("Noté. Il vous reste encore des films à regarder !");
                    }
                } else if (choice.equals("Q") || choice.equals("q")) {
                    newChoiceToMake = false;
                    System.out.println("Voici votre liste :");
                    for (Film f : utilisateur.watchlist) {
                        System.out.println(f);
                    }
                } else {
                    System.out.print("Sur quel film voulez-vous donner un avis ? ");
                    intitule = input.nextLine();
                    System.out.println("Qu'en avez-vous pensé ?");
                    String avis = input.nextLine();
                    utilisateur.ajouteAvisSurFilm(intitule, avis);
                    System.out.println("C'est fait. Faut bien partager avec tout le monde !");
                }
            }
            input.close();
        } finally {

        }
    }
}

class User {
    String nom;
    Vector<Film> watchlist;

    public User(String nom) {
        this.nom = nom;
        this.watchlist = new Vector<>();
    }

    public void ajoute_film(String titre) {
        this.watchlist.add(new Film(titre));
    }

    public void completeFilm(String titre) {
        for (Film f : this.watchlist) {
            if (f.nom_film.equals(titre)) {
                f.complete();
            }
        }
    }

    public boolean rienAVoir() {
        for (Film f : watchlist) {
            if (!f.estComplete) {
                return false;
            }
        }
        return true;
    }

    public void ajouteAvisSurFilm(String titre, String avis) {
        for (Film f : watchlist) {
            if (f.nom_film.equals(titre)) {
                f.ajouteAvis(avis);
                return;
            }
        }
        System.out.println("Vous n'avez pas ce film dans votre liste");
    }
}

class Film {
    public String nom_film;
    public boolean estComplete;
    public String avis;

    public Film(String nom) {
        this.nom_film = nom;
        this.estComplete = false;
        this.avis = "";
    }

    public void complete() {
        this.estComplete = true;
    }

    public void ajouteAvis(String avis) {
        this.avis = avis;
    }

    @Override
    public String toString() {
        String regarde_repr;
        if (this.estComplete) {
            regarde_repr = "VU";
        } else {
            regarde_repr = "A REGARDER";
        }
        return nom_film + "    " + "[" + regarde_repr + "]" + "\n" + avis + "\n";
    }
}