/**
 * @author Alejandra Avilés - 24722
 * Última fecha de modificación: 10/04/2025
 * Hoja de trabajo 8 para algoritmos y estructuras de datos
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Clase de pruebas unitarias para VectorHeap.
 * Verifica el correcto funcionamiento de las operaciones del montículo.
 */
public class VectorHeapTest {
    /**
     * Prueba la inserción y eliminación de elementos, verificando el orden de prioridad.
     */
    @Test
    public void testInsertAndRemove() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        heap.insert(new Paciente("Juan Pérez", "fractura de brazo", 'C', LocalDateTime.now()));
        heap.insert(new Paciente("Paquita Jimenez", "apendicitis", 'A', LocalDateTime.now()));
        heap.insert(new Paciente("Ana Luz", "dengüe", 'E', LocalDateTime.now()));
        heap.insert(new Paciente("Luisa Quirarte", "dolores de parto", 'B', LocalDateTime.now()));
        
        Paciente primero = heap.remove();
        assertEquals("Paquita Jimenez", primero.getNombre());
        assertEquals("apendicitis", primero.getSintoma());
        assertEquals('A', primero.getCodigoEmergencia());
        
        Paciente segundo = heap.remove();
        assertEquals("Luisa Quirarte", segundo.getNombre());
        assertEquals("dolores de parto", segundo.getSintoma());
        assertEquals('B', segundo.getCodigoEmergencia());
        
        Paciente tercero = heap.remove();
        assertEquals("Juan Pérez", tercero.getNombre());
        assertEquals("fractura de brazo", tercero.getSintoma());
        assertEquals('C', tercero.getCodigoEmergencia());
        
        Paciente cuarto = heap.remove();
        assertEquals("Ana Luz", cuarto.getNombre());
        assertEquals("dengüe", cuarto.getSintoma());
        assertEquals('E', cuarto.getCodigoEmergencia());
    }

    /**
     * Prueba el método isEmpty() con diferentes estados del montículo.
     */
    @Test
    public void testIsEmpty() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertTrue(heap.isEmpty());
        
        heap.insert(new Paciente("Test", "test", 'A', LocalDateTime.now()));
        assertFalse(heap.isEmpty());
    }

    /**
     * Prueba el comportamiento al intentar eliminar de un montículo vacío.
     */
    @Test
    public void testRemoveEmpty() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertThrows(IllegalStateException.class, () -> heap.remove());
    }

    /**
     * Prueba el caso cuando hay pacientes con la misma prioridad.
     * Deberían atenderse en orden de llegada (FIFO para misma prioridad).
     */
    @Test
    public void testSamePriority() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        LocalDateTime now = LocalDateTime.now();
        
        heap.insert(new Paciente("Primero", "test", 'A', now));
        heap.insert(new Paciente("Segundo", "test", 'A', now.plusMinutes(1)));
        
        Paciente p1 = heap.remove();
        assertEquals("Primero", p1.getNombre());
        assertEquals("test", p1.getSintoma());
        assertEquals('A', p1.getCodigoEmergencia());
        
        Paciente p2 = heap.remove();
        assertEquals("Segundo", p2.getNombre());
        assertEquals("test", p2.getSintoma());
        assertEquals('A', p2.getCodigoEmergencia());
    }
}