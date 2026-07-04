package org.example.siglc.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.beans.property.SimpleStringProperty;
import org.example.siglc.application.GerenteDeCenas;
import org.example.siglc.model.dto.ProdutoDTO;
import org.example.siglc.service.ProdutoService;

public class ProdutosController extends MenuComumController {
    private final ProdutoService produtoService;
    private List<ProdutoDTO> produtos;
    private String filtroNome;
    private String filtroCategoria;
    private String filtroStatus;

    @FXML private TextField textoProdutoNome;
    @FXML private ChoiceBox<String> escolhaCategoria;
    @FXML private ChoiceBox<String> escolhaStatus;
    @FXML private TableView<ProdutoDTO> tableViewProduto;
    @FXML private TableColumn<ProdutoDTO, String> colunaCodigo;
    @FXML private TableColumn<ProdutoDTO, String> colunaProduto;
    @FXML private TableColumn<ProdutoDTO, String> colunaCategoria;
    @FXML private TableColumn<ProdutoDTO, String> colunaPreco;
    @FXML private TableColumn<ProdutoDTO, String> colunaFornecedor;
    @FXML private TableColumn<ProdutoDTO, String> colunaEstoque;
    @FXML private TableColumn<ProdutoDTO, String> colunaStatus;

    private void carregarProdutos() {
        produtos = produtoService.listarProdutos();
    }

    // PREDICATES
    private boolean predicateNome(ProdutoDTO produtoDTO) {
        if (filtroNome == null || filtroNome.isBlank()) return true;
        else return produtoDTO.produto().toLowerCase().contains(filtroNome.toLowerCase());
    }

    private boolean predicateCategoria(ProdutoDTO produtoDTO) {
        if (filtroCategoria == null || filtroCategoria.isBlank() || filtroCategoria.equals("Todos")) return true;
        else return produtoDTO.categoria().equals(filtroCategoria);
    }

    private boolean predicateStatus(ProdutoDTO produtoDTO) {
        if (filtroStatus == null || filtroStatus.isBlank() || filtroStatus.equals("Todos")) return true;
        else return filtroStatus.equals(produtoDTO.status());
    }

    // Configuracao de filtros
    private void configurarFiltroStatus() {
        escolhaStatus.getItems().addAll("Todos", "OK", "ESTOQUE BAIXO", "ESGOTADO");
        escolhaStatus.getSelectionModel().selectFirst();
        escolhaStatus.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filtroStatus = newValue;
                    filtrarProdutos();
                }
        );
    }

    private void configurarFiltroCategoria() {
        List<String> categorias = produtos.stream().map(ProdutoDTO::categoria).distinct().toList();
        escolhaCategoria.getItems().setAll(categorias);
        escolhaCategoria.getItems().addFirst("Todos");
        escolhaCategoria.getSelectionModel().selectFirst();
        escolhaCategoria.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filtroCategoria = newValue;
                    filtrarProdutos();
                }
        );
    }

    private void configurarBuscaProdutos() {
        textoProdutoNome.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filtroNome = newValue;
                    filtrarProdutos();
                }
        );
    }

    private void configurarFiltros() {
        configurarBuscaProdutos();
        configurarFiltroCategoria();
        configurarFiltroStatus();
    }

    private void filtrarProdutos() {
        List<ProdutoDTO> produtosFiltrados = produtos
                .stream()
                .filter(this::predicateNome)
                .filter(this::predicateCategoria)
                .filter(this::predicateStatus)
                .toList();
        tableViewProduto.getItems().setAll(produtosFiltrados);
    }

    // Misc
    private void configurarColunas() {
        colunaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().codigo()));
        colunaProduto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().produto()));
        colunaCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().categoria()));
        colunaPreco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().preco()));
        colunaFornecedor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fornecedor()));
        colunaEstoque.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estoque()));
        colunaStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().status()));
    }

    @FXML
    private void botaoNovoProdutoPressionado() {
        GerenteDeCenas.alterarCena("NovoProduto");
    }

    @FXML
    private void botaoLimparFiltrosPressionado() {
        escolhaCategoria.getSelectionModel().selectFirst();
        escolhaStatus.getSelectionModel().selectFirst();
        textoProdutoNome.clear();
        filtrarProdutos();
    }

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        configurarColunas();
        carregarProdutos();
        configurarFiltros();
        filtrarProdutos();
    }

    public ProdutosController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
}
