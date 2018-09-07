//package com.cpfframe4j.util.epoint;
//
//import java.util.Map;
//
//import com.epoint.core.grammar.Record;
//
//public class RecordUtil
//{
//
//    public static Record retainAll(Record record1, Record record2) {
//        for(String key : record2.keySet()){
//            if (!record1.containsKey(key)){
//                record1.remove(key);
//            }
//        }
//        return record1;
//    }
//
//    public static Record retainAll(Record record1, String... fields) {
//        for(String key : fields){
//            if (!record1.containsKey(key)){
//                record1.remove(key);
//            }
//        }
//        return record1;
//    }
//
//
//    public static Record removeAll(Record record1, Record record2) {
//        for(String key : record2.keySet()){
//            record1.remove(key);
//        }
//        return record1;
//    }
//
//
//    public static Record removeAll(Record record1, String... fields) {
//        for(String key : fields){
//            record1.remove(key);
//        }
//        return record1;
//    }
//
//
//    public static Record convert2Record(Map<String, Object> map){
//        Record record = new Record();
//        record.putAll(map);
//        return record;
//    }
//
//}
