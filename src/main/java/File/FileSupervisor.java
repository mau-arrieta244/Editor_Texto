package File;
import java.io.*;
import java.nio.file.*;

import Enums.ComboColor;
import Memento.*;

public class FileSupervisor {

    Path currentRelativePath = Paths.get("");
    String stringRelativePathDocumentos = currentRelativePath.toAbsolutePath().toString().concat("\\Saved documents");
    String stringRelativePathSerializables = currentRelativePath.toAbsolutePath().toString().concat("\\Serializables");
    public FileSupervisor(){
        try {
            Files.createDirectories(Paths.get(stringRelativePathDocumentos));
            Files.createDirectories(Paths.get(stringRelativePathSerializables));
        } catch (IOException e) {
        }
    }

    public String guardarComo(stateDocument estadoRecibido,String nombreDocumento, String extensionDocumento, int lengthDocument) throws IOException {
        Path pathDocumentos = Paths.get(stringRelativePathDocumentos.concat("\\"+nombreDocumento+extensionDocumento));
        Path pathSerializable = Paths.get(stringRelativePathSerializables.concat("\\"+nombreDocumento+extensionDocumento+".txt"));
        String lineaEscrita = "";
        String colorTexto = "";
        int acumuladorLength = 0;
        int contadorTabuladores = 0;
        FileWriter writerDocumentos;
        if (Files.exists(pathDocumentos)) {
            return "El documento ya existe, por favor, utilice el botón guardar";
        }
        else if (nombreDocumento.equals("")){
            return "Por favor, ingrese un nombre válido para el documento";
        }
        else{
            //Se crea el serializable y se guarda
            File fileSerializble = new File(pathSerializable.toString());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSerializble));
            oos.writeObject(estadoRecibido);
            oos.flush();
            oos.close();
            //Se crea el documento con el texto crudo y los colores
            File fileDocumentos = new File(pathDocumentos.toString());
            writerDocumentos = new FileWriter(fileDocumentos);
            for(componenteDocument componenteIterado : estadoRecibido.getListaDocumento()){
                if (extensionDocumento.equals(".ttxt") && lengthDocument >= 10){
                    for(int i = 1;i <= componenteIterado.cuerpo.length()-contadorTabuladores; i++){
                        if ((i+acumuladorLength) % 10 == 0){
                            componenteIterado.setCuerpo(addChar(componenteIterado.cuerpo, '\t',i+contadorTabuladores));
                            contadorTabuladores++;
                        }
                    }
                    acumuladorLength = acumuladorLength + componenteIterado.cuerpo.length();
                }
                for (ComboColor colorIterable: ComboColor.values()){
                    if(colorIterable.getColor() == componenteIterado.colorTexto){
                        colorTexto = colorIterable.getText();
                    }
                }
                lineaEscrita = "["+componenteIterado.cuerpo+","+colorTexto+"]\n";
                writerDocumentos.write(lineaEscrita);
            }
            writerDocumentos.close();
            return "Guardado exitosamente";
        }
    }

    public String guardar(stateDocument estadoRecibido,String nombreDocumento, String extensionDocumento, int lengthDocument) throws IOException {
        Path pathDocumentos = Paths.get(stringRelativePathDocumentos.concat("\\"+nombreDocumento+extensionDocumento));
        Path pathSerializable = Paths.get(stringRelativePathSerializables.concat("\\"+nombreDocumento+extensionDocumento+".txt"));
        String lineaEscrita = "";
        String colorTexto = "";
        int acumuladorLength = 0;
        int contadorTabuladores = 0;
        FileWriter writerDocumentos;
        if (!Files.exists(pathDocumentos)) {
            return "El documento no existe, por favor, utilice el botón guardar como";
        }
        else if (nombreDocumento.equals("")){
            return "Por favor, ingrese un nombre válido para el documento";
        }
        else{
            //Se crea el serializable y se guarda
            File fileSerializble = new File(pathSerializable.toString());
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSerializble));
            oos.writeObject(estadoRecibido);
            oos.flush();
            oos.close();
            //Se crea el documento con el texto crudo y los colores
            File fileDocumentos = new File(pathDocumentos.toString());
            writerDocumentos = new FileWriter(fileDocumentos);
            for(componenteDocument componenteIterado : estadoRecibido.getListaDocumento()){
                if (extensionDocumento.equals(".ttxt") && lengthDocument >= 10){
                    for(int i = 1;i <= componenteIterado.cuerpo.length()-contadorTabuladores; i++){
                        if ((i+acumuladorLength) % 10 == 0){
                            componenteIterado.setCuerpo(addChar(componenteIterado.cuerpo, '\t',i+contadorTabuladores));
                            contadorTabuladores++;
                        }
                    }
                    acumuladorLength = acumuladorLength + componenteIterado.cuerpo.length();
                }
                for (ComboColor colorIterable: ComboColor.values()){
                    if(colorIterable.getColor() == componenteIterado.colorTexto){
                        colorTexto = colorIterable.getText();
                    }
                }
                lineaEscrita = "["+componenteIterado.cuerpo+","+colorTexto+"]\n";
                writerDocumentos.write(lineaEscrita);
            }
            writerDocumentos.close();
            return "Guardado exitosamente";
        }
    }

    public String[] getArchivos(){
        String[] res = null;
        File directory = new File(stringRelativePathDocumentos);
        String[] contents = directory.list();
        return contents;
    }
    public String addChar(String str, char ch, int position) {
        int len = str.length();
        char[] updatedArr = new char[len + 1];
        str.getChars(0, position, updatedArr, 0);
        updatedArr[position] = ch;
        str.getChars(position, len, updatedArr, position + 1);
        return new String(updatedArr);
    }

    public Object getDocumentObject(String nombreDocumento){
        String pathObjeto = stringRelativePathSerializables.concat("\\"+nombreDocumento+".txt");
        Object result = null;
        try (FileInputStream fis = new FileInputStream(pathObjeto);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
