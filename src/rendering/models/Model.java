package rendering.models;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryUtil;

import entities.Entity;
import rendering.buffers.VAOHandler;
import rendering.buffers.VBOHandler;

public class Model {

	public final int vao;
	
	public final int vertexCount;
	
	public final Vector3f maxPos;
	
	public final Vector3f minPos;
	
	public final Octree octree;
	
	public Model(float[] positions, float[] texCoords, int[] indices){
		FloatBuffer verticesBuffer = null;
		
		IntBuffer indicesBuffer = null;
		
		FloatBuffer texCoordsBuffer = null;
		
		maxPos = new Vector3f(0,0,0);
		minPos = new Vector3f(0,0,0);
		
		octree = new Octree(this);
		
		try{
			verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
			vertexCount = indices.length;
			verticesBuffer.put(positions).flip();
			
			indicesBuffer = MemoryUtil.memAllocInt(indices.length);
			indicesBuffer.put(indices).flip();
			
			texCoordsBuffer = MemoryUtil.memAllocFloat(texCoords.length);
			texCoordsBuffer.put(texCoords).flip();
			
			vao = VAOHandler.createNewVAO();
			VAOHandler.bindVAO(vao);
			{
			
			
				VBOHandler.newVBOData(3, verticesBuffer, 0);
				
				VBOHandler.newVBOData(2, texCoordsBuffer, 1);
				
				
				int vbo = VBOHandler.createNewVBO();
				GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
				
				GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
			
			}
			VAOHandler.unbind();
		} finally {
			if(verticesBuffer != null){
				MemoryUtil.memFree(verticesBuffer);
			}
			if(indicesBuffer != null){
				MemoryUtil.memFree(indicesBuffer);
			}
			if(texCoordsBuffer != null){
				MemoryUtil.memFree(texCoordsBuffer);
			}
			
			ModelHandler.entityModels.put(this, new ArrayList<Entity>());
		}
	}
	
}
