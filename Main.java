/**
 * @author Alejandra Avilés - 24722
 * Última fecha de modificación: 10/04/2025
 * Hoja de trabajo 8 para algoritmos y estructuras de datos
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Clase principal que implementa un sistema de atención de emergencias hospitalarias.
 * Permite gestionar pacientes usando tanto VectorHeap como PriorityQueue de Java.
 * Ofrece un menú interactivo para:
 * 1. Seleccionar el tipo de cola de prioridad
 * 2. Añadir nuevos pacientes
 * 3. Procesar la atención según prioridad
 */
public class Main {
    /**
     * Método principal que muestra el menú y gestiona el flujo del programa.
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione el método de atención de pacientes:");
            System.out.println("1. Usar VectorHeap.");
            System.out.println("2. Usar PriorityQueue.");
            System.out.println("3. Salir.");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    atencionDeEmergencias(scanner);
                    break;
                case 2:
                    atencionDeEmergenciasJC(scanner);
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    /**
     * Gestiona la atención de emergencias usando VectorHeap.
     * @param scanner Objeto Scanner para entrada de usuario
     */
    private static void atencionDeEmergencias(Scanner scanner) {
        VectorHeap<Paciente> colaPrioridad = new VectorHeap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        /* Lectura del txt */
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String sintoma = datos[1].trim();
                char codigoEmergencia = datos[2].trim().charAt(0);
                LocalDateTime horaLlegada = LocalDateTime.parse(datos[3].trim(), formatter);
                Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia, horaLlegada);
                colaPrioridad.insert(paciente);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        /* Añadir nuevos pacientes */
        boolean agregarPacientes = true;
        while (agregarPacientes) {
            System.out.println("¿Desea añadir un nuevo paciente? (s/n)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")){
                System.out.println("Ingrese el nombre del paciente:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese el síntoma del paciente:");
                String sintoma = scanner.nextLine();
                System.out.println("Ingrese el código de emergencia (De la A a la E):");
                char codigoEmergencia = scanner.nextLine().trim().toUpperCase().charAt(0);
                LocalDateTime horaLlegada = LocalDateTime.now();
                Paciente nuevoPaciente = new Paciente(nombre, sintoma, codigoEmergencia, horaLlegada);
                colaPrioridad.insert(nuevoPaciente);

                try (FileWriter fw = new FileWriter("pacientes.txt", true)) {
                    fw.write(nombre + "," + sintoma + "," + codigoEmergencia + "," + horaLlegada + "\n");
                } catch (IOException e) {
                    System.err.println("Error al guardar al paciente: " + e.getMessage());
                }
            } else {
                agregarPacientes = false;
            }
        }
        /* Procesamiento de la cola de prioridad */
        if (colaPrioridad.isEmpty()) {
            System.out.println("No se han insertado pacientes.");
            return;
        }
        while (!colaPrioridad.isEmpty()) {
            Paciente siguientePaciente = colaPrioridad.remove();
            System.out.println("Atendiendo a: " + siguientePaciente);
        }
    }
    
    /**
     * Gestiona la atención de emergencias usando PriorityQueue de Java Collections.
     * @param scanner Objeto Scanner para entrada de usuario
     */
    private static void atencionDeEmergenciasJC(Scanner scanner) {
        PriorityQueue<Paciente> colaPrioridad = new PriorityQueue<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        /* Lectura del txt */
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String sintoma = datos[1].trim();
                char codigoEmergencia = datos[2].trim().charAt(0);
                LocalDateTime horaLlegada = LocalDateTime.parse(datos[3].trim(), formatter);
                Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia, horaLlegada);
                colaPrioridad.add(paciente);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        /* Añadir nuevos pacientes */
        boolean agregarPacientes = true;
        while (agregarPacientes) {
            System.out.println("¿Desea añadir un nuevo paciente? (s/n)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")){
                System.out.println("Ingrese el nombre del paciente:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese el síntoma del paciente:");
                String sintoma = scanner.nextLine();
                System.out.println("Ingrese el código de emergencia (De la A a la E):");
                char codigoEmergencia = scanner.nextLine().trim().toUpperCase().charAt(0);
                LocalDateTime horaLlegada = LocalDateTime.now();
                Paciente nuevoPaciente = new Paciente(nombre, sintoma, codigoEmergencia, horaLlegada);
                colaPrioridad.add(nuevoPaciente);

                try (FileWriter fw = new FileWriter("pacientes.txt", true)) {
                    fw.write(nombre + "," + sintoma + "," + codigoEmergencia + "," + horaLlegada + "\n");
                } catch (IOException e) {
                    System.err.println("Error al guardar al paciente: " + e.getMessage());
                }
            } else {
                agregarPacientes = false;
            }
        }
        /* Procesamiento de la cola de prioridad */
        if (colaPrioridad.isEmpty()) {
            System.out.println("No se han insertado pacientes.");
            return;
        }
        while (!colaPrioridad.isEmpty()) {
            Paciente siguientePaciente = colaPrioridad.remove();
            System.out.println("Atendiendo a: " + siguientePaciente);
        }
    }
}