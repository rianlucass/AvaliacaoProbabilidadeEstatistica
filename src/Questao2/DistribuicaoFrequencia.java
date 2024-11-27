import java.util.ArrayList;
import java.util.List;

public class DistribuicaoFrequencia {

    public static void main(String[] args) {
        // Exemplo de dados
        int[] dados = {11, 12, 13, 15, 17, 19, 20, 21, 23, 25, 27, 28, 29, 30, 32, 33, 34, 35, 37, 39};

        // Parâmetros da tabela de frequência
        int numeroClasses = 6; // Número de classes
        int amplitude = 5;     // Amplitude de cada classe

        // Construir e exibir a tabela de frequência
        construirTabela(dados, numeroClasses, amplitude);
    }

    public static void construirTabela(int[] dados, int numeroClasses, int amplitude) {
        // Encontrar o valor mínimo para definir o limite inferior da primeira classe
        int valorMinimo = encontrarMinimo(dados);
        int limiteInferiorClasse = valorMinimo;

        System.out.println("Classe       | Frequência");
        System.out.println("-------------------------");

        // Construir cada classe e calcular a frequência
        for (int i = 0; i < numeroClasses; i++) {
            int limiteSuperiorClasse = limiteInferiorClasse + amplitude - 1;

            // Calcular a frequência dos valores dentro da classe
            int frequencia = calcularFrequencia(dados, limiteInferiorClasse, limiteSuperiorClasse);

            // Exibir a classe e sua frequência
            System.out.printf("%d - %d    | %d\n", limiteInferiorClasse, limiteSuperiorClasse, frequencia);

            // Ajustar o limite inferior para a próxima classe
            limiteInferiorClasse += amplitude;
        }
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
