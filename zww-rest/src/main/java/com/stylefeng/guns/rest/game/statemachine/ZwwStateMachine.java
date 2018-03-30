package com.stylefeng.guns.rest.game.statemachine;

import com.stylefeng.guns.rest.game.statemachine.config.StateConfigurer;
import com.stylefeng.guns.rest.game.statemachine.config.StateMachineTransitionConfigurer;
import com.stylefeng.guns.rest.game.statemachine.impl.ZwwStateContext;
import com.stylefeng.guns.rest.game.statemachine.impl.ZwwTransitionConfigurer;

public class ZwwStateMachine<T,S> {
	/**
	 * 
	 */
	private StateConfigurer<String,String> config = new ZwwStateContext<String,String>();
	private StateMachineTransitionConfigurer<T,S> transitions = new ZwwTransitionConfigurer<T,S>() ;
	
	public StateConfigurer<String,String> getConfig() {
		return config;
	}
	public void setConfig(StateConfigurer<String,String> config) {
		this.config = config;
	}
	public StateMachineTransitionConfigurer<T,S> getTransitions() {
		return transitions;
	}
	public void setTransitions(
			StateMachineTransitionConfigurer<T,S> transitions) {
		this.transitions = transitions;
	}
}
