package integer;

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
        ss=new char[32];
        cs=new char[33];
        cs[32]='0';
        for (int i=31;i>=0;i--){
            cs[i]=or(and(as[i],bs[i]),and(as[i],cs[i+1]),and(cs[i+1],bs[i]));
            ss[i]=xor(as[i],bs[i],cs[i+1]);
        }
        if (cs[0]!=cs[1]){
            throw new Exception("溢出");
        }
        if (and(and(as[0],bs[0],no(ss[0])),and(no(as[0]),no(bs[0]),ss[0]))=='1'){
            throw new Exception("溢出");
        }
        String result="";
        for (int i=0;i<32;i++){
            result=result+ss[i];
        }
        return new MyInteger(result);
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
}
