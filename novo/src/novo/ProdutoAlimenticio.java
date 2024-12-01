package novo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoAlimenticio {
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private String dataValidade;
    private String informacoesNutricionais;

    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, String dataValidade, String informacoesNutricionais) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.dataValidade = dataValidade;
        this.informacoesNutricionais = informacoesNutricionais;
    }

    // Método para calcular o lucro
    public double calcularLucro() {
        return precoVenda - precoCusto;
    }

    // Método para salvar o produto no banco de dados
    public void salvar(Connection connection) {
        String sql = "INSERT INTO Produto (nome, preco_custo, preco_venda, data_validade, informacoes_nutricionais, tipo_produto) VALUES (?, ?, ?, ?, ?, 'alimenticio')";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, precoCusto);
            stmt.setDouble(3, precoVenda);
            stmt.setString(4, dataValidade);
            stmt.setString(5, informacoesNutricionais);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
