package com.stylefeng.guns.rest.game.statemachine.action;

import com.stylefeng.guns.rest.game.statemachine.impl.ZwwExtentionTransitionConfigurer;
import com.stylefeng.guns.rest.game.statemachine.message.Message;

public interface Action<T,S> {
	void execute(Message<T> message , ZwwExtentionTransitionConfigurer<T, S> configurer); 
}
