package com.yxd.sum;

public interface SerialData {
    void packTo(Serializer serializer);
    void unpackFrom(Serializer serializer);
    void assertValid() throws Exception;
}
