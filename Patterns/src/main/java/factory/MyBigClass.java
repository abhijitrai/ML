package factory;

public class MyBigClass {
    private MyBigClass() {
    }

    private String s1, s2, s3, s4, s5, s6, s7, s8, s9;
    private int i1, i2, i3, i4, i5, i6, i7;
    private float f1, f2, f3, f4, f5, f6;
    private Double d1, d2, d3, d4;

    @Override
    public String toString() {
        return "MyBigClass{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                ", s3='" + s3 + '\'' +
                ", s4='" + s4 + '\'' +
                ", s5='" + s5 + '\'' +
                ", s6='" + s6 + '\'' +
                ", s7='" + s7 + '\'' +
                ", s8='" + s8 + '\'' +
                ", s9='" + s9 + '\'' +
                ", i1=" + i1 +
                ", i2=" + i2 +
                ", i3=" + i3 +
                ", i4=" + i4 +
                ", i5=" + i5 +
                ", i6=" + i6 +
                ", i7=" + i7 +
                ", f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4=" + f4 +
                ", f5=" + f5 +
                ", f6=" + f6 +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", d3=" + d3 +
                ", d4=" + d4 +
                '}';
    }

    public static class MyBigClassFactory {

        public static MyBigClass withS1(String s1) {
            MyBigClass cls = new MyBigClass();
            cls.s1 = s1;
            return cls;
        }

        public static MyBigClass withS1AndI1(String s1, int i1) {
            MyBigClass cls = new MyBigClass();
            cls.s1 = s1;
            cls.i1 = i1;
            return cls;
        }

    }

}

class TestClass {
    public static void main(String[] args) {
        MyBigClass.MyBigClassFactory factory = new MyBigClass.MyBigClassFactory();
        System.out.println(factory.withS1("String A1"));
        System.out.println(factory.withS1AndI1("String A1", 1));
    }
}
