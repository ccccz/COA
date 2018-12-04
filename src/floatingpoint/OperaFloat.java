package floatingpoint;
import floatingpoint.MyFloat;
import integer.MyInteger;

import static integer.OperaInt.*;

public class OperaFloat {
    public final static int sign=1,exponent=8,significand=23,allbits=32;

    /**
     * 浮点数加法
     * @param a
     * @param b
     * @return
     */
    public static MyFloat add(MyFloat a,MyFloat b){
        MyFloat result=new MyFloat();
        String binA=a.getBinaryFloat(),binB=b.getBinaryFloat();
        int expA=MyFloat.bin2int(binA.substring(sign,sign+exponent)),expB=MyFloat.bin2int(binB.substring(sign,sign+exponent));
        double floA=a.getNumbleFloat(),floB=b.getNumbleFloat();

        //a或b为0，结果为另一个数
        if (a.getNumbleFloat()==0){
            result.setBinaryFloat(b.getBinaryFloat());
            result.setNumbleFloat(b.getNumbleFloat());
            return result;
        }else if (b.getNumbleFloat()==0){
            result.setBinaryFloat(a.getBinaryFloat());
            result.setNumbleFloat(a.getNumbleFloat());
            return result;
        }

        //对齐
        String sigA="01"+a.getBinaryFloat().substring(sign+exponent,allbits);
        String sigB="01"+b.getBinaryFloat().substring(sign+exponent,allbits);
        if (expA>expB){
            while (expA!=expB){
                sigB="0"+sigB;
                expB++;
            }
            sigB=sigB.substring(0,sigA.length());
        }else if (expB>expA){
            while (expB!=expA){
                sigA="0"+sigA;
                expA++;
            }
            sigA=sigA.substring(0,sigB.length());
        }

        //移位后有一个为0
        if (MyFloat.bin2int(sigA)==0){
            result.setBinaryFloat(b.getBinaryFloat());
            result.setNumbleFloat(b.getNumbleFloat());
            return result;
        }else if (MyFloat.bin2int(sigB)==0){
            result.setBinaryFloat(a.getBinaryFloat());
            result.setNumbleFloat(a.getNumbleFloat());
            return result;
        }

        //相加
        if (!binA.substring(0,1).equals(binB.substring(0,1))){
            binB=MyInteger.negative(binB);
        }
        char[] as,bs,cs,ss;   //a,b,进位，结果
        as=sigA.toCharArray();
        bs=sigB.toCharArray();
        ss=new char[as.length];
        cs=new char[as.length+1];
        cs[as.length]='0';
        for (int i=as.length-1;i>=0;i--){
            cs[i]=or(and(as[i],bs[i]),and(as[i],cs[i+1]),and(cs[i+1],bs[i]));
            ss[i]=xor(as[i],bs[i],cs[i+1]);
        }

        //TODO: 我不想写了，现在得到了不知道是正是负的结果
        if (ss[0]=='1'){
            expA++;
        }

        return result;
    }

    /**
     * 浮点数减法
     * @param a
     * @param b
     * @return
     */
    public static MyFloat sub(MyFloat a,MyFloat b){
        return null;
    }

    /**
     * 浮点数乘法
     * @param a
     * @param b
     * @return
     */
    public static MyFloat mul(MyFloat a,MyFloat b){
        return null;
    }

    /**
     * 浮点数除法
     * @param a
     * @param b
     * @return
     */
    public static MyFloat div(MyFloat a,MyFloat b){
        return null;
    }
}
