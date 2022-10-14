import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class AlgShennon {
    static final int LANGUAGE_POWER = 32;
    public static void main(String[] args) throws FileNotFoundException {

        int hashLength = (int)(LANGUAGE_POWER/0.75);
        double shennonNumber;
        double overpower;
        double keyEntropy;

        FileReader file = new FileReader("input.txt");
        Scanner sc = new Scanner(file);
        String inputString = sc.nextLine();
        inputString = inputString.substring(0, inputString.indexOf(":"));
        char[] chars = inputString.toCharArray();

        HashMap <Character, Integer> hm = new HashMap<>(hashLength);
        for (char ch: chars){
            if(!hm.containsKey(ch)) hm.put(ch, 1);
            else hm.put(ch, hm.get(ch) + 1);
         }
        shennonNumber = getShennonNumber( hm, inputString.length());
        overpower = getOverpower( shennonNumber);
        keyEntropy = getShennonNumber(hm, inputString.length()) * inputString.length();
        double length = getLength(overpower,keyEntropy);
        printToFile(inputString,  shennonNumber,  overpower, keyEntropy, length);
        System.out.println(hm);
    }

    private static double getShennonNumber(HashMap<Character, Integer> hm, int length){
        double resultNumber = 0;
        Integer bufNumber;
        for (Map.Entry<Character, Integer> entry: hm.entrySet()){
            bufNumber = entry.getValue();
            double logNumber = (double)bufNumber/length;
            if(bufNumber!=0.0) {
                resultNumber -= logNumber * Math.log(logNumber) / Math.log(2);
            }

        }
        return resultNumber;
    }

    private static void printToFile (String s, double shennonNumber,
                                     double overpower, double keyEntropy, double length)
            throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("output.txt"));
        pw.println("String is \""+ s + "\"" );
        pw.println("Its Shennon number is " + shennonNumber);
        pw.println("Its overpower is " + overpower + " бит/симв.");
        pw.println("Key Entropy is " + keyEntropy);
        pw.println("Расстояние единственности:" +  length );
        pw.close();
    }

    private static double getOverpower( double shennon){
        double languageWorstPower = - Math.log((double)1/LANGUAGE_POWER)/Math.log(2);
        return languageWorstPower - shennon;
    }

    private static double getLength(double overpower, double keyEntropy){
        return (keyEntropy/overpower);
    }

}
