package com.zanvork.wowspring.model.DAO;

import com.zanvork.wowspring.model.Guild;
import com.zanvork.wowspring.model.Realm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jgreeman
 */
@Transactional
public interface GuildHibernateDAO extends CrudRepository<Guild, Long>{
    public Guild findByRealmAndName(Realm realm, String name);
}
