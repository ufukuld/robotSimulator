package com.ufuk.robot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ufuk.robot.model.Parameters;
import com.ufuk.robot.model.Robot;

@SpringBootApplication
public class RobotApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RobotApplication.class, args);
		try {
			Robot robot = new Robot();
			
			List<String> commands = readFile();
			for (String command : commands) {
				if (command.startsWith("PLACE ")) {
					String[] params = command.substring(6).split(",");
					if (checkPlaceCommand(params)) {
						robot.setXpos(Integer.valueOf(params[0]));
						robot.setYpos(Integer.valueOf(params[1]));
						robot.setFace(params[2].trim());	
					}
				} else {
					if (robot.getFace() != null) {	
						if (command.equals("MOVE")) {
							robot.move(robot);
						} else if (command.equals("LEFT")) {
							robot.setFace(robot.turnLeft(robot.getFace()));
						} else if (command.equals("RIGHT")) {
							robot.setFace(robot.turnRight(robot.getFace()));					
						} else if (command.equals("REPORT")) {
							System.out.println(robot.getXpos() + ", " + robot.getYpos() + ", " + robot.getFace());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> readFile() throws IOException {
		List<String> listCommands = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("commands.txt"));
		try {
		    String line = br.readLine();
		    
		    while (line != null) {
		    	listCommands.add(line);
		        line = br.readLine();
		    }
		} finally {
		    br.close();
		}
	    return listCommands;
	}
	
	private static boolean checkPlaceCommand (String[] params) {
	    for (char c : params[0].toCharArray()) {
	        if (!Character.isDigit(c)) return false;
	    }
	    
	    for (char c : params[1].toCharArray()) {
	        if (!Character.isDigit(c)) return false;
	    }
	    
	    if ((Integer.valueOf(params[0]) < Parameters.BOUND_LEFT)
	    		|| (Integer.valueOf(params[0]) > Parameters.BOUND_RIGHT)
	    		|| (Integer.valueOf(params[1]) < Parameters.BOUND_DOWN)
	    		|| (Integer.valueOf(params[1]) > Parameters.BOUND_UP)) {
	    	return false;
	    }
	    
	    if (!params[2].trim().equals("NORTH")
	    		&& !params[2].trim().equals("EAST")
	    		&& !params[2].trim().equals("SOUTH")
	    		&& !params[2].trim().equals("WEST")) {
	    	return false;
	    }
	    
		return true;
	}

}
