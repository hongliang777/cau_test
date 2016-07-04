package com.yhl.jmx;
/**
Code highlighting produced by Actipro CodeHighlighter (freeware)http://www.CodeHighlighter.com/-->/**
 * @author ChenGang 2005-12-3
 */
public interface HelloMBean {
    public String getName();
    public void setName(String name);
    public void printHello();
    public void printHello(String whoName);
}