package me.ziprow.qolplugin.events;

import me.ziprow.qolplugin.QoLPlugin;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.EulerAngle;

public class EditStands implements Listener
{
	
	@EventHandler
	public void onSignClick(PlayerInteractAtEntityEvent e)
	{
		Entity b = e.getRightClicked();
		
		if(!(b instanceof ArmorStand) || !e.getPlayer().isSneaking()) return;

		ArmorStand stand = (ArmorStand)b;

		if(!b.hasMetadata("poseNb"))
			b.setMetadata("poseNb", new FixedMetadataValue(QoLPlugin.getInstance(), 0));

		final int poseNb = (b.getMetadata("poseNb").get(0).asInt() + 1) % 10;

		b.setMetadata("poseNb", new FixedMetadataValue(QoLPlugin.getInstance(), poseNb));

		switch(poseNb)
		{
			default:
			case 0:
				b.getWorld().spawn(b.getLocation(), ArmorStand.class);
				b.remove();
				break;

			case 1:
				stand.setArms(true);
				break;

			case 2:
				stand.setHeadPose(new EulerAngle(0.2, 0, -0.02));
				stand.setBodyPose(new EulerAngle(0.01, 0, 0.03));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(-0.7, 0.4, 0.2));
				stand.setRightArmPose(new EulerAngle(-1.1, -0.1, -0.4));
				stand.setLeftLegPose(new EulerAngle(0, 0, -0.02));
				stand.setRightLegPose(new EulerAngle(0, 0, 0.02));
				break;

			case 3:
				stand.setHeadPose(new EulerAngle(0.2, 0, -0.02));
				stand.setBodyPose(new EulerAngle(0.01, 0, 0.03));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(0.1, 0, -0.05));
				stand.setRightArmPose(new EulerAngle(-1, 0.3, 0));
				stand.setLeftLegPose(new EulerAngle(-0.03, 0, -0.02));
				stand.setRightLegPose(new EulerAngle(0.04, 0, 0.02));
				break;

			case 4:
				stand.setHeadPose(new EulerAngle(-0.2, 0, -0.02));
				stand.setBodyPose(new EulerAngle(-0.015, 0, -0.04));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(0.2, 0, -0.2));
				stand.setRightArmPose(new EulerAngle(-1.9, 0.6, 0));
				stand.setLeftLegPose(new EulerAngle(0.03, 0, -0.02));
				stand.setRightLegPose(new EulerAngle(-0.04, 0.03, 0.06));
				break;

			case 5:
				stand.setHeadPose(new EulerAngle(-0.02, 0, -0.01));
				stand.setBodyPose(new EulerAngle(0, 0.04, 0));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(-1.9, 0.6, 0));
				stand.setRightArmPose(new EulerAngle(-1.9, -0.6, 0));
				stand.setLeftLegPose(new EulerAngle(0.03, 0, -0.02));
				stand.setRightLegPose(new EulerAngle(-0.04, 0.03, 0.06));
				break;

			case 6:
				stand.setHeadPose(new EulerAngle(-0.02, 0, -0.01));
				stand.setBodyPose(new EulerAngle(0, 0.04, 0));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(-1.9, -0.5, 0));
				stand.setRightArmPose(new EulerAngle(-1.9, 0.5, 0));
				stand.setLeftLegPose(new EulerAngle(0.03, 0, -0.02));
				stand.setRightLegPose(new EulerAngle(-0.04, 0.03, 0.06));
				break;

			case 7:
				stand.setHeadPose(new EulerAngle(-0.02, 0, -0.01));
				stand.setBodyPose(new EulerAngle(0, 0.04, 0));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(0.1, 0, -0.05));
				stand.setRightArmPose(new EulerAngle(-1.2, -0.6, 0));
				stand.setLeftLegPose(new EulerAngle(-0.02, 0, -0.02));
				stand.setRightLegPose(new EulerAngle(0.03, 0.03, 0.06));
				break;

			case 8:
				stand.setHeadPose(new EulerAngle(-0.04, 0.1, 0));
				stand.setBodyPose(new EulerAngle(0, 0.04, 0));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(3, 0, 1.5));
				stand.setRightArmPose(new EulerAngle(-1.5, -0.9, 0));
				stand.setLeftLegPose(new EulerAngle(-0.04, -0.3, -0.02));
				stand.setRightLegPose(new EulerAngle(0.03, 0.2, 0.06));
				break;

			case 9:
				stand.setHeadPose(new EulerAngle(-0.1, 0.05, -0.1));
				stand.setBodyPose(new EulerAngle(0, 0, 0));
				stand.setArms(true);
				stand.setLeftArmPose(new EulerAngle(-1.7, -0.1, 0));
				stand.setRightArmPose(new EulerAngle(-1.7, 0.1, 0));
				stand.setLeftLegPose(new EulerAngle(0.1, 0, 0));
				stand.setRightLegPose(new EulerAngle(-0.4, 0, 0));
				break;
		}
	}
	
}
