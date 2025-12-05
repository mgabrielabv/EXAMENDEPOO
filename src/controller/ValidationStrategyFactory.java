package controller;

public final class ValidationStrategyFactory {
    private ValidationStrategyFactory() {}

    public static ValidationStrategy getStrategy(String tipo) {
        if (tipo == null) return new CocheValidation();
        switch (tipo) {
            case "Bicicleta":
                return new BicicletaValidation();
            case "Moto":
                return new MotoValidation();
            case "Carro":
            default:
                return new CocheValidation();
        }
    }
}
