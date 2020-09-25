package com.wzw.structuredata.circleQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @description:
 * @author: wuziwei(ziwei.wu @ ucarinc.com)
 * @createDate: 2020/9/25
 * @version: 1.0
 */
public class CircleArrayQueueDemo {

    public final static Logger logger = LoggerFactory.getLogger(CircleArrayQueueDemo.class);

    public static void main(String[] args) {

        logger.info("数组模拟环形队列测试开始");
        CircleArray demo = new CircleArray(3);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        System.out.println("指令:");
        System.out.println("f   队列是否已满");
        System.out.println("e:  队列是否为空");
        System.out.println("a:  添加数据");
        System.out.println("g:  获取数据");
        System.out.println("h:  获取头部数据");
        System.out.println("c:  清空队列");
        System.out.println("s:  展示队列");
        System.out.println("q: 退出程序");
        while (loop) {
            //获取指令
            System.out.println("请输入指令:");
            key = scanner.next().charAt(0);
            System.out.println("f   队列是否已满");
            System.out.println("e:  队列是否为空");
            System.out.println("a:  添加数据");
            System.out.println("g:  获取数据");
            System.out.println("h:  获取头部数据");
            System.out.println("c:  清空队列");
            System.out.println("s:  展示队列");
            System.out.println("q: 退出程序");
            switch (key) {
                case 'f':
                    demo.isFull();
                    break;
                case 'e':
                    demo.isEmpty();
                    break;
                case 'a':
                    System.out.println("请输入一个数字，非数字可能会导致程序退出");
                    int num = scanner.nextInt();
                    demo.addQueue(num);
                    break;
                case 'g':
                    int res = demo.getQueue();
                    System.out.println("结果为：" + res);
                    break;
                case 'h':
                    int head = demo.headQueue();
                    System.out.println("头部数据为：" + head);
                    break;
                case 'c':
                    demo.clearQueue();
                    break;
                case 's' :
                    demo.showQueue();
                    break;
                case 'q':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        logger.info("数组模拟环形队测试结束");
    }


}

class CircleArray {
    private int maxSize; //数组最大容量
    private int front; //队列头，front指向队列的第一个元素，即arr[front]为队列的第一个元素，初始值为0
    private int rear;  //队列尾，rear指向队列最后一个元素的后一个位置，空出一个空间作为约定，初始值为0
    private int[] arr; //用于存放数据，模拟环形队列

    //构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //front是指向队列队列头的前一个位置
        front = 0;
        //注意一点，在本环形队列中，根据rear定义，无法完全填充满整个环形队列的，至少会空出一个位置
        rear = 0;
    }

    //判断队列是已满
    public boolean isFull() {
        try {
            if ((rear + 1) % maxSize == front) {
                throw new RuntimeException();
            }
            System.out.println("----队列未满,可以继续添加数据----");
            return false;
        } catch (Exception e) {
            System.out.println("----队列已满,不再继续添加数据----");
            return true;
        }
    }

    //判断队列是否已空
    public boolean isEmpty() {
        try {
            if (front == rear) {
                throw new RuntimeException();
            }
            System.out.println("----队列未空，还有数据----");
            return false;
        } catch (Exception e) {
            System.out.println("----队列已空,不再取数据----");
            return true;
        }
    }

    //为队列添加数据
    public void addQueue(int n) {
        if (isFull()) {
            return;
        }
        //注意rear的定义
        arr[rear] = n;
        //rear要后移，环形队列要考虑取模问题,不取模会出现越界问题
        rear = (rear + 1) % maxSize;
    }

    //从队列获取数据
    public int getQueue() {
        if (isEmpty()) {
            return -1;
        }
        //注意front定义，同时要防止越界
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示所有数据
    public void showQueue() {
        if (isEmpty()) {
            return;
        }
        //从front开始遍历，遍历多少个元素 （rear+maxSize-front）%maxSize
        for (int i = 0; i < getMaxDataSize(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //返回队列头部数据
    public int headQueue() {
        if (isEmpty()) {
            return -1;
        }
        //注意不是front++
        return arr[front];
    }

    //清空队列，重复使用
    public void clearQueue() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        int rear = 0;
        int front = 0;
    }

    //求出当前队列的实际有效数据个数
    public int getMaxDataSize() {
        return (rear + maxSize -front) % maxSize;
    }
}
