package novo;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/controleprodutos";  // URL do banco de dados
        String user = "root";  // Seu usuário do MySQL
        String password = "";  // Sua senha do MySQL

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            while (true) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Adicionar Produto Alimentício");
                System.out.println("2. Adicionar Produto de Vestuário");
                System.out.println("3. Listar Produtos");
                System.out.println("4. Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (opcao == 1) {
                    // Adicionar produto alimentício
                    System.out.println("Digite o nome do produto:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o preço de custo:");
                    double precoCusto = scanner.nextDouble();
                    System.out.println("Digite o preço de venda:");
                    double precoVenda = scanner.nextDouble();
                    scanner.nextLine(); // Consumir nova linha
                    System.out.println("Digite a data de validade (YYYY-MM-DD):");
                    String dataValidade = scanner.nextLine();
                    System.out.println("Digite as informações nutricionais:");
                    String informacoesNutricionais = scanner.nextLine();

                    ProdutoAlimenticio produtoAlim = new ProdutoAlimenticio(nome, precoCusto, precoVenda, dataValidade, informacoesNutricionais);
                    produtoAlim.salvar(connection);
                    System.out.println("Produto alimentício adicionado com lucro: " + produtoAlim.calcularLucro());
                } else if (opcao == 2) {
                    // Adicionar produto de vestuário
                    System.out.println("Digite o nome do produto:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o preço de custo:");
                    double precoCusto = scanner.nextDouble();
                    System.out.println("Digite o preço de venda:");
                    double precoVenda = scanner.nextDouble();
                    scanner.nextLine(); // Consumir nova linha
                    System.out.println("Digite o tamanho:");
                    String tamanho = scanner.nextLine();
                    System.out.println("Digite a cor:");
                    String cor = scanner.nextLine();
                    System.out.println("Digite o material:");
                    String material = scanner.nextLine();

                    ProdutoVestuario produtoVest = new ProdutoVestuario(nome, precoCusto, precoVenda, tamanho, cor, material);
                    produtoVest.salvar(connection);
                    System.out.println("Produto de vestuário adicionado com lucro: " + produtoVest.calcularLucro());
                } else if (opcao == 3) {
                    // Listar produtos
                    String query = "SELECT * FROM Produto";
                    try (Statement stmt = connection.createStatement();
                         ResultSet rs = stmt.executeQuery(query)) {
                        while (rs.next()) {
                            System.out.println("Produto ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
                        }
                    }
                } else if (opcao == 4) {
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
