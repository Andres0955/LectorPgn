package reproductorPgn.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase es la encargada de leer el archivo seleccionado por el usuario y cargar los movimientos de la partida.
 * @author Cesar Acosta
 */
public class LeerArchivo {
    private ArrayList<String> movimientos;
    
    /**
     * Constructor de la clas LeerArchivo, inicializa el arrayList donde se almacenaran los movientos leidos.
     */
    public LeerArchivo(){
        this.movimientos = new ArrayList<>();
    }
    
    /**
     * Lee el archivo suministrado linea a linea y carga los movimientos encontrados.
     * @param archivo archivo seleccionado por el usuario.
     * @return el arrayList <code>movimientos</code> con los movientos
     */
    public ArrayList<String> leerYcargarArchivo(File archivo) {
        Scanner scanner = null;
        movimientos.clear();

        try {
            scanner = new Scanner(archivo);
            boolean hayMovimientos = false;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();

                // Ignorar líneas de metadatos (que comienzan con '[')
                if (linea.startsWith("[")) {
                    continue; // O puedes procesar metadatos si lo deseas
                }

                // Si encontramos una línea no vacía, es posible que contenga movimientos
                if (!linea.isEmpty()) {
                    String movimientosSinTurno = linea.replaceAll("\\d+\\.\\s*", "");
                    //El metod .split pertence al objeto String y perimite borrar los espacios en blancos mediante la expresion regular.
                    String[] partes = movimientosSinTurno.split("\\s+");
                    for (String movimiento : partes) {
                        if (!movimiento.isEmpty()) {
                            movimientos.add(movimiento);
                        }
                    }
                    hayMovimientos = true;
                }
            }

            if (!hayMovimientos) {
                System.out.println("No se encontraron movimientos en el archivo.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + archivo);
        } finally {
            // Cerrar el Scanner en el bloque finally para liberar recursos
            if (scanner != null) {
                scanner.close();
            }
        }
        
        return movimientos;
    }
    
    /**
     * Obtiene los movimientos cargados en el arrayList.
     * @return el arrayList <code>movimientos</code> con los movimientos.
     */
    public ArrayList<String> getMovimientos() {
        return movimientos;
    }
}
