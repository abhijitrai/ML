package prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class MyBigClass implements Serializable {

    String s1 , s2 ;
    MySmallerClass mySmallerClass;

    public MyBigClass(String s1, String s2, MySmallerClass mySmallerClass) {
        this.s1 = s1;
        this.s2 = s2;
        this.mySmallerClass = mySmallerClass;
    }

    public MyBigClass createCopy(){
        return SerializationUtils.roundtrip(this);
    }

}

class MySmallerClass implements  Serializable{
    public MySmallerClass(String s3, String s4) {
        this.s3 = s3;
        this.s4 = s4;
    }

    String s3,s4 ;
}

class TestClass{

    public static void main(String[] args) {
        MyBigClass bigClass = new MyBigClass("s1","s2",  new MySmallerClass("s3","s4") );
        MyBigClass bigClass1 = bigClass.createCopy();
        System.out.println("Big Class " + bigClass + " \t Small Class : " + bigClass.mySmallerClass);
        System.out.println("Big Class " + bigClass1 + "\t Small Class : " + bigClass1.mySmallerClass);
    }
}
