package com.eva.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Arte.class)
public class Arte_ {
	
	public static volatile SingularAttribute<Arte, Long> codigo;
	public static volatile SingularAttribute<Arte, String> nome;
	public static volatile SingularAttribute<Arte, String> descricao;
	public static volatile SingularAttribute<Arte, LocalDate> data;
	public static volatile SingularAttribute<Arte, BigDecimal> valor;
	
}
