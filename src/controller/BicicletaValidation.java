package controller;

public class BicicletaValidation implements ValidationStrategy {
    @Override
    public ValidationResult validate(VehicleData data) {
        if (data == null) return new ValidationResult(false, "Datos invalidos.");
        if (data.getNombre() == null || data.getNombre().trim().isEmpty()) {
            return new ValidationResult(false, "El campo Nombre es obligatorio.");
        }
        if (data.getModelo() == null || data.getModelo().trim().isEmpty()) {
            return new ValidationResult(false, "Para Bicicleta el campo Modelo es obligatorio.");
        }
        if (data.getColor() == null || data.getColor().trim().isEmpty()) {
            return new ValidationResult(false, "Para Bicicleta el campo Color es obligatorio.");
        }
        return new ValidationResult(true, "");
    }
}
