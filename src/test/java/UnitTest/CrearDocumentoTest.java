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

public class CrearDocumentoTest {
    @Test
    @DisplayName("btnNuevo's actionPerformed should open a new file with null name")
    void buttonAction() {
        mainWindow ventana =  new mainWindow(null);
        assertEquals(ventana.getTextFieldNombre_docu().getText(), "");
    }

    @Test
    @DisplayName("btnNuevo's actionPerformed should open a new file with no text in it")
    void buttonAction2() {
        mainWindow ventana =  new mainWindow(null);
        assertEquals(ventana.getJTextAreaCuerpo_docu().getText(), "");
    }


}
