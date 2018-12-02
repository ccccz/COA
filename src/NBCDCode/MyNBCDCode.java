package NBCDCode;

import java.util.ArrayList;

public class MyNBCDCode {
    public static void main(String[] args) {
        MyNBCDCode a=new MyNBCDCode(-12);
        ArrayList<String> b=a.getBinme();
        String c="";
        for (int i=b.size()-1; i>=0; i--){
//            System.out.println(b.get(i)+" ");
            c=c+b.get(i);
        }
        System.out.println(new MyNBCDCode(c).getIntme());
    }

    ArrayList<String> binme=new ArrayList<>();
    int intme;

    public ArrayList<String> getBinme() {
        return binme;
    }
    public void setBinme(ArrayList<String> binme) {
        this.binme = binme;
    }
    public int getIntme() {
        return intme;
    }
    public void setIntme(int intme) {
        this.intme = intme;
    }

    public MyNBCDCode(){
        this(0);
    }
    public MyNBCDCode(int i){
        intme=i;
        binme=int2NBCD(intme);
    }
    public MyNBCDCode(String str){
        binme.add(0,str.substring(0,1));
        for (int i=0; i<str.length()/4; i++){
            binme.add(binme.size()-1,str.substring(1+i*4,5+i*4));
        }
        intme=NBCD2int(binme);
    }

    public static ArrayList<String> int2NBCD(int i){
        ArrayList<String> result=new ArrayList<>();
        if (i>=0){
            result.add(0,"0");
        }else{
            result.add(0,"1");
            i=-i;
        }
        
        if (i==0){
            result.add(0,"0000");
        }else{
            while(i>0){
                int temp=i%10;
                String t="";
                while(temp>0 || t.length()<4){
                    if (temp%2==0){
                        t="0"+t;
                    }else{
                        t="1"+t;
                    }
                    temp=temp/2;
                }
                result.add(result.size()-1,t);
                i=i/10;
            }
        }
        return result;
    }
    public static int NBCD2int(ArrayList<String> strs){
        int result=0;
        for (int i=0; i<strs.size()-1; i++){
            result=result*10;
            String temp=strs.get(i);
            int t=0;
            for (int j=0;j<temp.length();j++){
                t=t*2;
                if (temp.substring(j,j+1).equals("1")){
                    t=t+1;
                }
            }
            result=result+t;
        }

        if (strs.get(strs.size()-1).equals("1")){
            result=-result;
        }

        return result;
    }
    
}