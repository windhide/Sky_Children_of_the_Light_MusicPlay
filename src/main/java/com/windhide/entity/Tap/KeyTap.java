package com.windhide.entity.Tap;

import java.util.HashMap;

/*
 * 如果键盘按键实现，此处存放快捷键信息
 */
public class KeyTap {

    public String Do;
    public String Re;
    public String Mi;
    public String Fa;
    public String So;
    public String La;
    public String Xi;
    public String H_Do;
    public String H_Re;
    public String H_Mi;
    public String H_Fa;
    public String H_So;
    public String H_La;
    public String H_Xi;
    public String HH_Do;

    public HashMap<String, String> hashMap = new HashMap<>();

    public void hashMapInstall() {
        hashMap.put("1",Do);
        hashMap.put("2",Re);
        hashMap.put("3",Mi);
        hashMap.put("4",Fa);
        hashMap.put("5",So);
        hashMap.put("6",La);
        hashMap.put("7",Xi);
    }

}
