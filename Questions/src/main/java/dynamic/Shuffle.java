package dynamic;

import java.util.Arrays;

public class Shuffle {

    boolean [][] m ;

    public static void main(String... args) {

//        new Shuffle().sh("choco", "chips", "chochciops" ) ;
        new Shuffle().sh("choco", "chips", "chochciopsghjfghjgfjhgfjhg" ) ;
//        new Shuffle().sh("choco", "chips", "chochipsco" ) ;

    }
    
    boolean sh( String t1, String t2 , String t3)
    {
        String[] x1 = t1.split("");
        String[] y1 = t2.split("");
        
        m = new boolean [x1.length +1 ][ y1.length +1];
        
        String[] x = new String[x1.length +1 ];
        String[] y = new String[y1.length +1 ];
        for( int i= 0 ; i< x1.length + 1 ; i++ ){
            if( i == 0 )
                x[i]="";
            else
                x[i] = x1[i-1];
        }
        for( int i= 0 ; i< y1.length + 1 ; i++ ){
            if( i == 0 )
                y[i] = "";
            else
                y[i] = y1[i-1];
        }
        init0Row(x,t3);
        init0Col(y,t3);
        String[]z1 =  t3.split("");
        String[]z =  new String[z1.length+1];
        for( int i= 0 ; i< z1.length + 1 ; i++ ){
            if( i < 1  )
                z[i] = "";
            else
                z[i] = z1[i-1];
        }
        System.out.println(" " + t1 + Arrays.toString(x));
        System.out.println(" " + t2+ Arrays.toString(y));
        System.out.println(" " + t3 +  Arrays.toString(z));
        for(int i = 1 ;i <x.length ; i++){
            for( int j = 1 ; j< y.length ; j++){
                boolean check1 = x[i].equals(z [i+j]) && m[i-1][j];
                StringBuilder tmp =  new StringBuilder();
                tmp.append("Check 1 " + check1)
                        .append(" == x[" + i+"] -> "+ x[i])
                        .append(" z[ "+(i+ j)+"] -> " +z[i+j])
                        .append( " m["+ (i-1) +"]["+j+"] -> " + m[i-1][j] );

                System.out.println( tmp.toString());
//                System.out.println(x[i].equals(z [i+j-2]) );

                boolean check2 = y[j] .equals( z [i+j]) && m[i][j-1];
                tmp =  new StringBuilder();
                tmp.append("Check 2 "+ check2)
                        .append(" ==  y[" + j+"] -> "+y[j])
                        .append(" z[ "+(i+ j)+"] -> " +z[i+j])
                        .append( " m["+ (i) +"]["+(j-1)+"] -> " + m[i][j-1] );

                System.out.println( tmp.toString());
//                System.out.println(y[j] .equals( z [i+j-2]));

                m[i][j] = check1 || check2;
                System.out.println("Check -> " + m[i][j]);

            }
        }



        for( int i  = 0 ; i< m.length ; i++ ){
            System.out.println(Arrays.toString(m[i]));
        }

    return true;


    }


    void init0Col( String[]y, String s ){
//        System.out.println(" Col Init");
        String[]z =  s.split("");
        m[0][0] =true;
        boolean fallThrough =false;
        int k = 0 ;
        for( int i = 1; i< y.length; i++ ) {
            if(fallThrough)
            {
                m[i][0] = false;
                continue;
            }
            m[i][0] = false;
            for( int j = k ;j < z.length ; j++)
            {
                if(z[j].equals(y[i])){
                    m[i][0] = true;
                    continue;
                }
            }
        }
    }

    void init0Row( String[]x, String s ){
//        System.out.println(" Rows Init");
        String[]z =  s.split("");
        m[0][0] =true;
        boolean fallThrough =false;
        int k = 0 ;
        for( int i = 1; i< x.length; i++ ) {
            if(fallThrough)
            {
                m[0][i] = false;
                continue;
            }
            m[0][i] = false;
            for( int j = k ;j < z.length ; j++)
            {
                if(z[j].equals(x[i])){
                    m[0][i] = true;
                    continue;
                }
            }
        }
    }




    
    
}
