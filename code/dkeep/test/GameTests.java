package dkeep.test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import dkeep.logic.*;


public class GameTests {
	//assuming this is a lever not a key
	char[][] map = {{'X','X','X','X','X'},
					{'X',' ',' ',' ','X'},
					{'I',' ',' ',' ','X'},
					{'I','k',' ',' ','X'},
					{'X','X','X','X','X'}};
	
	
	private GenericMap game_map;
	private static ArrayList< Pair<Integer,Integer> > movement = new ArrayList< Pair<Integer,Integer> >( 
			   Arrays.asList(new Pair<Integer,Integer>(1,7),new Pair<Integer,Integer>(2,7),new Pair<Integer,Integer>(3,7),new Pair<Integer,Integer>(4,7),
							 new Pair<Integer,Integer>(5,7),new Pair<Integer,Integer>(5,6),new Pair<Integer,Integer>(5,5),new Pair<Integer,Integer>(5,4),
							 new Pair<Integer,Integer>(5,3),new Pair<Integer,Integer>(5,2),new Pair<Integer,Integer>(5,1),new Pair<Integer,Integer>(6,1),
							 new Pair<Integer,Integer>(6,2),new Pair<Integer,Integer>(6,3),new Pair<Integer,Integer>(6,4),new Pair<Integer,Integer>(6,5),
							 new Pair<Integer,Integer>(6,6),new Pair<Integer,Integer>(6,7),new Pair<Integer,Integer>(6,8),new Pair<Integer,Integer>(5,8),
							 new Pair<Integer,Integer>(4,8),new Pair<Integer,Integer>(3,8),new Pair<Integer,Integer>(2,8),new Pair<Integer,Integer>(1,8)) );
	
	private void setDefaultMap(int guard_type){
		ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
		ArrayList<Pair<Pair<Integer,Integer> , String > > doors = new ArrayList<Pair<Pair<Integer,Integer> , String> >();
		Pair<Integer,Integer> init_pos = new Pair<Integer,Integer>(1,3);
		this.game_map = new GenericMap( (this.map).clone() );
		doors.add( new Pair< Pair<Integer,Integer> ,String>( new Pair<Integer,Integer>( new Integer(2) ,new Integer(0)) , "S"));
		doors.add( new Pair< Pair<Integer,Integer> ,String>( new Pair<Integer,Integer>( new Integer(3) ,new Integer(0)) , "S"));
		characters.add( (guard_type == 0) ? new RookieGuard(init_pos ,game_map.getSize()) : 
					    (guard_type == 1) ? new SuspiciousGuard(init_pos ,game_map.getSize()) : new DrunkenGuard(init_pos ,game_map.getSize()) );
		characters.add(new Hero(new Pair<Integer,Integer>(1,1) , game_map.getSize() ) );
		
		this.game_map.setDoors(doors);
		this.game_map.setKey(new Pair<Integer,Integer>(3,1) , true );
		this.game_map.setCharacters(characters);
	}
	
	private boolean validGuardPos(Pair<Integer,Integer> p , int guard_type, int index){ //1 -> Rookie , 2 -> Suspicious/Drunken
		int ip = ( index+1 == movement.size() ) ? 0 : index+1 , il = ( index-1 < 0) ? movement.size()-1 : index-1;
		if( guard_type >= 2)
			return movement.contains(p);
		else
			return p.equals(movement.get(index));
	}
	
	
	@Test
	public void testMoveHeroIntoFreeCell(){
		Pair<Integer,Integer> test1 = new Pair<Integer,Integer>(1,1),test2 = new Pair<Integer,Integer>(2,1);
		this.setDefaultMap(0);
		GameLogic game = new GameLogic(this.game_map,0); //number is irrelevant
		assertEquals( test1 , game.getHero().getPos().get(0));
		game.moveHero('s');
		assertEquals( test2 , game.getHero().getPos().get(0));
		game.moveHero(' ');
		assertEquals( test2 , game.getHero().getPos().get(0));
	}
	
	@Test
	public void testHeroIsCapturedByGuard(){
		for(int i = 0 ; i < 3 ; i++){ //test if all guards capture hero
			this.setDefaultMap(i);
			GameLogic game=new GameLogic(this.game_map,0); //number is irrelevant
			assertFalse(game.isGameOver());
			game.moveHero('d');
			assertTrue(game.isGameOver());
		}
	}

	@Test
	public void testMoveHeroIntoWall(){
		this.setDefaultMap(0);
		Pair<Integer,Integer> test1 = new Pair<Integer,Integer>(1,1), test2 = new Pair<Integer,Integer>(2,1),
							  test3 = new Pair<Integer,Integer>(3,1), test4 = new Pair<Integer,Integer>(3,2),
							  test5 = new Pair<Integer,Integer>(3,3);
		GameLogic game = new GameLogic(this.game_map,0); //number is irrelevant
		assertEquals( test1 , game.getHero().getPos().get(0));
		game.moveHero('a');
		assertEquals( test1 , game.getHero().getPos().get(0));
		game.moveHero('w');
		assertEquals( test1 , game.getHero().getPos().get(0));
		game.moveHero('s');
		assertEquals( test2 , game.getHero().getPos().get(0));
		game.moveHero('s');
		assertEquals( test3 , game.getHero().getPos().get(0));
		game.moveHero('d');
		assertEquals( test4 , game.getHero().getPos().get(0));
		game.moveHero('d');
		assertEquals( test5 , game.getHero().getPos().get(0));
		game.moveHero('d');
		assertEquals( test5 , game.getHero().getPos().get(0));
		game.moveHero('s');
		assertEquals( test5 , game.getHero().getPos().get(0));
		game.getHero().moveCharacter(game.getHero().getPos(),-1);
		assertEquals( test5 , game.getHero().getPos().get(0));
		
		
	}

	@Test
	public void testMoveHeroIntoClosedDoor(){
		this.setDefaultMap(0);
		Pair<Integer,Integer> test1 = new Pair<Integer,Integer>(1,1), 
				 			  test2 = new Pair<Integer,Integer>(2,1);
		ArrayList< Pair< Pair<Integer,Integer> , String> > test3 = new ArrayList< Pair<Pair<Integer,Integer> , String> >();
		
		test3.add( new Pair<Pair<Integer,Integer> , String>( new Pair<Integer,Integer>(1,1) , "H"));
		
		GameLogic game = new GameLogic(this.game_map,0); //number is irrelevant
		assertEquals( test1, game.getHero().getPos().get(0));
		assertEquals( test3, game.getHero().getPrintable());
		game.moveHero('s');
		test3.remove(0);
		test3.add( new Pair<Pair<Integer,Integer> , String>( new Pair<Integer,Integer>(2,1) , "H"));
		assertEquals( test2, game.getHero().getPos().get(0));
		game.moveHero('a');
		assertEquals( test3, game.getHero().getPrintable());
		assertEquals( test2, game.getHero().getPos().get(0));
		assertEquals( false, game.wonGame() );
}

	@Test
	public void testOpenDoors(){
		Pair<Integer,Integer> test1 = new Pair<Integer,Integer>(3,1);
		this.setDefaultMap(0);
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		assertEquals( 'I' , game_map.getTile(new Pair<Integer,Integer>(2,0) )); //door 1
		assertEquals( 'I' , game_map.getTile(new Pair<Integer,Integer>(3,0) )); //door 2
		game.moveHero('s');
		assertEquals( 'I' , game_map.getTile(new Pair<Integer,Integer>(2,0) )); //door 1
		assertEquals( 'I' , game_map.getTile(new Pair<Integer,Integer>(3,0) )); //door 2
		game.moveHero('s');
		assertEquals( test1 , game.getHero().getPos().get(0));
		assertEquals( 'S' , game_map.getTile(new Pair<Integer,Integer>(2,0) )); //door 1
		assertEquals( 'S' , game_map.getTile(new Pair<Integer,Integer>(3,0) )); //door 2
	}

	@Test
	public void testEnterDoors(){
		Pair<Integer,Integer> test1 = new Pair<Integer,Integer>(3,1);
		this.setDefaultMap(0);
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		game.moveHero('s');
		game.moveHero('s');
		assertEquals( test1 , game.getHero().getPos().get(0));
		assertEquals( test1 , game.getHero().getPos().get(0));
		assertEquals( 'S' , game.getMap().getMap()[2][0]); //door 1
		assertEquals( 'S' , game.getMap().getMap()[3][0]); //door 2
	}
	
	@Test
	public void testMoveHeroNextOgre(){
		ArrayList<Pair<Pair<Integer,Integer>,String > > test_printable = new ArrayList<Pair<Pair<Integer,Integer>,String > >();
		ArrayList<GameCharacter> chars = new ArrayList<GameCharacter>();
		ArrayList<Pair<Integer,Integer> > test_game_over_pos = new ArrayList<Pair<Integer,Integer> >();
		test_printable.add(new Pair< Pair<Integer,Integer> , String >( new Pair<Integer,Integer>(1,3) , "*") );
		test_printable.add(new Pair< Pair<Integer,Integer> , String >( new Pair<Integer,Integer>(1,3) , "O") );
		test_game_over_pos.add( new Pair<Integer,Integer>(1,3));
		test_game_over_pos.add( new Pair<Integer,Integer>(1,3));
		this.setDefaultMap(0);
		chars.add(new Hero( new Pair<Integer,Integer>(1,1) , this.game_map.getSize() ));
		chars.add(new Ogre(new Pair<Integer,Integer>(1,3) ,this.game_map.getSize()));
		this.game_map.setCharacters(chars);
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		game.moveHero('d');
		assertEquals(null,game.getHero().getGameOverPos());
		assertEquals( test_game_over_pos, game.getVillains().get(0).getGameOverPos());
		assertEquals( test_printable , game.getVillains().get(0).getPrintable() );
		assertEquals( true,game.isGameOver());
	}
	
	@Test
	public void testMoveHeroIntoEnemy(){
		ArrayList<GameCharacter> chars = new ArrayList<GameCharacter>();
		Pair<Integer,Integer> test = new Pair<Integer,Integer>(1,2);
		this.setDefaultMap(0);
		chars.add(new Hero( new Pair<Integer,Integer>(1,1) , this.game_map.getSize() ));
		chars.add(new Ogre(new Pair<Integer,Integer>(1,3) ,this.game_map.getSize()));
		this.game_map.setCharacters(chars);
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		game.moveHero('d');
		assertEquals( true , game.isGameOver() );
		((Hero)this.game_map.getCharacters().get(0)).setArmed(true);
		((Ogre)this.game_map.getCharacters().get(1)).setClub(new Pair<Integer,Integer>(2,3));
		assertEquals( false, game.isGameOver());
		assertEquals( test , game.getHero().getPos().get(0) );
		assertEquals( false , game.moveHero('d'));
		assertEquals( test , game.getHero().getPos().get(0) );
	}
	
	@Test
	public void testChangeRepresentation(){
		this.setDefaultMap(0);
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		Hero h=game.getHero();
		assertEquals("H",h.toString());
		game.moveHero('s');
		game.moveHero('s');
		assertEquals("K",h.toString());
	}
	
	@Test
	public void testMoveAndClub(){
		ArenaMap map = new ArenaMap(1); //generate only 1 ogre
		GameLogic game = new GameLogic(map,0); //Number is irrelevant
		game.getHero().setPos(new ArrayList<Pair<Integer,Integer>>(Arrays.asList(new Pair<Integer,Integer>(0,0))) );
		
		int px = game.getVillains().get(0).getPos().get(0).getFirst() , py = game.getVillains().get(0).getPos().get(0).getSecond();
		boolean cnn= false,cns=false,cnw=false,cne=false, csn=false , css=false , csw=false , cse=false, 
				cen=false , ces=false , cew=false , cee=false , cwn=false , cws=false , cwe=false , cww=false;
		while ( !(cnn && cns && cnw && cne && csn && css && csw && cse && cen && ces && cew && cee && cwn && cws && cwe && cww) ){
			game.moveAllVillains();
			
			int ox = game.getVillains().get(0).getPos().get(0).getFirst(), oy = game.getVillains().get(0).getPos().get(0).getSecond(), 
				cx = ((Ogre)game.getVillains().get(0)).getClub().getFirst(), cy = ((Ogre)game.getVillains().get(0)).getClub().getSecond();

			if 		( (px-2) == cx &&   py   == cy && (px-1) == ox && py == oy ) //Ogre north, club north
				cnn = true;
			else if ( (px-1) == cx && (py-1) == cy && (px-1) == ox && py == oy ) //Ogre north, club west
				cnw = true;
			else if ( (px-1) == cx && (py+1) == cy && (px-1) == ox && py == oy ) //Ogre north, club east
				cne = true;
			else if (   px 	 == cx &&   py   == cy && (px-1) == ox && py == oy ) //Ogre north, club south
				cns = true;
			else if (   px   == cx &&   py   == cy && (px+1) == ox && py == oy ) //Ogre south, club north
				csn = true;
			else if ( (px+1) == cx && (py-1) == cy && (px+1) == ox && py == oy ) //Ogre south, club west
				csw = true;
			else if ( (px+1) == cx && (py+1) == cy && (px+1) == ox && py == oy ) //Ogre south, club east
				cse = true;
			else if ( (px+2) == cx &&   py   == cy && (px+1) == ox && py == oy ) //Ogre south, club south
				css = true;
			else if ( (px-1) == cx && (py-1) == cy && px == ox && (py-1) == oy ) //Ogre west, club north
				cwn = true;
			else if (   px   == cx && (py-2) == cy && px == ox && (py-1) == oy ) //Ogre west, club west
				cww = true;
			else if (   px   == cx &&   py   == cy && px == ox && (py-1) == oy ) //Ogre west, club east
				cwe = true;
			else if ( (px+1) == cx && (py-1) == cy && px == ox && (py-1) == oy ) //Ogre west, club south
				cws = true;
			else if ( (px-1) == cx && (py+1) == cy && px == ox && (py+1) == oy ) //Ogre east, club north
				cen = true;
			else if (   px   == cx &&   py   == cy && px == ox && (py+1) == oy ) //Ogre east, club west
				cew = true;
			else if (   px   == cx && (py+2) == cy && px == ox && (py+1) == oy ) //Ogre east, club east
				cee = true;
			else if ( (px+1) == cx && (py+1) == cy && px == ox && (py+1) == oy ) //Ogre east, club south
				ces = true;
			else
				fail("ERROR - Club=["+cx+","+cy+"] , Ogre=["+ox+","+oy+"] , Original=["+px+","+py+"] \n");
			
			px = game.getVillains().get(0).getPos().get(0).getFirst(); py = game.getVillains().get(0).getPos().get(0).getSecond();
		}
		
		assertEquals( true , ( ( game.getNextLevel(0).getMap() instanceof DungeonMap ) && (map.nextMap(0) == null) ) );
	}
	
	@Test
	public void testVictory(){
		this.setDefaultMap(0);
		this.game_map.setKey( this.game_map.getKey() , false );
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		assertEquals("H",game.getHero().toString());
		game.moveHero('s');
		game.moveHero('s');
		assertEquals(' ' , this.game_map.getTile( new Pair<Integer,Integer>(3,1)));
		assertEquals(null,this.game_map.nextMap(2));
		assertEquals("K",game.getHero().toString());
		game.moveHero('a');
		game.moveHero('a');
		assertEquals(true , game.wonGame() ); //moveHero returns true if it jumped on top of stairs
	}
	
	@Test
	public void stunOgre(){
		ArrayList< GameCharacter > chars = new ArrayList< GameCharacter >();
		this.setDefaultMap(0);
		Hero h = new Hero( new Pair<Integer,Integer>(1,1) , this.game_map.getSize() );
		h.setArmed(true);
		chars.add( new Ogre(new Pair<Integer,Integer>(1,3) ,game_map.getSize() ) );
		chars.add( h );
		this.game_map.setCharacters(chars);
		GameLogic game = new GameLogic(game_map,0); //number is irrelevant
		ArrayList<GameCharacter> ogres = game.getVillains();
		ArrayList<Pair<Integer,Integer> > test1 = new ArrayList<Pair<Integer,Integer> >(), test2 = new ArrayList<Pair<Integer,Integer> >();
		test1.add( new Pair<Integer,Integer>( this.game_map.getHeight()+1 , 0 ));
		test1.add( new Pair<Integer,Integer>(1,1));
		test2.add( new Pair<Integer,Integer>(1,1));
		test2.add( new Pair<Integer,Integer>(this.game_map.getWidth()+1,0));
		
		assertEquals(true,game.getHero().isArmed());
		assertEquals("O",ogres.get(0).toString());
		game.moveHero('d');
		((Ogre)ogres.get(0)).stunOgre( Ogre.STUN_ROUNDS );
		((Ogre)ogres.get(0)).setClub(new Pair<Integer,Integer>(2,3)); //remove club from same pos as ogre
		((Ogre)ogres.get(0)).setClubRepresentation("$");
		game.moveHero('a');
		
		assertEquals( "$" , ogres.get(0).getPrintable().get(0).getSecond() );
		assertEquals( false,game.isGameOver());
		game.moveAllVillains();
		assertEquals("8",ogres.get(0).toString());
		assertEquals(new Pair<Integer,Integer>(1,3) , ogres.get(0).moveCharacter( ogres.get(0).getPos() , 0).get(0) );
		game.moveAllVillains();
		assertEquals("O",ogres.get(0).toString());
		//test if diferent position
		assertEquals( false , ogres.get(0).moveCharacter( ogres.get(0).getPos() , 0).get(0).equals( new Pair<Integer,Integer>(1,3) ) );
		
		h.setArmed(false);
		assertEquals( "H" , h.getPrintable().get(0).getSecond() );
	}
	
	@Test (timeout = 1000)
	public void stunOgreMoveVillains(){
		ArrayList< GameCharacter > chars = new ArrayList< GameCharacter >();
		this.setDefaultMap(0);
		Hero h = new Hero(new Pair<Integer,Integer>(1,1) , this.game_map.getSize() );
		h.setArmed(true);
		chars.add(new Ogre( new Pair<Integer,Integer>(3,3) , game_map.getSize() ));
		chars.add(h);
		this.game_map.setCharacters(chars);
		GameLogic game = new GameLogic(game_map , 0); //number is irrelevant
		while( game.getVillains().get(0).toString() != "8" )
			game.moveAllVillains();
		
		assertEquals("8" , game.getVillains().get(0).toString() );
	}
	
	@Test
	public void testCreateDungeonMap(){
		for(int i = -1 ; i <= 3 ; i++){
			DungeonMap map = new DungeonMap(i);
			GameLogic game = new GameLogic(map,0); //number is irrelevant
			char[][] test_map = new char[][]{{'X','X','X','X','X','X','X','X','X','X'} ,
											 {'X',' ',' ',' ','I',' ','X',' ',' ','X'} ,
											 {'X','X','X',' ','X','X','X',' ',' ','X'} ,
											 {'X',' ','I',' ','I',' ','X',' ',' ','X'} ,
											 {'X','X','X',' ','X','X','X',' ',' ','X'} ,
											 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X','X','X',' ','X','X','X','X',' ','X'} ,
											 {'X',' ','I',' ','I',' ','X','k',' ','X'} ,
											 {'X','X','X','X','X','X','X','X','X','X'} };

			assertEquals(test_map , map.getMap() );
			assertEquals(true , (map.nextMap(1) instanceof ArenaMap) );
			assertEquals(false , map.getLever() );
			
			if(i == -1)
				assertEquals(true , game.getAllCharacters().size() == 1);
			else if (i == 0)
				assertEquals(true , game.getAllCharacters().size() == 2);
			else if (i > 0){
				boolean b = false;
				for (GameCharacter ch : game.getAllCharacters() )
					if ((ch instanceof RookieGuard && i == 1) || (ch instanceof DrunkenGuard && i == 2) || (ch instanceof SuspiciousGuard && i == 3))
						b = true;
				assertEquals(true,b);
			}
		}
		
	}
	
	@Test
	public void testCreateArenaMap(){
		for(int i = -1 ; i <= 5 ; i++){
			ArenaMap map = new ArenaMap(i);
			GameLogic game = new GameLogic(map,0); //number is irrelevant
			char[][] test_map = new char[][]{{'X','X','X','X','X','X','X','X','X','X'} ,
											 {'I',' ',' ',' ',' ',' ',' ',' ','k','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X',' ',' ',' ',' ',' ',' ',' ',' ','X'} ,
											 {'X','X','X','X','X','X','X','X','X','X'} };
			assertEquals(test_map , map.getMap() );
			assertEquals(null ,  map.nextMap(0) );
			assertEquals(false , map.getLever() );
			//test ogres generated
			if (i == -1)
				assertEquals(true , (game.getAllCharacters().size() == 1)); //only generate hero
			else if (i == 0)	
				assertEquals(true , (game.getAllCharacters().size() >= 2 && game.getAllCharacters().size() <= 6)); //generate random ogres + hero
			else
				assertEquals(true , (game.getAllCharacters().size() == (i+1) ));
		}
		
	}
	
	@Test
	public void testMoveGuards(){
		
		for(int i = 1 ; i < 4 ; i++){ //generate all three guards
			DungeonMap map = new DungeonMap(i);
			GameLogic game = new GameLogic(map,0); //number is irrelevant
			for(int j = 0 ; j < 50 ; j++){			
				game.moveAllVillains();
				assertEquals(true, validGuardPos(game.getVillains().get(0).getPos().get(0) , i, j%movement.size()) );
	
				if (i == 1){
					ArrayList< Pair<Pair<Integer,Integer> , String > > temp = new ArrayList< Pair< Pair<Integer,Integer> , String> >();
					temp.add( new Pair<Pair<Integer,Integer>,String>(movement.get(j % movement.size()) , "G") );
					assertEquals(temp, game.getVillains().get(0).getPrintable());
				}
			}
			assertEquals( true , ( game.getNextLevel(0).getMap() instanceof ArenaMap ));
			assertEquals( false , map.pickUpKey() );
		}
		
	}
	
	@Test
	public void testIsFreeOutOfRange(){
		Map map;
		ArrayList<Pair<Integer,Integer> > test1 = new ArrayList<Pair<Integer,Integer> >(), test2 = new ArrayList<Pair<Integer,Integer> >(),
				  						  test3 = new ArrayList<Pair<Integer,Integer> >(), test4 = new ArrayList<Pair<Integer,Integer> >();
		for (int i = 0 ; i < 3 ; i++){
			map = (i == 0) ? new DungeonMap(-1) : ( (i == 1) ? new ArenaMap(-1) : new GenericMap(this.map));
			test1.add( new Pair<Integer,Integer>( map.getHeight()+1 , 1 ));
			test2.add( new Pair<Integer,Integer>( 1 , map.getWidth()+1 ));
			test3.add( new Pair<Integer,Integer>(-1, 1) );
			test4.add( new Pair<Integer,Integer>(1, -1 ) );
			assertEquals( 0 , map.isFree(test1));
			assertEquals( 0 , map.isFree(test2));
			assertEquals( 0 , map.isFree(test3));
			assertEquals( 0 , map.isFree(test4));
				
		}
	}
	
	@Test
	public void testGuardsGameOverPos(){
		for(int i = 1 ; i < 4 ; i++){ //generate all three guards
			DungeonMap map = new DungeonMap(i);
			GameLogic game = new GameLogic(map,0); //number is irrelevant
			for(int j = 0 ; j < 50 ; j++){
				game.moveAllVillains();
				if (game.getVillains().get(0).toString() != "g")
					assertEquals( game.getVillains().get(0).getPos().get(0) , game.getVillains().get(0).getGameOverPos().get(0)  );
				else{
					assertEquals(new ArrayList<Pair<Integer,Integer> >() , game.getVillains().get(0).getGameOverPos());
					assertEquals( true, ((DrunkenGuard)game.getVillains().get(0)).isAsleep() );
				}
			}
		}
	}
	
	@Test
	public void testPickUpKeyArenaMap(){
		ArenaMap map = new ArenaMap(-1); //generate no enemies
		GameLogic game = new GameLogic(map,0); //number is irrelevant
		Pair<Integer,Integer> test1 = new Pair<Integer,Integer>(1,1), test2 = new Pair<Integer,Integer>(1,8);
		
		for(int i = 0 ; i < 7 ; i++)
			game.moveHero('w');
		assertEquals(test1,game.getHero().getPos().get(0));
		for(int i = 0 ; i < 7 ; i++)
			game.moveHero('d');
		assertEquals(test2,game.getHero().getPos().get(0));
		assertEquals(' ', map.getTile(test2) );
		for(int i = 0 ; i < 7 ; i++)
			game.moveHero('a');
		game.moveHero('a');
		assertEquals(test1,game.getHero().getPos().get(0));
		assertEquals('S',map.getTile( new Pair<Integer,Integer>(1,0) ));
		game.moveHero('a');
		assertEquals(true,game.wonGame());
	}
	
	@Test
	public void testPairClass(){
		Pair<Integer,Integer> test = new Pair<Integer,Integer>(1,2);
		assertEquals(1,((Integer)test.getFirst()).intValue()); assertEquals(2, ((Integer)test.getSecond()).intValue());
		assertEquals( "[1,2]" , test.toString());
		assertEquals(false , test.equals( new Pair<Integer,Integer>(1,1)));
		assertEquals(false , test.equals( new Pair<Integer,Integer>(2,1)));
		assertEquals(false , test.equals( new Pair<Integer,Integer>(2,2)));
		assertEquals(false , test.equals( new Integer(1) ));
		assertEquals(test  , test.clone());
	}
	
	@Test 
	public void testOgreToKey(){
		this.setDefaultMap(0);
		ArrayList<GameCharacter> chrs = new ArrayList<GameCharacter>();
		chrs.add( new Ogre( new Pair<Integer,Integer>(1,2) , this.game_map.getSize()) );
		chrs.add( new Hero( new Pair<Integer,Integer>(0,0) , this.game_map.getSize() ));
		this.game_map.setCharacters(chrs);
		GameLogic game = new GameLogic(this.game_map , 0); // irrelevant number
		boolean o_k = false , c_k = false;
		while ( !o_k || !c_k ){
			game.moveAllVillains();
			Ogre o = (Ogre)game.getVillains().get(0);
			assertEquals( o.getClub().equals( this.game_map.getKey() ) , o.getPrintable().get(0).getSecond() == "$");
			assertEquals(o.getPos().get(0).equals( this.game_map.getKey()) , o.getPrintable().get(1).getSecond() == "$");
			if ( o.getPos().get(0).equals( this.game_map.getKey()) )
				o_k = true;
			if ( o.getClub().equals( this.game_map.getKey() ) )
				c_k = true;
		}
		
	}
	}




































































