package test.java;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import Model.Map;

import static org.mockito.Mockito.*;

public class GameTest {
    // This is our "test" application
    private static Application application;
    protected static TiledMap mock_map = mock(TiledMap.class);
    private static TiledMapTileLayer.Cell mock_cell = mock( TiledMapTileLayer.Cell.class );
    private static TiledMapTileLayer mock_collision = mock(TiledMapTileLayer.class);
    protected static TiledMapTileLayer mock_stairs = mock(TiledMapTileLayer.class);
    protected static Map map;
    // Before running any tests, initialize the application with the headless backend
    @BeforeClass
    public static void init() {
        // Note that we don't need to implement any of the listener's methods
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        // Use Mockito to mock the OpenGL methods since we are running headlessly
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        Graphics graph = mock(Graphics.class);
        Gdx.graphics = graph;
        when( Gdx.graphics.getHeight() ).thenReturn( 1920 );
        when( Gdx.graphics.getWidth() ).thenReturn( 1080 );

        MapLayers mock_layers = mock(MapLayers.class);
        TiledMapTile mock_tile = mock( TiledMapTile.class );

        when( mock_cell.getTile() ).thenReturn( mock_tile );
        when( mock_map.getLayers() ).thenReturn( mock_layers );
        when( mock_layers.get("Stairs") ).thenReturn( mock_stairs );
        when( mock_layers.get("Floor") ).thenReturn( mock_collision );


        map = new Map();
        initiateFloor();
        initiateStairs();
        map.setMap(mock_map, mock_collision);
    }

    // After we are done, clean up the application
    @AfterClass
    public static void cleanUp() {
        // Exit the application first
        application.exit();
        application = null;
    }


    private static void initiateFloor(){
        when( mock_collision.getTileWidth() ).thenReturn(16f);
        when( mock_collision.getTileHeight() ).thenReturn(3f);
        when( mock_collision.getWidth() ).thenReturn(10);
        when( mock_collision.getHeight() ).thenReturn(18);

        when( mock_collision.getCell(0 ,0) ).thenReturn(null);
        when( mock_collision.getCell(1 ,0) ).thenReturn(null);
        when( mock_collision.getCell(2 ,0) ).thenReturn(null);
        when( mock_collision.getCell(3 ,0) ).thenReturn(null);
        when( mock_collision.getCell(4 ,0) ).thenReturn(null);
        when( mock_collision.getCell(5 ,0) ).thenReturn(null);
        when( mock_collision.getCell(6 ,0) ).thenReturn(null);
        when( mock_collision.getCell(7 ,0) ).thenReturn(null);
        when( mock_collision.getCell(8 ,0) ).thenReturn(null);
        when( mock_collision.getCell(9 ,0) ).thenReturn(null);

        when( mock_collision.getCell(0 ,1) ).thenReturn(null);
        when( mock_collision.getCell(1 ,1) ).thenReturn(null);
        when( mock_collision.getCell(2 ,1) ).thenReturn(null);
        when( mock_collision.getCell(3 ,1) ).thenReturn(null);
        when( mock_collision.getCell(4 ,1) ).thenReturn(null);
        when( mock_collision.getCell(5 ,1) ).thenReturn(null);
        when( mock_collision.getCell(6 ,1) ).thenReturn(null);
        when( mock_collision.getCell(7 ,1) ).thenReturn(null);
        when( mock_collision.getCell(8 ,1) ).thenReturn(null);
        when( mock_collision.getCell(9 ,1) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,2) ).thenReturn(mock_cell);
        when( mock_collision.getCell(1 ,2) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,2) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,2) ).thenReturn(null);
        when( mock_collision.getCell(4 ,2) ).thenReturn(null);
        when( mock_collision.getCell(5 ,2) ).thenReturn(null);
        when( mock_collision.getCell(6 ,2) ).thenReturn(null);
        when( mock_collision.getCell(7 ,2) ).thenReturn(null);
        when( mock_collision.getCell(8 ,2) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,2) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(1 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(4 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,3) ).thenReturn(null);
        when( mock_collision.getCell(6 ,3) ).thenReturn(null);
        when( mock_collision.getCell(7 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(8 ,3) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,3) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(1 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(4 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(8 ,4) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,4) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(1 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(4 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(8 ,5) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,5) ).thenReturn(null);

        when( mock_collision.getCell(0 ,6) ).thenReturn(null);
        when( mock_collision.getCell(1 ,6) ).thenReturn(null);
        when( mock_collision.getCell(2 ,6) ).thenReturn(null);
        when( mock_collision.getCell(3 ,6) ).thenReturn(mock_cell);
        when( mock_collision.getCell(4 ,6) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,6) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,6) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,6) ).thenReturn(mock_cell);
        when( mock_collision.getCell(8 ,6) ).thenReturn(null);
        when( mock_collision.getCell(9 ,6) ).thenReturn(null);

        when( mock_collision.getCell(0 ,7) ).thenReturn(null);
        when( mock_collision.getCell(1 ,7) ).thenReturn(null);
        when( mock_collision.getCell(2 ,7) ).thenReturn(null);
        when( mock_collision.getCell(3 ,7) ).thenReturn(null);
        when( mock_collision.getCell(4 ,7) ).thenReturn(null);
        when( mock_collision.getCell(5 ,7) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,7) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,7) ).thenReturn(null);
        when( mock_collision.getCell(8 ,7) ).thenReturn(null);
        when( mock_collision.getCell(9 ,7) ).thenReturn(null);

        when( mock_collision.getCell(0 ,8) ).thenReturn(null);
        when( mock_collision.getCell(1 ,8) ).thenReturn(null);
        when( mock_collision.getCell(2 ,8) ).thenReturn(null);
        when( mock_collision.getCell(3 ,8) ).thenReturn(null);
        when( mock_collision.getCell(4 ,8) ).thenReturn(null);
        when( mock_collision.getCell(5 ,8) ).thenReturn(null);
        when( mock_collision.getCell(6 ,8) ).thenReturn(null);
        when( mock_collision.getCell(7 ,8) ).thenReturn(null);
        when( mock_collision.getCell(8 ,8) ).thenReturn(null);
        when( mock_collision.getCell(9 ,8) ).thenReturn(null);

        when( mock_collision.getCell(0 ,9) ).thenReturn(null);
        when( mock_collision.getCell(1 ,9) ).thenReturn(null);
        when( mock_collision.getCell(2 ,9) ).thenReturn(null);
        when( mock_collision.getCell(3 ,9) ).thenReturn(null);
        when( mock_collision.getCell(4 ,9) ).thenReturn(null);
        when( mock_collision.getCell(5 ,9) ).thenReturn(null);
        when( mock_collision.getCell(6 ,9) ).thenReturn(null);
        when( mock_collision.getCell(7 ,9) ).thenReturn(null);
        when( mock_collision.getCell(8 ,9) ).thenReturn(null);
        when( mock_collision.getCell(9 ,9) ).thenReturn(null);

        when( mock_collision.getCell(0 ,10) ).thenReturn(null);
        when( mock_collision.getCell(1 ,10) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,10) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,10) ).thenReturn(null);
        when( mock_collision.getCell(4 ,10) ).thenReturn(null);
        when( mock_collision.getCell(5 ,10) ).thenReturn(null);
        when( mock_collision.getCell(6 ,10) ).thenReturn(null);
        when( mock_collision.getCell(7 ,10) ).thenReturn(null);
        when( mock_collision.getCell(8 ,10) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,10) ).thenReturn(null);

        when( mock_collision.getCell(0 ,11) ).thenReturn(null);
        when( mock_collision.getCell(1 ,11) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,11) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,11) ).thenReturn(null);
        when( mock_collision.getCell(4 ,11) ).thenReturn(null);
        when( mock_collision.getCell(5 ,11) ).thenReturn(null);
        when( mock_collision.getCell(6 ,11) ).thenReturn(null);
        when( mock_collision.getCell(7 ,11) ).thenReturn(null);
        when( mock_collision.getCell(8 ,11) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,11) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,12) ).thenReturn(null);
        when( mock_collision.getCell(1 ,12) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,12) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,12) ).thenReturn(null);
        when( mock_collision.getCell(4 ,12) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,12) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,12) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,12) ).thenReturn(null);
        when( mock_collision.getCell(8 ,12) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,12) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,13) ).thenReturn(null);
        when( mock_collision.getCell(1 ,13) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,13) ).thenReturn(mock_cell);
        when( mock_collision.getCell(3 ,13) ).thenReturn(null);
        when( mock_collision.getCell(4 ,13) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,13) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,13) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,13) ).thenReturn(null);
        when( mock_collision.getCell(8 ,13) ).thenReturn(mock_cell);
        when( mock_collision.getCell(9 ,13) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,14) ).thenReturn(null);
        when( mock_collision.getCell(1 ,14) ).thenReturn(mock_cell);
        when( mock_collision.getCell(2 ,14) ).thenReturn(null);
        when( mock_collision.getCell(3 ,14) ).thenReturn(null);
        when( mock_collision.getCell(4 ,14) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,14) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,14) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,14) ).thenReturn(null);
        when( mock_collision.getCell(8 ,14) ).thenReturn(null);
        when( mock_collision.getCell(9 ,14) ).thenReturn(mock_cell);

        when( mock_collision.getCell(0 ,15) ).thenReturn(null);
        when( mock_collision.getCell(1 ,15) ).thenReturn(null);
        when( mock_collision.getCell(2 ,15) ).thenReturn(null);
        when( mock_collision.getCell(3 ,15) ).thenReturn(null);
        when( mock_collision.getCell(4 ,15) ).thenReturn(mock_cell);
        when( mock_collision.getCell(5 ,15) ).thenReturn(mock_cell);
        when( mock_collision.getCell(6 ,15) ).thenReturn(mock_cell);
        when( mock_collision.getCell(7 ,15) ).thenReturn(null);
        when( mock_collision.getCell(8 ,15) ).thenReturn(null);
        when( mock_collision.getCell(9 ,15) ).thenReturn(null);

        when( mock_collision.getCell(0 ,16) ).thenReturn(null);
        when( mock_collision.getCell(1 ,16) ).thenReturn(null);
        when( mock_collision.getCell(2 ,16) ).thenReturn(null);
        when( mock_collision.getCell(3 ,16) ).thenReturn(null);
        when( mock_collision.getCell(4 ,16) ).thenReturn(null);
        when( mock_collision.getCell(5 ,16) ).thenReturn(null);
        when( mock_collision.getCell(6 ,16) ).thenReturn(null);
        when( mock_collision.getCell(7 ,16) ).thenReturn(null);
        when( mock_collision.getCell(8 ,16) ).thenReturn(null);
        when( mock_collision.getCell(9 ,16) ).thenReturn(null);

        when( mock_collision.getCell(0 ,17) ).thenReturn(null);
        when( mock_collision.getCell(1 ,17) ).thenReturn(null);
        when( mock_collision.getCell(2 ,17) ).thenReturn(null);
        when( mock_collision.getCell(3 ,17) ).thenReturn(null);
        when( mock_collision.getCell(4 ,17) ).thenReturn(null);
        when( mock_collision.getCell(5 ,17) ).thenReturn(null);
        when( mock_collision.getCell(6 ,17) ).thenReturn(null);
        when( mock_collision.getCell(7 ,17) ).thenReturn(null);
        when( mock_collision.getCell(8 ,17) ).thenReturn(null);
        when( mock_collision.getCell(9 ,17) ).thenReturn(null);

    }

    private static void initiateStairs(){
        when( mock_stairs.getTileWidth() ).thenReturn(16f);
        when( mock_stairs.getTileHeight() ).thenReturn(3f);
        when( mock_stairs.getWidth() ).thenReturn(10);
        when( mock_stairs.getHeight() ).thenReturn(17);

		when( mock_stairs.getCell(0 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,0) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(4 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,0) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,0) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,1) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(4 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,1) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,1) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,1) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,2) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(4 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,2) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,2) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,2) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,3) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(4 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,3) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,3) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,3) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,4) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,4) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,4) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,5) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,5) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,5) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,6) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,6) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,6) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,6) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,6) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,6) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,6) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,6) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,6) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,6) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,7) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,7) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,7) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,7) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,7) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,7) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,7) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(7 ,7) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,7) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,7) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,8) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,8) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,8) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,8) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,8) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,8) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(6 ,8) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,8) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,8) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,8) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,9) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,9) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,9) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,9) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,9) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,9) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(6 ,9) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,9) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,9) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,9) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,10) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,10) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,10) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,10) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,10) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,10) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(6 ,10) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,10) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,10) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,10) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,11) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,11) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,11) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,11) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,12) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,12) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,12) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,12) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,12) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,12) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(6 ,12) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,12) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,12) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,12) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,13) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,13) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,13) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(3 ,13) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,13) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,13) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(6 ,13) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,13) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,13) ).thenReturn(mock_cell);
        when( mock_stairs.getCell(9 ,13) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,14) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,14) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,15) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,15) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,16) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,16) ).thenReturn(null);

        when( mock_stairs.getCell(0 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(1 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(2 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(3 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(4 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(5 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(6 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(7 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(8 ,17) ).thenReturn(null);
        when( mock_stairs.getCell(9 ,17) ).thenReturn(null);

    }
}
