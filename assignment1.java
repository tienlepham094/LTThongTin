import java.util.Scanner;

class assignment1 {
    public static void hienthiMenu(){
        System.out.println("1. H(X), H(Y), H(X | Y), H(Y | X), H(X, Y), H(Y) - H(Y | X), I(X; Y)");
        System.out.println("2. D(P(x)||P(y)), D(P(y)||P(x))");
        System.out.println("3. Exit");
        
    }
    public static boolean ktraSo(double a){
        if(a<0)
            return false;
        else if(a>=1)
            return false;
        else
            return true;
    }
    public static double [][] nhapMaTran(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ma tran xac suat (m*n)");
        System.out.println("Nhap so hang (m): ");
        int m = Integer.parseInt(sc.nextLine());
        System.out.println("Nhap so cot (n): ");
        int n = Integer.parseInt(sc.nextLine());

        double[][] arr = new double[m][n];
        double temp;
        for(int i=0; i<m; i++){
            for(int j=0; j<n;j++){
                System.out.printf("Nhap ma tran [%d][%d]\n", i, j);
                temp = Double.parseDouble(sc.nextLine());
                while(!ktraSo(temp)){
                    System.out.println("Nhap so khong am, so duong nho hon 1, vui long nhap lai");
                    temp = Double.parseDouble(sc.nextLine());
                }
                arr[i][j] = temp;
            }
        }
        System.out.println("Ma tran xac suat vua nhap: ");
        for(int i=0; i<m; i++){
            for(int j=0; j<n;j++){
                System.out.print(arr[i][j] +" ");
                if(j==n-1)
                    System.out.println("");
            }
        }
        // double [][] arr = {{0.125, 0.0625, 0.03125, 0.03125}, 
        //                     {0.0625,0.125, 0.03125, 0.03125},
        //                     {0.0625, 0.0625, 0.0625, 0.0625},
        //                     {0.25, 0,0,0}};
        // double [][] arr = {{0.125, 0.0625, 0.03125, 0.03125, 0.03125}, 
        //                     {0.0625,0.125, 0.03125, 0.03125, 0.03125},
        //                     {0.0625, 0.0625, 0.0625, 0.0625, 0.03125},
        //                     {0.25, 0,0,0, 0}};
        return arr;
    }
    public static void tinhCauB(double [][] arr){
        int n = arr[0].length; // so cot - x
        int m = arr.length; // so hang - y
        double [] p_x = new double[n];
        double [] p_y = new double[m];
        // tinh p(x)
            for(int i=0; i<n; i++){
                for (int j=0; j<m; j++){
                    p_x[i]+= arr[j][i];
                }
            }
        
       
        // tinh p(y)
        for (int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                p_y[i]+= arr[i][j];
            }
        }
        // tinh p(x|y)
        double [][] p_x_y = new double[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                p_x_y[i][j] = arr[i][j]/p_y[i];
            }
        }
 
        // tinh p(y|x)
        double [][] p_y_x = new double[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                p_y_x[i][j] = arr[i][j]/p_x[j];
            }
        }
        // tinh H(x)
        double h_x = 0;
        for (int i=0; i<n; i++){
            h_x+=-(p_x[i]*(Math.log(p_x[i])/Math.log(2)));
        }
        System.out.println("H(X) = "+ h_x);

        // tinh H(y)
        double h_y = 0;
        for (int i=0; i<m; i++){
            h_y+=-(p_y[i]*(Math.log(p_y[i])/Math.log(2)));
        }
        System.out.println("H(Y) = "+ h_y);

        // tinh H(x|y)
        double h_x_y=0;
        for(int i =0; i<m;i++){
            for(int j=0; j<n; j++){
                if(p_x_y[i][j]!=0)
                    h_x_y+=-(arr[i][j]*(Math.log(p_x_y[i][j])/Math.log(2)));
                
            }
        }
        System.out.println("H(X|Y) = "+ h_x_y);
        // tinh H(y|x)
        double h_y_x=0;
        for(int i =0; i<m;i++){
            for(int j=0; j<n; j++){
                if(p_y_x[i][j]!=0)
                    h_y_x+=-(arr[i][j]*(Math.log(p_y_x[i][j])/Math.log(2)));
            }
        }
        System.out.println("H(Y|X) = "+ h_y_x);

        // tinh H(X, Y)
        double h_xy=0;
        for(int i =0; i<m;i++){
            for(int j=0; j<n; j++){
                if(arr[i][j]!=0)
                    h_xy+=-(arr[i][j]*(Math.log(arr[i][j])/Math.log(2)));
            }
        }
        System.out.println("H(X,Y) = "+ h_xy);

        //H(Y) - H(Y | X)
        System.out.println("H(Y) - H(Y | X) = "+ (h_y-h_y_x));
        //I(X; Y)
        double i_xy=0;
        for(int i =0; i<m;i++){
            for(int j=0; j<n; j++){
                if(arr[i][j]!=0)
                    i_xy+=(arr[i][j]*(Math.log(arr[i][j]/(p_x[j]*p_y[i]))/Math.log(2)));
            }
        }
        System.out.println("I(X; Y) = "+i_xy);
    }
    public static void tinhCauC(double [][] arr){
        int n = arr[0].length; // so cot - x
        int m = arr.length; // so hang - y
        if(n==m){
            double [] p_x = new double[n];
            double [] p_y = new double[m];
            // tinh p(x)
                for(int i=0; i<n; i++){
                    for (int j=0; j<m; j++){
                        p_x[i]+= arr[j][i];
                    }
                }
            
        
            // tinh p(y)
            for (int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    p_y[i]+= arr[i][j];
                }
            }
            // D(P(x)||P(y))
            double d_xy=0;
            for(int i=0; i<n; i++){
                d_xy+=p_x[i]*(Math.log(p_x[i]/(p_y[i]))/Math.log(2));
            }
            System.out.println("D(P(x)||P(y)) = "+ d_xy);

            // D(P(y)||P(x))
            double d_yx=0;
            for(int i=0; i<m; i++){
                d_yx+=p_y[i]*(Math.log(p_y[i]/(p_x[i]))/Math.log(2));
            }
            System.out.println("D(P(y)||P(x)) = "+ d_yx);
        }  
        else{
        System.out.println("Khong phai ma tran vuong");
        }
    }
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        double[][] arr = nhapMaTran();
        while(true){
            hienthiMenu();

            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    tinhCauB(arr);
                    break;
                case 2:
                    tinhCauC(arr);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }
}