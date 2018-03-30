package com.stylefeng.guns.rest.game.statemachine.impl;

import java.util.HashMap;
import java.util.Map;

import com.stylefeng.guns.rest.game.statemachine.config.ExternalTransitionConfigurer;
import com.stylefeng.guns.rest.game.statemachine.config.StateMachineTransitionConfigurer;


public class ZwwTransitionConfigurer<T,S> implements StateMachineTransitionConfigurer<T,S>{
	
	private Map<S, ZwwExtentionTransitionConfigurer<T, S>> transitions = new HashMap<S,ZwwExtentionTransitionConfigurer<T,S>>();
	
	@Override
	public ExternalTransitionConfigurer<T, S> withExternal() throws Exception {
		return new ZwwExtentionTransitionConfigurer<T, S>(this);
	}

	@Override
	public void apply(ZwwExtentionTransitionConfigurer<T, S> transition) {
		transitions.put(transition.getEvent(), transition);
	}

	@Override
	public ZwwExtentionTransitionConfigurer<T, S> transition(T event) {
		return transitions.get(event);
	}
}
