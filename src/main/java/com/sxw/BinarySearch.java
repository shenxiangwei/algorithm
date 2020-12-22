package com.sxw;

/**
 * 二分查找实现
 *
 * @author shenxiangwei
 * @date 2020/12/22 3:59 下午
 */
public class BinarySearch {
    public static void main(String[] args) {

        //初始化数据
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        System.out.println(find(8,numbers));

    }

    public static boolean find(int target,int[] numbers){
        int left = 0;
        int right = numbers.length -1;
        while (left < right){
            int mid = (left + right)/2;
            if(numbers[mid] == target){
                return true;
            }else if(numbers[mid] > target){
                right = mid - 1;
            }else if(numbers[mid] < target){
                left = mid + 1;
            }
        }
        return false;
    }
}
