/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.wowspring.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jgreeman
 */
public enum ItemStatTypes {
    HEALTH(1, "+%s Health"),
    MANA(2, "+%s Mana"),
    AGILITY(3, "+%s Agility"),
    STRENGH(4, "+%s Strength"),
    INTELLECT(5, "+%s Intellect"),
    SPIRIT(6, "+%s Spirit"),
    STAMINA(7, "+%s Stamina"),
    HEALTH_PER_5_SEC(46, "Equip: Restores %s health per 5 sec."),
    ARMOR_PENETRATION(44, "Equip: Increases your armor penetration rating by %s."),
    ATTACK_POWER(38, "Equip: Increases attack power by %s."),
    BLOCK_RATING(15, "Equip: Increases your shield block rating by %s."),
    BLOCK_VALUE(48, "Equip: Increases the block value of your shield by %s."),
    MELEE_CRITICAL_STRIKE_RATING(19, "Equip: Improves melee critical strike rating by %s."),
    RANGED_CRITICAL_STRIKE_RATING(20, "Equip: Improves ranged critical strike rating by %s."),
    CRITICAL_STRIKE_RATING(32, "Equip: Increases your critical strike rating by %s."),
    SPELL_CRITICAL_STRIKE_RATING(21, "Equip: Improves spell critical strike rating by %s."),
    MELEE_CRITICAL_AVOIDANCE_RATING(25, "Equip: Improves melee critical avoidance rating by %s."),
    RANGED_CRITICAL_AVOIDANCE_RATING(26, "Equip: Improves ranged critical avoidance rating by %s."),
    CRITICAL_AVOIDANCE_RATING(34, "Equip: Improves critical avoidance rating by %s."),
    SPELL_CRITICAL_AVOIDANCE_RATING(27, "Equip: Improves spell critical avoidance rating by %s."),
    DEFENSE_RATING(12, "Equip: Increases defense rating by %s."),
    DODGE_RATING(13, "Equip: Increases your dodge rating by %s."),
    EXPERTISE_RATING(37, "Equip: Increases your expertise rating by %s."),
    DRUID_ATTACK_POWER(40, "Equip: Increases attack power by %s in Cat, Bear, Dire Bear, and Moonkin forms only."),
    MELEE_HASTE_RATING(28, "Equip: Improves melee haste rating by %s."),
    RANGED_HASTE_RATING(29, "Equip: Improves ranged haste rating by %s."),
    HASTE_RATING(36, "Equip: Increases your haste rating by %s."),
    SPELL_HASTE_RATING(30, "Equip: Improves spell haste rating by %s."),
    MELEE_HIT_RATING(16, "Equip: Improves melee hit rating by %s."),
    RANGED_HIT_RATING(17, "Equip: Improves ranged hit rating by %s."),
    HIT_RATING(31, "Equip: Increases your hit rating by %s."),
    SPELL_HIT_RATING(18, "Equip: Improves spell hit rating by %s."),
    MELEE_HIT_AVOIDANCE_RATING(22, "Equip: Improves melee hit avoidance rating by %s."),
    RANGED_HIT_AVOIDANCE_RATING(23, "Equip: Improves ranged hit avoidance rating by %s."),
    HIT_AVOIDANCE_RATING(33, "Equip: Improves hit avoidance rating by %s."),
    SPELL_HIT_AVOIDANCE_RATING(24, "Equip: Improves spell hit avoidance rating by %s."),
    MANA_PER_5_SEC(43, "Equip: Restores %s mana per 5 sec."),
    MASTERY_RATING(49, "Equip: Increases your mastery rating by %s."),
    PARRY_RATING(14, "Equip: Increases your parry rating by %s."),
    RANGED_ATTACK_POWER(39, "Equip: Increases ranged attack power by %s."),
    RESILIENCE_RATING(35, "Equip: Increases your resilience rating by %s."),
    DAMAGE_DONE_BY_SPELLS(41, "Equip: Increases damage done by magical spells and effects by up to %s."),
    HEALING_DONE_BY_SPELLS(42, "Equip: Increases healing done by magical spells and effects by up to %s."),
    SPELL_PENETRATION(47, "Equip: Increases spell penetration by %s."),    
    SPELL_POWER(45, "Equip: Increases spell power by %s.");
        
    private static final Map<Integer, ItemStatTypes>   stats       =   new HashMap<>();
    private final int id;
    private final String value;
    
    static{
        for (ItemStatTypes stat : ItemStatTypes.values()){
            stats.put(stat.getId(), stat);
        }
    }
    ItemStatTypes(int id, String value){
        this.id     = id;
        this.value  = value;
    }
    
    public static ItemStatTypes getById(int id){
        return stats.get(id);
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
