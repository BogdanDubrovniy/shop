package com.my.dubrovnyi.shop.db.DAOs;

import com.my.dubrovnyi.shop.db.entities.dataTransferObject.GoodsDTO;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BucketDAO {
    private Map<GoodsDTO, Integer> bucket;

    public BucketDAO() {
        bucket = new LinkedHashMap<>();
    }

    public void addGoodToBucket(GoodsDTO bucketDTO, int amount) {
        bucket.put(bucketDTO, amount);
    }

    public Map<GoodsDTO, Integer> getBucket() {
        return Collections.unmodifiableMap(bucket);
    }

    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry entry : bucket.entrySet()) {
            int i = (int) entry.getValue();
            result = result + i;
        }
        return result;
    }

    public int getTotalCost() {
        return bucket.keySet().stream().mapToInt(entry -> bucket.get(entry)
                * entry.getPrice()).sum();
    }

    public boolean deleteGoodById(int id) {
        for (GoodsDTO entry : bucket.keySet()) {
            if (entry.getId() == id) {
                bucket.remove(entry);
                return true;
            }
        }
        return false;
    }

}
