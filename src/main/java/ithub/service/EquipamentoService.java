package br.com.ithub.service;

import br.com.ithub.domain.Equipamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipamentoService {
    private final List<Equipamento> repositorio = new ArrayList<>();

    public Equipamento cadastrarEquipamento(String id, String nome, String categoria, double valorDiaria) {
        Equipamento equipamento = new Equipamento(id, nome, categoria, valorDiaria);
        repositorio.add(equipamento);
        return equipamento;
    }

    public void aprovarAuditoriaELiberar(String id) {
        Equipamento eq = buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado."));
        eq.setStatus("DISPONIVEL");
    }

    public Optional<Equipamento> buscarPorId(String id) {
        return repositorio.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Equipamento> listarDisponiveis() {
        return repositorio.stream()
                .filter(e -> "DISPONIVEL".equals(e.getStatus()))
                .toList();
    }
}