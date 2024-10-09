package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileReaderUtil {
    public static int[] readNumbersFromFile(String fileName) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int readRandomLineFromFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        if (lines.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }

        // Seleccionar una línea aleatoria
        Random random = new Random();
        int randomIndex = random.nextInt(lines.size());
        return Integer.parseInt(lines.get(randomIndex));
    }
}