package com.nostudy.spider;

import java.util.LinkedList;

/**
 * Created by Lance on 7/9/16.
 * 设计一个数据结构用来存储TODO表,考虑到队列Queue
 */
public class Queue
{
    //定义一个队列，使用LinkedList实现
    private LinkedList<Object> queue=new LinkedList<>();

    //将t加入到队列中
    public void enQueue(Object t)
    {
        queue.addLast(t);
    }

    //移除队列中的第一项并将其返回
    public Object deQueue()
    {
        return queue.removeFirst();
    }

    //检查返回队列是否为空
    public boolean isQueueEmpty()
    {
        return queue.isEmpty();
    }

    public boolean isContain(Object t)
    {
        return queue.contains(t);
    }
}
