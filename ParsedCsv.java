import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class ParsedCsv {
    List<String> header;
    LinkedHashSet<String> content = new LinkedHashSet<>();
    final String delimiter = ",";

    public ParsedCsv(String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            if (firstLine == null) {
                reader.close();
                throw new Exception("Error: Empty contents.");
            }
            header = Arrays.asList(firstLine.split(","));
            String line;
            while ((line = reader.readLine()) != null) {
                checkLine(line);
                // List<String> lineAsArray = Arrays.asList(line.strip().split(delimiter));
                content.add(line.strip());
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void checkLine(String line) throws Exception {
        if (!line.contains(delimiter)) {
            throw new Exception("Error: Line is not a valid CSV entry.");
        } else if (line == "") {
            throw new Exception("Error: empty line detected.");
        }
    }

}
