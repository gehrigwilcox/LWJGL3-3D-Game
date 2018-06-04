package rendering.buffers;

import java.util.ArrayList;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VAOHandler {

	private static ArrayList<Integer> VAOs = new ArrayList<Integer>();
	
	public static int createNewVAO(){
		int vaoID = GL30.glGenVertexArrays();
		
		VAOs.add(vaoID);
		
		return vaoID;
	}
	
	public static void bindVAO(int vao){
		GL30.glBindVertexArray(vao);
	}
	
	public static void unbind(){
		GL30.glBindVertexArray(0);
	}
	
	public static void cleanup(){
		
		GL20.glDisableVertexAttribArray(0);
		
		unbind();
		
		for(Integer i : VAOs){
			GL30.glDeleteVertexArrays(i);
		}
		
		VAOs.clear();
	}
	
}
