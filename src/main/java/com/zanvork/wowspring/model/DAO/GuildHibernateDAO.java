package com.zanvork.wowspring.model.DAO;

import com.zanvork.wowspring.model.Guild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jgreeman
 */
@Transactional
public interface GuildHibernateDAO extends CrudRepository<Guild, Long>{
    
}
