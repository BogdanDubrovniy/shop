package com.my.dubrovnyi.shop.db.entities.dataTransferObject;

import com.my.dubrovnyi.shop.db.entities.BucketEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BucketDTO {
    private final Map<GoodsDTO, Integer> map;

    public BucketDTO(Map<GoodsDTO, Integer> map) {
        this.map = map;
    }

    public List<BucketEntity> getBucketEntity() {
        if (map == null) {
            return null;
        }
        List<BucketEntity> list = new ArrayList<>();
        for (GoodsDTO entry : map.keySet()) {
            BucketEntity bucketEntity = new BucketEntity(entry, map.get(entry));
            list.add(bucketEntity);
        }
        return list;
    }
}
