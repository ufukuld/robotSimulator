package com.ufuk.robot.model;

public class Robot {
	private int xpos;
	private int ypos;
	private Face face;
	
	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public Face getFace() {
		return face;
	}
	public void setFace(Face face) {
		this.face = (Face) face;
	}
	public void setFace(String strFace) {
		if (strFace.equals("NORTH")) this.face = Face.NORTH;
		if (strFace.equals("EAST")) this.face = Face.EAST;
		if (strFace.equals("SOUTH")) this.face = Face.SOUTH;
		if (strFace.equals("WEST")) this.face = Face.WEST;
	}
	
    public Face turnLeft(Face face) {
        if (face.equals(Face.NORTH)) return Face.WEST; 
        else if (face.equals(Face.EAST)) return Face.NORTH;
        else if (face.equals(Face.SOUTH)) return Face.EAST;
        else return Face.SOUTH;
    }

    public Face turnRight(Face face) {
        if (face.equals(Face.NORTH)) return Face.EAST; 
        else if (face.equals(Face.EAST)) return Face.SOUTH;
        else if (face.equals(Face.SOUTH)) return Face.WEST;
        else return Face.NORTH;
    }
    
	public void move(Robot robot) {
		if (robot.face.equals(Face.EAST) && robot.getXpos() < Parameters.BOUND_RIGHT) {
			robot.setXpos(robot.getXpos() + 1);
		}
			
		if (robot.face.equals(Face.NORTH) && robot.getYpos() < Parameters.BOUND_UP) {
			robot.setYpos(robot.getYpos() + 1);
		}
		
		if (robot.face.equals(Face.SOUTH) && robot.getYpos() > Parameters.BOUND_DOWN) {
			robot.setYpos(robot.getYpos() - 1);
		}

		if (robot.face.equals(Face.WEST) && robot.getXpos() > Parameters.BOUND_LEFT) {
			robot.setXpos(robot.getXpos() - 1);
		}	
	}
}
