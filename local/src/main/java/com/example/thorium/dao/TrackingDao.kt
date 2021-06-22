package com.example.thorium.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.thorium.dto.TrackingDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackingDao {
    @Query("select * from tracking")
    suspend fun getTrackingRecords(): List<TrackingDto>

    @Query("select * from tracking where is_active=1")
    suspend fun getActiveTrackings(): List<TrackingDto>

    @Query("select * from tracking where id=(select max(id) from tracking)")
    suspend fun getTrackingWithHighestId(): List<TrackingDto>

    @Insert
    suspend fun insertTracking(trackingDto: TrackingDto)

    @Query("update tracking set is_active=0 where is_active=1")
    suspend fun stopActiveTracking()

    @Query("select * from tracking")
    suspend fun getAllTrackings(): List<TrackingDto>

    @Query("select * from tracking where is_active=1")
    fun getActiveTrackingsFlow(): Flow<List<TrackingDto>>
}