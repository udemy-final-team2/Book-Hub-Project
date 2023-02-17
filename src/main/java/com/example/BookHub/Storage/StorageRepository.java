package com.example.BookHub.Storage;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("storagerepository")
public interface StorageRepository {
    void insertStorage(StorageDTO dto);
}
