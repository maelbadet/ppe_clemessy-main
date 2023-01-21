package com.example.ppe_clemessy;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM utilisateurs")
    List<User> getAll();

    @Query("SELECT * FROM utilisateurs WHERE nom LIKE :lenom LIMIT 1")
    User findByName(String lenom);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User ... users);

}
