package UnitTest;

import GUI.mainWindow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class AbrirDocumento {

    @Test
    @DisplayName("btnAbrir's mainWindow should open a file with a name")
    void openExistingFile() {
        final String fileName = "fire.json";
        final String fileWindowName = fileName.split("\\.")[0];

        final mainWindow window = new mainWindow(fileName);
        assertEquals(window.getTextFieldNombre_docu().getText(), fileWindowName);
    }

    @Test
    @DisplayName("btnAbrir's mainWindow should throw and error if the file is not found")
    void openNonExistingFile() {
        final String fileName = "mock.txt";

        Exception error = assertThrows(RuntimeException.class, () -> {
            mainWindow window = new mainWindow(fileName);
        });

        final String expectedError = "java.lang.RuntimeException";
        final String actualError = error.toString().split(":")[0];

        assertEquals(actualError, expectedError);

    }

}
