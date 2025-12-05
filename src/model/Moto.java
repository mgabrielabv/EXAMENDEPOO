package model;

public class Moto extends Vehiculo {
    private final String anio;
    private final boolean casco;

    public Moto(String nombre, String modelo, String color, String placa, String anio, boolean casco) {
        super("Moto", nombre, modelo, color, placa);
        this.anio = anio;
        this.casco = casco;
    }

    public String getAnio() {
        return anio;
    }

    public boolean isCasco() {
        return casco;
    }

    @Override
    public String getDescripcion() {
        return "Moto: vehiculo de dos ruedas con motor. Más ágil en ciudad, requiere casco y precaucion.";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
