package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;
import dkeep.logic.ArenaMap;
import dkeep.logic.GameLogic;

public class GameTests {
	char[][] map = {{'X','X','X','X','X'},
					{'X','H',' ','G','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	@Test
	public void testMoveHeroIntoFreeCell(){
		int[] test1 = {1,1},test2 = {2,1};
		ArenaMap game_map = new ArenaMap(this.map);
		GameLogic game = new GameLogic(game_map);
		System.out.print("["+game.getHero().getPos()[0]+","+game.getHero().getPos()[1]+"]\n");
		assertEquals( test1[0] , game.getHero().getPos()[0]);
		assertEquals( test1[1] , game.getHero().getPos()[1]);
		game.moveHero('s');
		assertEquals( test2[0] , game.getHero().getPos()[0]);
		assertEquals( test2[1] , game.getHero().getPos()[1]);
	}

//	public void testHeroIsCapturedByGuard(){
//	GameMap gameMap =new GameMap(map);
//	Game game=new Game(gameMap);
//	assertFalse(game.isGameOver());
//	game.moveHero('d');
//	assertTrue(game.isGameOver());
//	assertEquals(Game:DEFEAT,game.getEndStatus());
//}
}