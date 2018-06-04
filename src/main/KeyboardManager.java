package main;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import rendering.Camera;

public class KeyboardManager extends GLFWKeyCallback{
	
	
	private static boolean[] keys = {false,false,false,false,false,false};
	
	private static Vector3f cameraInc = new Vector3f();
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods){
		if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE){
			GLFW.glfwSetWindowShouldClose(window, true);
		}
		
		
		if (key == GLFW.GLFW_KEY_W) {
			if(action == GLFW.GLFW_PRESS){
				keys[0] = true;
			} else if(action == GLFW.GLFW_RELEASE){
				keys[0] = false;
			}
		}
		if (key == GLFW.GLFW_KEY_S) {
			if(action == GLFW.GLFW_PRESS){
				keys[1] = true;
			} else if(action == GLFW.GLFW_RELEASE){
				keys[1] = false;
			}
		}
		if (key == GLFW.GLFW_KEY_A) {
			if(action == GLFW.GLFW_PRESS){
				keys[2] = true;
			} else if(action == GLFW.GLFW_RELEASE){
				keys[2] = false;
			}
		} 
		if (key == GLFW.GLFW_KEY_D) {
			if(action == GLFW.GLFW_PRESS){
				keys[3] = true;
			} else if(action == GLFW.GLFW_RELEASE){
				keys[3] = false;
			}
		}
		if (key == GLFW.GLFW_KEY_Z) {
			if(action == GLFW.GLFW_PRESS){
				keys[4] = true;
			} else if(action == GLFW.GLFW_RELEASE){
				keys[4] = false;
			}
		}
		if (key == GLFW.GLFW_KEY_X) {
			if(action == GLFW.GLFW_PRESS){
				keys[5] = true;
			} else if(action == GLFW.GLFW_RELEASE){
				keys[5] = false;
			}
		}
	}
	
	public static void handleInput(Camera cam){
		
		cameraInc.set(0, 0, 0);
	    if (keys[0]) {
	        cameraInc.z = -1;
	    } else if (keys[1]) {
	        cameraInc.z = 1;
	    }
	    if (keys[2]) {
	        cameraInc.x = -1;
	    } else if (keys[3]) {
	        cameraInc.x = 1;
	    }
	    if (keys[4]) {
	        cameraInc.y = -1;
	    } else if (keys[5]) {
	        cameraInc.y = 1;
	    }
	    
	    cam.movePosition(cameraInc.x * Game.walkSpeed, cameraInc.y * Game.walkSpeed, cameraInc.z * Game.walkSpeed);
	}

}
