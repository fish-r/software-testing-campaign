import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.io.FileReader;
import java.util.Arrays;

public class CsvReader {
    List<String> header;

    public LinkedHashSet<String> read(String path) throws IOException {
        LinkedHashSet<String> content = new LinkedHashSet<>();
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            if (firstLine == null) {
                reader.close();
                throw new Exception("Error: Empty contents");
            }
            header = Arrays.asList(firstLine.split(","));
            // if empty
            String line;
            while ((line = reader.readLine()) != null) {
                checkLine(line);
                content.add(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return content;
    }

    private void checkLine(String line) throws Exception {
        String delimiter = ",";
        if (!line.contains(delimiter)) {
            throw new Exception("Error: Line is not a valid CSV entry.");
        } else if (line == "") {
            throw new Exception("Error: empty line detected");
        }
    }

}
