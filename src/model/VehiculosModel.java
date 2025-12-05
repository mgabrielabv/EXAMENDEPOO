package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehiculosModel {
    private final List<Vehiculo> lista = new ArrayList<>();

    private final List<VehiculosModelListener> listeners = new ArrayList<>();

    public synchronized void addVehiculo(Vehiculo v) {
        lista.add(v);
        notifyListeners();
    }

    public List<Vehiculo> getVehiculos() {
        return Collections.unmodifiableList(lista);
    }

    public void addListener(VehiculosModelListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    public void removeListener(VehiculosModelListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }

    private void notifyListeners() {
        List<VehiculosModelListener> copy;
        synchronized (listeners) {
            copy = new ArrayList<>(listeners);
        }
        for (VehiculosModelListener l : copy) {
            try {
                l.vehiculosChanged();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getDescripcionPorTipo(String tipo) {
        if (tipo == null) return "Tipo no especificado";
        switch (tipo) {
            case "Bicicleta":
                return "Bicicleta: vehículo de dos ruedas impulsado por pedales. Ideal para viajes cortos, ejercicio y sin consumo de combustible.";
            case "Carro":
                return "Carro: vehículo de cuatro ruedas con motor, pensado para transportar personas y equipaje en distancias medias/largas.";
            case "Moto":
                return "Moto: vehículo de dos ruedas con motor. Más ágil en ciudad, requiere casco y precaución.";
            default:
                return "Descripción no disponible para el tipo seleccionado.";
        }
    }
}
