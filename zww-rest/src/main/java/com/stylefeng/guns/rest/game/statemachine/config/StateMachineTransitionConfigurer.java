package com.stylefeng.guns.rest.game.statemachine.config;

import com.stylefeng.guns.rest.game.statemachine.impl.ZwwExtentionTransitionConfigurer;

public interface StateMachineTransitionConfigurer<S,E> {
	/**
	 * Gets a configurer for external transition.
	 *
	 * @return {@link ExternalTransitionConfigurer} for chaining
	 * @throws Exception if configuration error happens
	 * @see #withLocal()
	 */
	ExternalTransitionConfigurer<S, E> withExternal() throws Exception;
	
	void apply(ZwwExtentionTransitionConfigurer<S, E> transition);
	
	ZwwExtentionTransitionConfigurer<S,E> transition(S event) ; 
}
