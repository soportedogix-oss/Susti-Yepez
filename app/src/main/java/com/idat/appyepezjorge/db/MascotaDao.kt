package com.idat.appyepezjorge.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idat.appyepezjorge.model.Mascota

@Dao
interface MascotaDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(mascota: Mascota)

    @Query("SELECT * FROM tblyepez ORDER BY nombre ASC")
    fun listarMascotas(): LiveData<List<Mascota>>

    @Query("SELECT * FROM tblyepez WHERE codigo = :codigo LIMIT 1")
    suspend fun buscarPorCodigo(codigo: String): Mascota?
}