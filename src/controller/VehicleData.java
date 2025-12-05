package controller;

public class VehicleData {
    private final String nombre;
    private final String modelo;
    private final String color;
    private final String placa;
    private final String anio;
    private final boolean tieneLuces;
    private final boolean casco;
    private final boolean seguro;

    public VehicleData(String nombre, String modelo, String color, String placa, String anio, boolean tieneLuces, boolean casco, boolean seguro) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.color = color;
        this.placa = placa;
        this.anio = anio;
        this.tieneLuces = tieneLuces;
        this.casco = casco;
        this.seguro = seguro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getPlaca() {
        return placa;
    }

    public String getAnio() {
        return anio;
    }

    public boolean isTieneLuces() {
        return tieneLuces;
    }

    public boolean isCasco() {
        return casco;
    }

    public boolean isSeguro() {
        return seguro;
    }
}
