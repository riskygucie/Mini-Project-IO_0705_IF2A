// Package File: SRC/SistemData/Dosen.java
package SRC.SistemData;

// Class Dosen turunan dari Anggota
public class Dosen extends Anggota {

    // Constructor 
    public Dosen(String nama, String id) {
        super(nama, id);
    }

    // Overide KodeJenis
    @Override
    // Mengembalikan kode jenis "D" untuk Dosen
    public String getKodeJenis() {
    return "D";
    }

    // Overide toString
    @Override
    // Mengembalikan representasi string dari objek Dosen
    public String toString() {
    return "DOSEN :\n" + super.toString();
    }

}