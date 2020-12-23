package com.sxw;

/**
 * 替换空格
 *
 * @author shenxiangwei
 * @date 2020/12/22 4:36 下午
 */
public class ReplaceBlank {
    public static void main(String[] args) {

        StringBuffer stringBuffer = new StringBuffer("We Are Happy");
        System.out.println(replaceSpace(stringBuffer));
    }

    /*
     *利用双指针方法
     */
    public static String replaceSpace(StringBuffer str) {
        //计算空格的数量
        int blankNum=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                blankNum++;
            }
        }
        //记录初始的字符串、插入后的字符串的长度
        int originalStringLength=str.length();
        int newStringLength=originalStringLength+2*blankNum;
        //重新设置str的长度
        str.setLength(newStringLength);
        //定义两个指针，分别指向新旧字符串的末尾
        int indexOfOriginalString=originalStringLength-1;
        int indexOfNewString=newStringLength-1;
        //结束条件及确保是否越界
        while(indexOfOriginalString>=0&&indexOfNewString>indexOfOriginalString){
            if(str.charAt(indexOfOriginalString)==' '){
                //插入语%20
                str.setCharAt(indexOfNewString--,'0');
                str.setCharAt(indexOfNewString--,'2');
                str.setCharAt(indexOfNewString--,'%');
            }else{
                str.setCharAt(indexOfNewString--,str.charAt(indexOfOriginalString));
            }
            indexOfOriginalString--;
        }
        return str.toString();
    }
}
