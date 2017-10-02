package dayFourteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test a=new Test();
		int[][] b= {{0,0},{0,2},{0,1},{1,0},{2,2}};
		a.numIslands2(3, 3, b);
	}
	 public List<Integer> numIslands2(int m, int n, int[][] positions) {
	        int[] id = new int[m * n]; // 表示各个index对应的root
	        
	        List<Integer> res = new ArrayList<>();
	        Arrays.fill(id, -1); // 初始化root为-1，用来标记water, 非-1表示land
	        int count = 0; // 记录island的数量
	        
	        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	        for (int i = 0; i < positions.length; i++) {
	            count++;
	            int index = positions[i][0] * n + positions[i][1];           
	            id[index] = index; // root初始化
	            
	            for (int j = 0; j < dirs.length; j++) {
	                int x = positions[i][0] + dirs[j][0];
	                int y = positions[i][1] + dirs[j][1];
	                if (x >= 0 && x < m && y >= 0 && y < n && id[x * n + y] != -1) {
	                    int root = root(id, x * n + y);

	                    // 发现root不等的情况下，才union, 同时减小count
	                    if (root != index) {
	                        id[root] = index;
	                        count--;
	                    }
	                }
	            }
	            res.add(count);
	        }
	        return res;
	    }
	    
	    public int root(int[] id, int i) {
	        while (i != id[i]) {
	            id[i] = id[id[i]]; // 优化，为了减小树的高度，这样做是为了找到当前这座岛的根节点，这样只需要改变这个根节点的值，而并不需要每次都为整个岛屿重新赋值               
	            i = id[i];
	        }
	        return i;
	    }
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0)
        	return 0;
        int numlands=0;
        boolean[][] visitedIsland=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++)
        {
        	for(int j=0;j<grid[0].length;j++)
        	{
        		if(grid[i][j]=='1'&&!visitedIsland[i][j])
        		{
        			numlands++;
        			dfsHelper(grid,visitedIsland,i,j);
        		}
        	}
        }
        return numlands;
    }
    public void dfsHelper(char[][] grid,boolean[][] visited,int i,int j)
    {
    	visited[i][j]=true;
		if(i-1>=0&&grid[i-1][j]=='1'&&!visited[i-1][j])
			dfsHelper(grid, visited, i-1, j);
		if(i+1<grid.length&&grid[i+1][j]=='1'&&!visited[i+1][j])
			dfsHelper(grid, visited, i+1, j);
		if(j-1>=0&&grid[i][j-1]=='1'&&!visited[i][j-1])
			dfsHelper(grid, visited, i, j-1);
		if(j+1<grid[0].length&&grid[i][j+1]=='1'&&!visited[i][j+1])
			dfsHelper(grid, visited, i, j+1);
    }
	  public int islandPerimeter1(int[][] grid) {
	        int islands = 0;
	        int neighbors = 0;
	        for (int i = 0; i < grid.length; i++) {
	            for (int j = 0; j < grid[0].length; j++) {
	                if (grid[i][j] == 1) {
	                    islands++;
	                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
	                        neighbors++;
	                    }
	                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
	                        neighbors++;
	                    }
	                }
	            }
	        }
	        return islands*4 - neighbors*2;
	    }
    public int islandPerimeter(int[][] grid) {
        int perimeter=0;
        if(grid==null||grid.length==0)
        	return perimeter;
        for(int i=0;i<grid.length;i++)
        {
        	for(int j=0;j<grid[0].length;j++)
        	{
        		int cellPerimeter=0;
        		if(grid[i][j]==1)
        		{
        			if(i-1<0||grid[i-1][j]==0)
        				cellPerimeter+=1;
        			if(i+1>=grid.length||grid[i+1][j]==0)
        				cellPerimeter+=1;
        			if(j-1<0||grid[i][j-1]==0)
        				cellPerimeter+=1;
        			if(j+1>=grid[0].length||grid[i][j+1]==0)
        				cellPerimeter+=1;
        		}
        		perimeter+=cellPerimeter;
        	}
        }
        return perimeter;
    }
    public int[] twoSum(int[] numbers, int target) {
    	int end=numbers.length;
    	for(int i=0;i<end;i++)
    	{
    		for(int j=i+1;j<end;j++)
    		{
    			if((numbers[i]+numbers[j])==target)
    				return new int[] {i+1,j+1};
    			else if(numbers[i]+numbers[j]>target)
    			{
    				end=j;
    				break;
    			}
    		}
    	}
    	return new int[] {0,0};
    }
    public int[] twoSum1(int[] numbers, int target) 
    
    {
                int a[];
                a = new int [2];
        
      int head = 0;
      int tail= (numbers.length)-1;
        
         while(head<tail)
         { long sum=numbers[head]+numbers[tail];
             if(sum==target)
               
                {     a[0]=head+1;
                      a[1]=tail+1;  
                 break;
                 }
        
         else if (sum > target)
         { tail=tail-1;}
         else
         {head = head+1;}
          
         }
       return a; 
        
    
    }
}
