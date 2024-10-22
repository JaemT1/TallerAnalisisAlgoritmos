package Graphs;

import Benchmarks.Benchmark;
import Utils.FileReaderUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Chart {

    public static void createBarChart(Map<String, Double> sortedResults, String chartTitle) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        sortedResults.forEach((algorithm, time) -> {
            dataset.addValue(time, "Tiempo (s)", algorithm);
        });

        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Algoritmo",
                "Tiempo en segundos",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();

        // Cambia el rango máximo del eje Y para que todos los tiempos se vean mejor
        //NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setRange(0.0, 1.0); // Fija el máximo en 10 segundos

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        // Leer el archivo con los números
        int[] numbers = FileReaderUtil.readNumbersFromFile("./numbersFiles/TenThousandNumbers.txt");

        // Crear un diálogo para permitir al usuario elegir entre búsqueda y ordenamiento
        String[] options = {"Algoritmos de Búsqueda", "Algoritmos de Ordenamiento"};
        int choice = JOptionPane.showOptionDialog(null,
                "Seleccione el tipo de algoritmos que desea ejecutar:",
                "Selección de Algoritmos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) { // Opción: Algoritmos de Búsqueda
            // Ejecutar el benchmark de algoritmos de búsqueda
            Map<String, Double> resultsSearch = Benchmark.benchmarkSearch(numbers);

            // Ordenar los resultados de búsqueda de mayor a menor tiempo de ejecución
            Map<String, Double> sortedSearchResults = resultsSearch.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));

            // Imprimir los tiempos de ejecución de los algoritmos de búsqueda
            System.out.println("Tiempos de ejecución de los algoritmos de búsqueda (en segundos):");
            sortedSearchResults.forEach((algorithm, time) -> {
                System.out.printf("%s: %.6f segundos%n", algorithm, time);  // Imprimir con 6 decimales
            });

            // Generar gráfico para algoritmos de búsqueda
            createBarChart(sortedSearchResults, "Tiempos de Ejecución de Algoritmos de Búsqueda");

        } else if (choice == 1) { // Opción: Algoritmos de Ordenamiento
            // Ejecutar el benchmark de algoritmos de ordenamiento
            Map<String, Double> resultsSort = Benchmark.benchmarkSorts(numbers);

            // Ordenar los resultados de ordenamiento de mayor a menor tiempo de ejecución
            Map<String, Double> sortedSortResults = resultsSort.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));

            // Imprimir los tiempos de ejecución de los algoritmos de ordenamiento
            System.out.println("Tiempos de ejecución de los algoritmos de ordenamiento (en segundos):");
            sortedSortResults.forEach((algorithm, time) -> {
                System.out.printf("%s: %.6f segundos%n", algorithm, time);  // Imprimir con 6 decimales
            });

            // Generar gráfico para algoritmos de ordenamiento
            createBarChart(sortedSortResults, "Tiempos de Ejecución de Algoritmos de Ordenamiento");
        } else {
            System.out.println("Ninguna opción seleccionada. Finalizando...");
        }
    }
}
