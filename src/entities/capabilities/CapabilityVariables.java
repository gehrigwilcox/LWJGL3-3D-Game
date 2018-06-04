package entities.capabilities;

import toolbox.DataTypes;

public enum CapabilityVariables {

	MAX_HEALTH(DataTypes.FLOAT), HEALTH(DataTypes.FLOAT) ,MOVEMENT(DataTypes.VECTOR3F);
	
	public final DataTypes type;
	
	private CapabilityVariables(DataTypes type){
		this.type = type;
	}
}
