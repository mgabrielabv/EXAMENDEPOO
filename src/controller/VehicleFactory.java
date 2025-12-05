package controller;

import model.Bicicleta;
import model.Coche;
import model.Moto;
import model.Vehiculo;

public final class VehicleFactory {
    private VehicleFactory() {}

    public static Vehiculo create(String tipo, VehicleData data) {
        if (tipo == null) tipo = "Carro";
        switch (tipo) {
            case "Bicicleta":
                return new Bicicleta(data.getNombre(), data.getModelo(), data.getColor(), data.isTieneLuces());
            case "Moto":
                return new Moto(data.getNombre(), data.getModelo(), data.getColor(), data.getPlaca(), data.getAnio(), data.isCasco());
            case "Carro":
            default:
                return new Coche(data.getNombre(), data.getModelo(), data.getColor(), data.getPlaca(), data.isSeguro(), data.getAnio());
        }
    }
}
