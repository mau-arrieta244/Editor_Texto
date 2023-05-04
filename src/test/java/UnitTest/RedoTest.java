package UnitTest;

import Memento.componenteDocument;
import Memento.documentCaretaker;
import Memento.documentMemento;
import Memento.stateDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class RedoTest {

    @Test
    @DisplayName("Try tu get less than 20 limit changes")
    void lessThantwentylimitChanges(){
        stateDocument estadoActual = new stateDocument();
        documentCaretaker caretaker = new documentCaretaker();
        estadoActual.listaDocumento.add(new componenteDocument("0", new Color(0, 0, 0)));
        documentMemento firstState = estadoActual.createMemento();
        caretaker.addNewMemento(firstState);
        for(int i = 1; i < 10; i++){
            estadoActual.listaDocumento.add(new componenteDocument(String.valueOf(i), new Color(0,0,0)));
            documentMemento newState = estadoActual.createMemento();
            caretaker.addNewMemento(newState);
        }
        while(caretaker.getPreviousMemento()!=null){}
        assertNull(caretaker.getPreviousMemento());
    }

    @Test
    @DisplayName("Try to get all mementos")
    void exactlytwentyMementos(){
        stateDocument estadoActual = new stateDocument();
        documentCaretaker caretaker = new documentCaretaker();
        estadoActual.listaDocumento.add(new componenteDocument("0", new Color(0, 0, 0)));
        documentMemento firstState = estadoActual.createMemento();
        caretaker.addNewMemento(firstState);
        for(int i = 1; i < 20; i++){
            estadoActual.listaDocumento.add(new componenteDocument(String.valueOf(i), new Color(0,0,0)));
            documentMemento newState = estadoActual.createMemento();
            caretaker.addNewMemento(newState);
        }
        int counter = 1;
        while(caretaker.getPreviousMemento()!=null){
            counter++;
        }
        assertEquals(counter,20);
    }

}
