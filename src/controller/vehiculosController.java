package controller;

import model.*;

import java.util.List;

public class vehiculosController {
    private final VehiculosModel model;

    public vehiculosController(VehiculosModel model) {
        this.model = model;
    }

    public String getDescripcion(String tipo) {
        return VehiculosModel.getDescripcionPorTipo(tipo);
    }

    public ValidationResult validateInputs(String tipo, VehicleData data) {
        ValidationStrategy strat = ValidationStrategyFactory.getStrategy(tipo);
        return strat.validate(data);
    }

    public void addVehiculo(String tipo, VehicleData data) {
        Vehiculo v = VehicleFactory.create(tipo, data);
        model.addVehiculo(v);
    }

    public void addListener(VehiculosModelListener l) {
        model.addListener(l);
    }

    public void removeListener(VehiculosModelListener l) {
        model.removeListener(l);
    }

    public List<Vehiculo> getVehiculos() {
        return model.getVehiculos();
    }
}
