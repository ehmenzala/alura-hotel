package dto;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AlmacenDto {
    
    private static ArrayList<Object> dataAlmacenada = new ArrayList<>();

    public AlmacenDto() {
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
