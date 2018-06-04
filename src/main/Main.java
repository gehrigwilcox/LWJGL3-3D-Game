package main;

public class Main {

	
	public static Game game;
	
	
	public static void main(String[] args) {
		
		WindowManager.init();
		
		game = new Game();
		
		try{
		
			game.start();
		
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			game.cleanup();
		
			WindowManager.cleanup();
		
		}
	}

}
