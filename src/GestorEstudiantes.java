import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorEstudiantes {

    // Calcula la nota media de un estudiante
    public static double calcularNotaMedia(Estudiante estudiante) {
        {
            double[] notas = estudiante.getNotas();
            if (notas == null || notas.length == 0) {
                return 0.0;
            } // Comprobamos el caso en el que las notas son 0 o nulas
            double suma = 0;
            for (int i = 0; i < notas.length; i++) {
                suma += notas[i]; // notas mayores a 0 y con el for bien realizaod para que no se salga del codigo
            }
            return suma / notas.length;
        }
    }

    // Encuentra al estudiante con la mejor nota media
    public static Estudiante encontrarMejorEstudiante(Estudiante[] estudiantes) {
        if (estudiantes == null || estudiantes.length == 0) {
            return null; // Evita errores cuando el estudiante este vació
        }

        Estudiante mejor = null;
        double mejorNota = -1;

        for (Estudiante estudiante : estudiantes) {
            double media = calcularNotaMedia(estudiante);
            if (media > mejorNota) {
                mejorNota = media;
                mejor = estudiante;
            }
        }
        return mejor;
    }

    // Guarda los resultados en un fichero
    public static void guardarResultados(Estudiante[] estudiantes, String rutaFichero) {
        if (estudiantes == null || rutaFichero == null || rutaFichero.isEmpty()) {
            // Si el array de estudiantes o la ruta del fichero es null o vacía, mostramos un mensaje de error
            System.out.println("Datos inválidos para guardar resultados.");
            return; // Salimos del método si los datos no son válidos
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Estudiante estudiante : estudiantes) {
                // Comprobamos que el estudiante no sea null
                if (estudiante != null) {
                    double notaMedia = calcularNotaMedia(estudiante);
                    writer.write("Nombre: " + estudiante.getNombre() + ", Nota Media: " + notaMedia);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            // Capturamos cualquier error
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }
}
