package Memento;

import java.io.Serializable;
import java.util.LinkedList;

public class stateDocument implements Cloneable, Serializable {
    public LinkedList<componenteDocument> listaDocumento;

    public stateDocument (LinkedList<componenteDocument> _lista){
        this.listaDocumento = _lista;
    }
    public stateDocument() {
        this.listaDocumento = new LinkedList<componenteDocument>();
    }

    public stateDocument(stateDocument toClone){
        this.listaDocumento = new LinkedList<componenteDocument>();
        for(componenteDocument componente:toClone.getListaDocumento()){
            this.listaDocumento.add(componente);
        }
    }

    public documentMemento createMemento() {
        try {
            return new documentMemento(new stateDocument(this));
        } catch (Exception e) {
            throw new RuntimeException("Error al clonar el estado");
        }
    }

    public void restoreMemento(documentMemento memento){
        stateDocument estado = memento.getMemento();
        this.listaDocumento = estado.listaDocumento;
    }


    public void add(componenteDocument _componente){
        listaDocumento.add(_componente);
    }

    public void clear(){
        listaDocumento.clear();
    }

    public LinkedList<componenteDocument> getListaDocumento(){
        return listaDocumento;
    }
}
