package controller;

public class MotoValidation implements ValidationStrategy {
    @Override
    public ValidationResult validate(VehicleData data) {
        if (data == null) return new ValidationResult(false, "Datos invalidos.");
        if (data.getNombre() == null || data.getNombre().trim().isEmpty()) {
            return new ValidationResult(false, "El campo Nombre es obligatorio.");
        }
        if (data.getModelo() == null || data.getModelo().trim().isEmpty()) {
            return new ValidationResult(false, "Para Moto el campo Modelo es obligatorio.");
        }
        if (data.getAnio() == null || data.getAnio().trim().isEmpty()) {
            return new ValidationResult(false, "Para Moto el campo Año es obligatorio.");
        }
        if (data.getPlaca() == null || data.getPlaca().trim().isEmpty()) {
            return new ValidationResult(false, "Para Moto el campo Placa es obligatorio.");
        }
        if (data.getColor() == null || data.getColor().trim().isEmpty()) {
            return new ValidationResult(false, "Para Moto el campo Color es obligatorio.");
        }
        return new ValidationResult(true, "");
    }
}
