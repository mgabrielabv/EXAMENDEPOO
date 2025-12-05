
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            model.VehiculosModel model = new model.VehiculosModel();
            controller.vehiculosController controller = new controller.vehiculosController(model);
            view.vehiculosView view = new view.vehiculosView(controller);
            view.setVisible(true);
        });
    }
}

