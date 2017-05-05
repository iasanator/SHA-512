import java.util.Scanner;

/**
 * Created by iassona on 5/5/2017.
 */
public class InputReader {

    public InputReader() {

    }

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        String filePath;

        do {
            System.out.println("Please enter a valid filepath:");
            filePath = sc.nextLine();
        } while (!validateFilePath(filePath));

        return readBytes(filePath);


    }

    private boolean validateFilePath(String filePath) {
        return true;
    }

    private String readBytes(String filePath) {
        return "";
    }

}
