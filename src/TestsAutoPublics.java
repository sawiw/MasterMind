//placer ce fichier dans le meme dossier que les *.java fournis par les étudiants, et lancer le main de ce fichier.

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.*;

import java.util.Arrays; 

public class TestsAutoPublics {
 
    public static void main(String[] args) {

	double note = 0;
	
	// Ici on lance les tests, si l'appels aux méthodes concernées déclenche une exception ou fait une boucle infinie, pas de pb, on passera qd même au suivant.
	
        note += runTest(TestsAutoPublics::testInitTab, "testInitTab",1); // Le dernier paramètre est le barême du test.
        note += runTest(TestsAutoPublics::testCopieTab, "testCopieTab",1);
	note += runTest(TestsAutoPublics::testListElem, "testListElem",1);
      	note += runTest(TestsAutoPublics::testPLusGrandIndice, "testPLusGrandIndice",1);
	note += runTest(TestsAutoPublics::testElemDiff, "testElemDiff",1);
	note += runTest(TestsAutoPublics::testSontEgaux, "testSontEgaux",1);
	note += runTest(TestsAutoPublics::testCodeAleat, "testCodeAleat",1);
	note += runTest(TestsAutoPublics::testCodeCorrect, "testCodeCorrect",1);
	note += runTest(TestsAutoPublics::testMotVersEntiers, "testMotVersEntiers",1);

	note += runTest(TestsAutoPublics::testNbBienPlaces, "testNbBienPlaces",1);
	note += runTest(TestsAutoPublics::testTabFrequence1, "testTabFrequence1",1);
	note += runTest(TestsAutoPublics::testNbCommuns1, "testNbCommuns1",1);
	note += runTest(TestsAutoPublics:: testNbBienMalPlaces1, "testNbBienMalPlaces1",1);
	
	note +=runTest(TestsAutoPublics::testRepCorrecte, "testRepCorrecte",1);
        note +=runTest(TestsAutoPublics::testPasseCodeSuivantLexico2, "testPasseCodeSuivantLexico2",1);
        note +=runTest(TestsAutoPublics::testEstCompat1, "testEstCompat1",1);
        note +=runTest(TestsAutoPublics::testPasseCodeSuivantLexicoCompat2, "testPasseCodeSuivantLexicoCompat2",1);
        
	System.out.println("fin des tests : note = " + note);
       
    }


    public static double runTest(Callable<Double> r, String s, int bareme){
	ExecutorService executorService = Executors.newSingleThreadExecutor(); //si on submit à nouveau sans re créer ça timeout aussi pour tests suivants
	Future<Double> future = executorService.submit(r);
	double note = 0;
	try {
	     
	    note+= future.get(1L, TimeUnit.SECONDS)*bareme; //get renvoie entre 0 et 1
	    System.out.println("****************************************************");
	    System.out.println(s + " terminé, note: " + note + "/" + bareme);
	    System.out.println("****************************************************");

	} catch (TimeoutException e) {
	    System.out.println("****************************************************");
	    System.out.println(s + " timeout");
	    System.out.println("****************************************************");
	} catch (InterruptedException | ExecutionException e) {
	    System.out.println("****************************************************");
	    System.out.println(s + " erreur " + e.getMessage());
	    System.out.println("****************************************************");
        }
	executorService.shutdownNow();
	return note;
    }

    
    private static double testInitTab() {

	int[] t = MM.initTab(10, -4);
	if (t.length == 10 && t[0] == -4 && t[9] == -4)
	    return 1;
	else
	    return 0;
    }

    private static double testCopieTab() {

	int[] t1 = {1, 2, 3, 4};
	int[] t2 = MM.copieTab(t1);
	if (t1 != t2 && Arrays.equals(t1,t2))
	    return 1;
	else
	    return 0;
    }

    private static double testListElem() {

	char [] ch = {'B', 'V'};
	String res = MM.listElem(ch);
	if (res.length() == 5)
	    return 1;
	else
	    return 0;
    }

    private static double testPLusGrandIndice() {
	char [] mot = {'R','o','u','l','o','n','s'};
	int i = MM.plusGrandIndice(mot, 'o');
	if (i == 4)
	    return 1;
	else
	    return 0;
    }

    private static double testElemDiff() {
	char [] mot = {'b','i','n','g','o'};
	if ( MM.elemDiff(mot) ) 
	    return 1;
	else
	    return 0;
    }

    private static double testSontEgaux() {
	int [] t1 = {1, 2, 3, 4};
	int [] t2 = {1, 2, 3, 5};
	
	if ( MM.sontEgaux(t1, t2) ) 
	    return 0;
	else
	    return 1;
    }

    private static double testCodeAleat() {
	int[] code = MM.codeAleat(1000, 6);
	for (int i = 0 ; i < 1000 ; i++) {
	    if ( ! (0 <= code[i] && code[i] <= 5) )
		return 0;
	}
	return 1;
    }

    private static double testCodeCorrect() {
	char[] tCoul = {'A','B','C'};
	if ( MM.codeCorrect("ABABCDA", 7, tCoul) )
	    return 0;
	else
	    return 1;
    }

    private static double testMotVersEntiers() {
	char[] tCoul = {'A','B','C'};
	int [] res = MM.motVersEntiers("ABAABA", tCoul);
	if ( res[0] == 0 && res[1] == 1 && res[2] == 0 && res[3] == 0 && res[4] == 1 && res[5] == 0 )
	    return 1;
	else
	    return 0;
    }

    private static double testNbBienPlaces() {

	int[] t1 = {1,0,2,0};
        int[] t2 = {0,1,0,0};

	int[] t3 = {2,2,2};
        int[] t4 = {2,2,2};

	int[] t5 = {0,1,2};
        int[] t6 = {1,2,0};

	int[] t7 = {0,1,0,1,1,0,1,0};
        int[] t8 = {1,1,1,0,0,0,1,0};

	double res = 0;
	if (MM.nbBienPlaces(t1,t2)  == 1)
	    res+=0.25;

	if (MM.nbBienPlaces(t3,t4)  == 3)
	    res+=0.25;
		
	if (MM.nbBienPlaces(t5,t6)  == 0)
	    res+=0.25;
		
	if (MM.nbBienPlaces(t7,t8)  == 4)
	    res+=0.25;

	return res;

    }

    private static double testTabFrequence1(){
	int[]t1 = {0,1,0,1};
	int nbc1 = 2;
	int[]res1= {2,2};

	int[]t2 = {0,1,0,1};
	int nbc2 = 3;
	int[]res2= {2,2,0};

	double res=0;
	if(Arrays.equals(MM.tabFrequence(t1,nbc1),res1))
	    res+=0.5;

	if(Arrays.equals(MM.tabFrequence(t2,nbc2),res2))
	    res+=0.5;

	return res;
    }



    private static double testNbCommuns1(){

	//tests avec longueur des tableaux supérieure à nb couleurs
	int[]t1a = {0,1,2};
	int[]t1b = {2,0,1};
	int nbc1 = 3;
	 
	int[]t2a = {0,0,1,1,2,2};
	int[]t2b = {2,2,2,2,2,2};
	int nbc2 = 3;

	int[]t3a = {0,0,0,0,0,0,0,1,1,2,2,2,2,2,2,2,2,2};
	int[]t3b = {1,1,1,1,1,2,2,2,0,0};
	int nbc3 = 3;

	int[]t4a = {0,0,0,0,0,0,0,1,1,2,2,2,2,2,2,2,2,2};
	int[]t4b = {0,0,0,0,0,0,0,1,1,2,2,2,2,2,2,2,2,2};
	int nbc4 = 3;

	double res = 0;
	if(MM.nbCommuns(t1a,t1b,nbc1)==3)
	    res+=0.25;
	if(MM.nbCommuns(t2a,t2b,nbc2)==2)
	    res+=0.25;
	if(MM.nbCommuns(t3a,t3b,nbc3)==7)
	    res+=0.25;
	if(MM.nbCommuns(t4a,t4b,nbc4)==t4a.length)
	    res+=0.25;

	return res;
    }

    private static double testNbBienMalPlaces1(){
	int[]t1a = {0,1,2};
	int[]t1b = {2,0,1};
	int nbc1 = 3;
	int[]res1 = {0,3};


	int[]t2a = {0,0,1,1,2,2};
	int[]t2b = {2,2,2,2,2,2};
	int nbc2 = 3;
	int[]res2 = {2,0};

	int[]t3a = {1,0,2,0};
	int[]t3b = {0,1,0,0};
	int nbc3 = 3;
	int[]res3 = {1,2};
	// x   x   x x     x x     x       x
	int[]t4a = {0,0,0,0,0,0,0,1,1,2,2,2,2,2,2,2,2,2};
	int[]t4b = {0,2,0,1,0,0,2,2,1,2,0,1,2,0,0,0,2,1};
	int nbc4 = 3;
	int[]res4 = {8,7};

	double res = 0;
	if(Arrays.equals(MM.nbBienMalPlaces(t1a,t1b,nbc1),res1))
	    res+=0.25;
	if(Arrays.equals(MM.nbBienMalPlaces(t2a,t2b,nbc2),res2))
	    res+=0.25;
	if(Arrays.equals(MM.nbBienMalPlaces(t3a,t3b,nbc3),res3))
	    res+=0.25;
	if(Arrays.equals(MM.nbBienMalPlaces(t4a,t4b,nbc4),res4))
	    res+=0.25;

	return res;
    }

    private static double testRepCorrecte() {

	int[] t1 = {0, 0};
	int[] t2 = {-1, 2};
	int[] t3 = {3, -1};
	int[] t4 = {1, 3};
	int note = 0;
	if (MM.repCorrecte(t1,3) & !MM.repCorrecte(t2,3) & !MM.repCorrecte(t3,3) & !MM.repCorrecte(t4,3))
	    return 1;
	else
	    return 0;
    }

    private static double testPasseCodeSuivantLexico2() {

	int[] t1 = {1, 2, 3, 3};
        int[] t2 = {1, 3, 0, 0};
	if (MM.passeCodeSuivantLexico(t1,4) && Arrays.equals(t1,t2))
	    return 1;
	else
	    return 0;
    }

    private static double testEstCompat1() {

	int[][] cod = {{0,0,0,0,0}};
	int[][] rep = {{0,0}};

	int[] t1 = {6,6,6,6,6};
        int[] t2 = {4,0,1,2,3};

	if (MM.estCompat(t1,cod,rep,1,7) && !MM.estCompat(t2,cod,rep,1,7))
	    return 1;
	else
	    return 0;
    }

    private static double testPasseCodeSuivantLexicoCompat2() {

	int[][] cod = {{0,0,0,0,0}, {0,1,1,1,1}};
	int[][] rep = {{1,0},       {2,1}};

	int[] t1 = {0,1,1,1,1};
        int[] t2 = {2,0,1,1,2};

	if (MM.passeCodeSuivantLexicoCompat(t1,cod,rep,2,3) && Arrays.equals(t1,t2))
	    return 1;
	else
	    return 0;
    }
    
} // end class
