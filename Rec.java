import java.util.*;
import java.util.Stack;

/**
 * Class that contains multiple recursion question.
 * 
 * @author Ela Weinsberg
 */

public class Rec{

	final static int MAT_SIZE = 3;
	public static Vector<Integer> v = new Vector<Integer>();

	/**
	* Hanoi gardens question, moves discs between towers
	*
	* @param discNum is the number od discs to move between the towers
	* @param from this is the origion tower
	* @param to this is the destination tower
	* @param help this is an extra tower that will assist us to move the discs between origion and destionation towers
	*/
	public static void hanoi(int discNum, int from, int to, int help)
	{
		if (discNum==0)
			return;
		
		hanoi(discNum-1, from, help, to);
		System.out.println("move: "+discNum+ "from"+ from+ " to: "+ to);
		hanoi(discNum-1, help, to, from);
	}

	/**
	* Fibonacci series
	*
	* @param n this is the lenght of the fibonacci series
	* @return a number which is the cumulative sum of the numbers in the series
	**/
	public static int Fib(int n)
	{
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return Fib(n-1)+Fib(n-2);
	}

	
	public static int sum;
	public static void recRobotPath1(int x, int y)
	{

		v.addElement(x);
		v.addElement(y);

		if (x==MAT_SIZE-1 && y==MAT_SIZE-1)
		{	System.out.println("path:");
			// print the entire path
			for (int i=0; i<v.size(); i+=2)
			{
				int xx = v.get(i);
				int yy = v.get(i+1);
				System.out.println("("+xx+","+yy+")");
			}
			++sum;
			return ;
		}	
		
		if (x<MAT_SIZE-1)
			recRobotPath1(x+1,y);
		if (y<MAT_SIZE-1)
			recRobotPath1(x, y+1);

		int s=v.size();
		v.remove(s-1);
		v.remove(s-2);
	}



	public static void printVector()
	{
		/*Enumeration vEnum = v.elements();
      	System.out.println("\nElements in vector:");
      	while(vEnum.hasMoreElements())
         	System.out.println(vEnum.nextElement() + " ");
	*/
         	// use an iterator to display contents 
		Iterator vItr = v.iterator(); 
		System.out.println("\\nElements in vector:"); 
		while(vItr.hasNext()) 
			{
				System.out.print(vItr.next() + " "); 
				System.out.println();
			}
	}

	public static ArrayList<ArrayList<String>> getSubset(ArrayList<String> set, int index)
	{
		ArrayList<ArrayList<String>> subsets;
		// we arrived to the last element of the set, lets add empty list for every index
		if (index == set.size())
		{
			subsets = new ArrayList<ArrayList<String>>();
			subsets.add(new ArrayList<String>());
		}
		else
		{
			// create new subsets with the next element in the set
			subsets = getSubset(set, index+1);
			// get current element
			String s = set.get(index);

			ArrayList<ArrayList<String>> moresubsets = new ArrayList<ArrayList<String>>();
			// for every subset that was created add the current elemet
			for (ArrayList<String> subset : subsets)
			{
				ArrayList<String> newsubset = new ArrayList<String>();
				newsubset.addAll(subset); //
				newsubset.add(s);
				moresubsets.add(newsubset);
			}
			subsets.addAll(moresubsets);
		}
		return subsets;
	}

	public static ArrayList<String> getPerm(String s)
	{	
		ArrayList<String> perm = new ArrayList<String>();

		if (s.length() ==0)
		{
			perm.add("");
			return perm;
		}
		else
		{
			char first = s.charAt(0); // get the first character
			String remainder = s.substring(1); // remove the first character
			ArrayList<String> words = getPerm(remainder);
			for (String word : words) 
			{
				for (int j = 0; j <= word.length(); j++) 
				{
					perm.add(insertCharAt(word, first, j));
				}
			}
		}
		return perm;
	}

	public static String insertCharAt(String word, char c, int i)
	{
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}

	public static boolean recIsPali(String s)
	{
		boolean success = false;

		if (s.length() == 1 || s.length() == 0)
		{
			return true;
		}
		else 
		{	
			
			String start = s.substring(0,1);
			String news = s.substring(1,s.length()-1);
			String end = s.substring(s.length()-1,s.length());

			if (start.equals(end))
			{
				success = recIsPali(news);
			}
		}
		return success;
	}

	public static Stack<Integer> centsInCoins = new Stack<Integer>();

	public static void recRepCent(int cents)
	{
		boolean solved = false;

		if (cents<0)
		{
			return;
		}

		if (cents == 0)
		{	
			System.out.println("I have a cents representation:");
			for (int i=0; i<centsInCoins.size(); i++)
			{
				int coin = centsInCoins.elementAt(i);
				System.out.print(coin+",");
			}
			System.out.println("");
			return;
		}
		else
		{ 
			if (cents>=25)
			{
				centsInCoins.push(25);
				recRepCent(cents-25);
				centsInCoins.pop();
			}
			if (cents>=10)
			{
				centsInCoins.push(10);
				recRepCent(cents-10);
				centsInCoins.pop();
			}
			if (cents>=5)
			{
				centsInCoins.push(5);
				recRepCent(cents-5);
				centsInCoins.pop();
			}
			if (cents>=1)
			{
				centsInCoins.push(1);
				recRepCent(cents-1);
				centsInCoins.pop();
			}
					
		}
	}

	public static int intrecRepCent(int cents)
	{
		int retVal=0;

		if (cents<0)
		{
			return 0;
		}

		if (cents == 0)
		{	
			System.out.println("I have a cents representation:");
			for (int i=0; i<centsInCoins.size(); i++)
			{
				int coin = centsInCoins.elementAt(i);
				System.out.print(coin+",");
			}
			System.out.println("");
			return 1;
		}
		else
		{ 
			if (cents>=25)
			{
				centsInCoins.push(25);
				retVal = 1+ intrecRepCent(cents-25);
				centsInCoins.pop();
			}
			if (cents>=10)
			{
				centsInCoins.push(10);
				retVal = 1+ intrecRepCent(cents-10);
				centsInCoins.pop();
			}
			if (cents>=5)
			{
				centsInCoins.push(5);
				retVal = 1+ intrecRepCent(cents-5);
				centsInCoins.pop();
			}
			if (cents>=1)
			{
				centsInCoins.push(1);
				retVal = 1+ intrecRepCent(cents-1);
				centsInCoins.pop();
			}
			return retVal;
		}
	}

	public static int makeChange(int n, int denom) 
	{
		int next_denom = 0;
		switch (denom) 
		{
			case 25:
				next_denom = 10;
				break;
			case 10:
			next_denom = 5;
				break;
			case 5:
				next_denom = 1;
				break;
			case 1:
				return 1;
		}
		int ways = 0;
		for (int i = 0; i * denom <= n; i++) 
		{
			ways += makeChange(n - i * denom, next_denom);
		}
		return ways;
	}

	public static int[] chess =  new int[8];

	public static void placeQueen(int row)
	{
		if (row == 8)
		{
			printChess();
			return;
		}

		for (int i=0; i<8; i++)
		{
			//System.out.println("row is:"+row+" i is: "+i);
			chess[row]=i;
			if (check(row))
			{
				placeQueen(1+row);
			}
		}
	}

	public static boolean check(int row)
	{
		for (int i=0; i<row; i++)
		{
			int diff = Math.abs(chess[i]-chess[row]);
			//System.out.println("diff"+diff);
			if (diff==0 || diff == row-i)
			{
				return false;
			}
		}
		return true;
	}

	public static void printChess()
	{
		System.out.println("Chess is:");
		for (int i=0; i<8; i++)
		{
			System.out.print(" "+chess[i]+" ");
		}
		System.out.println("");
	}


	public static int[][] colorMat;
	final static int ROW_SIZE =10;
	final static int COL_SIZE=10;
	public static void setColorMat()
	{
		colorMat = new int[ROW_SIZE][COL_SIZE];
		
		for (int i=0; i<ROW_SIZE; i++)
		{
			for (int j=0; j<COL_SIZE; j++)
			{
				colorMat[i][j] =1;
			}
		}

		colorMat[1][1] = 2;
		colorMat[1][2] = 2;
		colorMat[2][1] = 2;
		colorMat[0][1] = 2;
	}

	public static void printMat()
	{
		for (int i=0; i<ROW_SIZE; i++)
		{
			for (int j=0; j<COL_SIZE; j++)
			{
				System.out.print(colorMat[i][j]);
			}
			System.out.println("");
		}

	}

	public static void paintMat(int x, int y, int newColor, int oldColor)
	{
		if (x<0 || y<0 || x>ROW_SIZE-1 || y> COL_SIZE-1)
		{
			return;
		}

		if( colorMat[x][y] == oldColor)
		{
			colorMat[x][y]=newColor;
			paintMat(x+1, y, newColor, oldColor);
	    	paintMat(x-1, y, newColor, oldColor);
			paintMat(x, y+1, newColor, oldColor);
			paintMat(x, y-1, newColor, oldColor);
			paintMat(x+1, y+1, newColor, oldColor);
			paintMat(x-1, y-1, newColor, oldColor);
			paintMat(x+1, y-1, newColor, oldColor);
			paintMat(x-1, y+1, newColor, oldColor);

		}
	}

	public static void hasWon()
	{
		int i=0;
		int j;

		j = checkRows();
		if (i == j)
		{
			j = checkCols();
			if (i == j)
			{
				j = checkDiagonals();
				if (i == j)
					System.out.println("No winner!");
				else
					System.out.println("Winner is:"+j);
			}
			else
			{
				System.out.println("Winner is:"+j);
			}
		
		}
		else
		{
			System.out.println("Winner is:"+j);
		}
		
	}

	public static int checkDiagonals()
	{
		if (ttt[0][0] !=0 && ttt[0][0]==ttt[1][1] && ttt[0][0]==ttt[2][2])
		{
			return ttt[0][0];
		}
		if (ttt[0][2] != 0 && ttt[0][2] ==ttt[1][1] && ttt[0][2]==ttt[2][0])
		{
			return ttt[0][2];
		}
		return 0;
	}
	public static int checkCols()
	{
		for (int i=0; i<3; i++)
		{
			if (ttt[0][i] != 0 && ttt[0][i] == ttt[1][i] && ttt[0][i] == ttt[2][i])
			{
				return ttt[0][i];
			}
		}	
		return 0;
	}

	public static int checkRows()
	{
		for (int i=0; i<3; i++)
		{
			if (ttt[i][0] != 0 && ttt[i][1] == ttt[i][0] && ttt[i][2] == ttt[i][0])
			{
				return ttt[i][0];
			}
		}
		return 0;
	}

	public static int[][] ttt;

	public static void setTTT()
	{
		ttt = new int[3][3];
		
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				ttt[i][j] =0;
			}
		}

		ttt[0][0] = 1;
		ttt[0][1] = 1;
		ttt[0][2] = 1;

	}


	public static void recPrimeFractuals(int num, Vector<Integer> vec)
	{
		if (num == 1)
		{
			return;
		}

		int i=0;
		while (num%ve.get(i) != 0)
		{
			i++;
		}
		vec.addElement(ve.get(i));
		recPrimeFractuals(num/ve.get(i), vec);
	}

	public static Vector<Integer> ve = new Vector<Integer>();

	public static void findPrimes(int num)
	{
		boolean found = false;
		//System.out.println("Number is:"+num);
		for (int i=2; i<=num; i++)
		{
			for (int j=2; j<=i/2 && !found; j++)
			{
				//System.out.println("i: "+i+" j: "+j);
				if (i%j == 0)
				{
					found = true;
				}
			}
			if (!found)
			{
				//System.out.println("added i: "+i);
				ve.addElement(i);
				
			}
			found = false;
		}
	}

 	public static void main(String[] args) {
		/*hanoi(10, 1,3,2);
		System.out.println("Fib of 8 is:"+Fib(8));
*/
		recRobotPath1(0,0);
		System.out.println("path number is: "+sum);
		printVector();

		ArrayList<String> al= new ArrayList<String>();
     	ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>(); 	
     	// add elements to the array list
      	al.add("1");
      	al.add("2");
      	al.add("3");
      	al.add("4");

		all = getSubset(al,0);
		
		for (ArrayList<String> subset : all) 
		{
			System.out.println("Contents of al: " + subset);
		}

		ArrayList<String> as = getPerm("abc");
		System.out.println("perm: " + as);

		if (recIsPali("abbcabba"))
		{
			System.out.println("abcde true");
		}
		else
		{
			System.out.println("abcde false");
		}

		if (recIsPali("abcba"))
		{
			System.out.println("abcba true");
		}
		else
		{
			System.out.println("abcba false");
		}

		//recRepCent(10);
		System.out.println(intrecRepCent(10));
		int n=47;
		System.out.println(makeChange(n, 25));

		placeQueen(0);

		setColorMat();
		printMat();
		paintMat(1,1,7,2);
		System.out.println("new mat");
		printMat();

		setTTT();
		hasWon();

		findPrimes(90);
		for (int i=0; i<ve.size(); i++)
		{
			//System.out.println("Here");	
			System.out.println(ve.get(i));
		}
		Vector<Integer> vecto = new Vector<Integer>();
		recPrimeFractuals(90, vecto);
		System.out.print("90 = ");
		int i=0;
		for (i=0; i<vecto.size()-1; i++)
		{
			//System.out.println("Here");	
			System.out.print(vecto.get(i)+"*");
		}	
		System.out.println(vecto.get(i));

	}
}