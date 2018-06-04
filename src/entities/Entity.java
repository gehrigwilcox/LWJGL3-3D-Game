package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector3f;

import entities.capabilities.Capabilities;
import entities.capabilities.CapabilityTypes;
import entities.capabilities.CapabilityVariables;
import physics.CollisionHandler;
import rendering.models.Model;
import rendering.models.ModelHandler;
import toolbox.Utils;

public class Entity {

	private static ArrayList<Entity> dirty = new ArrayList<Entity>();
	
	Model model;
	
	int tex;
	
	Vector3f pos = new Vector3f(0,0,-1.05f);
	
	Vector3f rot = new Vector3f(0,0,0);
	
	Vector3f scale = new Vector3f(1,1,1);
	
	Vector3f movementVector = new Vector3f(0,0,0);
	
	public Map<CapabilityVariables,Object> variables = new HashMap<CapabilityVariables,Object>();
	
	
	
	public static void processDirtyEntities(){
		
		for(Entity e : dirty){
			
			if(e.movementVector != Utils.zeroVector){
				CollisionHandler.processEntity(e);
			}
			
		}
		
		dirty.clear();
	}
	
	
	
	public Entity(Model m, int tex){
		changeModel(m);
		this.tex = tex;
	}
	
	public void markDirty(){
		dirty.add(this);
	}
	
	public void addCapability(CapabilityTypes c){
		Capabilities.init(c, this);
	}
	
	public void removeCapability(CapabilityTypes c){
		Capabilities.remove(c, this);
	}
	
	public void changeModel(Model m){
		ModelHandler.changeModel(this, m);
		model = m;
	}
	
	public Model getModel(){
		return model;
	}
	
	public int getTex(){
		return tex;
	}
	
	public Vector3f getPostion(){
		return pos;
	}
	
	public Vector3f getRotation(){
		return rot;
	}
	
	public Vector3f getScale(){
		return scale;
	}
	
	public void setScale(float x, float y, float z){
		scale.x = x;
		scale.y = y;
		scale.z = z;
	}
	
	public void setScale(Vector3f s){
		scale = s;
	}
	
	public void setRotation(float x, float y, float z){
		rot.x = x;
		rot.y = y;
		rot.z = z;
	}
	
	public void setRotation(Vector3f r){
		rot = r;
	}
	
	public void setPosition(float x, float y, float z){
		pos.x = x;
		pos.y = y;
		pos.z = z;
	}
	
	public void setPostion(Vector3f p){
		pos = p;
	}
	
	public void move(float x, float y, float z){
		pos.x += x;
		pos.y += y;
		pos.z += z;
	}
	
	public void move(Vector3f p){
		pos.x += p.x;
		pos.y += p.y;
		pos.z += p.z;
	}
	
}
