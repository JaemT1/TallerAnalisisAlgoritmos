package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
}