package rendering.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Entity;

public class ModelHandler {

	public static Map<Model,List<Entity>> entityModels = new HashMap<Model,List<Entity>>();
	
	public static void changeModel(Entity entity, Model newModel){
		removeEntity(entity);
		entityModels.get(newModel).add(entity);
	}
	
	public static void removeEntity(Entity entity){
		if(entity.getModel() != null)
			entityModels.get(entity.getModel()).remove(entity);
	}
	
}
