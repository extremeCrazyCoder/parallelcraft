package com.parallelcraft.nbt;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contains one Compound element with all its values
 * 
 * @author extremeCrazyCoder
 */
public class NBTCompoundElement {
    private List<Map.Entry<String, Object>> elements;
//    private Map<String, Object> elements;

    public NBTCompoundElement() {
//        this.elements = new HashMap<>();
        this.elements = new ArrayList<>();
    }
    
    public NBTCompoundElement put(String name, Object value) {
        if(value == null) return this;
        
        if(value instanceof NBTAble) {
            value = ((NBTAble) value).toNBTElement();
        } else if(value instanceof List) {
            List<?> valList = (List<?>) value;
            List<Object> valListCopy = null;
            int size = valList.size();
            for(int i = 0; i < size; i++) {
                if(valList.get(i) instanceof NBTAble) {
                    Object elm = ((NBTAble) valList.get(i)).toNBTElement();
                    if(valListCopy == null) {
                        valListCopy = new ArrayList<>(valList);
                    }
                    valListCopy.set(i, elm);
                }
            }
            
            if(valListCopy != null) {
                value = valListCopy;
            }
        } else if(value instanceof Enum) {
            value = ((Enum) value).toString().toLowerCase();
        } else if(value instanceof Boolean) {
            value = ((Boolean) value)?1:0;
        }
        
//        elements.put(name, value);
        elements.add(new AbstractMap.SimpleEntry<String, Object>(name, value));
        return this;
    }
    
//    public Set<Map.Entry<String, Object>> allEntries() {
    public List<Map.Entry<String, Object>> allEntries() {
//        return elements.entrySet();
        return elements;
    }
    
    public Object get(String name) {
        for(int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getKey().equals(name)) {
                return elements.get(i).getValue();
            }
        }
        return null;
//        return elements.get(name);
    }
    
    public NBTCompoundElement merge(NBTCompoundElement toMerge) {
        elements.addAll(toMerge.elements);
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append("NBTCompoundElement[\n");
        for(Map.Entry<String, Object> elm : elements) {
            data.append(",\n\t ").append(elm.getKey()).append("='");
            if(elm.getValue() instanceof List) {
                List<?> valList = (List<?>) elm.getValue();
                data.append("[\n");
                for(Object elmList : valList) {
                    data.append("\t\t").append(elmList.toString().replaceAll("\n", "\n\t\t")).append("\n");
                }
                data.append("\t]");
            } else if(elm.getValue() instanceof long[]) {
                long[] val = (long[]) elm.getValue();
                for(int i = 0; i < val.length; i++) {
                    data.append("\"").append(val[i]).append("\",\n");
                }
            } else {
                data.append(elm.getValue().toString().replaceAll("\n", "\n\t\t"));
            }
            data.append("'");
        }
        data.append("\n]");
        return data.toString();
    }
    
    public String toJSON() {
        StringBuilder data = new StringBuilder();
        data.append("{");
        boolean first = true;
        for(Map.Entry<String, Object> elm : elements) {
            if(!first) data.append(", ");
            data.append("\"").append(elm.getKey()).append("\":");
            if(elm.getValue() instanceof List) {
                List<?> valList = (List<?>) elm.getValue();
                data.append("[");
                boolean firstInner = true;
                for(Object elmList : valList) {
                    if(!firstInner) data.append(", ");
                    if(elmList instanceof NBTCompoundElement) {
                        data.append(((NBTCompoundElement) elmList).toJSON());
                    } else {
                        data.append("\"").append(elmList).append("\"");
                    }
                    firstInner = false;
                }
                data.append("]");
            } else {
                if(elm.getValue() instanceof NBTCompoundElement) {
                    data.append(((NBTCompoundElement) elm.getValue()).toJSON());
                } else {
                    data.append("\"").append(elm.getValue()).append("\"");
                }
            }
            first = false;
        }
        data.append("}");
        return data.toString();
    }
}
