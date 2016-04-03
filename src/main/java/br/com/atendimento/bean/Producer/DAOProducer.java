package br.com.atendimento.bean.Producer;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import br.com.atendimento.DAO.GenericDAO;

public class DAOProducer {
		
	
	@SuppressWarnings("rawtypes")
	public GenericDAO create(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];
		return new GenericDAO();

	}
}
