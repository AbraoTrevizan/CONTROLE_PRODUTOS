package novo;
import java.sql.*;

public class Produto {
    String nome;
    double precoCusto;
    double precoVenda;
    int id;  // Id do produto no banco de dados

    public Produto(String nome, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    public double calcularLucro() {
        return precoVenda - precoCusto;
    }

    // Método para salvar o produto no banco de dados
    public void salvar(Connection connection) throws SQLException {
        String query = "INSERT INTO Produto (nome, preco_custo, preco_venda) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, precoCusto);
            stmt.setDouble(3, precoVenda);
            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente para o produto
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            }
        }
    }

    // Método para atualizar o produto no banco de dados
    public void atualizar(Connection connection) throws SQLException {
        String query = "UPDATE Produto SET nome = ?, preco_custo = ?, preco_venda = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, precoCusto);
            stmt.setDouble(3, precoVenda);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    // Método para deletar o produto do banco de dados
    public void deletar(Connection connection) throws SQLException {
        String query = "DELETE FROM Produto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
