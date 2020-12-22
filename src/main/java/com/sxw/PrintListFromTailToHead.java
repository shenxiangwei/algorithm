package com.sxw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 从尾到头打印链表几种方法
 *
 * create by shenxiangwei on 2020/12/22 下午 11:35
 */
public class PrintListFromTailToHead {
    public static void main(String[] args) {

        ListNode listNode = new ListNode(5);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(1);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        //第一种
//        printListFromTailToHead(listNode);

        //第二种
//        printListFromTailToHead2(listNode);

        //第三种
//        printListFromTailToHead3(listNode);

        //第四种
        printListFromTailToHead4(listNode);

        System.out.println(arrayList);
    }



    static ArrayList<Integer> arrayList = new ArrayList<Integer>();

    /**
     * 递归法
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    /**
     * 使用Collections反转
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {

        while(listNode != null){
            arrayList.add(listNode.val);
            listNode = listNode.next;
        }

        Collections.reverse(arrayList);//使用Collections的reverse方法，直接将list反转
        return arrayList;
    }


    /**
     * 插入指定位置（头插法）
     *
     * 此方法效率较低
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {

        while(listNode != null){
            arrayList.add(0,listNode.val);
            listNode = listNode.next;
        }
        return arrayList;
    }

    /**
     * 堆栈法
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead4(ListNode listNode) {

        Stack<Integer> stack = new Stack<Integer>();

        while(listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

}
