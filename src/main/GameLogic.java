package main;

import org.joml.Vector2f;

public class GameLogic {

	static float rotation = 0;
	
	public static void tick(){
		
		input();
		
		/*rotation = Renderer.e.getRotation().x + 0.5f;
		
		if(rotation > 360){
			rotation -= 360;
		}
		
		Renderer.e.setRotation(rotation, 0, 0);*/
		
	}
	
	private static void rotateCamera(){
		Vector2f rotVec = MouseHandler.getDisplVec();
		
		Game.mainCam.moveRot(rotVec.x * Game.mouseSensitivity, rotVec.y * Game.mouseSensitivity, 0);
	}
	
	private static void input(){
		KeyboardManager.handleInput(Game.mainCam);
		
		MouseHandler.input();
		
		rotateCamera();
	}
	
}
