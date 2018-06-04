package toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import org.joml.Vector3f;
import org.lwjgl.openvr.Texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Utils {

	public static Vector3f zeroVector = new Vector3f(0,0,0);
	
	public static String loadResource(String filepath){
		
		
		StringBuilder shaderSource = new StringBuilder();
		try{
			InputStream in = Class.class.getResourceAsStream(filepath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = reader.readLine())!=null){
				shaderSource.append(line).append("//\n");
			}
			reader.close();
			in.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		return shaderSource.toString();
	}
	
	public static Object[] loadTexture(String filepath) throws IOException{
		PNGDecoder png = new PNGDecoder(Texture.class.getResourceAsStream(filepath));
		
		ByteBuffer buf = ByteBuffer.allocateDirect(4 * png.getWidth() * png.getHeight());
		
		png.decode(buf, png.getWidth() * 4, Format.RGBA);
		
		buf.flip();
		
		return new Object[]{buf, png.getWidth(), png.getHeight()};
	}
	
	
	
}
