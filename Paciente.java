/**
 * @author Alejandra Avilés - 24722
 * Última fecha de modificación: 10/04/2025
 * Hoja de trabajo 8 para algoritmos y estructuras de datos
 */

import java.time.LocalDateTime;

/**
 * Clase que representa un paciente en el sistema de atención de emergencias.
 * Implementa Comparable para permitir la comparación basada en prioridad.
 */
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma; 
    private char codigoEmergencia;
    private LocalDateTime horaLlegada;

    /**
     * Constructor para crear un nuevo paciente.
     * @param nombre nombre completo del paciente
     * @param sintoma descripción del síntoma o condición
     * @param codigoEmergencia código de prioridad (A-E, donde A es mayor prioridad)
     * @param horaLlegada fecha y hora de llegada del paciente
     */
    public Paciente(String nombre, String sintoma, char codigoEmergencia, LocalDateTime horaLlegada){
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = codigoEmergencia;
        this.horaLlegada = horaLlegada;
    }

    /**
     * Obtiene el nombre del paciente.
     * @return nombre del paciente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene el síntoma o condición del paciente.
     * @return descripción del síntoma
     */
    public String getSintoma() {
        return sintoma;
    }
    
    /**
     * Obtiene el código de emergencia del paciente.
     * @return código de prioridad (A-E)
     */
    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }
    
    /**
     * Obtiene la fecha y hora de llegada del paciente.
     * @return objeto LocalDateTime con la hora de llegada
     */
    public LocalDateTime getHoraLlegada(){
        return horaLlegada;
    }
    
    /**
     * Compara este paciente con otro basado en prioridad.
     * Primero compara por código de emergencia, luego por hora de llegada.
     * @param otro el otro paciente a comparar
     * @return valor negativo si este paciente tiene mayor prioridad,
     *         positivo si tiene menor prioridad, 0 si igual prioridad
     */
    @Override
    public int compareTo(Paciente otro) {
        int cmp = Character.compare(this.codigoEmergencia, otro.codigoEmergencia);
        if (cmp == 0) {
            return this.horaLlegada.compareTo(otro.horaLlegada);
        }
        return cmp;
    }

    /**
     * Representación en String del paciente.
     * @return String con nombre, síntoma, código y hora de llegada
     */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia + ", " + horaLlegada;
    }
}