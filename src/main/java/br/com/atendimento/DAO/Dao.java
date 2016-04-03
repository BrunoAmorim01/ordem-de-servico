package br.com.atendimento.DAO;

import java.util.List;

public interface Dao<T> {

	public T merge(T t);

	public void remove(T t);

	public List<T> list();

	public T porID(Long codigo);
}
