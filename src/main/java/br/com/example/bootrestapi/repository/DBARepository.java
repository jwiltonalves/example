package br.com.example.bootrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.bootrestapi.model.DBAEntity;

@Repository
public interface DBARepository extends JpaRepository<DBAEntity, Integer> {
	public List<DBAEntity> findAllByTamanhoGalha(Integer tamanhoGalha);
}
