package model;

public class Coche extends Vehiculo {
    private final boolean seguro;
    private final String anio;

    public Coche(String nombre, String modelo, String color, String placa, boolean seguro, String anio) {
        super("Carro", nombre, modelo, color, placa);
        this.seguro = seguro;
        this.anio = anio;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public String getAnio() {
        return anio;
    }

    @Override
    public String getDescripcion() {
        return "Carro: vehículo de cuatro ruedas con motor. Asegúrate de mantener el mantenimiento al día.";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
