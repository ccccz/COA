package integer;

import java.util.ArrayList;

/**
 * 计算类
 */
public class OperaInt {

    /**
     * 加法
     * 进位c实际是比其他的多一位的，多一位0
     * @param a
     * @param b
     * @return
     */
    public static MyInteger add(MyInteger a,MyInteger b) throws Exception {
        char[] as,bs,cs,ss;   //a,b,进位，结果
        as=a.getBinaryMy().toCharArray();
        bs=b.getBinaryMy().toCharArray();
        ss=new char[as.length];
        cs=new char[as.length+1];
        cs[as.length]='0';
        for (int i=as.length-1;i>=0;i--){
            cs[i]=or(and(as[i],bs[i]),and(as[i],cs[i+1]),and(cs[i+1],bs[i]));
            ss[i]=xor(as[i],bs[i],cs[i+1]);
        }

        //判断溢出
        if (cs[0]!=cs[1]){
            throw new Exception("溢出");
        }
        if (and(and(as[0],bs[0],no(ss[0])),and(no(as[0]),no(bs[0]),ss[0]))=='1'){
            throw new Exception("溢出");
        }

        //连接
        String result="";
        for (int i=0;i<as.length;i++){
            result=result+ss[i];
        }
        return new MyInteger(result);
    }

    /**
     * 整数的减法
     * 挺复杂的，我觉得需要重构代码
     * 其实就是加法，加它的负数
     * 我可能需要一个对整个字符串做逻辑运算的方法
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    public static MyInteger subtract(MyInteger a,MyInteger b) throws Exception{
        return add(a,new MyInteger(MyInteger.negative(b.getBinaryMy())));
    }

    /**
     * 整数的乘法
     * 好麻烦啊，写了好久
     * 头疼
     * 写一点有用的吧
     * 首先是Y0-Y1，反过来减的！！
     * 其次的话一定是算数右移，就是那种补一的
     * 再有的，需要两倍的位数来存储，即使最后的运算结果没有这么大
     * 小心使用toString，和我以为的不太一样QAQ
     * 有一个多补的Y0，为0
     * 布斯算法，其实没看懂不过会用就好
     * 有溢出的进位的话肯定是之前做了什么，一般都是直接抹去忽视掉的
     * 应该没有了吧，再想想
     * 刚刚在听EXO的歌，说来好久都没有听过了，挺好听的真的，EXO火起来不完全是因为脸啊
     * 肯定要重构的，不过改天再说吧
     * 其实就是不断的加法，不过加的数字有三种而已，可以写成三个字符串的运算
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    public static MyInteger multi(MyInteger a,MyInteger b) throws Exception {
        //是否有0
        if (a.getMe()==0 || b.getMe()==0){
            return new MyInteger(0);
        }

        VirtualRegister register;
        String ap=a.getBinaryMy();
        String an=MyInteger.negative(a.getBinaryMy());
        char[] bs=b.getBinaryMy().toCharArray();
        if (bs[bs.length-1]=='1'){
            register=new VirtualRegister(an);
        }else{
            register=new VirtualRegister(new MyInteger(0).getBinaryMy());
        }
        for (int i=bs.length-2; i>=0; i--){
            register.right(1);
            an=an+'0';
            ap=ap+'0';
            if (bs[i]<bs[i+1]){
                register=register.add(ap);
            }else if(bs[i]>bs[i+1]){
                register=register.add(an);
            }
        }
        register.right(1);
        int result=MyInteger.bin2int(toString(register.getMyRegister()));
        System.out.println(result);
        return new MyInteger(result);
//        //计算
//        long result=0;
//        //求出+x,-x
//        int ap=a.getMe();
//        int an;
//        if (ap>0){
//            an= (int) Math.pow(2,a.binaryMy.length()/2)-ap;
//        }else{
//            an=0-ap;
//            ap=(int) Math.pow(2,a.binaryMy.length()/2)+ap;
//        }
//        char[] asPos=a.getBinaryMy().toCharArray();
//        char[] asneg=MyInteger.negative(a.getBinaryMy()).toCharArray();
//        char[] bs=b.getBinaryMy().toCharArray();
//        if (bs[bs.length-1]=='1'){
//            result=result+ap;
//        }
//        for (int i=bs.length-2; i>=0; i--){
//            if (result>=Math.pow(2,bs.length*2-i-2)){
//                result= (long) (result+Math.pow(2,bs.length*2-i-1));
//            }
//            if (bs[i]>bs[i+1]){
//                result= (long) (result+ap*Math.pow(2,bs.length-i-1));
//            }else if(bs[i]<bs[i+1]){
//                result= (long) (result+an*Math.pow(2,bs.length-i-1));
//            }
//        }
//        return new MyInteger((int)result);
    }

    public static MyInteger division(MyInteger a,MyInteger b) throws Exception {
        return null;
    }


    //一些静态方法
    public static char and(char c1,char c2){
        if (c1=='1' && c2=='1'){
            return '1';
        }
        return '0';
    }
    public static char or(char c1,char c2){
        if (c1=='0' && c2=='0'){
            return '0';
        }
        return '1';
    }
    public static char xor(char c1,char c2){
        if (c1==c2){
            return '1';
        }
        return '0';
    }
    public static char no(char c){
        if (c=='1'){
            return '0';
        }
        return '1';
    }
    public static char and(char c1,char c2,char c3){
        return and(c1,and(c2,c3));
    }
    public static char or(char c1,char c2,char c3){
        return or(c1,or(c2,c3));
    }
    public static char xor(char c1,char c2,char c3){
        return xor(c1,xor(c2,c3));
    }
    public static String toString(char[] cs){
        String result="";
        for (int i=0; i<cs.length ; i++){
            result=result+cs[i];
        }
        return result;
    }
    public static String toString(ArrayList<Character> cs){
        String result="";
        for (int i=0; i<cs.size(); i++){
            result=result+cs.get(i);
        }
        return result;
    }

}
