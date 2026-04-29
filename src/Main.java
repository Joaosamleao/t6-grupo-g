public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "informe dois arquivos de entrada. Ex.: java Main ../dados/arvore1.txt ../dados/arvore2.txt"
            );
        }

        Graph tree1 = new Graph(new In(args[0]));
        Graph tree2 = new Graph(new In(args[1]));

        StdOut.println("Arvore 1:");
        StdOut.println(tree1);
        StdOut.println();

        StdOut.println("Arvore 2:");
        StdOut.println(tree2);
        StdOut.println();

        TreeIsomorphism analysis1 = new TreeIsomorphism(tree1);
        TreeIsomorphism analysis2 = new TreeIsomorphism(tree2);

        StdOut.println("Validacao Arvore 1: " + analysis1.getValidationMessage());
        StdOut.println("Validacao Arvore 2: " + analysis2.getValidationMessage());

        if (analysis1.isTree() && analysis2.isTree()) {
            StdOut.print("Centros Arvore 1: ");
            for (int c : analysis1.getCenters()) {
                StdOut.print(c + " ");
            }
            StdOut.println();

            StdOut.print("Centros Arvore 2: ");
            for (int c : analysis2.getCenters()) {
                StdOut.print(c + " ");
            }
            StdOut.println();

            String code1 = analysis1.getCanonicalEncoding();
            String code2 = analysis2.getCanonicalEncoding();

            StdOut.println("Codificacao Canonica 1: " + code1);
            StdOut.println("Codificacao Canonica 2: " + code2);

            if (code1.equals(code2)) {
                StdOut.println("VEREDITO: Isomorfas");
            } else {
                StdOut.println("VEREDITO: Nao isomorfas");
            }
        } else {
            StdOut.println("Nao foi possivel comparar (entrada invalida)");
        }
    }
}
