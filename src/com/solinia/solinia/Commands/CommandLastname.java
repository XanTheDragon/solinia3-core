package com.solinia.solinia.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.solinia.solinia.Adapters.SoliniaPlayerAdapter;
import com.solinia.solinia.Exceptions.CoreStateInitException;
import com.solinia.solinia.Managers.StateManager;

public class CommandLastname implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
		{
			sender.sendMessage("This is a Player only command");
			return false;
		}
		
		Player player = (Player)sender;
		try {
			player.sendMessage("Old Lastname: " + SoliniaPlayerAdapter.Adapt(player).getLastname());
			
			if (args.length == 0)
				return false;
			
			if (!StateManager.getInstance().getPlayerManager().IsNewNameValid(SoliniaPlayerAdapter.Adapt(player).getForename(), args[0]))
			{
				player.sendMessage("Forename + Lastname length must be between 3 and 14 characters and not in use by other players");
				
				String newname = SoliniaPlayerAdapter.Adapt(player).getForename();
				if (!args[0].equals(""))
					newname = SoliniaPlayerAdapter.Adapt(player).getForename() + "_" + args[0];
				
				player.sendMessage("Target name [" + newname + "] length: " + newname.length());
				return false;
			}
			
			SoliniaPlayerAdapter.Adapt(player).setLastname(args[0]);
			sender.sendMessage("* Lastname set");
			return true;
			
		} catch (CoreStateInitException e) {
			// TODO Auto-generated catch block
			sender.sendMessage(e.getMessage());
		}

		return false;
	}
}
