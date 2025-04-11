/**
 * @author Alejandra Avilés - 24722
 * Última fecha de modificación: 10/04/2025
 * Hoja de trabajo 8 para algoritmos y estructuras de datos
 */

import java.util.ArrayList;

/**
 * Implementación de un montículo (heap) usando un Vector (ArrayList) como estructura subyacente.
 * Esta clase implementa la interfaz PriorityQueue para proveer operaciones de cola de prioridad.
 * @param <E> el tipo de elementos almacenados en el montículo, debe implementar Comparable
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    private ArrayList<E> data;

    /**
     * Constructor que inicializa un VectorHeap vacío.
     */
    public VectorHeap() {
        data = new ArrayList<>();
    }

    /**
     * Inserta un elemento en el montículo y lo reordena para mantener la propiedad de heap.
     * Complejidad: O(log n)
     * @param item el elemento a insertar
     */
    @Override
    public void insert(E item) {
        data.add(item);
        siftUp(data.size() - 1);
    }

    /**
     * Elimina y devuelve el elemento con mayor prioridad (raíz del montículo).
     * Reordena el montículo para mantener la propiedad de heap.
     * Complejidad: O(log n)
     * @return el elemento con mayor prioridad
     * @throws IllegalStateException si el montículo está vacío
     */
    @Override
    public E remove() {
        if (data.isEmpty()) {
            throw new IllegalStateException("No se puede remover de un montículo vacío.");
        }
        if (data.size() == 1) {
            return data.remove(0);
        }
        E result = data.get(0); /* Guarda el primer elemento */
        data.set(0, data.remove(data.size() - 1)); /* Mueve el último elemento a la raíz */
        siftDown(0); /* Reorganización del heap */
        return result;
    }

    /**
     * Reordena el montículo hacia arriba desde el índice especificado.
     * @param index el índice desde donde comenzar el reordenamiento
     */
    private void siftUp(int index) {
        E item = data.get(index);
        while (index > 0) {
            int parentIndex = (index-1)/2;
            E parent = data.get(parentIndex);
            if (item.compareTo(parent) >= 0) {
                break; /* El elemento ha llegado a la posición correcta */
            }
            data.set(index, parent); /* Desplaza al padre hacia abajo */
            index = parentIndex; /* El índice se mueve hacia arriba */
        }
        data.set(index, item); /* Posiciona al elemento en la posición correcta */
    }

    /**
     * Reordena el montículo hacia abajo desde el índice especificado.
     * @param index el índice desde donde comenzar el reordenamiento
     */
    private void siftDown(int index){
        E item = data.get(index);
        int half = data.size()/2;
        while (index < half) {
            int childIndex = 2*index +1; /* Hijo izquierdo */
            E child = data.get(childIndex);
            int rightIndex = childIndex +1; /* Hijo derecho */
            /* Verificación para saber si hay un hijo derecho y si éste es menor que el hijo izquierdo */
            if (rightIndex < data.size() && child.compareTo(data.get(rightIndex)) > 0) {
                childIndex = rightIndex; /* Uso del hijo derecho */
                child = data.get(childIndex);
            }
            /* Si el elemento es menor o igual que el hijo, su posición es correcta */
            if (item.compareTo(child) <= 0) {
                break;
            }
            data.set(index, child);
            index = childIndex;
        }
        data.set(index, item); /* Posiciona correctamente al elemento */
    }

    /**
     * Verifica si el montículo está vacío.
     * @return true si el montículo está vacío, false en caso contrario
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}