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
        System.out.println(find(2,numbers));

    }

    /**
     * 二分查找
     *
     * @param target 待查找的数字
     * @param numbers 数字集合
     * @return 待查找的数字 在 数字集合中的索引值,未查询到返回 -1
     */
    public static int find(int target,int[] numbers){
        int left = 0;
        int right = numbers.length -1;
        while (left < right){
            int mid = (left + right)/2;
            if(numbers[mid] == target){
                return mid;
            }else if(numbers[mid] > target){
                right = mid - 1;
            }else if(numbers[mid] < target){
                left = mid + 1;
            }
        }
        return -1;
    }
}
