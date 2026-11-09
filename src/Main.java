import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    record Gasto(int id, String descricao, double valor, String categoria) {}

    static int proximoId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Gasto> gastos = new ArrayList<>();

        while (true) {
            System.out.println("1 - Adicionar | 2 - Listar | 3 - Editar | 4 - Excluir | 0 - Sair");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> adicionar(scanner, gastos);
                case "2" -> listar(gastos);
                case "3" -> editar(scanner, gastos);
                case "4" -> remover(scanner, gastos);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    static void adicionar(Scanner scanner, List<Gasto> gastos) {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        Gasto gasto = new Gasto(proximoId++, descricao, valor, categoria);
        gastos.add(gasto);

        System.out.println("Gasto adicionado!");
    }

    static void listar(List<Gasto> gastos) {
        if (gastos.isEmpty()) {
            System.out.println("Nenhum gasto cadastrado");
            return;
        }

        for (Gasto g : gastos) {
            System.out.println(g.id + " - " + g.descricao() + " - R$ " + g.valor() + " - " + g.categoria());
        }
    }

    static int buscarPosicaoPorId(List<Gasto> gastos, int id) {
        for (int i = 0; i < gastos.size(); i++) {
            if (gastos.get(i).id() == id) {
                return i;
            }
        }
        return -1;
    }

    static void editar(Scanner scanner, List<Gasto> gastos) {
        System.out.print("Id do gasto: ");
        int id = Integer.parseInt(scanner.nextLine());

        int posicao = buscarPosicaoPorId(gastos, id);
        if (posicao == -1) {
            System.out.println("Gasto não encontrado");
            return;
        }

        Gasto atual = gastos.get(posicao);

        System.out.print("Descrição (" + atual.descricao() + "): ");
        String descricao = scanner.nextLine();
        if (descricao.isBlank()) {
            descricao = atual.descricao();
        }

        System.out.print("Valor (" + atual.valor() + "): ");
        String valorTexto = scanner.nextLine();
        double valor = valorTexto.isBlank() ? atual.valor() : Double.parseDouble(valorTexto);

        System.out.print("Categoria (" + atual.categoria() + "): ");
        String categoria = scanner.nextLine();
        if (categoria.isBlank()) {
            categoria = atual.categoria();
        }

        gastos.set(posicao, new Gasto(id, descricao, valor, categoria));
        System.out.println("Gasto atualizado!");
    }

    static void remover(Scanner scanner, List<Gasto> gastos) {
        System.out.print("Id do gasto: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean removido = gastos.removeIf(g -> g.id() == id);
        System.out.println(removido ? "Gasto removido!" : "Gasto não encontrado");
    }
}