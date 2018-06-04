package rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import entities.Entity;
import main.Renderer;

public class MatrixHandlers {

	private static Matrix4f modelViewMatrix = new Matrix4f();
	
	private static Matrix4f viewCurr;
	
	public static void updateViewMatrix(Camera cam){
			cam.viewMatrix.identity();
			
			cam.viewMatrix.rotate((float)Math.toRadians(cam.rot.x), new Vector3f(1,0,0))
			.rotate((float)Math.toRadians(cam.rot.y), new Vector3f(0,1,0));
			
			cam.viewMatrix.translate(-cam.pos.x, -cam.pos.y, -cam.pos.z);
	}
	
	public static void updateProjectionMatrix(Camera cam){
		cam.calculateAspectRatio();
		
		cam.projectionMatrix.identity();
		cam.projectionMatrix = cam.projectionMatrix.perspective(cam.FOV, cam.aspectRatio, cam.Z_NEAR, cam.Z_FAR);
	}
	
	public static Matrix4f getModelViewMatrix(Entity e, Camera cam){
		modelViewMatrix.identity().translate(e.getPostion())
		.rotateX((float)Math.toRadians(e.getRotation().x))
		.rotateY((float)Math.toRadians(e.getRotation().y))
		.rotateZ((float)Math.toRadians(e.getRotation().z))
		.scale(e.getScale());
		
		viewCurr = new Matrix4f(cam.viewMatrix);
		
		return viewCurr.mul(modelViewMatrix);
	}
}
