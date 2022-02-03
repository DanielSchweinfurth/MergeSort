import java.util.*;
//IMPORTS

public class MergeSort{

    //MAIN
    public static void main(String[] args) {
        int[] feldToSort = new int[100];//Feld zum Sortieren
        randomFill(feldToSort);

        ausgebenFeld(feldToSort);
        System.out.println("unsortiertes Feld");
        dots(feldToSort);
        System.out.println();
        System.out.println();
        ausgebenFeld(mergeSort(feldToSort));
        dots(feldToSort);

    }

    //VARIABLEN
    int counter = 0;

    //CONSTRUCTORS

    //METHODEN
    public static int[] mergeSort(int[] feldSort) {//Übergabe feld das sortiert werden soll
        if (feldSort.length <= 1) { //Prüfen ob das Array mehr als ein Element hat
            return feldSort;
        }

        int haelfte = feldSort.length/2; //Mitte der Arrays finden

        int[] links = new int[haelfte];//Linke hälfte die Mitte geben
        int[] rechts;//Rechts initialisieren

        if (feldSort.length % 2 == 0) { //Prüfen ob wir gerade teilen können
            rechts = new int[haelfte];//Wenn ja, toll dann andere Hälfte auch Mitte
        }else{
            rechts = new int[haelfte + 1];//Wenn nicht, dann ist es die Länge der Hälfte und das Element was übrig bleibt
        }

        for (int i = 0; i < links.length; i++) {//Den Linken Array füllem mit der ersten Hälfte
            links[i] = feldSort[i];
        }
        dots(links);//VISUELL
        System.out.println("Trennung");


        for (int i = 0; i < rechts.length; i++) {//Rechten Array füllen mit dem zweiten Teil der Zahlen
            rechts[i] = feldSort[haelfte + i]; // ab der Hälfte des origonal Array füllen um Dopplung zu vermeiden
        }
        dots(rechts);//VISUELL
        System.out.println("Trennung");

        int[] ergebnis = new int[feldSort.length];// Initialisieren des Ergebnis feldes, welches dann zurück gegeben wird

        links = mergeSort(links);// rekursiver Aufruf linken haelfte und damit teilen
        rechts = mergeSort(rechts);//rekursiver Aufruf der rechten haelfte und damit teilen von rechts

        ergebnis = merge(links, rechts);// mergen der beiden nun geteilten Hälten, durch die Rekursion wird vom kleinsten Teil bis zum größten geteilt

        return ergebnis;// gibt den sortierten Array zurück
    }


    private static int[] merge(int [] links, int[] rechts) {

        int[] ergebnis = new int[links.length + rechts.length]; //Länge des ergebnis Arrays festlegen

        int linksZeiger = 0;
        int rechtsZeiger = 0;
        int ergebnisZeiger = 0;

        while (linksZeiger < links.length || rechtsZeiger < rechts.length) { //Nur mergen, wenn die listen auch etwas in sich haben, also rechts und links größer als 0 sind
            
            if (linksZeiger < links.length && rechtsZeiger < rechts.length) {//Wenn der rechte und Linke Array Elemente in sich haben müssen wir sie vergleichen
                
                if (links[linksZeiger] < rechts[rechtsZeiger]) {//wenn das Element im Liken Array kleiner ist als das Elememt im Rechten:
                    ergebnis[ergebnisZeiger++] = links[linksZeiger++];//Nun fügen wir das kleinste Element, wenn es in dem Linken array ist in die Ergebnis Liste ein, und erhöhen den Pointer, an dem wir das Element vergleichen/ einfügen für den nächsten Aufruf
                    
                }else{
                    ergebnis[ergebnisZeiger++] = rechts[rechtsZeiger++];
                }

            }
            
            else if(linksZeiger < links.length){
                ergebnis[ergebnisZeiger++] = links[linksZeiger++];
            }
            
            else if(rechtsZeiger < rechts.length){
                ergebnis[ergebnisZeiger++] = rechts[rechtsZeiger++];
            }
        }

        return ergebnis;        
    }


    private static void randomFill(int[] feldToFill){
        Random zufall = new Random();
        for (int i = 0; i < feldToFill.length; i++) {
            feldToFill[i] = zufall.nextInt(100);
        }
    }




    private static void ausgebenFeld(int[] feldToAusgeben) {
        for (int i = 0; i < feldToAusgeben.length; i++) {
            System.out.print(feldToAusgeben[i] + ",");
            
        }
    }


    public static void dots(int[] zahlen) {
        for (int i = 0; i < zahlen.length; i++) {
            if (zahlen[i] == 0) {
                System.out.println("-");
            }else{
                for (int j = 0; j < zahlen[i]-1; j++) {
                        System.out.print(".");
                }
            System.out.println(".");
            }
        }
    }

    public static void ZeitMessenInSekunden(){
       long start_time = System.nanoTime();
       //HIER DIE METHODE EINGEBEN VON DER MAN DIE ZEIT BRAUCHT

       long end_time = System.nanoTime();
   
       System.out.println("Der Methodenaufruf hat: " + (end_time - start_time)/1000000 + " Millisekunden gedauert.");
       System.out.println("Laufzeit ist in allen cases N * log(N)");
    }
    


}
