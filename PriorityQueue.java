/**
 * @author Alejandra Avilés - 24722
 * Última fecha de modificación: 10/04/2025
 * Hoja de trabajo 8 para algoritmos y estructuras de datos
 */

/**
 * Interfaz que define las operaciones básicas de una cola de prioridad.
 * @param <E> el tipo de elementos almacenados en la cola
 */
public interface PriorityQueue<E> {
    /**
     * Inserta un elemento en la cola de prioridad.
     * @param item el elemento a insertar
     */
    void insert(E item);

    /**
     * Elimina y devuelve el elemento con mayor prioridad.
     * @return el elemento con mayor prioridad
     * @throws IllegalStateException si la cola está vacía
     */
    E remove();

    /**
     * Verifica si la cola de prioridad está vacía.
     * @return true si la cola está vacía, false en caso contrario
     */
    boolean isEmpty();
}