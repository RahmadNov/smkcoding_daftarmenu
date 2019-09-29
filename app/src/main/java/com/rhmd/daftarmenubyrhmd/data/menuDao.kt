package com.rhmd.daftarmenubyrhmd.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface menuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahMakanan(modelMakan: MenuModelMakan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahMinuman(modelMinum: MenuModelMinum)

    @Query ("Select * from MenuModelMakan")
    fun ambilMenuMakanan():LiveData<List<MenuModelMakan>>

    @Query ("Select * from MenuModelMinum")
    fun ambilMenuMinuman():LiveData<List<MenuModelMinum>>

}