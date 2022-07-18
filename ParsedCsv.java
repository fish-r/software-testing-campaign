
import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.io.FileReader;
import java.util.Arrays;

public class ParsedCsv {
    List<String> header;
    LinkedHashSet<String> content = new LinkedHashSet<>();
    final String delimiter = ",";
    Integer lineNumber = 1;

    public ParsedCsv(String path) {
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            checkLine(firstLine);
            header = Arrays.asList(firstLine.split(delimiter));
            String line;
            while ((line = reader.readLine()) != null) {
                checkLine(line);
                // List<String> lineAsArray = Arrays.asList(line.strip().split(delimiter));
                content.add(line.strip());
                lineNumber++;
            }
            if (content == null) {
                reader.close();
                throw new Exception("Error: CSV file is empty.");
            }
            reader.close();
            checkSize();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void checkLine(String line) throws Exception {
        System.out.println("checking line " + lineNumber);
        if (line == null) {
            throw new Exception("Error: Line" + lineNumber + " is not a valid CSV entry: " + line);
        } else if (line.isEmpty() || line.isBlank() || line.equals("\n")) {
            throw new Exception("Error: empty line detected at line " + lineNumber);
        } else if (header != null) {
            // check against header size, if header size exists
            List<String> entry = Arrays.asList(line.split(delimiter));
            if (entry.size() != header.size()) {
                throw new Error("Error: Line " + lineNumber + " is not a valid CSV entry: " + line);
            } else {
                for (String e : entry) {
                    if (e.isBlank() || e.isEmpty() || e.equals("/n")) {
                        throw new Exception("Error: Line " + lineNumber + "contains invalid elements: " + line);
                    }
                }
            }
        }
    }

    private void checkSize() throws Exception {
        if (this.content.size() < 2) {
            throw new Error("Error: Invald CSV file format.");
        }
    }

}
