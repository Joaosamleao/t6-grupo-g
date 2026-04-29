import java.util.*;

public class TreeIsomorphism {
    private final Graph graph;

    public TreeIsomorphism(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public boolean isTree() {
        int V = graph.V();
        int E = graph.E();
        if (V == 0) return false;
        if (E != V - 1) return false;

        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            count++;
            for (int v : graph.adj(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        return count == V;
    }

    public String getValidationMessage() {
        if (isTree()) {
            return "Valida: Representa uma arvore";
        } else {
            return "Invalida: Nao e uma arvore";
        }
    }

    public int[] getCenters() {
        int V = graph.V();
        if (V == 1) return new int[]{0};

        int[] degree = new int[V];
        List<Integer> leaves = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            degree[v] = graph.degree(v);
            if (degree[v] == 1) {
                leaves.add(v);
            }
        }

        int removedNodes = leaves.size();
        while (removedNodes < V) {
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                for (int neighbor : graph.adj(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        newLeaves.add(neighbor);
                    }
                }
            }
            removedNodes += newLeaves.size();
            leaves = newLeaves;
        }

        int[] result = new int[leaves.size()];
        for (int i = 0; i < leaves.size(); i++) {
            result[i] = leaves.get(i);
        }
        return result;
    }

    public String getCanonicalEncoding() {
        if (!isTree()) {
            return "N/A";
        }

        int[] centers = getCenters();
        List<String> codes = new ArrayList<>();

        for (int center : centers) {
            codes.add(computeEncoding(center, -1));
        }

        Collections.sort(codes);
        return codes.get(0);
    }

    private String computeEncoding(int u, int p) {
        List<String> children = new ArrayList<>();
        for (int v : graph.adj(u)) {
            if (v != p) {
                children.add(computeEncoding(v, u));
            }
        }
        Collections.sort(children);

        StringBuilder sb = new StringBuilder("(");
        for (String s : children) {
            sb.append(s);
        }
        return sb.append(")").toString();
    }
}
