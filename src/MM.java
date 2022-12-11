import java.util.Arrays;
import java.util.Random;

public class MM {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux

    /**
     * @Pré-requis :nb >= 0
     * @Résultat un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val) {
        int[] tab = new int[nb];
        for (int i = 0; i < nb; i++) {
            tab[i] = val;
        }
        return tab;
    }

    //______________________________________________

    /**
     * @Pré-requis aucun
     * @Résultat une copie de tab
     */
    public static int[] copieTab(int[] tab) {
        int[] tabCopie = new int[tab.length];
        System.arraycopy(tab, 0, tabCopie, 0, tab.length);
        return tabCopie;
    }

    //______________________________________________

    /**
     * @Pré-requis aucun
     * @Résultat la liste des éléments de t entre parenthèses et séparés par des virgules
     */
    public static String listElem(char[] t) {
        StringBuilder liste = new StringBuilder("(");
        for (int i = 0; i < t.length; i++) {
            if (i == t.length - 1) {
                liste.append(t[i]).append(')');
            } else liste.append(t[i]).append(',');
        }
        return liste.toString();
    }

    //______________________________________________

    /**
     * @Pré-requis aucun
     * @Résultat le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     */
    public static int plusGrandIndice(char[] t, char c) {
        int indexMax = -1;
        for (int i = 0; i < t.length; i++) {
            if (t[i] == c && i > indexMax) {
                indexMax = i;
            }
        }
        return indexMax;
    }
    //______________________________________________

    /**
     * @Pré-requis aucun
     * @Résultat vrai ssi c est un élément de t
     * @Stratégie utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c) {
        return plusGrandIndice(t, c) != -1;
    }

    //______________________________________________

    /**
     * @Action affiche un doublon et 2 de ses indices dans t s'il en existe
     * @Stratégie utilise la fonction plusGrandIndice
     * @Pré-requis aucun
     * @Résultat Vrai ssi les éléments de t sont différents
     */
    public static boolean elemDiff(char[] t) {
        for (int i = 0; i < t.length; i++) {
            int index = plusGrandIndice(t, t[i]);
            if (index != -1) {
                System.out.println("Le doublon " + t[i] + " est à l'indice " + i + " et à l'indice " + index);
                return true;
            }
        }
        return false;
    }

    //______________________________________________

    /**
     * @Pré-requis t1 .lenght == t2.lenght
     * @Résultat vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2) {
        for (int i = 0; i < t1.length; i++) {
            if (t1[i] != t2[i]) {
                return false;
            }
        }
        return true;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme @Pré-requis implicites sur les
    // @Pré-requisètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0
    // et les éléments de tabCouleurs sont différents
    // fonctions sur les codes pour la manche Humain

    /**
     * @Pré-requis aucun
     * @Résultat un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs) {
        Random aleatoire = new Random();
        int[] code = new int[lgCode];
        for (int i = 0; i < lgCode; i++) {
            code[i] = aleatoire.nextInt(nbCouleurs);
        }
        return code;
    }

    //____________________________________________________________

    /**
     * @Action si codMot n'est pas correct, affiche pourquoi
     * @Pré-requis aucun;
     * @Résultat vrai ssi codMot est correct, c'est-à-dire de longueur
     * lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
        // Vérifie si la longueur du code est correcte
        if (codMot.length() != lgCode) {
            System.out.println("Le code doit contenir " + lgCode + " couleurs.");
            return false;
        }

        // Vérifie si les éléments du code sont des couleurs valides
        for (int i = 0; i < codMot.length(); i++) {
            char c = codMot.charAt(i);
            boolean couleurValide = false;

            for (char tabCouleur : tabCouleurs) {
                if (c == tabCouleur) {
                    couleurValide = true;
                    break;
                }
            }

            if (!couleurValide) {
                System.out.println("La couleur " + c + " n'est pas valide.");
                return false;
            }
        }

        // Si on arrive ici, cela signifie que le code a la bonne longueur
        // et que les éléments du code sont des couleurs valides
        return true;
    }

    //____________________________________________________________

    /**
     * @Pré-requis les caractères de codMot sont des éléments de tabCouleurs
     * @Résultat le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
        int[] tabIntCodMot = new int[codMot.length()];

        // Parcourir le tableau de caractères et trouver l'index de chaque caractère dans le tableau tabCouleurs
        for (int i = 0; i < tabIntCodMot.length; i++) {
            for (int j = 0; j < tabCouleurs.length; j++) {
                if (codMot.charAt(i) == tabCouleurs[j]) {
                    tabIntCodMot[i] = j;
                    break;
                }
            }
        }

        // Renvoyer le tableau d'indices
        return tabIntCodMot;
    }


    //____________________________________________________________

    /**
     * @Action demande au joueur humain de saisir la (nbCoups + 1)ème proposition
     * de code sousforme de mot, avec re-saisie éventuelle jusqu'à ce
     * qu'elle soit correcte (le @Pré-requisètre nbCoups ne sert que pour l'affichage)
     * @Pré-requis aucun
     * @Résultat le code saisi sous forme de tableau d'entiers
     */
    public static int[] saisirCode(int nbCoups, int lgCode, char[] tabCouleurs) {
        // Variable pour stocker le code saisi
        String codMot;

        // Répéter jusqu'à ce que le code soit correct
        do {
            // Demander au joueur de saisir un code
            System.out.println("Saisissez la " + (nbCoups + 1) + "ème proposition de code :");
            codMot = Ut.saisirChaine();
        } while (!codeCorrect(codMot, lgCode, tabCouleurs));

        // Convertir le code en un tableau d'entiers
        return motVersEntiers(codMot, tabCouleurs);
    }


    //____________________________________________________________

    /**
     * @Pré-requis cod1.length = cod2.length
     * @Résultat le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * @Exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1, int[] cod2) {
        int bienPlaces = 0;
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) bienPlaces += 1;
        }
        return bienPlaces;
    }

    //____________________________________________________________

    /**
     * @Pré-requis les éléments de cod sont des entiers de 0 à nbCouleurs-1
     * @Résultat un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
     * @Exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {

        int[] tabFrequence = new int[nbCouleurs];
        for (int i = 0; i < cod.length; i++) {
            tabFrequence[cod[i]]++;
        }
        return tabFrequence;
    }

    //____________________________________________________________

    /**
     * @Pré-requis les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     * @Résultat le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     * @Exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
        int[] tabFrequence1 = tabFrequence(cod1, nbCouleurs);
        int[] tabFrequence2 = tabFrequence(cod2, nbCouleurs);
        int communs = 0;
        for (int i = 0; i <= nbCouleurs - 1; i++) {
            communs += Math.min(tabFrequence1[i], tabFrequence2[i]);
        }
        return communs;
    }

    //____________________________________________________________

    /**
     * @Pré-requis cod1.length=cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     *
     * @Résultat un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
     * se trouvant  (resp. ne se trouvant pas) au même indice
     *
     * @Exemple si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) 1 bien placé (le "0" à l'indice 3)
     * et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
        int bienPlaces = nbBienPlaces(cod1, cod2);
        int communs = nbCommuns(cod1, cod2, nbCouleurs);
        int malPlaces = communs - bienPlaces;
        return new int[]{bienPlaces, malPlaces};
    }


    //____________________________________________________________

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    /**
     * @Pré-requis numMache >= 1
     * @Action effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
     * @Pré-requis numManche ne sert que pour l'affichage
     * @Résultat - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par le joueur humain
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        int[] code = codeAleat(lgCode, tabCouleurs.length);
        int nbEssais = 0;
        while (nbEssais < nbEssaisMax){
            System.out.println("Manche : " + numManche);
            System.out.println("Essai n°" + (nbEssais + 1));
            int[] proposition = saisirCode(nbEssais, lgCode, tabCouleurs);
            int[] resultat = nbBienMalPlaces(code, proposition, tabCouleurs.length);
            System.out.println("Résultat : " + resultat[0] + " bien placé(s) et " + resultat[1] + " mal placé(s)");
            if (resultat[0] == lgCode) return nbEssais + 1;
            nbEssais++;
        }

        return nbEssais;
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /**
     * @Pré-requis les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
     * @Résultat le code cod sous forme de mot d'après le tableau tabCouleurs
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs) {

        return null;
    }

    //___________________________________________________________________

    /**
     * @Pré-requis rep.length = 2
     * @Action si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
     * les nombres de bien et mal placés resp.
     * @Résultat vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {

        return false;
    }

    //___________________________________________________________________

    /**
     * @Pré-requis aucun
     * @Action demande au joueur humain de saisir les nombres de bien et mal placés,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @Résultat les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode) {

        return new int[0];
    }

    //___________________________________________________________________

    /**
     * CHANGE @Action si le code suivant n'existe pas
     * ************************************************
     *
     * @Pré-requis les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     * @Action/@Résultat met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
     * des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {

        return false;
    }

    //___________________________________________________________________

    /**
     * CHANGE ajout du @Pré-requisètre cod1 et modification des spécifications
     * ********************************************************************
     *
     * @Pré-requis cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     * @Résultat vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
     * c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
     * propositions de cod seraient les nbCoups premières réponses de rep resp.
     */
    public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {

        return false;
    }

    //___________________________________________________________________

    /**
     * CHANGE renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
     * ajout du @Pré-requisètre cod1 et modification des spécifications
     * *************************************************************************
     *
     * @Pré-requis cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     * nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     * @Action/@Résultat met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
     * des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
     * cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
     * sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups, int nbCouleurs) {

        return false;
    }

    //___________________________________________________________________

    // manche Ordinateur

    /**
     * @Pré-requis numManche >= 2
     * @Action effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
     * (le @Pré-requisètre numManche ne sert que pour l'affichage)
     * @Résultat - 0 si le programme détecte une erreur dans les réponses du joueur humain
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {

        return lgCode;
    }

    //___________________________________________________________________

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................


    /**
     * @Pré-requis aucun
     * @Action demande au joueur humain de saisir un entier strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @Résultat l'entier strictement positif saisi
     */
    public static int saisirEntierPositif() {


        return 0;
    }

    //___________________________________________________________________

    /**
     * @Pré-requis aucun
     * @Action demande au joueur humain de saisir un entier pair strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @Résultat l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {

        return 0;
    }

    //___________________________________________________________________

    /**
     * @Pré-requis aucun
     * @Action demande au joueur humain de saisir le nombre de couleurs (stricement positif),
     * puis les noms de couleurs aux initiales différentes,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @Résultat le tableau des initiales des noms de couleurs saisis
     */
    public static char[] saisirCouleurs() {

        return new char[0];
    }

    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................


    /**
     * CHANGE ajout de le nombre d'essais maximum doit être strictement positif
     * *****************************************************************************
     *
     * @Action demande à l'utilisateur de saisir les pré-requis ètres de la partie (lgCode, tabCouleurs,
     * nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args) {
//        // Test 1 :
//        int lgCode = 4;
//        char[] tabCouleurs = {'R', 'B', 'V', 'J'};
//        int numManche = 1;
//        int nbEssaisMax = 10;
//        int resultatAttendu = 11;
//
//        int resultatObtenu = mancheHumain(lgCode, tabCouleurs, numManche, nbEssaisMax);
//
//        if (resultatAttendu == resultatObtenu)
//        {
//            System.out.println("Test 1 réussi");
//        }
//        else
//        {
//            System.out.println("Test 1 échoué");
//        }
//
//        // Test 2 :
//        lgCode = 5;
//        char[] tabCouleurs2 = {'R', 'B', 'V', 'J', 'G'};
//        numManche = 2;
//        nbEssaisMax = 15;
//        resultatAttendu = 16;
//
//        resultatObtenu = mancheHumain(lgCode, tabCouleurs2, numManche, nbEssaisMax);
//
//        if (resultatAttendu == resultatObtenu)
//        {
//            System.out.println("Test 2 réussi");
//        }
//        else
//        {
//            System.out.println("Test 2 échoué");
//        }
    } // fin main

}
