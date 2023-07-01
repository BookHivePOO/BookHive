package application.swing;

import dao.LivroDAO;
import dao.TransacaoDAO;
import dto.LivroDTO;
import dto.TransacaoDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Represents a Swing application for displaying the purchase history of a user.
 */
public class TelaHistoricoCompras extends JFrame {
    private JTable tabelaCompras;

    /**
     * Creates a new instance of the TelaHistoricoCompras class.
     *
     * @param idUsuario    the user ID
     * @param nomeUsuario  the user name
     */
    public TelaHistoricoCompras(long idUsuario, String nomeUsuario) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Histórico de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelNomeUsuario = new JLabel("Histórico de Compras - Usuário: " + nomeUsuario);
        labelNomeUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        labelNomeUsuario.setHorizontalAlignment(JLabel.CENTER);
        painelPrincipal.add(labelNomeUsuario, BorderLayout.NORTH);

        List<TransacaoDTO> listaCompras = new TransacaoDAO().pesquisarComprasPorIdUsuario(idUsuario);

        criarTabelaCompras(listaCompras);
        JScrollPane scrollPane = new JScrollPane(tabelaCompras);

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creates and populates the table with the purchase history data.
     *
     * @param compras  the list of purchase transactions
     */
    private void criarTabelaCompras(List<TransacaoDTO> compras) {
        String[] colunas = {"ID Transação", "Título do Livro", "Autor", "ID Compra", "ID Venda", "ID Pagamento", "ID Endereço de Entrega", "Total a Pagar"};
        String[][] dados = new String[compras.size()][colunas.length];

        for (int i = 0; i < compras.size(); i++) {
            TransacaoDTO compra = compras.get(i);
            LivroDTO livro = new LivroDAO().pesquisarLivroPorId(compra.getIdLivro());

            dados[i][0] = String.valueOf(compra.getIdTransacao());
            dados[i][1] = livro.getTitulo();
            dados[i][2] = livro.getAutor();
            dados[i][3] = String.valueOf(compra.getIdCompra());
            dados[i][4] = String.valueOf(compra.getIdVenda());
            dados[i][5] = String.valueOf(compra.getIdPagamento());
            dados[i][6] = String.valueOf(compra.getIdEnderecoEntrega());
            dados[i][7] = String.valueOf(compra.getTotalPagar());
        }

        tabelaCompras = new JTable(dados, colunas);
        tabelaCompras.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelaCompras.setRowHeight(20);
        tabelaCompras.getColumnModel().getColumn(0).setPreferredWidth(80);

        JScrollPane scrollPane = new JScrollPane(tabelaCompras);
        tabelaCompras.setFillsViewportHeight(true);
        tabelaCompras.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Entry point of the application.
     *
     * @param args  the command-line arguments
     */
    public static void main(String[] args) {
        // Example usage:
        long idUsuario = 12345;
        String nomeUsuario = "John Doe";

        SwingUtilities.invokeLater(() -> new TelaHistoricoCompras(idUsuario, nomeUsuario));
    }
}
