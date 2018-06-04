package rendering;

import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import main.WindowManager;

public class Camera {
	
	public Vector3f pos = new Vector3f();
	
	public Vector3f rot = new Vector3f();
	
	public final float FOV = (float) Math.toRadians(60.0f);
	
	public final float Z_NEAR = 0.01f;
	
	public final float Z_FAR = 1000.0f;
	
	public Matrix4f projectionMatrix = new Matrix4f();
	
	public Matrix4f viewMatrix = new Matrix4f();
	
	public float aspectRatio = 0f;
	
	public void calculateAspectRatio(){
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(WindowManager.window, w, h);
		
		aspectRatio = (float) w.get(0) / h.get(0);
	}
	
	public void setPosition(float x, float y, float z){
		pos.x = x;
		pos.y = y;
		pos.z = z;
	}
	
	public void setPosition(Vector3f p){
		pos = p;
	}
	
	public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if ( offsetZ != 0 ) {
            pos.x += (float)Math.sin(Math.toRadians(rot.y)) * -1.0f * offsetZ;
            pos.z += (float)Math.cos(Math.toRadians(rot.y)) * offsetZ;
        }
        if ( offsetX != 0) {
            pos.x += (float)Math.sin(Math.toRadians(rot.y - 90)) * -1.0f * offsetX;
            pos.z += (float)Math.cos(Math.toRadians(rot.y - 90)) * offsetX;
        }
        pos.y += offsetY;
    }
	
	public void setRot(float x, float y, float z){
		rot.x = x;
		rot.y = y;
		rot.z = z;
	}
	
	public void setRot(Vector3f r){
		rot = r;
	}
	
	public void moveRot(float x, float y, float z){
		rot.x += x;
		rot.y += y;
		rot.z += z;
	}
	
}
