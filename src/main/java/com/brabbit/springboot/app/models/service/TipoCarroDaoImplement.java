package com.brabbit.springboot.app.models.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brabbit.springboot.app.models.entity.Direccion;
import com.brabbit.springboot.app.models.entity.TipoCarro;
import com.brabbit.springboot.app.models.entity.TipoGas;
import com.brabbit.springboot.app.models.entity.TipoUsuario;
import com.brabbit.springboot.app.models.entity.Vehiculo;

@Repository
@EntityScan("com.brabbit.springboot.app.models.entity")
public class TipoCarroDaoImplement implements InterfaceTipoCarroDao {

	@PersistenceContext
	private EntityManager em;


	@Transactional
	@Override
	public void save(TipoCarro tipocarro) {
		// TODO Auto-generated method stub
		em.persist(tipocarro);

	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<TipoCarro> findAll() {
		List tipos = em.createQuery("SELECT e FROM TipoCarro e", TipoCarro.class).getResultList();
		
		return tipos;
	}

	public TipoCarro findOne(long tipo) {
		return em.find(TipoCarro.class, tipo);
	}


	
}
