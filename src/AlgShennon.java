import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlgShennon {
    public static void main(String[] args) throws FileNotFoundException {
        final int LANGUAGE_POWER = 32;
        int hashLength = (int)(LANGUAGE_POWER/0.75);
        FileReader file = new FileReader("input.txt");
        Scanner sc = new Scanner(file);
        String inputString = sc.nextLine();
        char[] chars = inputString.toCharArray();

        HashMap <Character, Integer> hm = languageInput(hashLength);
        for (char ch: chars){
            hm.put(ch, hm.get(ch) + 1);
        }

        printShennon(inputString, shennonNumber(hm, inputString.length()));
        System.out.println( shennonNumber(hm, inputString.length()) );
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

    private static double shennonNumber(HashMap<Character, Integer> hm, int length){
        double resultNumber = 0;
        int bufNumber;
        for (Map.Entry<Character, Integer> entry: hm.entrySet()){
            bufNumber = entry.getValue();
            if(bufNumber!=0) resultNumber -= bufNumber/length * Math.log(bufNumber/length);
        }
        return resultNumber;
    }

    private static void printShennon (String s, double number) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream("output.txt"));
        pw.println("String is \""+ s + "\"" );
        pw.println("Its Shennon number is " + number);
        pw.close();
    }

  /*  enum Alphabet {
        А, Б, В, Г, Д, Е, Ж, З, И, К, Л, М, Н, О, П, Р, С, Т, У, Ф, Х, Ц, Ч, Ш, Щ, Ъ, Ы, Ь, Э, Ю, Я;

        int count;

        Alphabet(){
            this.count = 0;
        }
    }
*/
}
