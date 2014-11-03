package com.blackvine.manager;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 战斗管理类
 * 
 * @author songjunfeng
 * 
 */
public class FightManager {
	private static FightManager Instance = null;

	private boolean attackable = true;
	private Actor attacker, getAttacker;

	public boolean isAttackable() {
		return attackable;
	}

	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}

	private FightManager() {

	}
/**
 * 
 * @param attacker 攻击者
 */
	private FightManager(Actor attacker) {
		this.attacker = attacker;
	}
/**
 * 
 * @param attacker 攻击者
 * @param getAttacker 被攻击者
 */
	private FightManager(Actor attacker, Actor getAttacker) {
		this.attacker = attacker;
		this.getAttacker = getAttacker;
	}

	public static FightManager getFightManager() {
		if (Instance == null) {
			Instance = new FightManager();
		}
		return Instance;
	}

	/**
	 * 攻击
	 */
	private void attack() {
		// 从技能管理器中获取到技能进行释放
	}

	/**
	 * 受伤
	 */
	private void getHurt() {
		if (attackable) {
			hpFormula();
		}
	}

	/**
	 * 扣血公式
	 */
	private void hpFormula() {
		
		
	}
}
