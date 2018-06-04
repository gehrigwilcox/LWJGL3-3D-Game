package main;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import rendering.Camera;
import rendering.MatrixHandlers;
import rendering.models.ModelHandler;

public class Game {
	
	public static Camera mainCam = new Camera();
	
	private long secondsPerUpdate = 1/60l;
	
	private long lastFrame = 0;
	
	public static float mouseSensitivity = 0.25f;
	
	public static float walkSpeed = /*0.00009f*/ 0.05f;
	
	public void start() throws Exception{
		
		Renderer.init();
		
		WindowManager.grabMouse();
		
		loop();
	}
	
	private int getDelta(){
		long time = System.currentTimeMillis();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	private void loop(){
		
		int delta;
		
		while(!GLFW.glfwWindowShouldClose(WindowManager.window)){
			
			delta = getDelta();
			
			if(WindowManager.resized){
				MatrixHandlers.updateProjectionMatrix(mainCam);
				WindowManager.resized = false;
			}
			
				
			GameLogic.tick();
			
			Renderer.render(ModelHandler.entityModels);
			
			sync(lastFrame + secondsPerUpdate);
		}
	}
	
	private void sync(double time){
		
		while(System.currentTimeMillis() < time){
			try{
				Thread.sleep(1);
			} catch (InterruptedException e){}
		}
	}
	
	public void cleanup(){
		Renderer.cleanup();
	}
}
