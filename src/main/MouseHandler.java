package main;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class MouseHandler {

	private static final Vector2d previousPos = new Vector2d(-1,-1);
	
	private static final Vector2d currentPos = new Vector2d(0,0);
	
	private static final Vector2f displVec = new Vector2f();
	
	private static boolean inWindow = false;
	
	private static boolean leftButtonPressed = false;
	
	private static boolean rightButtonPressed = false;
	
	public static void init(long window){
		GLFW.glfwSetCursorPosCallback(window, (windowHandle, x, y) -> {
			currentPos.x = x;
			currentPos.y = y;
		});
		
		GLFW.glfwSetCursorEnterCallback(window, (windowHandle, entered) ->{
			inWindow = entered;
		});
		
		GLFW.glfwSetMouseButtonCallback(window, (windowHandle, button, action, mode) -> {
			leftButtonPressed = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
			rightButtonPressed = button == GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS;
		});
	}
	
	public static Vector2f getDisplVec(){
		return displVec;
	}
	
	public static void input(){
		displVec.x = 0;
		displVec.y = 0;
		
		if(inWindow){
			double deltaX = currentPos.x - WindowManager.width/2;
			double deltaY = currentPos.y - WindowManager.height/2;
			
			if(deltaX != 0){
				displVec.y = (float) deltaX;
			}
			if(deltaY != 0){
				displVec.x = (float) deltaY;
			}
		}
		previousPos.x = currentPos.x;
		previousPos.y = currentPos.y;
		
		currentPos.x = 0;
		currentPos.y = 0;
		GLFW.glfwSetCursorPos(WindowManager.window, WindowManager.width/2, WindowManager.height/2);
	}
	
	public static boolean leftButtonPressed(){
		return leftButtonPressed;
	}
	
	public static boolean rightButtonPressed(){
		return rightButtonPressed;
	}
}
