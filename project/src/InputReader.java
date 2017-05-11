import java.io.File;
import java.io.IOException;
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
            System.out.println("Please enter a valid input filepath:");
            filePath = sc.nextLine();
        } while (!validateFilePath(filePath));

        return readString(filePath);


    }

    private boolean validateFilePath(String filePath) {
        return new File(filePath).exists();
    }

    private String readString(String filePath){

        StringBuilder builder = new StringBuilder();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while(scanner.hasNext()) {
                builder.append(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

}
