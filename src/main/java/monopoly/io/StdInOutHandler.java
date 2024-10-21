package monopoly.io;

import monopoly.gameplay.Choice;
import monopoly.gameplay.Choices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StdInOutHandler implements IOHandler {

    public static final StdInOutHandler INSTANCE = new StdInOutHandler(System.out, System.in);

    private final OutputStream outputStream;
    private final InputStream inputStream;

    private StdInOutHandler(OutputStream outputStream, InputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    public void writeMessage(String message) {
        writeMessage(message, true);
    }

    @Override
    public void writeMessage(String message, boolean newLine) {
        try {
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            if (newLine) {
                outputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8)); // add new line after the message
            }
            outputStream.flush();
        } catch (IOException e) {
            writeMessage("Something went terrible wrong, exiting");
            System.exit(1);
        }
    }

    @Override
    public void writeChoices(Choices choice) {
        for (int i = 0; i < choice.choices().size(); i++) {
            String optionMessage = String.format("%d. %s", i + 1, choice.choices().get(i).name());
            writeMessage(optionMessage);
        }
    }

    @Override
    public Choice readChoice(Choices choice) {
        writeMessage("> ", false);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int choiceIndex = -1;
        while (choiceIndex == -1) {
            try {
                String input = reader.readLine();
                choiceIndex = Integer.parseInt(input) - 1;
                if (choiceIndex >= choice.choices().size()) {
                    writeMessage("Invalid choice, choose again.");
                    choiceIndex = -1;
                }
            } catch (IOException e) {
                writeMessage("Something went terrible wrong, exiting");
                System.exit(1);
            } catch (NumberFormatException e) {
                writeMessage("Invalid choice, choose again.");
            }
        }
        return choice.choices().get(choiceIndex);
    }
}
