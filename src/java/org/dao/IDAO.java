
package org.dao;

import java.util.List;

/**
 *
 * @author gabrielhs
 * @param <T>
 */
public interface IDAO <T> {
    public boolean ingresar(T pojo);
    public boolean actualizar (T pojo);
    public boolean eliminar (String id);
    public T mostrarById (String id);
    public List<T> mostrarAll();
    
    
}
