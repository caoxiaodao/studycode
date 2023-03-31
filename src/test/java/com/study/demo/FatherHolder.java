package com.study.demo;

/**
 * @author caonan
 * @createtime 2023/2/23 17:47
 * @Description TODO
 * @Version 1.0
 */
public class FatherHolder {
    private static Father father;
    public static Father getDbPrecTool(){
        return father;
    }
    public static void loadDbPrecTool(String string){
        switch (string){
            case "son1":
                father = new Son1();
                break;
            case "son2":
            default:
                father= new Son2();
                break;
        }
    }
    public static void main(String[] args) {
        loadDbPrecTool("son1");
        FatherHolder.getDbPrecTool();
    }
}
