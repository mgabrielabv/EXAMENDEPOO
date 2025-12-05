package view;

import controller.ValidationResult;
import controller.VehicleData;
import controller.vehiculosController;
import model.Vehiculo;
import model.VehiculosModelListener;
import model.Bicicleta;
import model.Coche;
import model.Moto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class vehiculosView extends JFrame implements VehiculosModelListener {
    private final vehiculosController controller;
    private JComboBox<String> tipoCombo;
    private JTextField nombreField;
    private JTextField modeloField;
    private JTextField colorField;
    private JTextField placaField;
    private JTextField anioField;
    private JCheckBox lucesCheck;
    private JCheckBox cascoCheck;
    private JCheckBox seguroCheck;
    // Usamos JEditorPane para poder mostrar HTML y frases más agradables
    private JEditorPane descripcionPane;
    private DefaultListModel<Vehiculo> listModel;
    private JList<Vehiculo> lista;

    private JLabel nombreLabel;
    private JLabel modeloLabel;
    private JLabel colorLabel;
    private JLabel placaLabel;
    private JLabel anioLabel;

    public vehiculosView(vehiculosController controller) {
        this.controller = controller;
        initUI();
        this.controller.addListener(this);

        refreshList();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.removeListener(vehiculosView.this);
            }
        });
    }

    private void initUI() {
        setTitle("Gestor de Vehiculos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // Color de fondo general suave
        getContentPane().setBackground(new Color(245, 248, 252));

        JPanel top = new JPanel(new GridBagLayout());
        top.setBorder(new EmptyBorder(8, 8, 8, 8));
        top.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER; // centrar los componentes del formulario
        top.add(new JLabel("Tipo:"), c);

        tipoCombo = new JComboBox<>(new String[]{"Bicicleta", "Carro", "Moto"});
        c.gridx = 1;
        top.add(tipoCombo, c);

        // Nombre (y=1)
        nombreLabel = new JLabel("Nombre:");
        c.gridx = 0;
        c.gridy = 1;
        top.add(nombreLabel, c);
        nombreField = new JTextField(12);
        c.gridx = 1;
        top.add(nombreField, c);

        // Modelo (y=2)
        modeloLabel = new JLabel("Modelo:");
        c.gridx = 0;
        c.gridy = 2;
        top.add(modeloLabel, c);
        modeloField = new JTextField(12);
        c.gridx = 1;
        top.add(modeloField, c);

        // Color (y=3)
        colorLabel = new JLabel("Color:");
        c.gridx = 0;
        c.gridy = 3;
        top.add(colorLabel, c);
        colorField = new JTextField(12);
        c.gridx = 1;
        top.add(colorField, c);

        // Placa (y=4)
        placaLabel = new JLabel("Placa:");
        c.gridx = 0;
        c.gridy = 4;
        top.add(placaLabel, c);
        placaField = new JTextField(12);
        c.gridx = 1;
        top.add(placaField, c);

        // Año (y=5)
        anioLabel = new JLabel("Año:");
        c.gridx = 0;
        c.gridy = 5;
        top.add(anioLabel, c);
        anioField = new JTextField(8);
        c.gridx = 1;
        top.add(anioField, c);

        // Checkboxes (y=6)
        lucesCheck = new JCheckBox("Luces");
        cascoCheck = new JCheckBox("Casco a juego");
        seguroCheck = new JCheckBox("Seguro");
        JPanel checks = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        checks.setOpaque(false);
        checks.add(lucesCheck);
        checks.add(cascoCheck);
        checks.add(seguroCheck);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        top.add(checks, c);
        c.gridwidth = 1;

        JButton addBtn = new JButton("Agregar vehículo");
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        top.add(addBtn, c);

        add(top, BorderLayout.NORTH);

        // Usamos JEditorPane para permitir HTML y oraciones en la descripción
        descripcionPane = new JEditorPane();
        descripcionPane.setContentType("text/html");
        descripcionPane.setEditable(false);
        descripcionPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        descripcionPane.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descripcionPane.setOpaque(true);
        descripcionPane.setBackground(new Color(250, 253, 255));

        JScrollPane descScroll = new JScrollPane(descripcionPane);
        descScroll.setBorder(BorderFactory.createTitledBorder("Descripción"));
        descScroll.setBackground(new Color(245, 248, 252));
        // add(descScroll, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        lista = new JList<>(listModel);
        lista.setVisibleRowCount(10);
        JScrollPane listScroll = new JScrollPane(lista);
        listScroll.setBorder(BorderFactory.createTitledBorder("Vehículos"));
        listScroll.setPreferredSize(new Dimension(220, 340));
        listScroll.setBackground(new Color(245, 248, 252));
        // add(listScroll, BorderLayout.WEST);

        // Centrar formulario en la parte superior
        JPanel topWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topWrapper.setOpaque(false);
        topWrapper.add(top);
        add(topWrapper, BorderLayout.NORTH);

        // Panel central que contiene la lista y la descripción, centrados
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        centerPanel.setOpaque(false);
        // Ajustes de tamaño para la descripción para que quede más ancha y centrada
        descScroll.setPreferredSize(new Dimension(520, 360));
        listScroll.setPreferredSize(new Dimension(240, 360));
        centerPanel.add(listScroll);
        centerPanel.add(descScroll);
        add(centerPanel, BorderLayout.CENTER);

        // Mejor renderer para que la lista muestre "Nombre (Tipo)" y esté centrada
        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.CENTER);
                setOpaque(true);
                setBackground(isSelected ? new Color(11, 57, 84) : new Color(250, 253, 255));
                setForeground(isSelected ? Color.white : new Color(11, 57, 84));
                setBorder(new EmptyBorder(6, 6, 6, 6));
                if (value instanceof Vehiculo) {
                    Vehiculo v = (Vehiculo) value;
                    String display = (v.getNombre() != null && !v.getNombre().trim().isEmpty()) ? v.getNombre() + " (" + v.getTipo() + ")" : v.getTipo();
                    setText(display);
                }
                return this;
            }
        });

        tipoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFieldsVisibility();
                updateDescripcion();
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Vehiculo sel = lista.getSelectedValue();
                    if (sel != null) {
                        showVehiculoDetalles(sel);
                    } else {
                        updateDescripcion();
                    }
                }
            }
        });

        updateFieldsVisibility();
        updateDescripcion();

        pack();
        setLocationRelativeTo(null);
    }

    private void updateFieldsVisibility() {
        String tipo = (String) tipoCombo.getSelectedItem();
        boolean showModelo = true;
        boolean showColor = true;
        boolean showPlaca = true;
        boolean showAnio = true;
        boolean showLuces = false;
        boolean showCasco = false;
        boolean showSeguro = false;

        if ("Bicicleta".equals(tipo)) {
            showColor = true;
            showPlaca = false;
            showAnio = false;
            showLuces = true;
            showCasco = false;
            showSeguro = false;
        } else if ("Carro".equals(tipo)) {
            showColor = true;
            showPlaca = true;
            showAnio = true;
            showLuces = false;
            showCasco = false;
            showSeguro = true;
        } else if ("Moto".equals(tipo)) {
            showColor = true;
            showPlaca = true;
            showAnio = true;
            showLuces = false;
            showCasco = true;
            showSeguro = false;
        }

        nombreLabel.setVisible(true);
        nombreField.setVisible(true);
        modeloLabel.setVisible(showModelo);
        modeloField.setVisible(showModelo);
        colorLabel.setVisible(showColor);
        colorField.setVisible(showColor);
        placaLabel.setVisible(showPlaca);
        placaField.setVisible(showPlaca);
        anioLabel.setVisible(showAnio);
        anioField.setVisible(showAnio);
        lucesCheck.setVisible(showLuces);
        cascoCheck.setVisible(showCasco);
        seguroCheck.setVisible(showSeguro);

        revalidate();
        repaint();
    }

    private void showVehiculoDetalles(Vehiculo v) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body style='font-family: SansSerif; font-size: 13pt; text-align:center; color:#1b2733;'>");

        // Header
        html.append("<h2 style='margin:0 0 8px 0;color:#0b3954;'>").append(v.getTipo());
        if (v.getNombre() != null && !v.getNombre().trim().isEmpty()) {
            html.append(" — ").append(escapeHtml(v.getNombre()));
        }
        html.append("</h2>");

        // Frase introductoria más humana
        String sentenceStart = sentenceStartForTipo(v.getTipo(), v.getNombre());
        html.append("<p style='margin:8px 12px; line-height:1.4em;'>");
        html.append("Te presento " ).append(sentenceStart).append(".");

        // Detalles separados en oraciones cortas y naturales
        boolean hasColorOrModel = false;
        if (v.getColor() != null && !v.getColor().trim().isEmpty()) {
            html.append(" Es de color <b>").append(escapeHtml(v.getColor())).append("</b>");
            hasColorOrModel = true;
        }
        if (v.getModelo() != null && !v.getModelo().trim().isEmpty()) {
            if (hasColorOrModel) html.append(" y tiene el modelo <b>").append(escapeHtml(v.getModelo())).append("</b>");
            else html.append(" Es del modelo <b>").append(escapeHtml(v.getModelo())).append("</b>");
            hasColorOrModel = true;
        }
        if (hasColorOrModel) html.append(".");
        else html.append(" No hay información de color o modelo registrada.");

        // Placa y año
        if (v instanceof Coche) {
            Coche c = (Coche) v;
            if (c.getPlaca() != null && !c.getPlaca().trim().isEmpty()) {
                html.append(" Su placa es <b>").append(escapeHtml(c.getPlaca())).append("</b>.");
            }
            if (c.getAnio() != null && !c.getAnio().trim().isEmpty()) {
                html.append(" Fue fabricado en <b>").append(escapeHtml(c.getAnio())).append("</b>.");
            }
            html.append(" Disponibilidad de seguro: <b>").append(c.isSeguro() ? "Sí" : "No").append("</b>.");
        } else if (v instanceof Moto) {
            Moto m = (Moto) v;
            if (m.getPlaca() != null && !m.getPlaca().trim().isEmpty()) {
                html.append(" Su placa es <b>").append(escapeHtml(m.getPlaca())).append("</b>.");
            }
            if (m.getAnio() != null && !m.getAnio().trim().isEmpty()) {
                html.append(" Fue fabricada en <b>").append(escapeHtml(m.getAnio())).append("</b>.");
            }
            html.append(" Cuenta con casco a juego: <b>").append(m.isCasco() ? "Sí" : "No").append("</b>.");
        } else if (v instanceof Bicicleta) {
            Bicicleta b = (Bicicleta) v;
            html.append(" Tiene luces: <b>").append(b.isTieneLuces() ? "Sí" : "No").append("</b>.");
        }

        String genDesc = v.getDescripcion();
        if (genDesc != null && !genDesc.trim().isEmpty()) {
            html.append("<br/><br/><i>").append(escapeHtml(genDesc)).append("</i>");
        }

        html.append("</p>");
        html.append("</body></html>");

        descripcionPane.setText(html.toString());
        descripcionPane.setCaretPosition(0);
    }

    private void updateDescripcion() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String desc = controller.getDescripcion(tipo);
        // Presentamos la descripción del tipo como una frase introductoria en HTML, centrada y con color
        String html = "<html><body style='font-family: SansSerif; font-size:13pt; text-align:center; padding:12px; background:#FAFEFF; color:#1b2733;'>" +
                "<h2 style='margin:0 0 8px 0; color:#0b3954; text-align:center;'>" + tipo + "</h2>" +
                "<p style='margin:6px 10px; line-height:1.4em;'>" + escapeHtml("Breve nota: " + desc) + "</p></body></html>";
        descripcionPane.setText(html);
        descripcionPane.setCaretPosition(0);
    }

    private void onAdd() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nombre = nombreField.isVisible() ? nombreField.getText().trim() : "";
        String modelo = modeloField.isVisible() ? modeloField.getText().trim() : "";
        String color = colorField.isVisible() ? colorField.getText().trim() : "";
        String placa = placaField.isVisible() ? placaField.getText().trim() : "";
        String anio = anioField.isVisible() ? anioField.getText().trim() : "";
        boolean tieneLuces = lucesCheck.isVisible() && lucesCheck.isSelected();
        boolean casco = cascoCheck.isVisible() && cascoCheck.isSelected();
        boolean seguro = seguroCheck.isVisible() && seguroCheck.isSelected();

        VehicleData data = new VehicleData(nombre, modelo, color, placa, anio, tieneLuces, casco, seguro);

        ValidationResult vr = controller.validateInputs(tipo, data);
        if (!vr.isValid()) {
            JOptionPane.showMessageDialog(this, vr.getMessage(), "Campos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        controller.addVehiculo(tipo, data);

        nombreField.setText("");
        modeloField.setText("");
        colorField.setText("");
        placaField.setText("");
        anioField.setText("");
        lucesCheck.setSelected(false);
        cascoCheck.setSelected(false);
        seguroCheck.setSelected(false);
    }

    private void refreshList() {
        List<Vehiculo> items = controller.getVehiculos();
        listModel.clear();
        for (Vehiculo v : items) {
            listModel.addElement(v);
        }
    }

    @Override
    public void vehiculosChanged() {
        SwingUtilities.invokeLater(() -> {
            refreshList();

            if (!listModel.isEmpty()) lista.setSelectedIndex(0);
        });
    }

    private String sentenceStartForTipo(String tipo, String nombre) {
        boolean hasName = nombre != null && !nombre.trim().isEmpty();
        if (hasName) {
            if ("Carro".equals(tipo)) return "el carro '" + escapeHtml(nombre) + "'";
            if ("Moto".equals(tipo)) return "la moto '" + escapeHtml(nombre) + "'";
            return "la bicicleta '" + escapeHtml(nombre) + "'";
        } else {
            if ("Carro".equals(tipo)) return "este carro";
            if ("Moto".equals(tipo)) return "esta moto";
            return "esta bicicleta";
        }
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("\n", "<br/>");
    }
}
