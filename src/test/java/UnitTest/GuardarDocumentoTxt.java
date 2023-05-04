/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnitTest;
import GUI.mainWindow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author gmora
 */
public class GuardarDocumentoTxt {
    
@Test
@DisplayName("btnAbrir's mainWindow should open a file with a name")
void GuardarArchivoTxt() {
        final String fileName = "fire.json";
        final String fileWindowName = fileName.split("\\.")[0];

        final mainWindow window = new mainWindow(fileName);
        assertEquals(window.getTextFieldNombre_docu().getText(), fileWindowName);
    }
    
}
