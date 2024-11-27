package Questao2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class DistribuicaoFrequencia extends JFrame {

    static int[][] distribuicao;

    public DistribuicaoFrequencia() {
        setTitle("Distribuição de Frequência");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JFreeChart barChart = criarGrafico();
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        int[] dados = {61, 65, 43, 53, 55, 51, 58, 55, 59, 56,
                       52, 53, 62, 49, 68, 51, 50, 67, 62, 64,
                       53, 56, 48, 60, 61, 44, 54, 63, 53, 55,
                       48, 54, 57, 41, 54, 71, 57, 53, 56, 48,
                       55, 46, 57, 54, 48, 63, 55, 52, 52, 51};

        int numeroClasses = 6;
        int amplitude = 5;

        construirTabela(dados, numeroClasses, amplitude);

        DistribuicaoFrequencia dist = new DistribuicaoFrequencia();

        System.out.println("\nModa: " + dist.calcularModa());
        System.out.println("Média: " + dist.calcularMedia());
        System.out.println("Mediana: " + dist.calcularMediana());
        System.out.println("Desvio padrão: " + dist.calcularDesvioPadrao());

        System.out.println("1º Quartil (Q1): " + dist.calcularPercentil(25));
        System.out.println("3º Decil (D3): " + dist.calcularPercentil(30));
        System.out.println("7º Decil (D7): " + dist.calcularPercentil(70));
        System.out.println("15º Percentil (P15): " + dist.calcularPercentil(15));
        System.out.println("90º Percentil (P90): " + dist.calcularPercentil(90));
        dist.exibirGrafico();
    }

    public static void construirTabela(int[] dados, int numeroClasses, int amplitude) {
        int valorMinimo = encontrarMinimo(dados);
        int limiteInferiorClasse = valorMinimo;

        distribuicao = new int[numeroClasses][3];

        System.out.println("-----------------");
        System.out.println("Classe  | Fi");
        System.out.println("--------|--------");

        for (int i = 0; i < numeroClasses; i++) {
            int limiteSuperiorClasse = limiteInferiorClasse + amplitude - 1;
            int frequencia = calcularFrequencia(dados, limiteInferiorClasse, limiteSuperiorClasse);

            distribuicao[i][0] = limiteInferiorClasse;
            distribuicao[i][1] = limiteSuperiorClasse;
            distribuicao[i][2] = frequencia;

            System.out.printf("%d - %d | %d\n", limiteInferiorClasse, limiteSuperiorClasse, frequencia);

            limiteInferiorClasse += amplitude;
        }
        System.out.println("--------|--------");
    }

    public static int calcularFrequencia(int[] dados, int limiteInferior, int limiteSuperior) {
        int frequencia = 0;
        for (int valor : dados) {
            if (valor >= limiteInferior && valor <= limiteSuperior) {
                frequencia++;
            }
        }
        return frequencia;
    }

    public static int encontrarMinimo(int[] dados) {
        int minimo = dados[0];
        for (int valor : dados) {
            if (valor < minimo) {
                minimo = valor;
            }
        }
        return minimo;
    }

    public void exibirGrafico() {
        setVisible(true);
    }

    public JFreeChart criarGrafico() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < distribuicao.length; i++) {
            String intervalo = distribuicao[i][0] + " - " + distribuicao[i][1];
            int frequencia = distribuicao[i][2];
            dataset.addValue(frequencia, "Frequência", intervalo);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Distribuição de Frequência",
                "Intervalo",
                "Frequência",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    public double calcularModa() {
        int indiceClasseModal = 0;
        int f_m = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            if (distribuicao[i][2] > f_m) {
                f_m = distribuicao[i][2];
                indiceClasseModal = i;
            }
        }

        int L = distribuicao[indiceClasseModal][0];
        int f_a = (indiceClasseModal > 0) ? distribuicao[indiceClasseModal - 1][2] : 0;
        int f_p = (indiceClasseModal < distribuicao.length - 1) ? distribuicao[indiceClasseModal + 1][2] : 0;
        int h = distribuicao[indiceClasseModal][1] - distribuicao[indiceClasseModal][0];

        return L + ((f_m - f_a) / (double)(2 * f_m - f_a - f_p)) * h;
    }

    public double calcularMedia() {
        double somaProduto = 0;
        int somaFrequencia = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            int limiteInferior = distribuicao[i][0];
            int limiteSuperior = distribuicao[i][1];
            int fi = distribuicao[i][2];

            double xi = (limiteInferior + limiteSuperior) / 2.0;

            somaProduto += xi * fi;
            somaFrequencia += fi;
        }

        return somaProduto / somaFrequencia;
    }

    public double calcularMediana() {
        int N = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            N += distribuicao[i][2];
        }

        int posicaoMediana = N / 2;

        int F = 0;
        int L = 0;
        int f = 0;
        int h = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            F += distribuicao[i][2];

            if (F >= posicaoMediana) {
                L = distribuicao[i][0];
                f = distribuicao[i][2];
                h = distribuicao[i][1] - distribuicao[i][0];
                F -= f;
                break;
            }
        }

        return L + ((posicaoMediana - F) / (double)f) * h;
    }

    public double calcularDesvioPadrao() {
        double media = calcularMedia();
        double somaQuadrados = 0;
        int somaFrequencia = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            int limiteInferior = distribuicao[i][0];
            int limiteSuperior = distribuicao[i][1];
            int fi = distribuicao[i][2];

            double xi = (limiteInferior + limiteSuperior) / 2.0;

            somaQuadrados += fi * Math.pow(xi - media, 2);
            somaFrequencia += fi;
        }
        return Math.sqrt(somaQuadrados / somaFrequencia);
    }

    public double calcularPercentil(double percentil) {
        int N = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            N += distribuicao[i][2];
        }

        double posicaoPercentil = percentil * N / 100.0;

        int F = 0;
        int L = 0;
        int f = 0;
        int h = 0;

        for (int i = 0; i < distribuicao.length; i++) {
            F += distribuicao[i][2];

            if (F >= posicaoPercentil) {
                L = distribuicao[i][0];
                f = distribuicao[i][2];
                h = distribuicao[i][1] - distribuicao[i][0];
                F -= f;
                break;
            }
        }

        return L + ((posicaoPercentil - F) / f) * h;
    }
}
