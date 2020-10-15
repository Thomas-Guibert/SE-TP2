
/* TextGraphics class
 *
 * TP of SE (version 2020)
 *
 * AM
 */

public class TextGraphics
{
   // Data
   private int n;
   private int m;
   private boolean [][] A;

   // Constructor I
   public TextGraphics(int n,int m)
   {
      try
      {
         if (n <= 0) throw new Exception("TextGraphics: the given size value for n is negative or zero");
         if (n%4 != 0) throw new Exception("TextGraphics: the given size value for n is not a multiple of 4");
         this.n = n;
         if (m <= 0) throw new Exception("TextGraphics: the given size value for m is negative or zero");
         if (m%4 != 0) throw new Exception("TextGraphics: the given size value for m is not a multiple of 4");
         this.m = m;
         this.A = new boolean [this.n][this.m];
         this.setAllPixelsOff();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }

   // Constructor II
   public TextGraphics(TextGraphics t)
   {
      try
      {
         if (t == null) throw new Exception("TextGraphics: input TextGraphics object is null");
         this.n = t.n;
         this.m = t.m;
         this.A = new boolean [this.n][this.m];
         for (int i = 0; i < this.n; i++)  for (int j = 0; j < this.m; j++)  this.A[i][j] = t.A[i][j];
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }

   // Set all Pixels Off
   public void setAllPixelsOff()
   {
      for (int i = 0; i < this.n; i++)  for (int j = 0; j < this.m; j++)  this.A[i][j] = false;
   }

   // Set all Pixels On
   public void setAllPixelsOn()
   {
      for (int i = 0; i < this.n; i++)  for (int j = 0; j < this.m; j++)  this.A[i][j] = true;
   }

   // Sierpinski iteration (sequential version)
   public TextGraphics Sierpinski1()
   {
      TextGraphics h = new TextGraphics(this.n,this.m);

      int i = 0;
      for (; i < this.n/2; i++)
      {
         int j = 0;
         for (; j < this.m/4; j++) h.A[i][j] = false;
         for (; j < 3*this.m/4; j++)
         {
            int row = 2*i;  int col = 2*(j - this.m/4);
            h.A[i][j] = this.A[row][col] || this.A[row][col+1] || this.A[row+1][col] || this.A[row+1][col+1];
         }
         for (; j < this.m; j++) h.A[i][j] = false;
      }
      for (; i < this.n; i++)
      {
         int j = 0;
         for (; j < this.m/2; j++)
         {
            int row = 2*(i - this.n/2);  int col = 2*j;
            h.A[i][j] = this.A[row][col] || this.A[row][col+1] || this.A[row+1][col] || this.A[row+1][col+1];
         }
         for (; j < this.m; j++)
         {
            int row = 2*(i - this.n/2);  int col = 2*(j - this.m/2);
            h.A[i][j] = this.A[row][col] || this.A[row][col+1] || this.A[row+1][col] || this.A[row+1][col+1];
         }
      } 
      return h;
   }

   // Sierpinski iteration (pseudo multi-threading version)
   public TextGraphics Sierpinski2()
   {
      TextGraphics h = new TextGraphics(this.n,this.m);

      for (int i = 0; i < this.n; i++)
      {
         for (int j = 0; j < this.m; j++)
         {
            // to be completed: please consider that you're not supposed to change the two initial for loops
         }
      }

      return h;
   }

   // Get "Pixel" symbol
   public static char pixel(boolean b)
   {
      char c = ' ';
      if (b)  c = 'o';
      return c;
   }

   // toString
   public String toString()
   {
      String print = "";
      for (int j = -1; j < this.m + 1; j++)  print = print + "-";
      print = print + "\n";
      for (int i = 0; i < this.n; i++)
      {
         print = print + "|";
         for (int j = 0; j < this.m; j++)  print = print + TextGraphics.pixel(this.A[i][j]);
         print = print + "|\n";
      }
      for (int j = -1; j < this.m + 1; j++)  print = print + "-";
      print = print + "\n";

      return print;
   }

   // main
   public static void main(String[] args) throws InterruptedException
   {
      TextGraphics g = new TextGraphics(36,80);
      g.setAllPixelsOn();
      System.out.println(g);
      for (int i = 0; i < 12; i++)
      {
         TextGraphics h = g.Sierpinski1();
         System.out.println(h);
         g = h;
         Thread.sleep(800);
      }
   }
}

