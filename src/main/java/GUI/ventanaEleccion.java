package GUI;

import File.FileSupervisor;

import javax.swing.*;
import java.awt.*;

public class ventanaEleccion extends javax.swing.JFrame {
    private static JFrame frame;
    private JButton btnAbrir;
    private JButton btnNuevo;

    private JComboBox comboBoxDocumentos;
    String[] documentos;
    FileSupervisor fileManager;

    public ventanaEleccion(){
        initialize();
    }

    public static void init() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //PantallaSecundaria window = new PantallaSecundaria(jugador);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize () {
        frame = new JFrame();
        frame.setBounds(100, 100, 200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnAbrir = new JButton("Abrir existente");
        btnAbrir.setBounds(20  ,50,130,40);
        frame.getContentPane().add(btnAbrir);

        btnNuevo = new JButton("Abrir nuevo");
        btnNuevo.setBounds(20  ,100,130,40);
        frame.getContentPane().add(btnNuevo);

        fileManager = new FileSupervisor();
        documentos = fileManager.getArchivos();

        comboBoxDocumentos = new JComboBox<>(documentos);
        comboBoxDocumentos.setBounds(20,20,150,20);
        frame.getContentPane().add(comboBoxDocumentos);

        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindow ventana =  new mainWindow((String) comboBoxDocumentos.getSelectedItem());
                ventana.init();
            }
        });

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindow ventana =  new mainWindow(null);
                ventana.init();
            }
        });



    }

}
