package util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conection.SQLConnection;
import dao.CredencialDAO;
import dao.EnderecoDAO;
import dao.PagamentoDAO;
import dao.TransacaoDAO;
import dao.UsuarioDAO;
import dao.LivroDAO;
import dao.GeneroDAO;
import dto.LivroDTO;
import dto.TransacaoDTO;
import model.Credencial;
import model.Endereco;
import model.Pagamento;
import model.Transacao;
import model.Usuario;
import model.Livro;
import model.Genero;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioGenerator {
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String RELATORIO_ENDERECO = PROJECT_ROOT + "/relatorios/endereco.jrxml";
    private static final String RELATORIO_PAGAMENTO = PROJECT_ROOT + "/relatorios/pagamento.jrxml";
    private static final String RELATORIO_TRANSACAO = PROJECT_ROOT + "/relatorios/transacao.jrxml";
    private static final String RELATORIO_USUARIO = PROJECT_ROOT + "/relatorios/usuario.jrxml";
    private static final String RELATORIO_CREDENCIAL = PROJECT_ROOT + "/relatorios/credencial.jrxml";
    private static final String RELATORIO_LIVRO = PROJECT_ROOT + "/relatorios/livro.jrxml";
    private static final String RELATORIO_GENERO = PROJECT_ROOT + "/relatorios/genero.jrxml";
    private static final String OUTPUT_PATH = PROJECT_ROOT + "/relatorios/response/";

    public static void gerarRelatorioCompleto() {
        gerarRelatorioEndereco();
        gerarRelatorioPagamento();
        gerarRelatorioCredencial();
        gerarRelatorioTransacao();
        gerarRelatorioGenero();
        gerarRelatorioLivro();
        gerarRelatorioUsuario();
    }

    public static void gerarRelatorioEndereco() {
        try {
            Connection conn = criarConexao();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            List<Endereco> enderecos = enderecoDAO.listarTodosEnderecos();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(enderecos);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_ENDERECO), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_endereco.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioPagamento() {
        try {
            Connection conn = criarConexao();
            PagamentoDAO pagamentoDAO = new PagamentoDAO();
            List<Pagamento> pagamentos = pagamentoDAO.listarPagamentos();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pagamentos);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_PAGAMENTO), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_pagamento.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioTransacao() {
        try {
            Connection conn = criarConexao();
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            List<TransacaoDTO> transacoes = transacaoDAO.listarTransacoes();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transacoes);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_TRANSACAO), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_transacao.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioUsuario() {
        try {
            Connection conn = criarConexao();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuarios);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_USUARIO), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_usuario.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioCredencial() {
        try {
            Connection conn = criarConexao();
            CredencialDAO credencialDAO = new CredencialDAO();
            List<Credencial> credenciais = credencialDAO.listarCredenciais();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(credenciais);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_CREDENCIAL), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_credencial.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioLivro() {
        try {
            Connection conn = criarConexao();
            LivroDAO livroDAO = new LivroDAO();
            List<LivroDTO> livros = livroDAO.listarTodosLivros();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(livros);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_LIVRO), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_livro.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gerarRelatorioGenero() {
        try {
            Connection conn = criarConexao();
            GeneroDAO generoDAO = new GeneroDAO();
            List<Genero> generos = generoDAO.listarGeneros();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(generos);
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(RELATORIO_GENERO), parametros, dataSource);
            String outputFile = OUTPUT_PATH + "relatorio_genero.pdf";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            JasperExportManager.exportReportToPdfStream(print, outputStream);
            outputStream.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection criarConexao() throws SQLException {
        // Implemente o código para criar e retornar a conexão com o banco de dados
        return SQLConnection.getConnection();
    }

}
