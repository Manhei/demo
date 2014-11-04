package com.blackvine.manager;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.blackvine.actor.Hero;

/**
 * 战斗管理类
 * 
 * @author songjunfeng
 * 
 */
public class FightManager {
	private static FightManager Instance = null;

	private boolean attackable = true;
	private boolean isAoeAttack = false; // 群攻
	private Actor attacker, getAttacker;
	private int[] attack_Range; // 群攻范围

	public int[] getAttack_Range() {
		return attack_Range;
	}

	public void setAttack_Range(int[] attack_Range) {
		this.attack_Range = attack_Range;
	}

	public boolean isAoeAttack() {
		return isAoeAttack;
	}

	public void setAoeAttack(boolean isAoeAttack) {
		this.isAoeAttack = isAoeAttack;
	}

	public Actor getAttacker() {
		return attacker;
	}

	public void setAttacker(Actor attacker) {
		this.attacker = attacker;
	}

	public Actor getGetAttacker() {
		return getAttacker;
	}

	public void setGetAttacker(Actor getAttacker) {
		this.getAttacker = getAttacker;
	}

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
	 * @param attacker
	 *            攻击者
	 */
	private FightManager(Actor attacker) {
		this.attacker = attacker;
	}

	/**
	 * 
	 * @param attacker
	 *            攻击者
	 * @param getAttacker
	 *            被攻击者
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
	 * 
	 * @param skillNo
	 *            技能编号
	 */
	private void attack(int skillNo) {
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

	/**
	 * 根据人物状态获取群攻的范围
	 * 
	 * @param HeroState
	 */
	public void getAttackRangeByState(int HeroState, int[] attack_Range) {
		switch (HeroState) {
		case Hero.RIGHT:
		case Hero.STATIC_RIGHT:

			break;
		case Hero.UP_RIGHT:
		case Hero.STATIC_UP_RIGHT:

			break;
		case Hero.UP:
		case Hero.STATIC_UP:

			break;
		case Hero.UP_LEFT:
		case Hero.STATIC_UP_LEFT:

			break;
		case Hero.LEFT:
		case Hero.STATIC_LEFT:

			break;
		case Hero.DOWN_LEFT:
		case Hero.STATIC_DOWN_LEFT:

			break;
		case Hero.DOWN:
		case Hero.STATIC_DOWN:

			break;
		case Hero.DOWN_RIGHT:
		case Hero.STATIC_DOWN_RIGHT:

			break;

		}
	}
}
