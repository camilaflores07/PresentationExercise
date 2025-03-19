/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class CamilaAirlines {
    private static HashMap<String, String[]> listaPasajeros = new HashMap<>();
    private static Random id = new Random();

    public static void main(String[] args) {
        JFrame portada = new JFrame("Camila Airlines️");
        portada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        portada.setSize(500, 400);
        portada.setLocationRelativeTo(null);
        portada.setLayout(new BorderLayout());

        ImageIcon iconoOriginal = new ImageIcon("C:\\Users\\50494\\OneDrive\\Documents\\NetBeansProjects\\presentacion\\src\\presentacion\\imagenes\\portadaavion.png");
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(portada.getWidth(), portada.getHeight(), Image.SCALE_SMOOTH);
        JLabel imagenPortada = new JLabel(new ImageIcon(imagenRedimensionada));
        portada.add(imagenPortada, BorderLayout.CENTER);

        JButton botonEntrar = new JButton("Enter");
        botonEntrar.setBackground(new Color(70, 130, 180));
        botonEntrar.setForeground(Color.WHITE);
        botonEntrar.setFocusPainted(false);
        botonEntrar.setPreferredSize(new Dimension(100, 40));

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonEntrar);
        portada.add(panelBoton, BorderLayout.SOUTH);

        portada.setVisible(true);

        botonEntrar.addActionListener(e -> {
            portada.dispose();
            mostrarLogin();
        });
    }

    private static void mostrarLogin() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(350, 350);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);

        JLabel titulo = new JLabel("Login", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBounds(0, 20, 350, 30);

        JLabel userLabel = new JLabel("Nombre:");
        userLabel.setBounds(50, 70, 250, 25);
        JTextField userField = new JTextField();
        userField.setBounds(50, 95, 250, 25);

        JLabel passLabel = new JLabel("Seleccione su tipo de boleto:");
        passLabel.setBounds(50, 125, 250, 25);
        String[] opciones = {"Primera Clase", "Ejecutivo", "Economico"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(50, 150, 250, 25);

        JButton botonLogin = new JButton("Generar Codigo");
        botonLogin.setBounds(50, 190, 250, 30);
        
        JButton botonPasajeros = new JButton("Pasajeros");
        botonPasajeros.setBounds(50, 230, 250, 30);

        loginFrame.add(titulo);
        loginFrame.add(userLabel);
        loginFrame.add(userField);
        loginFrame.add(passLabel);
        loginFrame.add(comboBox);
        loginFrame.add(botonLogin);
        loginFrame.add(botonPasajeros);

        loginFrame.setVisible(true);

        botonLogin.addActionListener(e -> {
            String nombrePasajero = userField.getText();
            String seleccion = (String) comboBox.getSelectedItem();
            String codigo = generarCodigoUnico();
            
            listaPasajeros.put(nombrePasajero, new String[]{seleccion, codigo});
            JOptionPane.showMessageDialog(loginFrame, "Su codigo de boleto es: " + codigo);
            loginFrame.dispose();
            mostrarValidador(nombrePasajero);
        });
        
        botonPasajeros.addActionListener(e -> mostrarPasajeros());
    }

    private static String generarCodigoUnico() {
        int numero = 10000 + id.nextInt(90000); //solo para 5 numeritos
        return String.valueOf(numero);
    }

    private static void mostrarValidador(String nombrePasajero) {
        JFrame frame = new JFrame("Validador de Boletos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 1));

        JLabel etiqueta = new JLabel("Hola, " + nombrePasajero + ". Valide su boleto:", SwingConstants.CENTER);
        JTextField campoTexto = new JTextField();
        JButton botonValidar = new JButton("Validar codigo");
        JButton botonRegresar = new JButton("Regresar");
        JLabel resultado = new JLabel("", SwingConstants.CENTER);

        botonValidar.addActionListener(e -> {
            String codigoIngresado = campoTexto.getText().trim();
            if (listaPasajeros.containsKey(nombrePasajero) && listaPasajeros.get(nombrePasajero)[1].equals(codigoIngresado)) {
                resultado.setText("Boleto " + listaPasajeros.get(nombrePasajero)[0] + " válido. ¡Buen viaje, " + nombrePasajero + "!");
                resultado.setForeground(new Color(0, 150, 0));
            } else {
                resultado.setText("Boleto invalido, usted no puede abordar.");
                resultado.setForeground(Color.RED);
            }
        });

        botonRegresar.addActionListener(e -> {
            frame.dispose();
            mostrarLogin();
        });

        frame.add(etiqueta);
        frame.add(campoTexto);
        frame.add(botonValidar);
        frame.add(resultado);
        frame.add(botonRegresar);

        frame.setVisible(true);
    }

    private static void mostrarPasajeros() {
        JFrame frame = new JFrame("Lista de Pasajeros");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        listaPasajeros.forEach((nombre, datos) ->
            areaTexto.append("Nombre: " + nombre + " | Boleto: " + datos[0] + " | ID: " + datos[1] + "\n"));
        
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
