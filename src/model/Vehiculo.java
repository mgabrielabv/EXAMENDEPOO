package model;

public abstract class Vehiculo {

    private final String tipo;
    private final String nombre;
    private final String modelo;
    private final String color;
    private final String placa;

    protected Vehiculo(String tipo, String nombre, String modelo, String color, String placa) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.modelo = modelo;
        this.color = color;
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
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
    public abstract String getDescripcion();

    @Override
    public String toString() {
        if (nombre != null && !nombre.trim().isEmpty()) return nombre;
        return tipo;
    }
}
