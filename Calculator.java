import java.util.*;


class Calculator{

    public static int add(int a,int b){
        return a+b;
    }
    public static int sub(int a,int b){
        return a-b;
    }
    public  static double mul(int a,int b){
        return (double) a*b;
    }

    public static double div(int a,int b){
        return (double) a/b;
    }
    public static  void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("Welcome to Calc where u can add, multiply, divide or subtract two number");
            System.out.println("Choose any one option: \n1.Add\n2.Subtract\n3.Multiply\n4.Divide\n5.Exit");
            int choice=sc.nextInt();
            if(choice==5){
                break;
            }
            System.out.print("Enter First Number ");
            int a=sc.nextInt();
            System.out.print("\nEnter Second NUmber");
            int b=sc.nextInt();
            System.out.println();
            switch(choice){
                case 1:
                    System.out.println("Sum of "+a+" and "+b+" is "+add(a,b));
                    break;
                case 2:
                    System.out.println("Difference of "+a+" and "+b+" is "+sub(a,b));
                    break;
                case 3:
                    System.out.println("Multiplication of "+a+" and "+b+" is "+mul(a,b));
                    break;
                case 4:
                    if(b==0){
                        System.err.println("Error cant divide by 0");
                    }
                    else{
                    System.out.println("Division of "+a+" and "+b+" is "+div(a,b));
                    }
                    break;

                
            }
            System.out.println("Do you want to exit 1.Yes 2.No");
            choice=sc.nextInt();
            if(choice==1){
                break;
            }
        }
    }

}