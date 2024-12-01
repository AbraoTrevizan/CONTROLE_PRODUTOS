package novo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoVestuario {
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private String tamanho;
    private String cor;
    private String material;

    public ProdutoVestuario(String nome, double precoCusto, double precoVenda, String tamanho, String cor, String material) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    // Método para calcular o lucro
    public double calcularLucro() {
        return precoVenda - precoCusto;
    }

    // Método para salvar o produto no banco de dados
    public void salvar(Connection connection) {
        String sql = "INSERT INTO Produto (nome, preco_custo, preco_venda, tamanho, cor, material, tipo_produto) VALUES (?, ?, ?, ?, ?, ?, 'vestuario')";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, precoCusto);
            stmt.setDouble(3, precoVenda);
            stmt.setString(4, tamanho);
            stmt.setString(5, cor);
            stmt.setString(6, material);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
