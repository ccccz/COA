package integer;

/**
 * 计算类
 */
public class OperaInt {

    //
    //加法
    public MyInteger add(MyInteger a,MyInteger b){
        char[] as,bs,cs,ss;   //a,b,进位，结果
        as=a.getBinaryMy().toCharArray();
        bs=b.getBinaryMy().toCharArray();
        ss=new char[32];
        cs=new char[32];
        cs[31]='0';
        for (int i=31;i>=0;i--){
            // TODO: 写到这里惹
        }
        return new MyInteger(0);
    }
}
