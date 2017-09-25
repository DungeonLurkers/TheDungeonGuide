package tk.avabin.tdg.beans.services.entities;

public interface IEntityService<T> {
    T getByName(String name);

    boolean contains(String name);

    T saveOrUpdate(T c);

    void delete(T c);
}
