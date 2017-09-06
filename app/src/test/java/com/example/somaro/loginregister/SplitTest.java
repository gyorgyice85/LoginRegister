package com.example.somaro.loginregister;

import org.junit.Test;

import exception.XMustBeLargerThanZeroException;
import exception.YMustBeLargerThanZeroException;
import model.Corner;
import model.Node;
import model.Zone;
import source.DatabaseManager;
import source.DateiMemoDbHelper;
import source.DateiMemoDbSource;

import static org.junit.Assert.*;

/**
 * Created by Alexander on 06.09.2017.
 */

public class SplitTest {



    @Test
    public void testSplit_Vertical()
    {
        DateiMemoDbHelper dateiMemoDbHelper = new DateiMemoDbHelper();
        DatabaseManager.initializeInstance(dateiMemoDbHelper);

        Corner cornerBottomLeft;
        Corner cornerBottomRight;
        Corner cornerTopLeft;
        Corner cornerTopRight;
        Zone zone;
        try {
            cornerBottomLeft = new Corner(0.0,0.0);
            cornerBottomRight = new Corner(1.0,0.0);
            cornerTopLeft = new Corner(0.0,1.0);
            cornerTopRight = new Corner(1.0,1.0);

            zone = new Zone(cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight);

            Node node1 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);
            Node node2 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);
            Node node3 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);
            Node node4 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);


            zone.split(node1,node2,node3,node4);


            /*assertEquals(0.5, node1.getCornerBottomRightX(), 0);
            assertEquals(0.5, node1.getCornerTopRightX() ,0);
            assertEquals(0.5, node2.getCornerBottomRightX(), 0);
            assertEquals(0.5, node2.getCornerTopRightX() ,0);
            assertEquals(0.5, node3.getCornerBottomLeftX(), 0);
            assertEquals(0.5, node3.getCornerTopLeftX() ,0);
            assertEquals(0.5, node4.getCornerBottomLeftX(), 0);
            assertEquals(0.5, node4.getCornerTopLeftX() ,0);*/
        }
        catch(XMustBeLargerThanZeroException xMBLTZE)
        {

        }catch(YMustBeLargerThanZeroException yMBLTZE)
        {

        }catch( Exception e)
        {

        }

    }


    @Test
    public void testSplit_Horizontal()
    {

        Node node = new Node();
        Corner cornerBottomLeft;
        Corner cornerBottomRight;
        Corner cornerTopLeft;
        Corner cornerTopRight;
        Zone zone;
        try {
            cornerBottomLeft = new Corner(0.0,0.0);
            cornerBottomRight = new Corner(1.0,0.0);
            cornerTopLeft = new Corner(0.0,1.0);
            cornerTopRight = new Corner(1.0,1.0);
            zone = new Zone(cornerTopLeft,cornerTopRight,cornerBottomLeft,cornerBottomRight);

            Node node1 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);
            Node node2 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);
            Node node3 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);
            Node node4 = new Node(cornerBottomLeft,cornerBottomRight,cornerTopLeft,cornerTopRight);

            zone.split(node1,node2,node3,node4);
            zone.split(node1,node2,node3,node4);

            assertEquals(0.5, node1.getCornerTopLeftY(), 0);
            assertEquals(0.5, node1.getCornerTopRightY() ,0);
            assertEquals(0.5, node2.getCornerBottomRightY(), 0);
            assertEquals(0.5, node2.getCornerBottomLeftY() ,0);
            assertEquals(0.5, node3.getCornerTopRightY(), 0);
            assertEquals(0.5, node3.getCornerTopLeftY() ,0);
            assertEquals(0.5, node4.getCornerBottomLeftY(), 0);
            assertEquals(0.5, node4.getCornerBottomRightY() ,0);
        }
        catch(XMustBeLargerThanZeroException xMBLTZE)
        {

        }catch(YMustBeLargerThanZeroException yMBLTZE)
        {

        }catch( Exception e)
        {

        }

    }
}
