package rendering.buffers;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class VBOHandler {

	private static ArrayList<Integer> VBOs = new ArrayList<Integer>();
	
	public static int createNewVBO(){
		
		int vbo = GL15.glGenBuffers();
		
		VBOs.add(vbo);
		
		return vbo;
	}
	
	public static void newVBOData(int vectorLength, FloatBuffer data, int attributeNum){
		bind(createNewVBO());
		{
			loadData(vectorLength, data, attributeNum);
		}
		unbind();
	}
	
	public static void bind(int vbo){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
	}
	
	public static void unbind(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public static void loadData(int vectorLength, FloatBuffer data, int attributeNum){
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNum, vectorLength, GL11.GL_FLOAT, false, 0, 0);
	}
	
	public static void cleanup(){
		unbind();
		
		for(Integer i : VBOs){
			GL15.glDeleteBuffers(i);
		}
		VBOs.clear();
	}
	
}
