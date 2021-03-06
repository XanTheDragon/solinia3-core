package com.solinia.solinia.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.solinia.solinia.Factories.SoliniaItemFactory;
import com.solinia.solinia.Interfaces.ISoliniaItem;

public class CommandCreateItem implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		
		Player player = (Player)sender;
		if (!player.isOp())
		{
			player.sendMessage("This is an operator only command");
			return false;
		}
		
		ItemStack itemstack = player.getInventory().getItemInMainHand();
        
        if (itemstack.getType().equals(Material.AIR))
        {
        	player.sendMessage(ChatColor.GRAY+"Empty item in hand");
        	return false;
        }
        
        if (itemstack.getEnchantmentLevel(Enchantment.OXYGEN) > 999)
        {
        	player.sendMessage("You can only create a new item from core minecraft items, not solinia items");
        	return true;
        }
        
        player.sendMessage("Building new item based on: " + itemstack.getType().name());
        try
        {
        	ISoliniaItem item = SoliniaItemFactory.CreateItem(itemstack);
        	player.sendMessage("New Item Created with ID: " + item.getId());
        } catch (Exception e)
        {
        	player.sendMessage(e.getMessage());
        }

		return true;
	}
}
