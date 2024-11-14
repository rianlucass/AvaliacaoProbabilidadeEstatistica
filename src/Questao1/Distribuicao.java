public class Distribuicao {

    static int[][] distribuicao = {{300, 400, 14},
                                   {400, 500, 46},
                                   {500, 600, 48},
                                   {600, 700, 76},
                                   {700, 800, 68},
                                   {800, 900, 62},
                                   {900, 1000, 48},
                                   {1000, 1100, 22},
                                   {1100, 1200, 6}};

    public static void main(String[] args) {
        Distribuicao dist = new Distribuicao();

        System.out.println("  Idade   | fi ");
        System.out.println("----------|----");
        System.out.println(distribuicao[0][0] + " - " + distribuicao[0][1] + " | " + distribuicao [0][2]);
        System.out.println(distribuicao[1][0] + " - " + distribuicao[1][1] + " | " + distribuicao [1][2]);
        System.out.println(distribuicao[2][0] + " - " + distribuicao[2][1] + " | " + distribuicao [2][2]);
        System.out.println(distribuicao[3][0] + " - " + distribuicao[3][1] + " | " + distribuicao [3][2]);
        System.out.println(distribuicao[4][0] + " - " + distribuicao[4][1] + " | " + distribuicao [4][2]);
        System.out.println("----------|----\n");

        double moda = dist.calcularModa();
        System.out.println("Moda: " + moda);

        double media = dist.calcularMedia();
        System.out.println("Média: " + media);

        double mediana = dist.calcularMediana();
        System.out.println("Mediana: " + mediana);

        double desvioPadrao = dist.calcularDesvioPadrao();
        System.out.println("Desvio padrão: " + desvioPadrao);
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

            //(xi - media)^2 * fi
            somaQuadrados += fi * Math.pow(xi - media, 2);
            somaFrequencia += fi;
        }

        // Calcular o desvio padrão
        return Math.sqrt(somaQuadrados / somaFrequencia);
    }


}

