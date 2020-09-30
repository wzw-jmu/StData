package com.wzw.structuredata.linkedlist.single;

import java.util.Objects;

/**
 * @description: 单链表的CRUD、按顺序添加
 * @author: wuziwei
 * @createDate: 2020/9/28 16:44
 * @version: 1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用", "智多星");
        HeroNode hero4 = new HeroNode(4,"林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        /*
        增
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        */

        //按顺序增
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        HeroNode hero41 = new HeroNode(4,"李逵", "李鬼");

        //改
        singleLinkedList.update(hero41);
        System.out.println("————修改后的链表————");
        singleLinkedList.list();

        //删
        singleLinkedList.delete(hero41);
        System.out.println("————删除后的链表————");
        singleLinkedList.list();
        singleLinkedList.delete(hero1);
        System.out.println("————删除后的链表————");
        singleLinkedList.list();

        //查很简单，就不写了，简单点就是根据编号去判断

    }
}

//定义单链表，管理英雄节点
class SingleLinkedList {
    //初始化一个头节点，不动，不存放数据，作为定位遍历用
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 1.定位到当前链表的最后节点
     * 2.最后节点的next指向 新的节点
     */
    public void add(HeroNode heroNode){

        //head不能动，所以需要一个辅助节点协助遍历
        HeroNode temp = head;
        while (true) {
            if (null == temp.next){
                break;
            }
            temp = temp.next;
        }

        temp.next = heroNode;
    }

    /**
     *     第二种添加，根据排名决定节点顺序
     */
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            if (temp.next.no > heroNode.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            } else if (temp.next.no == heroNode.no) {
                System.out.printf("————待插入英雄编号 %d 已存在————\n", heroNode.no);
                break;
            }
            temp = temp.next;
        }
    }

    //展示遍历
    public void list(){
        if (isEmpty()){
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (null == temp){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改（编号不动，动了就变成添加），很明显，根据新节点的编号进行定位，再修改
    public void update(HeroNode newHeroNode) {
        if (isEmpty()){
            return;
        }
        HeroNode modifyTmp = head;
        boolean isFindFlag = false;
        while (true) {

            if (modifyTmp.no == newHeroNode.no) {
                isFindFlag = true;
                break;
            }
            if (null == modifyTmp.next) {
                break; //遍历结束
            }
            modifyTmp = modifyTmp.next;
        }
        if (isFindFlag) {
            modifyTmp.name = newHeroNode.name;
            modifyTmp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("未找到编号为 %d 的节点\n", newHeroNode.no);
        }
    }

    /**
     * 删除
     * 判断要删除节点是否存在
     * 找到欲删除节点的前一个节点
     */
    public void delete(HeroNode deleNode) {
        if (isEmpty()) {
            return;
        }
        HeroNode tmp = head;
        boolean flag = false;
        while (true) {
            if (null == tmp.next){
                break;
            }
            if (tmp.next.no == deleNode.no && tmp.next.nickname == deleNode.nickname
                    && tmp.next.name == deleNode.name){
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.next = tmp.next.next;
        } else {
            System.out.printf("————要删除的节点 %d 不村子————", deleNode.no);
        }
    }

    public boolean isEmpty(){
        if (head.next == null) {
            System.out.println("链表已空，无数据可以修改");
            return true;
        }
        return false;
    }

}

//定义节点
class HeroNode {

    //编号
    public int no;

    //名字
    public String name;

    //昵称
    public String nickname;

    //下一个节点
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
