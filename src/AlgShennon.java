import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlgShennon {
    static final int LANGUAGE_POWER = 32;
    public static void main(String[] args) throws FileNotFoundException {

        int hashLength = (int)(LANGUAGE_POWER/0.75);
        double shennonNumber;
        double overpower;

        FileReader file = new FileReader("input.txt");
        Scanner sc = new Scanner(file);
        String inputString = sc.nextLine();
        char[] chars = inputString.toCharArray();


        HashMap <Character, Integer> hm = languageInput(hashLength);
        for (char ch: chars){
            hm.put(ch, hm.get(ch) + 1);
        }
        shennonNumber = getShennonNumber( hm, inputString.length());
        overpower = getOverpower(hm, shennonNumber);

        printToFile(inputString,  shennonNumber,  overpower);

    }

    private static HashMap<Character, Integer> languageInput(int mapSize){
        int count = 0;
        HashMap<Character, Integer> hm = new HashMap<>(mapSize);
        hm.put('А', count);
        hm.put('Б', count);
        hm.put('В', count);
        hm.put('Г', count);
        hm.put('Д', count);
        hm.put('Е', count);
        hm.put('Ж', count);
        hm.put('З', count);
        hm.put('И', count);
        hm.put('К', count);
        hm.put('Л', count);
        hm.put('М', count);
        hm.put('Н', count);
        hm.put('О', count);
        hm.put('П', count);
        hm.put('Р', count);
        hm.put('С', count);
        hm.put('Т', count);
        hm.put('У', count);
        hm.put('Ф', count);
        hm.put('Х', count);
        hm.put('Ц', count);
        hm.put('Ш', count);
        hm.put('Щ', count);
        hm.put('Ъ', count);
        hm.put('Ь', count);
        hm.put('Ы', count);
        hm.put('Э', count);
        hm.put('Ю', count);
        hm.put('Я', count);
        hm.put(' ', count);

        return hm;
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

    private static void printToFile (String s, double shennonNumber, double overpower) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("output.txt"));
        pw.println("String is \""+ s + "\"" );
        pw.println("Its Shennon number is " + shennonNumber);
        pw.println("Its overpower is " + overpower + " бит/симв.");
        pw.close();
    }

    private static double getOverpower(HashMap<Character, Integer> hm, double shennon){
        double languageWorstPower = - Math.log((double)1/LANGUAGE_POWER)/Math.log(2);
        return languageWorstPower - shennon;
    }

}
