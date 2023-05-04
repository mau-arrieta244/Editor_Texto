/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UnitTest;
import File.FileSupervisor;
import GUI.mainWindow;
import Memento.stateDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *
 * @author gmora
 */
public class GuardarDocumentoTxt {

@Test
@DisplayName("GuardarComo's mainWindow should save a file with a name")
void GuardarArchivoTxt()  throws IOException {
        final FileSupervisor file = new FileSupervisor();
        final stateDocument doc = new stateDocument();

        final String fileName = "prueba.txt";
        final String extension = ".txt";
        final String fileWindowName = fileName.split("\\.")[0];
        final mainWindow window = new mainWindow(fileName);
        assertEquals(file.guardar(doc, fileWindowName, extension,window.getJTextAreaCuerpo_docu().getText().length()), "Guardado exitosamente");

}

}
