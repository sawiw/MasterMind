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
                liste.append(t[i]).append(")");
            } else liste.append(t[i]).append(",");
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
        int[] code = new int[lgCode];
        for (int i = 0; i < lgCode; i++) {
            code[i] = Ut.randomMinMax(0, nbCouleurs - 1);
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
            if (nbCoups == 0) {
                System.out.println("Saisissez la 1ère proposition de code : ");
                codMot = Ut.saisirChaine();
            } else {
                System.out.println("Saisissez la " + (nbCoups + 1) + "ème proposition de code :");
                codMot = Ut.saisirChaine();
            }

        } while (!codeCorrect(codMot, lgCode, tabCouleurs));

        // Convertir le code en un tableau d'entiers
        return motVersEntiers(codMot, tabCouleurs);
    }


    //____________________________________________________________

    /**
     * @Pré-requis cod1.length = cod2.length
     * @Résultat le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * @Exemple si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
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
     * @Exemple si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
     */
    public static int[] tabFrequence(int[] cod, int nbCouleurs) {

        int[] tabFrequence = new int[nbCouleurs];
        int i = 0;
        while (i < cod.length) {
            tabFrequence[cod[i]]++;
            i++;
        }
        return tabFrequence;
    }

    //____________________________________________________________

    /**
     * @Pré-requis les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     * @Résultat le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     * @Exemple si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
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
        int[] code = {0, 1, 2, 3};
        int nbEssais = 0;
        while (nbEssais < nbEssaisMax){
            System.out.println("Manche : " + numManche);
            System.out.println("Vous avez " + (nbEssaisMax - nbEssais) + " essais");
            int[] proposition = saisirCode(nbEssais, lgCode, tabCouleurs);
            int[] resultat = nbBienMalPlaces(code, proposition, tabCouleurs.length);
            System.out.println("Résultat : " + resultat[0] + " bien placé(s) et " + resultat[1] + " mal placé(s)");
            //Si le code est trouvé
            if (resultat[0] == lgCode) return nbEssais + 1;
            nbEssais++;
        }
        //Si le code n'est pas trouvé
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
        StringBuilder mot = new StringBuilder();
        for (int i = 0; i < cod.length; i++) {
            mot.append(tabCouleurs[cod[i]]);
        }
        return mot.toString();
    }

    //___________________________________________________________________

    /**
     * @Pré-requis rep.length = 2
     * @Action si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
     * les nombres de bien et mal placés resp.
     * @Résultat vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode) {
        if ((rep[0] >= 0 && rep[1] >= 0) && (rep[0] + rep[1] <= lgCode)) {
            return true;
        }
        System.out.println("La réponse n'est pas correcte. Vérifiez que les valeurs de rep[0] et rep[1] soient >= 0 et que leur somme soit <= lgCode");
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
        int[] rep = new int[lgCode];
        do {
            System.out.println("Entrez le nombre de bien placés");
            rep[0] = Ut.saisirEntier();
            System.out.println("Entrez le nombre de mal placés");
            rep[1] = Ut.saisirEntier();
        } while (!repCorrecte(rep, lgCode));
        return rep;
    }

    //___________________________________________________________________

    /**
     /**CHANGE : action si le code suivant n'existe pas
     //     *************************************************
     //    pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     //	   action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
     //    des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     //    sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     //    */
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs) {
        // Trouver l'indice de l'élément le plus à droite qui est différent de nbCouleurs - 1
        int i = cod1.length - 1;
        while (i >= 0 && cod1[i] == nbCouleurs - 1) {
            i--;
        }
        // Si aucun élément n'a été trouvé, cela signifie que le code ne peut pas être incrémenté
        if (i < 0) {
            return false;
        }
        // Incrémenter l'élément trouvé de 1 et mettre tous les éléments suivants à 0
        cod1[i]++;
        for (int j = i + 1; j < cod1.length; j++) {
            cod1[j] = 0;
        }
        return true;
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
        for (int i = 0; i < nbCoups; i++) {
            int[] rep1 = nbBienMalPlaces(cod1, cod[i], nbCouleurs);
            if (rep1[0] != rep[i][0] || rep1[1] != rep[i][1]) return false;
        }
        return true;
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
        do {
            if (!passeCodeSuivantLexico(cod1, nbCouleurs)) return false;
        } while (!estCompat(cod1, cod, rep, nbCoups, nbCouleurs));
        return true;
    }

    //___________________________________________________________________

    // manche Ordinateur

    /**
     * @Pré-requis numManche >= 2
     * @Action effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
     * (le pré-requis numManche ne sert que pour l'affichage)
     * @Résultat - 0 si le programme détecte une erreur dans les réponses du joueur humain
     * - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet),
     * s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     * - sinon le nombre de codes proposés par l'ordinateur
     */
    public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
        // Afficher le numéro de la manche
        System.out.println("Manche " + numManche + " : Humain (Codeur) vs Ordinateur (Décodeur)");
        // Initialiser les variables pour stocker les codes et les réponses de l'ordinateur
        int[][] cod = new int[nbEssaisMax][lgCode];
        int[][] rep = new int[nbEssaisMax][2];
        // Initialiser le 1er code de l'orderinateur à 0
        cod[0] = initTab(lgCode, 0);
        // Afficher le 1er code de l'ordinateur
        // Répéter les essais de l'ordinateur jusqu'à ce qu'il trouve le code secret
        int nbEssais = 0;
        while (nbEssais < nbEssaisMax) {
            // Afficher le code proposé par l'ordinateur
            System.out.println(entiersVersMot(cod[nbEssais], tabCouleurs));
            // Demander au joueur humain de saisir les réponses à l'essai de l'ordinateur
            int[] reponse = reponseHumain(lgCode);
            // Vérifier si la réponse est valide
            if (reponse == null || !repCorrecte(reponse, lgCode)) {
                // Retourner 0 si le joueur humain a saisi des réponses incorrectes
                return 0;
            }
            // Stocker la réponse du joueur humain
            rep[nbEssais] = reponse;
            // Vérifier si l'ordinateur a trouvé le code secret
            if (reponse[0] == lgCode) {
                // Retourner le nombre d'essais de l'ordinateur
                return nbEssais + 1;
            }
            // Passer au code suivant de l'ordinateur
            if (!passeCodeSuivantLexicoCompat(cod[nbEssais + 1], cod, rep, nbEssais + 1, tabCouleurs.length)) {
                // Retourner un nombre supérieur à nbEssaisMax si l'ordinateur n'a pas trouvé le code secret
                return nbEssaisMax + 1;
            }
            // Vérifier si le code suivant est valide
            if (cod[nbEssais] == null) {
                // Retourner un nombre supérieur à nbEssaisMax si l'ordinateur n'a pas trouvé le code secret au bout du nombre maximum d'essais
                return nbEssaisMax + 1;
            }
            // Incrémenter le nombre d'essais de l'ordinateur
            nbEssais++;
        }

        // Retourner le nombre d'essais de l'ordinateur si le code secret n'a pas été trouvé au bout du nombre maximum d'essais
        return nbEssais;
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
        int n;
        do {
            System.out.println("Veillez saisir un entier strictement positif");
            n = Ut.saisirEntier();
        } while (n <= 0);
        return n;
    }

    //___________________________________________________________________

    /**
     * @Pré-requis aucun
     * @Action demande au joueur humain de saisir un entier pair strictement positif,
     * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     * @Résultat l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif() {
        int n;
        do {
            System.out.println("Veuillez saisir un entier pair strictement positif");
            n = Ut.saisirEntier();
        } while (n <= 0 || n % 2 != 0);
        return n;

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
        // Saisir le nombre de couleurs
        int nbCouleurs = saisirEntierPositif();
        // Initialiser le tableau des initiales des noms de couleurs
        char[] tabCouleurs = new char[nbCouleurs];
        // Saisir les noms de couleurs
        for (int i = 0; i < nbCouleurs; i++) {
            // Saisir le nom de la couleur
            System.out.println("Veuillez saisir le nom de la couleur " + (i + 1));
            String nomCouleur = Ut.saisirChaine();
            // Vérifier si le nom de la couleur est valide
            if (nomCouleur.length() == 0 || nomCouleur.length() > 1) {
                // Afficher un message d'erreur
                System.out.println("Le nom de la couleur doit être une lettre");
                // Répéter la saisie de la couleur
                i--;
                continue;
            }
            // Stocker l'initiale du nom de la couleur
            tabCouleurs[i] = nomCouleur.charAt(0);
            // Vérifier si l'initiale du nom de la couleur est valide
            if (i > 0 && tabCouleurs[i] == tabCouleurs[i - 1]) {
                // Afficher un message d'erreur
                System.out.println("Les initiales des noms de couleurs doivent être différentes");
                // Répéter la saisie de la couleur
                i--;
                continue;
            }
        }
        // Retourner le tableau des initiales des noms de couleurs saisis
        return tabCouleurs;
    }

    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................


    /**
     * CHANGE ajout de le nombre d'essais maximum doit être strictement positif
     * *****************************************************************************
     *
     * @return
     * @Action demande à l'utilisateur de saisir les pré-requis ètres de la partie (lgCode, tabCouleurs,
     * nbManches, nbEssaisMax),
     * effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     * La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs.
     * Le nombre de manches doit être un nombre pair strictement positif.
     * Les initiales des noms de couleurs doivent être différentes.
     * Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */

    public static void main(String[] args) {
        int lgCode = 4;
        char[] tabCouleurs = {'V', 'B', 'J', 'R'};
        int numManche = 2;
        int nbEssaisMax = 20;

        // Cas où le code secret est généré aléatoirement
        int res = mancheOrdinateur(lgCode, tabCouleurs, numManche, nbEssaisMax);
        if (res > 0 && res <= nbEssaisMax) {
            System.out.println("La manche a été jouée avec succès en " + res + " essais");
        } else if (res == 0) {
            System.out.println("Erreur : le joueur humain a saisi des réponses incorrectes");
        } else {
            System.out.println("Erreur : le code secret n'a pas été trouvé au bout du nombre maximum d'essais");
        }

//        // Cas où le code secret est fixé manuellement
//        int[] codeSecret = {0, 1, 2, 3};
//        res = mancheOrdinateur(lgCode, tabCouleurs, numManche, nbEssaisMax);
//        if (res > 0 && res <= nbEssaisMax) {
//            System.out.println("La manche a été jouée avec succès en " + res + " essais");
//        } else if (res == 0) {
//            System.out.println("Erreur : le joueur humain a saisi des réponses incorrectes");
//        } else {
//            System.out.println("Erreur : le code secret n'a pas été trouvé au bout du nombre maximum d'essais");
//        }
    } // fin main

}
