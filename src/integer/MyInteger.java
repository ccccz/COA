package integer;

public class MyInteger {

    public static void main(String[] args) throws Exception {
        System.out.println(OperaInt.add(new MyInteger(2),new MyInteger(3)).getMe());
    }

    static long theBig=(long)Math.pow(2,32);
    private int me;
    public String binaryMy;

    //构造方法
    public MyInteger(){
        me=0;
        binaryMy=complement(Integer.toBinaryString(me));
    }
    public MyInteger(String str){
        binaryMy=complement(str);
        me=bin2int(binaryMy);
    }
    public MyInteger(int i){
        me=i;
        binaryMy=Integer.toBinaryString(me);
        binaryMy=complement(binaryMy);
    }

    //get and set
    public int getMe() {
        return me;
    }
    public String getBinaryMy() {
        return binaryMy;
    }
    public void setMe(int me) {
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
        while(str.length()<32){
            str="0"+str;
        }

        return str;
    }

    //二进制字符串的大小
    public static int bin2int(String str){
        int num=0;
        char[] cs=str.toCharArray();
        for (int i=0;i<cs.length;i++){
            if (cs[i]=='1'){
                num=num*2+1;
            }else{
                num=num*2;
            }
        }
        if (str.charAt(1)=='1'){
            //负数
            num=(int)(num-theBig);
        }
        return num;
    }
}
