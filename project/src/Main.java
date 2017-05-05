/**
 * Created by iassona on 5/5/2017.
 */
public class Main {

    public static void main(String[] args){

        InputReader inputReader = new InputReader();
        Hasher hasher = new Hasher();


        String input = inputReader.getInput();
        String output = hasher.hash(input);

        System.out.println(output);

    }

}
