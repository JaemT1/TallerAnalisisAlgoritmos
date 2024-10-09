package Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomNumberFileGenerator {
    public static void generateRandomNumbers(String fileName, int numNumbers) throws IOException {
        Random random = new Random();
        int minValue = 10000000;  // Mínimo 8 dígitos
        int maxValue = 99999999;  // Máximo 8 dígitos
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < numNumbers; i++) {
                int number = minValue + random.nextInt(maxValue - minValue + 1);
                writer.write(number + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        generateRandomNumbers("./numbersFiles/OneMillionNumbers.txt", 1000000);
    }
}