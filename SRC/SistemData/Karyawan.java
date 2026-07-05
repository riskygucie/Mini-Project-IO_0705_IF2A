// Package File: SRC/SistemData/Karyawan.java
package SRC.SistemData;

// Class Karyawan turunan dari Anggota
public class Karyawan extends Anggota {

    // Constructor
    public Karyawan(String nama, String id) {
        super(nama, id);
    }

    // Overide KodeJenis
    @Override
    // Mengembalikan kode jenis "K" untuk Karyawan
    public String getKodeJenis() {
    return "K";
    }

    // Overide toString
    @Override
    // Mengembalikan representasi string dari objek Karyawan
    public String toString() {
    return "KARYAWAN :\n" + super.toString();
    }
}