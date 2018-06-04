package main;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class WindowManager {

	public static long window;
	
	public static boolean resized = false;
	
	private static GLFWKeyCallback keyCallback;
	
	public static int width = 0;
	public static int height = 0;
	
	public static void init(){
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!GLFW.glfwInit()){
			throw new IllegalStateException("Unable to init GLFW");
		}
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		
		window = GLFW.glfwCreateWindow(300, 300, "Game", MemoryUtil.NULL, MemoryUtil.NULL);
		
		if(window == MemoryUtil.NULL){
			throw new RuntimeException("Failed to create window");
		}
		
		GLFW.glfwSetKeyCallback(window, keyCallback = new KeyboardManager());
		
		MouseHandler.init(window);
		
		GLFW.glfwSetFramebufferSizeCallback(window, (window, w, h) -> {
			width = w;
			height = h;
			resized = true;
		});
		
		try(MemoryStack stack = MemoryStack.stackPush()){
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			GLFW.glfwGetWindowSize(window, pWidth, pHeight);
			
			GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			
			GLFW.glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0))/2, (vidmode.height() - pHeight.get(0))/2);
			
		}
		
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSwapInterval(1); //v-sync 60fps
		
		GLFW.glfwShowWindow(window);
	}
	
	public static void grabMouse(){
		GLFW.glfwSetCursorPos(window, 0, 0);
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
	}
	
	public static void releaseMouse(){
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
	
	public static void cleanup(){
		keyCallback.free();
		
		GLFW.glfwDestroyWindow(window);
		
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
	
}
