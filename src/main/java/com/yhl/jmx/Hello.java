package com.yhl.jmx;
/*
Code highlighting produced by Actipro CodeHighlighter (freeware)http://www.CodeHighlighter.com/-->/**
 * @author ChenGang 2005-12-3
 */
public class Hello implements HelloMBean {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printHello() {
        System.out.println("Hello World, " + name);
    }
    public void printHello(String whoName) {
        System.out.println("Hello , " + whoName);
    }
}