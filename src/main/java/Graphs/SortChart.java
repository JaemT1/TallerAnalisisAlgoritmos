package Graphs;

import Benchmarks.SortBenchmark;
import Utils.FileReaderUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class SortChart {

    public static void createBarChart(Map<String, Double> sortedResults) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        sortedResults.forEach((algorithm, time) -> {
            dataset.addValue(time, "Tiempo (s)", algorithm);
        });

        JFreeChart barChart = ChartFactory.createBarChart(
                "Tiempos de Ejecución de Algoritmos de Ordenamiento",
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
        int[] numbers = FileReaderUtil.readNumbersFromFile("./numbersFiles/OneMillionNumbers.txt");

        Map<String, Double> resultsSearch = SortBenchmark.benchmarkSearch(numbers);
        /*Map<String, Double> results = SortBenchmark.benchmarkSorts(numbers);


        // Ordenar los resultados de mayor a menor tiempo de ejecución
        Map<String, Double> sortedResults = results.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));  // Usa LinkedHashMap para preservar el orden de inserción

        System.out.println("Tiempos de ejecución de los algoritmos (en segundos):");
        sortedResults.forEach((algorithm, time) -> {
            System.out.printf("%s: %.6f segundos%n", algorithm, time);  // Imprimir con 6 decimales
        });*/

        Map<String, Double> sortedSearchResults = resultsSearch.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        System.out.println("Tiempos de ejecución de los algoritmos de búsqueda (en segundos):");
        sortedSearchResults.forEach((algorithm, time) -> {
            System.out.printf("%s: %.6f segundos%n", algorithm, time);  // Imprimir con 6 decimales
        });

        // Generar el gráfico
        createBarChart(sortedSearchResults);
    }
}