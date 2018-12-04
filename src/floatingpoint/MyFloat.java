package floatingpoint;

public class MyFloat {

    public static void main(String[] args) {
        MyFloat a=new MyFloat(0.5);
        MyFloat b=new MyFloat(a.getBinaryFloat());
        System.out.println(b.getNumbleFloat());
    }

    public static void x(int a){
        a=a+5;
        System.out.println(a);

    }

    public final static int sign=1,exponent=8,significand=23,allbits=32;
    private String binaryFloat;
    private double numbleFloat;

    public String getBinaryFloat() {
        return binaryFloat;
    }
    public void setBinaryFloat(String binaryFloat) {
        this.binaryFloat = binaryFloat;
    }
    public double getNumbleFloat() {
        return numbleFloat;
    }
    public void setNumbleFloat(double numbleFloat) {
        this.numbleFloat = numbleFloat;
    }

    public MyFloat(){
        numbleFloat=0;
        binaryFloat="";
        for (int i=0;i<allbits; i++){
            binaryFloat=binaryFloat+"0";
        }
    }
    public MyFloat(double numbleFloat){
        this.numbleFloat=numbleFloat;
        binaryFloat=float2bin(numbleFloat);
    }
    public MyFloat(String binaryFloat){
        this.binaryFloat=binaryFloat;
        numbleFloat=bin2float(binaryFloat);
    }

    /**
     * 将浮点数转化为二进制表示
     * @param numbleFloat
     * @return
     */
    public String float2bin(double numbleFloat) {
        String result="";
        //符号位
        if (numbleFloat<0){
            result=result+"1";
            numbleFloat=-numbleFloat;
        }else{
            result=result+"0";
        }

        String theInt="",theFloat="";        //整数部分与小数部分
        int intPart=(int)numbleFloat;
        double floatPart=numbleFloat-intPart;

        //向下溢出的情况
        if (numbleFloat<Math.pow(2,-126)){
            for (int i=0; i<exponent; i++){
                result=result+"1";
            }
            numbleFloat=Math.pow(2,126)*numbleFloat;
            while(floatPart>0 && theFloat.length()<significand){         //32是随便捏的数，理论上是要一直算直到有效位数为24位为止的
                floatPart=floatPart*2;
                if (floatPart>=1){
                    theFloat=theFloat+"1";
                    floatPart=floatPart-1;
                }else{
                    theFloat=theFloat+"0";
                }
            }
            result=result+floatPart;
            while (result.length()<allbits){
                result=result+"0";
            }
            return result;
        }else if (numbleFloat>(Math.pow(2,127)*(2-Math.pow(2,-23)))){      //向上溢出的情况
            for (int i=0; i<exponent; i++){
                result=result+"1";
            }
            for (int i=0; i<significand; i++){
                result=result+"0";
            }
            return result;
        }

        //尾数
        while(intPart>0){
            if (intPart%2==0){
                theInt="0"+theInt;
            }else{
                theInt="1"+theInt;
            }
            intPart=intPart/2;
        }
        while(floatPart>0 && theFloat.length()<32){         //32是随便捏的数，理论上是要一直算直到有效位数为24位为止的
            floatPart=floatPart*2;
            if (floatPart>=1){
                theFloat=theFloat+"1";
                floatPart=floatPart-1;
            }else{
                theFloat=theFloat+"0";
            }
        }

        //指数位
        int exp=0;
        if (!theInt.equals("")) {
            exp=theInt.length()-1;
        }else{
            while (theFloat.substring(0,1).equals("0")){
                exp=exp-1;
                theFloat=theFloat.substring(1);
            }
            exp=exp-1;
            theFloat=theFloat.substring(1);
        }
        exp=exp+127;
        String binexp=int2bin(exp);
        while (binexp.length()<exponent){
            binexp="0"+binexp;
        }
        result=result+binexp;          //加指数位
        if (exp>=127){                 //加尾数位
            result=result+theInt.substring(1)+theFloat;
        }else{
            result=result+theFloat;
        }
        //对齐
        if (result.length()>allbits){
            result=result.substring(0,allbits);
        }else if(result.length()<allbits){
            while (result.length()<allbits){
                result=result+"0";
            }
        }
        return result;
    }
    public double bin2float(String binaryFloat) {
        String str1,str2,str3;
        double result=0;
        str1=binaryFloat.substring(0,sign);
        str2=binaryFloat.substring(sign,exponent+sign);
        str3=binaryFloat.substring(exponent+sign,allbits);
        
        //先解决尾数部分
        char[] cs=str3.toCharArray();
        for (int i=significand-1; i>=0; i--){
            result=result/2;
            if (cs[i]=='1'){
                result=result+0.5;
            }
        }
        result=result+1;
        
        //再解决指数问题
        result=result*Math.pow(2,(bin2int(str2)-127));

        //符号问题
        if (str1.equals("1")){
            result=-result;
        }
        return result;
    }

    /**
     *
     * @param i
     * @return
     */
    public static String int2bin(int i){
        String result="";
        while(i>0){
            if (i%2==0){
                result="0"+result;
            }else{
                result="1"+result;
            }
            i=i/2;
        }
        return result;
    }
    public static int bin2int(String str){
        int result=0;
        char[] cs=str.toCharArray();
        for (int i=0;i<cs.length;i++){
            if (cs[i]=='1'){
                result=result*2+1;
            }else{
                result=result*2;
            }
        }
        return result;
    }

}
