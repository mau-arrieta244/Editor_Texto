package UnitTest;
import Enums.ComboColor;
import GUI.mainWindow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CambiarColorDelTexto {

    //Path test

    /*
    * @brief Prueba que el color no se cambie si no hay texto seleccionado
    * @pre textArea con texto
    * @post textArea con texto y color default
    * @return void
    * */
    @Test
    @DisplayName("[cambiarColor] should set a null style to the textArea with no selection")
    void textColorNoSelection() throws BadLocationException {

        JTextPane textArea = new JTextPane();
        textArea.setText("test");
        mainWindow window = new mainWindow("fire.json");

        window.setJTextAreaCuerpo_docu(textArea);
        window.cambiarColor();

        StyledDocument doc = textArea.getStyledDocument();
        Style style = doc.getStyle("MyHilite");

        assertNull(style);
    }

    /*
    * @brief Prueba que el color se cambie si hay texto seleccionado
    * @pre textArea con texto
    * @post textArea con texto y color seleccionado
    * @return void
    * */
    @Test
    @DisplayName("[cambiarColor] should set a style to the textArea with selection")
    void textColorSelection() throws BadLocationException {

        JTextPane textArea = new JTextPane();
        textArea.setText("test");
        mainWindow window = new mainWindow("fire.json");

        window.setJTextAreaCuerpo_docu(textArea);
        textArea.setSelectionStart(0);
        textArea.setSelectionEnd(4);
        window.setComboBoxColor(ComboColor.RED);
        window.cambiarColor();

        StyledDocument doc = textArea.getStyledDocument();
        Style style = doc.getStyle("MyHilite");

        assertEquals(style.getAttribute(StyleConstants.Foreground), window.getComboBoxColor().getColor());
    }

}
