package model;

public class Bicicleta extends Vehiculo {
    private final boolean tieneLuces;

    public Bicicleta(String nombre, String modelo, String color, boolean tieneLuces) {
        super("Bicicleta", nombre, modelo, color, "");
        this.tieneLuces = tieneLuces;
    }

    public boolean isTieneLuces() {
        return tieneLuces;
    }

    @Override
    public String getDescripcion() {
        return "Bicicleta: vehiculo de dos ruedas impulsado por pedales. Ideal para viajes cortos, ejercicio y sin consumo de combustible.";
    }
}
