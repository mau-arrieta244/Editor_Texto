package UnitTest;
import Memento.componenteDocument;
import Memento.documentCaretaker;
import Memento.documentMemento;
import Memento.stateDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class UndoTest {


    @Test
    @DisplayName("The limit changes is 20, more than 20 override the first change")
    void twentylimitChanges(){
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
        assertEquals(firstState, caretaker.get(0));
    }


    @Test
    @DisplayName("Try to get memento without changes")
    void getPreviousMementoWithoutChanges(){
        stateDocument estadoActual = new stateDocument();
        documentCaretaker caretaker = new documentCaretaker();
        assertNull(caretaker.getPreviousMemento());
    }
    @Test
    @DisplayName("Try to get memento with changes")
    void getPreviousMementoWithChanges(){
        stateDocument estadoActual = new stateDocument();
        documentCaretaker caretaker = new documentCaretaker();
        estadoActual.listaDocumento.add(new componenteDocument("Text", new Color(0, 0, 0)));
        documentMemento firstState = estadoActual.createMemento();
        caretaker.addNewMemento(firstState);
        assertEquals(firstState, caretaker.getCurrentMemento());
        assertNull(caretaker.getPreviousMemento());
    }

}
