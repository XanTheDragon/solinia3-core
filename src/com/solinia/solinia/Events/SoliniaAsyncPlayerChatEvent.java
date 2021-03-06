package com.solinia.solinia.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.solinia.solinia.Interfaces.ISoliniaPlayer;

public class SoliniaAsyncPlayerChatEvent extends Event implements Cancellable  {

	private boolean cancelled;
	private static final HandlerList handlers = new HandlerList();
	private ISoliniaPlayer player;
	private Event rawevent;
	private String message;

	public SoliniaAsyncPlayerChatEvent(Event rawevent, ISoliniaPlayer player, String message) 
    {
		this.player = player;
		this.cancelled = false;
		this.rawevent = rawevent;
		this.message = message;
    }
	
	public boolean isCancelled() {
        return this.cancelled;
    }
 
    public void setCancelled(boolean cancelled) {
    	this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public ISoliniaPlayer getPlayer()
    {
    	return this.player;
    }
    
    public Event getRawEvent()
    {
    	return this.rawevent;
    }
    
    public String getMessage()
    {
    	return this.message;
    }

}
