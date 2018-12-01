package integer;

public class MyInteger {

    public static void main(String[] args) throws Exception {
//        System.out.println(OperaInt.multi(new MyInteger(-7),new MyInteger(-6)).getMe());
        OperaInt.division(new MyInteger(24),new MyInteger(-9));
//        System.out.println(MyInteger.logicBin2int("111111111111111111111111111111111111111111111111111111111111111"));
    }

    final static int bits=32;
    static long theBig=(long)Math.pow(2,bits);
    private long me;
    public String binaryMy;

    //构造方法
    public MyInteger(){
        me=0;
        binaryMy=complement(Integer.toBinaryString((int) me));
    }
    public MyInteger(String str){
        me=bin2int(str);
        if (str.substring(0,1).equals("1")){
            me= (long) (me-theBig);
        }
        binaryMy=int2bin(me);
        binaryMy=binaryMy.substring(binaryMy.length()-bits,binaryMy.length());
    }
    public MyInteger(long i){
        me=i;
        binaryMy=Integer.toBinaryString((int) me);
        binaryMy=complement(binaryMy);
    }

    //get and set
    public long getMe() {
        return me;
    }
    public String getBinaryMy() {
        return binaryMy;
    }
    public void setMe(long me) {
        this.me = me;
    }
    public static void setTheBig(long theBig) {
        MyInteger.theBig = theBig;
    }

    //在左边添0式补全
    public static String complement(String str){
        if (str==null){
            str="";
        }
        while(str.length()<bits){
            str="0"+str;
        }

        return str;
    }

    //二进制字符串的大小
    public static long bin2int(String str){
        long num=0;
        char[] cs=str.toCharArray();
        for (int i=0;i<cs.length;i++){
            if (cs[i]=='1'){
                num=num*2+1;
            }else{
                num=num*2;
            }
        }
        if (str.charAt(0)=='1'){
            //负数
            num=(int)(num-theBig);
        }
        return num;
    }
    public static long logicBin2int(String str){
        long num=0;
        char[] cs=str.toCharArray();
        for (int i=0;i<cs.length;i++){
            if (cs[i]=='1'){
                num=num*2+1;
            }else{
                num=num*2;
            }
        }
        return num;
    }

    //数字转字符串
    public static String int2bin(long a){
        String bin=Integer.toBinaryString((int) a);
        bin=complement(bin);
        return bin;
    }

    //取反加一
    public static String negative(String str){
        char[] strs=str.toCharArray();
        String result="";
        for (int i=0;i<strs.length;i++){
            strs[i]=OperaInt.no(strs[i]);
            result=result+strs[i];
        }
        try {
            result=OperaInt.add(new MyInteger(result),new MyInteger(1)).getBinaryMy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
