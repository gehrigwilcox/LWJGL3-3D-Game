package main;

import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import entities.Entity;
import rendering.Camera;
import rendering.MatrixHandlers;
import rendering.buffers.TextureHandler;
import rendering.buffers.VAOHandler;
import rendering.buffers.VBOHandler;
import rendering.models.Model;
import rendering.shaders.ShaderProgram;
import toolbox.Utils;

public class Renderer {

	private static ShaderProgram shaderProgram;
	
	static Model test;
	
	public static Entity e;
	
	static Matrix4f worldMatrix = new Matrix4f();
	
	
	
	public static void render(Map<Model,List<Entity>> entities){
		render(entities, Game.mainCam);
	}
	
	public static void render(Map<Model,List<Entity>> entities, Camera cam){
		clear();
		
		MatrixHandlers.updateViewMatrix(cam);
		
		shaderProgram.bind();
		{
		
			shaderProgram.setUniform("projectionMatrix", cam.projectionMatrix);
			
			renderEntities(entities, cam);
		
			
		} 
		shaderProgram.unbind();
		
		update();
	}
	
	private static void renderEntities(Map<Model,List<Entity>> entities, Camera cam){
		
		for(Model m : entities.keySet()){
			List<Entity> e = entities.get(m);
			loadModel(m);
			{
			
				for(Entity entity : e){
					prepareEntity(entity, cam);
					
					GL11.glDrawElements(GL11.GL_TRIANGLES, m.vertexCount, GL11.GL_UNSIGNED_INT, 0);
				}
			}
			unbindModel();
			
		}
		
	}

	
	
	private static void loadModel(Model m){
		VAOHandler.bindVAO(m.vao);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
	}
	
	private static void unbindModel(){
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		VAOHandler.unbind();
	}
	
	
	
	private static void prepareEntity(Entity e, Camera cam){
		shaderProgram.setUniform("worldMatrix", MatrixHandlers.getModelViewMatrix(e, cam));
		shaderProgram.setUniform("texture_sampler", 0);//0 = whatever number you put in TextureHandler.activateTextureUnit();
		TextureHandler.activateTextureUnit(GL13.GL_TEXTURE0);
		TextureHandler.bind(e.getTex());
	}
	
	
	
	
	
	private static void clear(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	private static void update(){
		
		GLFW.glfwSwapBuffers(WindowManager.window);
		
		GLFW.glfwPollEvents();
	}
	
	
	
	
	
	
	public static void init() throws Exception{
		
		GL.createCapabilities();
		
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		shaderProgram = new ShaderProgram();
		shaderProgram.createVertexShader(Utils.loadResource("/rendering/shaders/shaderFiles/vertex.vs"));
		shaderProgram.createFragmentShader(Utils.loadResource("/rendering/shaders/shaderFiles/fragment.fs"));
		shaderProgram.link();
		
		MatrixHandlers.updateProjectionMatrix(Game.mainCam);
		
		shaderProgram.createUniform("projectionMatrix");
		shaderProgram.createUniform("worldMatrix");
		shaderProgram.createUniform("texture_sampler");
		
		test = new Model(new float[]{
				
				 -0.5f,  0.5f, -1.05f,
			     -0.5f, -0.5f, -1.05f,
			      0.5f, -0.5f, -1.05f,
			      0.5f,  0.5f, -1.05f,
			     -0.5f, 0.5f, -2.10f,
			      0.5f, 0.5f, -2.10f,
			      -0.5f, -0.5f, -2.10f,
			      0.5f, -0.5f, -2.10f
				
				 /*-0.5f,  0.5f, 0.5f,
			     -0.5f, -0.5f, 0.5f,
			      0.5f, -0.5f, 0.5f,
			      0.5f,  0.5f, 0.5f,
			      -0.5f, 0.5f, -0.5f,
			      0.5f, 0.5f, -0.5f,
			      -0.5f, -0.5f, -0.5f,
			      0.5f, -0.5f, -0.5f*/
		}, new float[]{
				0.0f, 0.0f,
				0.0f, 0.5f,
				0.5f, 0.5f,
				0.5f, 0.0f
		}, new int[]{
				0, 1, 3, 3, 1, 2,
				4, 0, 3, 5, 4, 3,
				3, 2, 7, 5, 3, 7,
				6, 1, 0, 6, 0, 4,
				2, 1, 6, 2, 6, 7,
				7, 6, 4, 7, 4, 5
		});
		
		e = new Entity(test, TextureHandler.newTexLoad(Utils.loadTexture("/resources/textures/test.png")));
		
	}
	
	public static void cleanup(){
		shaderProgram.cleanup();
		VBOHandler.cleanup();
		VAOHandler.cleanup();
		TextureHandler.cleanup();
	}
	
}
