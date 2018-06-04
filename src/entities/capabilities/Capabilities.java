package entities.capabilities;

import org.joml.Vector3f;

import entities.Entity;

public class Capabilities {

	
	public static void init(CapabilityTypes c, Entity e){
		switch(c){
		case HEALTH:
			e.variables.put(CapabilityVariables.HEALTH, 10);
			break;
		case MOVEMENT:
			e.variables.put(CapabilityVariables.MOVEMENT, new Vector3f());
			break;
		default:
				
			break;
		}
	}
	
	public static void fire(CapabilityTypes c, Object...data){
			switch(c){
			case HEALTH:
				((Entity)data[0]).variables.put(CapabilityVariables.HEALTH, (int)data[1]);
				break;
			case MOVEMENT:
				((Entity)data[0]).variables.put(CapabilityVariables.MOVEMENT, (Vector3f)data[1]);
				break;
			default:
				
				break;
			}
	}
	
	public static void remove(CapabilityTypes c, Entity e){
			switch(c){
			case HEALTH:
				e.variables.remove(CapabilityVariables.HEALTH);
				break;
			case MOVEMENT:
				e.variables.remove(CapabilityVariables.MOVEMENT);
				break;
			default:
				
				break;
			}
	}
}
