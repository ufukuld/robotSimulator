package com.ufuk.robot.model;

import org.junit.Assert;
import org.junit.Test;

public class RobotTest {

	@Test
    public void testTurnLeft() throws Exception {
        Robot robot = new Robot();	
        robot.setFace(Face.EAST);
        Assert.assertEquals(robot.turnLeft(robot.getFace()), Face.NORTH);
	}
	
	@Test
    public void testTurnRight() throws Exception {
        Robot robot = new Robot();	
        robot.setFace(Face.EAST);
        Assert.assertEquals(robot.turnRight(robot.getFace()), Face.SOUTH);
	}
	
	@Test
    public void testMove() throws Exception {
        Robot robot = new Robot();
        robot.setXpos(3);
        robot.setYpos(2);
        robot.setFace(Face.EAST);
        robot.move(robot);
        Assert.assertEquals(robot.getXpos(), 4);
        robot.move(robot);
        Assert.assertEquals(robot.getXpos(), 4);
        
	}
}
