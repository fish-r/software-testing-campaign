package main;

import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.io.FileReader;
import java.util.Arrays;

import exceptions.CsvParsingException;

public class CsvParser {
    final String delimiter = ",";
    List<String> header;
    Integer lineNumber = 1;

    public CsvParser() {
    }

    public ParsedCsv read(String path) {
        LinkedHashSet<String> content = new LinkedHashSet<>();
        try {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                checkLine(line);
                if (lineNumber == 1) {
                    this.header = Arrays.asList(line.strip().split(delimiter));
                } else {
                    // line.replaceAll("\\s+", ""); // to remove all whitespaces
                    content.add(line.strip());
                    checkAgainstHeader(line);
                }
                lineNumber++;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        ParsedCsv parsedCsv = new ParsedCsv(content, header);
        return parsedCsv;
    }

    public List<String> getHeader() {
        return header;
    }

    public class ParsedCsv {

        private List<String> header;

        private LinkedHashSet<String> content;

        public ParsedCsv(LinkedHashSet<String> content, List<String> header) {
            this.content = content;
            this.header = header;
            try {
                checkSize();
            } catch (CsvParsingException e) {
                System.out.println(e);
            }
        };

        public List<String> getHeader() {
            return header;
        }

        public LinkedHashSet<String> getContent() {
            return content;
        }

        private void checkSize() throws CsvParsingException {
            if (this.content.size() < 2) {
                throw new CsvParsingException("Error: Invalid CSV file format");
            }
        }
    }

    private void checkLine(String line) throws CsvParsingException {
        // System.out.println("checking line " + lineNumber);
        if (line == null) {
            throw new CsvParsingException("Error: Line " + lineNumber + " is not a valid CSV entry");

        } else if (line.isEmpty() || line.isBlank() || line.equals("\n")) {
            throw new CsvParsingException("Error: empty line detected at line " + lineNumber);
        }
    }

    private void checkAgainstHeader(String line) throws CsvParsingException {
        List<String> entry = Arrays.asList(line.split(delimiter));
        if (entry.size() != header.size()) {
            throw new CsvParsingException("Error: Line " + lineNumber + " does not match with header");
        } else {
            for (String e : entry) {
                if (e.isBlank() || e.isEmpty() || e.equals("/n")) {
                    throw new CsvParsingException("Error: Line " + lineNumber + "contains invalid elements");
                }
            }
        }

    }
}
