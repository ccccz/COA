package integer;

import java.util.ArrayList;

/**
 * 一个模拟寄存器
 * 可以做加法
 * 可以左移或右移
 * 可以进位和抹去前面的位数
 */
public class VirtualRegister {
    private ArrayList<Character> myRegister=new ArrayList<>();

    //构造方法
    public VirtualRegister(){}
    public VirtualRegister(String str){
        char[] ms=str.toCharArray();
        for (int i=0;i<ms.length;i++){
            myRegister.add(i,ms[i]);
        }
    }

    public void setMyRegister(ArrayList<Character> myRegister) {
        this.myRegister = myRegister;
    }
    public ArrayList<Character> getMyRegister() {
        return myRegister;
    }

    //
    public VirtualRegister add(String str){
        for (int i=str.length(); i<myRegister.size();i++){
            if (str.toCharArray()[0]=='0'){
                str="0"+str;
            }else{
                str="1"+str;
            }

        }
        char[] strs=str.toCharArray();
        char[] cs=new char[strs.length+1];
        cs[strs.length]='0';
        for (int i=strs.length-1;i>=0;i--){
            cs[i]=OperaInt.or(OperaInt.and(strs[i],myRegister.get(i)),OperaInt.and(strs[i],cs[i+1]),OperaInt.and(cs[i+1],myRegister.get(i)));
            char c=OperaInt.xor(strs[i],myRegister.get(i),cs[i+1]);
            myRegister.set(i,c);
        }
        return this;
    }
    public VirtualRegister sub(String str){
        this.add(MyInteger.negative(str));
        return this;
    }

    //右移
    public VirtualRegister right(int i){
        for (int j=0;j<i;j++){
            myRegister.add(0,myRegister.get(0));
        }
        return this;
    }
    public VirtualRegister logicRight(int i){
        for (int j=0;j<i;j++){
            myRegister.add(0,'0');
        }
        return this;
    }

    public int size(){
        return myRegister.size();
    }
}
