package top.yuwenxin.jdk.reflect;

public class Obj {
    private String priS;
    protected String proS;
    public String pubS;
    String s;
    static String ss;

    public String getPriS() {
        return priS;
    }

    public void setPriS(String priS) {
        this.priS = priS;
    }

    public String getProS() {
        return proS;
    }

    public void setProS(String proS) {
        this.proS = proS;
    }

    public String getPubS() {
        return pubS;
    }

    public void setPubS(String pubS) {
        this.pubS = pubS;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void pubM(){

    }

    protected void proM(){

    }

    private void priM(){

    }

    public void method(int a, String b, double c){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Obj{" +
                "priS='" + priS + '\'' +
                ", proS='" + proS + '\'' +
                ", pubS='" + pubS + '\'' +
                ", s='" + s + '\'' +
                '}';
    }
}
