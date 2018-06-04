package rendering.buffers;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

public class TextureHandler {

	private static ArrayList<Integer> texs = new ArrayList<Integer>();
	
	
	public static int newTexture(){
		int tex = GL11.glGenTextures();
		texs.add(tex);
		return tex;
	}
	
	public static void bind(int tex){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
	}
	
	public static void unbind(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public static void loadData(ByteBuffer data, int width, int height){
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data);
	}
	
	public static void genMipMap(){
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
	}
	
	public static void activateTextureUnit(int num){
		GL13.glActiveTexture(num);
	}
	
	public static int newTexLoad(ByteBuffer data, int width, int height){
		int tex = newTexture();
		bind(tex);
		loadData(data, width, height);
		genMipMap();
		unbind();
		return tex;
	}
	
	public static int newTexLoad(Object[] in){
		int tex = newTexture();
		bind(tex);
		loadData((ByteBuffer)in[0], (int)in[1], (int)in[2]);
		genMipMap();
		unbind();
		return tex;
	}
	
	public static void cleanup(){
		unbind();
		
		for(int i : texs){
			GL11.glDeleteTextures(i);
		}
		texs.clear();
	}
}
