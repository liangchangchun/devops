package com.stylefeng.guns.rest.game;

import com.stylefeng.guns.rest.game.engine.state.GameEvent;
import com.stylefeng.guns.rest.game.statemachine.impl.MessageBuilder;
import com.stylefeng.guns.rest.game.statemachine.impl.ZwwMachineHandler;

/*
 * Copyright 2015 the original author or authors. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */


public class Game { 

	private final ZwwMachineHandler handler; 

	public Game(ZwwMachineHandler handler) { 
		this.handler = handler; 
	} 
	public void change(GameRoom gameRoom , String event) { 
		change(gameRoom, event , 0);
	}
	
	public void change(GameRoom gameRoom , String event , int interval ) { 
		handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("room", gameRoom.getId()).setHeader("interval", interval).build(), event);
	}
	
	public void change(GameEvent gameEvent) { 
		change(gameEvent , 0); 
	} 
	
	public void change(GameEvent gameEvent , int interval ) { 
		handler.handleEventWithState(MessageBuilder.withPayload(gameEvent.getEvent()).setHeader("room", gameEvent.getRoomid()).setHeader("interval", interval).build(), gameEvent.getEvent()); 
	} 
}
