package Memento;

import java.awt.*;
import java.io.Serializable;

public class componenteDocument implements Serializable {
    public String cuerpo;
    public Color colorTexto;
    public componenteDocument(String _cuerpo, Color _color){
        this.cuerpo = _cuerpo;
        this.colorTexto = _color;
    }

    public componenteDocument(){
        this.cuerpo = "";
        this.colorTexto = null;
    }

    public void setCuerpo (String _cuerpo){
        this.cuerpo = _cuerpo;
    }

}
