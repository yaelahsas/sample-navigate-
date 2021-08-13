package com.example.testingninik.model

import android.os.Parcel
import android.os.Parcelable

class ResponModelEbook {

    lateinit var ebook: List<ModelEbook>

    data class ModelEbook(
        val created_at: String? = null,
        val file_ebook: String? = null,
        val foto_cover: String? = null,
        val id: Int = 0,
        val jenis_buku: String? = null,
        val judul_buku: String? = null,
        val jumlah_buku: Int = 0,
        val jumlah_halaman: Int = 0,
        val kategori_id: Int = 0,
        val nama_pengarang: String? = null,
        val penerbit: String? = null,
        val tahun_terbit: String? = null,
        val updated_at: String? = null
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(created_at)
            parcel.writeString(file_ebook)
            parcel.writeString(foto_cover)
            parcel.writeInt(id)
            parcel.writeString(jenis_buku)
            parcel.writeString(judul_buku)
            parcel.writeInt(jumlah_buku)
            parcel.writeInt(jumlah_halaman)
            parcel.writeInt(kategori_id)
            parcel.writeString(nama_pengarang)
            parcel.writeString(penerbit)
            parcel.writeString(tahun_terbit)
            parcel.writeString(updated_at)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ModelEbook> {
            override fun createFromParcel(parcel: Parcel): ModelEbook {
                return ModelEbook(parcel)
            }

            override fun newArray(size: Int): Array<ModelEbook?> {
                return arrayOfNulls(size)
            }
        }
    }
}
