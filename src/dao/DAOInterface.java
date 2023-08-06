package dao;

import java.util.List;

public interface DAOInterface<T> {
    public int them(T t);

    public int capNhap(T t);

    public int xoa(T t);

    public List<T> selectAll();
    
    public T selectById(String t);
}
