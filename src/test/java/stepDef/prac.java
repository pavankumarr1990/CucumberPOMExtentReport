package stepDef;

public class prac {
    public static void main(String args[]){
        System.out.println("Pavan");
        int n=5;
        int i,j;
      for(i=1;i<=2*n;i++)
      {
int c=i>n?2*n-i:i;
int space=n-c;
          for(j=1;j <=c;j++)
          {
              System.out.print("* ");
          }
          System.out.println();
      }
    }
}
