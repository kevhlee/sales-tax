package sales.tax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class UserInputReader {

    private final BufferedReader bufferedReader;
    private final PrintStream printStream;

    public UserInputReader(InputStream inputStream, PrintStream printStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.printStream = printStream;
    }

    public String promptInput(String prompt) throws IOException {
        printStream.print(prompt);
        return bufferedReader.readLine();
    }

    public int promptInteger(String prompt) throws IOException {
        return Integer.parseInt(promptInput(prompt));
    }

    public double promptDouble(String prompt) throws IOException {
        return Double.parseDouble(promptInput(prompt));
    }

    public boolean promptYesOrNo(String prompt) throws IOException {
        while (true) {
            String input = promptInput(prompt);

            if (input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n")) {
                return input.equals("Y") || input.equals("y");
            }
            else {
                printStream.print("Please enter 'Y' or 'N': ");
            }
        }
    }

}
