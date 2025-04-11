/**
 * @author Alejandra Avilés - 24722
 * Última fecha de modificación: 10/04/2025
 * Hoja de trabajo 8 para algoritmos y estructuras de datos
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Clase de pruebas unitarias para la clase Paciente.
 * Verifica el correcto funcionamiento del método compareTo() y toString().
 */
public class PacienteTest {
    /**
     * Prueba que compareTo() funcione correctamente cuando los pacientes tienen
     * el mismo código de emergencia pero diferente hora de llegada.
     * El paciente que llegó primero debe tener mayor prioridad.
     */
    @Test
    public void testCompareToSameCode() {
        LocalDateTime now = LocalDateTime.now();
        Paciente p1 = new Paciente("A", "test", 'A', now);
        Paciente p2 = new Paciente("B", "test", 'A', now.plusMinutes(1));
        
        assertTrue(p1.compareTo(p2) < 0);
    }

    /**
     * Prueba que compareTo() funcione correctamente cuando los pacientes tienen
     * diferente código de emergencia pero misma hora de llegada.
     * El paciente con código de mayor prioridad (A > B) debe tener mayor prioridad.
     */
    @Test
    public void testCompareToDifferentCode() {
        LocalDateTime now = LocalDateTime.now();
        Paciente pA = new Paciente("A", "test", 'A', now);
        Paciente pB = new Paciente("B", "test", 'B', now);
        
        assertTrue(pA.compareTo(pB) < 0);
    }

    /**
     * Prueba que compareTo() funcione correctamente cuando los pacientes tienen
     * el mismo código de emergencia y misma hora de llegada.
     * Debe retornar 0 indicando igual prioridad.
     */
    @Test
    public void testCompareToSameTime() {
        LocalDateTime now = LocalDateTime.now();
        Paciente p1 = new Paciente("A", "test", 'A', now);
        Paciente p2 = new Paciente("B", "test", 'A', now);
        
        assertEquals(0, p1.compareTo(p2));
    }

    /**
     * Prueba que el método toString() retorne el formato esperado:
     * "Nombre, Sintoma, Codigo, FechaHora"
     */
    @Test
    public void testToStringFormat() {
        LocalDateTime time = LocalDateTime.of(2023, 1, 1, 12, 0);
        Paciente p = new Paciente("Nombre", "Sintoma", 'A', time);
        
        String expected = "Nombre, Sintoma, A, 2023-01-01T12:00";
        assertEquals(expected, p.toString());
    }
}