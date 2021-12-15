package sand.controller;
import sand.view.SandDisplay;
import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SNAD = 2;
  public static final int WATER = 3;
  public static final int CORAL = 4;
  public static final int SNOW = 5;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[6];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SNAD] = "Snad";
    names[WATER] = "Water";
    names[CORAL] = "Coral";
    names[SNOW] = "Snow";
    
    //1. Add code to initialize the data member grid with same dimensions
    
    this.grid = new int[numRows] [numCols];
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
   grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
    for (int row = 0; row < grid.length; row++)
    {
    	for (int col = 0; col < grid[0].length; col++)
    	{
    		int currentTool = grid [row][col];
    		
    		if (currentTool == METAL)
    		{
    			display.setColor(row, col, Color.GRAY);
    		}
    		else if (currentTool == EMPTY)
    		{
    			display.setColor(row, col, Color.BLACK);
    		}
    		else if (currentTool == SNAD)
    		{
    			display.setColor(row, col, Color.YELLOW);
    		}
    		else if (currentTool == WATER)
    		{
    			display.setColor(row, col, Color.BLUE);
    		}
    		else if (currentTool == CORAL)
    		{
    			display.setColor(row, col, Color.MAGENTA);
    		}
    		else if (currentTool == SNOW)
    		{
    			display.setColor(row, col, Color.WHITE);
    		}
    	}
    }
  }
  
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    int randomRow = (int) (Math.random() * grid.length);
    int randomCol = (int) (Math.random() * grid[0].length);
    
    int currentTool = grid[randomRow][randomCol];
    if (currentTool == SNAD)
    {
    	if (randomRow + 1 < grid.length && grid[randomRow + 1][randomCol] == EMPTY || randomRow + 1 < grid.length && grid[randomRow + 1][randomCol] == WATER)
    	{
    			int swappedParticle = grid[randomRow + 1][randomCol];
    			grid[randomRow + 1][randomCol] = SNAD;
    			grid[randomRow][randomCol] = swappedParticle;
    	}
    		
    }
    else if (currentTool == METAL)
    {
    	
    }
    else if (currentTool == EMPTY)
    {
    	
    }
    else if (currentTool == WATER)
    {
    	int randomMove = (int) (Math.random() * 3);
    
    	if (randomMove == 0)
    	{
    		if (randomRow + 1 < grid.length && grid[randomRow + 1][randomCol] == EMPTY)
    		{
    			int swappedParticle = grid[randomRow + 1][randomCol];
    			grid[randomRow + 1][randomCol] = WATER;
    			grid[randomRow][randomCol] = swappedParticle;
    		}
    	}
    	else if (randomMove == 1)
    	{
    		if (randomCol + 1 < grid[0].length && grid[randomRow][randomCol + 1] == EMPTY)
    		{
    			int swappedPixel = grid[randomRow][randomCol + 1];
    			grid[randomRow][randomCol + 1] = WATER;
    			grid[randomRow][randomCol] = swappedPixel;
    		}
    	}
    	else if (randomMove == 2)
    	{
    		if (randomCol - 1 >= 0 && grid[randomRow][randomCol - 1] == EMPTY)
    		{
    			int swappedPixel = grid[randomRow][randomCol - 1];
    			grid[randomRow][randomCol - 1] = WATER;
    			grid[randomRow][randomCol] = swappedPixel;	
    		}
    	}
    }
    else if (currentTool == CORAL)
    {
    	int nearbyCoralCount = 0;
    	int coralGrowth = (int)(Math.random() * 20);
    	if (randomRow - 1 >= 0 && randomCol - 1 >= 0 && grid[randomRow - 1][randomCol - 1] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (randomRow - 1 >= 0 && grid[randomRow - 1][randomCol] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (randomRow - 1 >= 0 && randomCol + 1 < grid[0].length && grid[randomRow - 1][randomCol + 1] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (grid[randomRow + 1][randomCol] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (randomCol + 1 < grid[0].length && grid[randomRow + 1][randomCol + 1] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (randomCol - 1 >= 0 && grid[randomRow + 1][randomCol - 1] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (randomCol + 1 < grid[0].length && grid[randomRow][randomCol + 1] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (randomCol - 1 >= 0 && grid[randomRow][randomCol - 1] == CORAL)
    	{
    		nearbyCoralCount += 1;
    	}
    	if (nearbyCoralCount <= 3)
    	{
    		if (coralGrowth == 0)
    		{
    			if (randomCol - 1 >= 0 && grid[randomRow][randomCol - 1] == WATER)
    			{
    				grid[randomRow][randomCol - 1] = CORAL;
    			}
    		}
    		else if (coralGrowth == 1)
    		{
    			if (randomCol + 1 < grid[0].length && grid[randomRow][randomCol + 1] == WATER)
    			{
    				grid[randomRow][randomCol + 1] = CORAL;
    			}
    		}
    		else if (coralGrowth == 2)
    			if (randomRow - 1 >= 0 && grid[randomRow - 1][randomCol] == WATER)
    			{
    				grid[randomRow - 1][randomCol] = CORAL;
    			}
    	}
    }
    
    else if (currentTool == SNOW)
    {
        
    		if (randomRow + 1 < grid.length && grid[randomRow + 1][randomCol] == EMPTY)
    		{
    			grid[randomRow + 1][randomCol] = SNOW;
    			grid[randomRow][randomCol] = EMPTY;
    			
    			int randomMove = (int) (Math.random() * 2);
    		
    			if (randomMove == 0)
    			{
    				if (randomCol + 1 < grid[0].length && grid[randomRow + 1][randomCol + 1] == EMPTY)
    				{
    					int swappedParticle = grid[randomRow + 1][randomCol + 1];
    	    			grid[randomRow + 1][randomCol + 1] = SNOW;
    	    			grid[randomRow + 1][randomCol] = swappedParticle;
    				}
    			}
    			else if (randomMove == 1)
    			{
    				if (randomCol - 1 >= 0 && grid[randomRow + 1][randomCol - 1] == EMPTY)
    				{
    					int swappedParticle = grid[randomRow + 1][randomCol - 1];
    	    			grid[randomRow + 1][randomCol - 1] = SNOW;
    	    			grid[randomRow + 1][randomCol] = swappedParticle;
    				}
    			}
    		}
    }
    
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
