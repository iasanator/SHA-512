import java.io.*;
import java.util.Scanner;

/**
 * Created by iassona on 5/5/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException{

        InputReader inputReader = new InputReader();
        Hasher hasher = new Hasher();

        String input = inputReader.getInput();
        String output = hasher.hash("abc");


        //System.out.println(Byte.valueOf("00001000"));

        //System.out.println(output);


        /*
        File file = new File("C:/Users/Nick/Desktop/SHA-512/project/src/input.txt");
        Scanner scanner = new Scanner(file);

        InputStream in = new FileInputStream("C:/Users/Nick/Desktop/SHA-512/project/src/input.txt");


        int a = 0;
        while (a != -1) {
            a = in.read();
            System.out.println(a);
        }
        */


    }

}
