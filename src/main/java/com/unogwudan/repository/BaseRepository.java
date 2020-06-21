 package com.unogwudan.repository;

 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.repository.NoRepositoryBean;
 import javax.persistence.EntityManager;
 import java.io.Serializable;

 /**
  * Created by Daniel Unogwu on 21/06/20.
  */
 @NoRepositoryBean
 public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
     EntityManager getEntityManager();

     T find(ID id);

     T create(T entity);

     T update(T entity);
 }
