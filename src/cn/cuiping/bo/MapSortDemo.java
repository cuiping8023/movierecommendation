package cn.cuiping.bo;

import java.util.*;

// 主类
public class MapSortDemo {
    public static void main(String[] args) {
        Map<Integer, Double> map = new TreeMap<>();
        map.put(123, 10000.0);
        map.put(2222222, 333.1);
        map.put(4444,33333.3);

//        Map<Integer, Double> resultMap = sortMapByValue(map); //按Value进行排序
//        for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
    }

//    /**
//     * 使用 Map按value进行排序
//     * @param map
//     * @return
//     */
//    public static Map<Integer, Double> sortMapByValue(Map<Integer, Double> map) {
//        if (map == null || map.isEmpty()) {
//            return null;
//        }
//        Map<Integer, Double> sortedMap = new LinkedHashMap<>();
//        List<Map.Entry<Integer, Double>> entryList = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());
//        Collections.sort(entryList, new MapValueComparator());
//        Iterator<Map.Entry<Integer, Double>> iter = entryList.iterator();
//        Map.Entry<Integer, Double> tmpEntry = null;
//        while (iter.hasNext()) {
//            tmpEntry = iter.next();
//            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
//        }
//        return sortedMap;
//    }
//}
//
////比较器类
//    class MapValueComparator implements Comparator<Map.Entry<Integer, Double>> {
//    public int compare(Map.Entry<Integer, Double> me1, Map.Entry<Integer, Double> me2) {
//        return me2.getValue().compareTo(me1.getValue());
//    }
}