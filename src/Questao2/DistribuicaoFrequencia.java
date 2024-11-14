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
}
