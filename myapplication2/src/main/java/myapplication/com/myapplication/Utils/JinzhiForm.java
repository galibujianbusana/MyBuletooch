package myapplication.com.myapplication.Utils;

/**
 * Created by Administrator on 2016/9/30.
 */
public class JinzhiForm {
    // 16进制转2进制 返回3-13位有效数据
     public   String Fromerjinzhi(String str){
         String str1="";
         String a=str.substring(0,1);
         String a1=JinzhiForm.Form(a);

         String b=str.substring(1,2);
         String b1=JinzhiForm.Form(b);

         String c=str.substring(2,3);
         String c1=JinzhiForm.Form(c);

         String d=str.substring(3,4);
         String d1=JinzhiForm.Form(d);

         str1=a1+b1+c1+d1;
         str1=str1.substring(3,13);



        return  str1;
    }
    public static String Form(String s){
        String str="";
        if(s.equals("f")){
            str="1111";
        }
        if(s.equals("e")){
            str="1110";
        }
        if(s.equals("d")){
            str="1101";
        }
        if(s.equals("c")){
            str="1100";
        }
        if(s.equals("b")){
            str="1011";
        }
        if(s.equals("a")){
            str="1010";
        }
        if(s.equals("9")){
            str="1001";
        }
        if(s.equals("8")){
            str="1000";
        }
        if(s.equals("7")){
            str="0111";
        }
        if(s.equals("6")){
            str="0110";
        }
        if(s.equals("5")){
            str="0101";
        }
        if(s.equals("4")){
            str="0100";
        }
        if(s.equals("3")){
            str="0011";
        }
        if(s.equals("2")){
            str="0010";
        }
        if(s.equals("1")){
            str="0001";
        }
        if(s.equals("0")){
            str="0000";
        }
        return  str;
    }
}
