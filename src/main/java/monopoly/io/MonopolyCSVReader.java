package monopoly.io;

import monopoly.gameplay.MonopolyProperty;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MonopolyCSVReader {
    public static List<MonopolyProperty> readPropertiesFromCSV(String fileName) {
        List<MonopolyProperty> properties = new ArrayList<>();
        String line;

        try (InputStream is = MonopolyCSVReader.class.getResourceAsStream("/" + fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                MonopolyProperty property = new MonopolyProperty(
                        Integer.parseInt(values[0]),  // costOfRent
                        Integer.parseInt(values[1]),  // costOfHouse
                        0,                            // numOfHouses
                        values[2],                    // name
                        Integer.parseInt(values[3])   // buybackRate
                );
                properties.add(property);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(127);
        }

        return properties;
    }

}
