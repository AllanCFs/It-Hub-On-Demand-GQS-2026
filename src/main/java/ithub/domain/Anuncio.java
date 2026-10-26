package br.com.ithub.domain;

public class Anuncio {
    private String id;
    private Equipamento equipamento;
    private Usuario anunciante;
    private String modalidade; // "ALUGUEL", "VENDA", "AMBOS"

    public Anuncio(String id, Equipamento equipamento, Usuario anunciante, String modalidade) {
        if (!"DISPONIVEL".equals(equipamento.getStatus())) {
            throw new IllegalStateException("Apenas equipamentos aprovados na auditoria podem ser anunciados.");
        }
        this.id = id;
        this.equipamento = equipamento;
        this.anunciante = anunciante;
        this.modalidade = modalidade;
    }

    public String getId() { return id; }
    public Equipamento getEquipamento() { return equipamento; }
    public Usuario getAnunciante() { return anunciante; }
    public String getModalidade() { return modalidade; }
}