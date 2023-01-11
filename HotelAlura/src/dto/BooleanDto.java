/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author shmen
 */
public class BooleanDto {
    
    private static ArrayList<Object> dataAlmacenada = new ArrayList<>();

    public BooleanDto() {
    }
    
    public static void addNewData(Object... newData) {
        for (Object data : newData) {
            dataAlmacenada.add(data);
        }
    }
    
    public static ArrayList<Object> getData() {
        ArrayList<Object> copyOfData = new ArrayList<>(dataAlmacenada);
        removeData();
        return copyOfData;
    }
    
    public static void removeData(){
        dataAlmacenada.clear();
    }
    
}
