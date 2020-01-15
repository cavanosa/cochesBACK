package com.concesionario.cochesbackend.dto;

public class BusquedaDTO {
    private String marca;
    private String modelo;
    private String version;
    private String cambio;
    private String color;
    private Integer kmDesde;
    private Integer kmHasta;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getKmDesde() {
        return kmDesde;
    }

    public void setKmDesde(Integer kmDesde) {
        this.kmDesde = kmDesde;
    }

    public Integer getKmHasta() {
        return kmHasta;
    }

    public void setKmHasta(Integer kmHasta) {
        this.kmHasta = kmHasta;
    }
}
