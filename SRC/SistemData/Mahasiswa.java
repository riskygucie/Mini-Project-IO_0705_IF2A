// Package File: SRC/SistemData/Mahasiswa.java
package SRC.SistemData;

// Class Mahasiswa turunan dari Anggota
public class Mahasiswa extends Anggota {

    // Constructor
    public Mahasiswa(String nama, String id) {
        super(nama, id);
    }

    // Overide KodeJenis
    @Override
    // Mengembalikan kode jenis "M" untuk Mahasiswa
    public String getKodeJenis() {
        return "M";
    }

    // Overide toString
    @Override
    // Mengembalikan representasi string dari objek Mahasiswa
    public String toString() {
        return "MAHASISWA :\n" +
                super.toString();
    }

}
