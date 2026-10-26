package br.com.ithub.service;

import br.com.ithub.domain.Anuncio;
import br.com.ithub.domain.Usuario;
import java.util.List;
import java.util.stream.Collectors;

public class GeolocalizacaoService {

    private static final double RAIO_TERRA_KM = 6371.0;

    public double calcularDistanciaKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round((RAIO_TERRA_KM * c) * 100.0) / 100.0;
    }

    public List<Anuncio> buscarAnunciosPorRaio(List<Anuncio> todosAnuncios, Usuario solicitante, double raioMaximoKm) {
        if (raioMaximoKm <= 0) {
            throw new IllegalArgumentException("O raio de busca deve ser maior que zero.");
        }

        return todosAnuncios.stream()
                .filter(anuncio -> "DISPONIVEL".equals(anuncio.getEquipamento().getStatus()))
                .filter(anuncio -> {
                    double distancia = calcularDistanciaKm(
                            solicitante.getLatitude(), solicitante.getLongitude(),
                            anuncio.getAnunciante().getLatitude(), anuncio.getAnunciante().getLongitude()
                    );
                    return distancia <= raioMaximoKm;
                })
                .collect(Collectors.toList());
    }
}