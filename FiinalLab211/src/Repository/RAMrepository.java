/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.RAMmodel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author se180663
 */
public class RAMrepository implements IRAMrepository<String, RAMmodel>{
    private static int counter = 0; 

    HashMap <String, RAMmodel> RamMap;
    private final String path ="RAMModules.dat";
    
    public RAMrepository(){
        this.RamMap = new HashMap<>();
        loadFromFile();
    }
    
    @Override
   public int create(RAMmodel newItem) {
    if (!RamMap.containsKey(newItem.getCode())) {
//        int c = newItem.getCode();
        
        RamMap.put(newItem.getCode(), newItem);
        return 1;
    }
    return 0;
}

    @Override
    public HashMap<String, RAMmodel> read() {
        return RamMap;
    }

    @Override
    public RAMmodel details(String code) {
        return RamMap.get(code);
        
    }

    @Override
    public int update(RAMmodel edititem) {
        if(RamMap.containsKey(edititem.getCode())){
            RamMap.put(edititem.getCode(), edititem);
            return 1;
        }
        return 0;
    }

    
  

    @Override
public int delete(String code) {
    if (RamMap.containsKey(code)) {
        RAMmodel item = RamMap.get(code);
        if (item.isActive()) {
            item.setActive(false); // Đánh dấu là không hoạt động
            return 1; // Đánh dấu thành công
        }
    }
    return 0; 
}

public List<RAMmodel> searchByType(String type) {
    List<RAMmodel> result = new ArrayList<>();
    for (RAMmodel ram : RamMap.values()) {
        if ( ram.getType().equalsIgnoreCase(type)) {
            result.add(ram);
        }
    }
    return result;
}
public List<RAMmodel> searchByBus(int bus) {
    List<RAMmodel> result = new ArrayList<>();
    for (RAMmodel ram : RamMap.values()) {
        if (ram.getBus() == bus) {
            result.add(ram);
        }
    }
    return result;
}
public List<RAMmodel> searchByBrand(String brand) {
    List<RAMmodel> result = new ArrayList<>();
    for (RAMmodel ram : RamMap.values()) {
        if (ram.getBrand().equalsIgnoreCase(brand)) {
            result.add(ram);
        }
    }
    return result;
}
public void saveToFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
        oos.writeObject(RamMap);
    } catch (IOException e) {
        e.printStackTrace(); // Xử lý ngoại lệ
    }
} 
public void loadFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
        RamMap = (HashMap<String, RAMmodel>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace(); 
    }
}
}
