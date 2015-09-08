/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model.DAO;

import com.zanvork.wowspring.model.WarcraftGuild;

/**
 *
 * @author jgreeman
 */
public interface WarcraftGuildDAO {
	void save(WarcraftGuild guild);
	void update(WarcraftGuild guild);
	void delete(WarcraftGuild guild);
}
