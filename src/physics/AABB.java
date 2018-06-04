package physics;

import org.joml.Vector3f;

import rendering.models.Model;

public class AABB {

	public Vector3f maxPos;
	
	public Vector3f minPos;
	
	
	private Vector3f pos = new Vector3f(0,0,0);
	
	
	
	public AABB(Vector3f maxPos, Vector3f minPos){
		this.maxPos = maxPos;
		this.minPos = minPos;
	}
	
	public AABB(Model model, Vector3f scale){
		this(model.maxPos.mul(scale),
				model.minPos.mul(scale));
	}
	
	public static boolean isColliding(AABB a, AABB b){
		
		return (a.minPos.x <= b.maxPos.x && a.maxPos.x >= b.minPos.x) &&
				(a.minPos.y <= b.maxPos.y && a.maxPos.y >= b.minPos.y) &&
				(a.minPos.z <= b.maxPos.z && a.maxPos.z >= b.minPos.z);
	}
	
	public boolean isColliding(AABB other){
		return (this.minPos.x <= other.maxPos.x && this.maxPos.x >= other.minPos.x) &&
				(this.minPos.y <= other.maxPos.y && this.maxPos.y >= other.minPos.y) &&
				(this.minPos.z <= other.maxPos.z && this.maxPos.z >= other.minPos.z);
	}
	
	public void setPos(Vector3f pos){
		this.pos = pos;
	}
	
	public void setPos(float x, float y, float z){
		pos.x = x;
		pos.y = y;
		pos.z = z;
	}
	
	public void resize(Model m, Vector3f scale){
		this.maxPos = m.maxPos.mul(scale);
		this.minPos = m.minPos.mul(scale);
	}
	
	public void resize(Vector3f maxPos, Vector3f minPos){
		this.maxPos = maxPos;
		this.minPos = minPos;
	}
	
}
