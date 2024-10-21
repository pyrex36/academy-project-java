package monopoly.io;

import monopoly.gameplay.Choice;
import monopoly.gameplay.Choices;

public interface IOHandler {
    void writeMessage(String message);

    void writeMessage(String message, boolean newLine);

    void writeChoices(Choices choice);

    Choice readChoice(Choices choice);
}
