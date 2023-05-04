package GUI;

import Enums.ComboColor;
import Memento.documentCaretaker;
import Memento.componenteDocument;
import Memento.documentMemento;
import Memento.stateDocument;
import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import File.FileSupervisor;


public class mainWindow extends javax.swing.JFrame {
    private static JFrame frame;
    private JButton btnGuardar_como;
    private JButton btnGuardar;
    private JButton btnCambiar_color;
    private JButton btnDeshacer;
    private JButton btnRehacer;
    private JTextField textFieldNombre_docu;
    private JComboBox comboBoxExtension_docu;


    private JComboBox comboBoxColor;
    private JTextPane  JTextAreaCuerpo_docu;
    private JLabel JLabelNombre_docu = new JLabel("Nombre del documento:");
    private JLabel JLabelExtension_docu = new JLabel("Extensión del documento:");
    private JLabel JLabelNombre_docu_abierto = new JLabel();
    private int contadorCambios = 0;
    private documentCaretaker caretaker;
    private stateDocument estadoActual;
    private FileSupervisor fileHandler;

    String[] extensiones={".txt",".json",".ttxt"};
    public mainWindow(String nombreDocumentoAbierto){
        initialize(nombreDocumentoAbierto);
    }
    private void initialize(String nombreDocumentoAbierto) {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 760);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabelNombre_docu.setBounds(200,20,150,20);
        frame.getContentPane().add(JLabelNombre_docu);

        JLabelNombre_docu_abierto.setBounds(20,20,150,20);
        frame.getContentPane().add(JLabelNombre_docu_abierto);

        textFieldNombre_docu = new JTextField();
        textFieldNombre_docu.setBounds(340,20,150,20);
        frame.getContentPane().add(textFieldNombre_docu);

        JLabelExtension_docu.setBounds(500,20,150,20);
        frame.getContentPane().add(JLabelExtension_docu);

        comboBoxExtension_docu = new JComboBox<>(extensiones);
        comboBoxExtension_docu.setBounds(650,20,150,20);
        frame.getContentPane().add(comboBoxExtension_docu);

        JTextAreaCuerpo_docu = new JTextPane();
        JScrollPane scroll = new JScrollPane(JTextAreaCuerpo_docu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(40, 60, 900, 550);
        JTextAreaCuerpo_docu.setForeground(Color.black);
        frame.getContentPane().add(scroll);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120,620,130,40);
        frame.getContentPane().add(btnGuardar);

        btnGuardar_como = new JButton("Guardar como");
        btnGuardar_como.setBounds(280,620,130,40);
        frame.getContentPane().add(btnGuardar_como);

        btnCambiar_color = new JButton("Cambiar color");
        btnCambiar_color.setBounds(440,620,130,40);
        frame.getContentPane().add(btnCambiar_color);

        btnDeshacer = new JButton("Deshacer");
        btnDeshacer.setBounds(600,620,130,40);
        frame.getContentPane().add(btnDeshacer);

        btnRehacer = new JButton("Rehacer");
        btnRehacer.setBounds(760  ,620,130,40);
        frame.getContentPane().add(btnRehacer);

        DefaultComboBoxModel<ComboColor> cModel = new DefaultComboBoxModel<>(
                ComboColor.values());
        comboBoxColor = new JComboBox<>(cModel);
        comboBoxColor.setBounds(440,675,130,30);
        frame.getContentPane().add(comboBoxColor);

        caretaker = new documentCaretaker();

        estadoActual = new stateDocument();

        fileHandler = new FileSupervisor();
        

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    escanearDocumento();
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnCambiar_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    cambiarColor();
                    escanearDocumento();
                    saveState();
                    contadorCambios = 0;
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getNextState();
                updateUI();
            }
        });

        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPreviousState();
                updateUI();
            }
        });

        btnGuardar_como.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String res = "";
                try {
                    escanearDocumento();
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
                saveState();
                try {

                    res = fileHandler.guardarComo(estadoActual, textFieldNombre_docu.getText(),String.valueOf(comboBoxExtension_docu.getSelectedItem()),JTextAreaCuerpo_docu.getText().length());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JOptionPane.showMessageDialog(frame, res);
                return;
            }
        });

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String res = "";
                try {
                    escanearDocumento();
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
                saveState();
                try {
                    res = fileHandler.guardar(estadoActual, textFieldNombre_docu.getText(),String.valueOf(comboBoxExtension_docu.getSelectedItem()),JTextAreaCuerpo_docu.getText().length());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JOptionPane.showMessageDialog(frame, res);
                return;
            }

        });

        JTextAreaCuerpo_docu.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                contadorCambios = contadorCambios + 1;
                try {
                    checkIfAddState();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        if (nombreDocumentoAbierto != null){

            JLabelNombre_docu_abierto.setText(nombreDocumentoAbierto);
            stateDocument estadoRecibido = (stateDocument) fileHandler.getDocumentObject(nombreDocumentoAbierto);
            estadoActual = estadoRecibido;
            textFieldNombre_docu.setText(nombreDocumentoAbierto.substring(0, nombreDocumentoAbierto.lastIndexOf('.')));
            updateUI();
        }
    }
    private void saveState(){
        caretaker.addNewMemento(estadoActual.createMemento());
    }
    private void getPreviousState(){
        documentMemento memento = caretaker.getPreviousMemento();

        if(memento==null){
            JOptionPane.showMessageDialog(this, "No existen más estados");
            return;
        }

        estadoActual.restoreMemento(memento);
    }
    private void getNextState(){
        documentMemento memento = caretaker.getNextMemento();

        if(memento==null){
            JOptionPane.showMessageDialog(this, "No existen más estados");
            return;
        }

        estadoActual.restoreMemento(memento);
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
    private void checkIfAddState() throws BadLocationException {
        if (contadorCambios >= 3){
            escanearDocumento();
            saveState();
            contadorCambios = 0;
        }
    }
    private stateDocument escanearDocumento() throws BadLocationException {
        Color color = null;
        Color colorActual = null;
        int startIndex = 0;
        int counter = 0;
        int endIndex = 0;
        componenteDocument componente = null;
        Element element = null;
        estadoActual.clear();
        StyledDocument doc = JTextAreaCuerpo_docu.getStyledDocument();
        endIndex = doc.getLength();
        if (endIndex == 0){return null;}
        colorActual = doc.getForeground(doc.getCharacterElement(0).getAttributes());
        while (counter < endIndex){
            element = doc.getCharacterElement(counter);
            color = doc.getForeground(element.getAttributes());
            if (colorActual != color && !doc.getText(counter,1).equals(" ")){
                componente = new componenteDocument(JTextAreaCuerpo_docu.getText(startIndex,counter-startIndex),colorActual);
                colorActual = color;
                estadoActual.add(componente);
                startIndex = counter;
            }
            counter++;
        }
        componente = new componenteDocument(JTextAreaCuerpo_docu.getText(startIndex,counter-startIndex),colorActual);
        estadoActual.add(componente);
        return estadoActual;
    }

    //=======================================================================================================
    //======================================  Cambio de color  =============================================
    public void cambiarColor() throws BadLocationException {
        StyledDocument doc = JTextAreaCuerpo_docu.getStyledDocument();
        int start = JTextAreaCuerpo_docu.getSelectionStart();
        int end = JTextAreaCuerpo_docu.getSelectionEnd();
        if (start == end) { // No selection, cursor position.
            return;
        }
        if (start > end) { // Backwards selection?
            int life = start;
            start = end;
            end = life;
        }
        Style style = JTextAreaCuerpo_docu.addStyle("MyHilite", null);
        ComboColor cColor = (ComboColor) comboBoxColor.getSelectedItem();
        Color color = (cColor.getColor());
        style.addAttribute(StyleConstants.Foreground, color);

//        StyleConstants.setForeground(style, color);
        doc.setCharacterAttributes(start, end - start, style, false);
    }
    public JComboBox getComboBoxExtension_docu() {
        return comboBoxExtension_docu;
    }
    public void setComboBoxColor(ComboColor color){
        comboBoxColor.setSelectedItem(color);
    };
    public ComboColor getComboBoxColor(){
        return (ComboColor) comboBoxColor.getSelectedItem();
    };
    public void setJTextAreaCuerpo_docu(JTextPane panel){
        JTextAreaCuerpo_docu = panel;
    };

    public JTextPane getJTextAreaCuerpo_docu(){
        return JTextAreaCuerpo_docu;
    };

    //pruebas mau
    public JTextField getTextFieldNombre_docu(){return textFieldNombre_docu;}

    //=======================================================================================================

    private void updateUI(){
        JTextAreaCuerpo_docu.selectAll();
        JTextAreaCuerpo_docu.replaceSelection("");
        StyledDocument doc = JTextAreaCuerpo_docu.getStyledDocument();
        Style style = JTextAreaCuerpo_docu.addStyle("Estilo",null);
        for (componenteDocument componente : estadoActual.listaDocumento){
            StyleConstants.setForeground(style, componente.colorTexto);
            try { doc.insertString(doc.getLength(), componente.cuerpo,style); }
            catch (BadLocationException e){}
        }
        JTextAreaCuerpo_docu.setDocument(doc);
        JTextAreaCuerpo_docu.update(JTextAreaCuerpo_docu.getGraphics());
    }
}
