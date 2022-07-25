package main;

import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.io.FileReader;
import java.util.Arrays;

import exceptions.CsvParsingException;

public class ParsedCsv {
    private List<String> header;

    private LinkedHashSet<String> content;

    final String delimiter = ",";
    Integer lineNumber = 1;
    private String path;

    public ParsedCsv(String path) {
        this.path = path;
        read(this.path);
    }

    public void read(String path) {
        try {
            this.content = new LinkedHashSet<>();
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
            reader.close();
            checkSize();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public LinkedHashSet<String> getContent() {
        return content;
    }

    public List<String> getHeader() {
        return header;
    }

    private void checkLine(String line) throws Exception {
        // System.out.println("checking line " + lineNumber);
        if (line == null) {
            throw new CsvParsingException("Error: Line " + lineNumber + " is not a valid CSV entry");
        } else if (line.isEmpty() || line.isBlank() || line.equals("\n")) {
            throw new CsvParsingException("Error: empty line detected at line " + lineNumber);
        } else if (header != null) {
            // check against header size, if header size exists
            List<String> entry = Arrays.asList(line.split(delimiter));
            if (entry.size() != header.size()) {
                throw new CsvParsingException("Error: Line " + lineNumber + " is not a valid CSV entry");
            } else {
                for (String e : entry) {
                    if (e.isBlank() || e.isEmpty() || e.equals("/n")) {
                        throw new CsvParsingException(
                                "Error: Line " + lineNumber + "contains invalid elements");
                    }
                }
            }
        }
    }

    private void checkSize() throws Exception {
        if (this.content.size() < 2) {
            throw new CsvParsingException("Error: Invald CSV file format");
        }
    }

}
