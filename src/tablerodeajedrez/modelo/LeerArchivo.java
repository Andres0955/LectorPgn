package tablerodeajedrez.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeerArchivo {
    private ArrayList<String> movimientos;
    
    public LeerArchivo(){
        this.movimientos = new ArrayList<>();
    }
    
    public void leerYcargarArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        Scanner scanner = null;
        movimientos.clear();

        try {
            scanner = new Scanner(archivo);
            boolean enMovimientos = false;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();

                // Ignorar líneas de metadatos (que comienzan con '[')
                if (linea.startsWith("[")) {
                    continue; // O puedes procesar metadatos si lo deseas
                }

                // Si encontramos una línea no vacía, es posible que contenga movimientos
                if (!linea.isEmpty()) {
                    // Separar los movimientos por espacios y agregarlos al ArrayList
                    String[] partes = linea.split("\\s+");
                    for (String movimiento : partes) {
                        if (!movimiento.isEmpty()) {
                            movimientos.add(movimiento);
                        }
                    }
                    enMovimientos = true;
                }
            }

            if (!enMovimientos) {
                System.out.println("No se encontraron movimientos en el archivo.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + e.getMessage());
        } finally {
            // Cerrar el Scanner en el bloque finally para liberar recursos
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }
}
