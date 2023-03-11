package com.cst438.domain;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MultiplyAttemptRepository extends CrudRepository <MultiplyAttempt, Integer>  {

	@Query("select a from MultiplyAttempt a where a.user.alias=:alias order by a.id desc")
	public Iterable<MultiplyAttempt> findByAliasOrderByIdDesc(@Param("alias") String alias);
	
}
