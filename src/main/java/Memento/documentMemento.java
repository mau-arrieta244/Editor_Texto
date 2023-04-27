package Memento;

public class documentMemento {
    public stateDocument estado;
    public documentMemento(stateDocument _estado){
        this.estado = _estado;
    }

    public stateDocument getMemento(){
        return estado;
    }
}
