package Memento;
import java.util.LinkedList;

public class documentCaretaker {

    private int currentIndex = -1;
    private final LinkedList<documentMemento> states = new LinkedList<>();

    public void addNewMemento(documentMemento newMemento){
        if (currentIndex + 1>=20){
            states.remove(0);
        }
        else{
            currentIndex++;
        }
        states.subList(currentIndex, states.size()).clear();
        states.add(newMemento);
    }

    public documentMemento getCurrentMemento(){
        return states.get(currentIndex);
    }

    public documentMemento getNextMemento(){
        int newIndex = currentIndex +1;
        if( newIndex >= states.size()){
            return null;
        }

        currentIndex = newIndex;
        return states.get(currentIndex);
    }

    public documentMemento getPreviousMemento() {
        int newIndex = currentIndex-1;

        if(newIndex  <= -1 || newIndex >= states.size()-1){
            return null;
        }
        currentIndex= newIndex;

        return states.get(currentIndex);
    }
}
