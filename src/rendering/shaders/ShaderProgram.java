package rendering.shaders;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;


public class ShaderProgram {

	private final int programID;
	
	private int vertexShaderID;
	
	private int fragmentShaderID;
	
	private final Map<String, Integer> uniforms;
	
	public ShaderProgram() throws Exception{
		programID = GL20.glCreateProgram();
		
		uniforms = new HashMap<>();
		
		if(programID == 0){
			throw new Exception("Could not create shader");
		}
	}
	
	public void createUniform(String name) throws Exception{
		int uniformLocation = GL20.glGetUniformLocation(programID, name);
		
		if(uniformLocation < 0){
			throw new Exception("Could not find uniform: " + name);
		}
		
		uniforms.put(name,uniformLocation);
	}
	
	public void setUniform(String name, Matrix4f matrix){
		
		try(MemoryStack stack = MemoryStack.stackPush()){
			FloatBuffer fb = stack.mallocFloat(16);
			matrix.get(fb);
			GL20.glUniformMatrix4fv(uniforms.get(name), false, fb);
		}
	}
	
	public void setUniform(String name, int value){
		GL20.glUniform1i(uniforms.get(name), value);
	}
	
	public void createVertexShader(String shadercode) throws Exception{
		vertexShaderID = createShader(shadercode, GL20.GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shadercode) throws Exception{
		fragmentShaderID = createShader(shadercode, GL20.GL_FRAGMENT_SHADER);
	}
	
	protected int createShader(String shadercode, int shadertype) throws Exception{
		
		int shaderID = GL20.glCreateShader(shadertype);
		
		if(shaderID == 0){
			throw new Exception("Error creating shader. Type: " + shadertype);
		}
		
		GL20.glShaderSource(shaderID, shadercode);
		GL20.glCompileShader(shaderID);
		
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0){
			throw new Exception("Error compiling shader code: " + GL20.glGetShaderInfoLog(shaderID, 1024));
		}
		
		GL20.glAttachShader(programID, shaderID);
		
		return shaderID;
	}
	
	public void link() throws Exception{
		GL20.glLinkProgram(programID);
		if(GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == 0){
			throw new Exception("Error linking shader code: " + GL20.glGetProgramInfoLog(programID, 1024));
		}
		
		if(vertexShaderID != 0){
			GL20.glDetachShader(programID, vertexShaderID);
		}
		
		if(fragmentShaderID != 0){
			GL20.glDetachShader(programID, fragmentShaderID);
		}
		
		//only needed if testing to see if I can run a draw command in the current state
		
		/*GL20.glValidateProgram(programID);
		if(GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == 0){
			System.err.println("Warning validating Shader code: " + GL20.glGetProgramInfoLog(programID, 1024));
		}*/
	}
	
	public void bind(){
		GL20.glUseProgram(programID);
	}
	
	public void unbind(){
		GL20.glUseProgram(0);
	}
	
	public void cleanup(){
		unbind();
		if(programID != 0){
			GL20.glDeleteProgram(programID);
		}
	}
	
}
