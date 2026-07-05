// Package File: SRC/SistemData/Anggota.java
package SRC.SistemData;

//Class induk (abstract)
public abstract class Anggota {
    
    // Menyimpan nama anggota
    protected String nama;
    // Menyimpan ID anggota
    protected String id;

    // Constructor
    public Anggota(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    // Mengambil nama anggota
    public String getNama() {
        return nama;
    }

    // Mengambil ID anggota
    public String getId() {
        return id;
    }

    // Method abstrak
    public abstract String getKodeJenis();

    // Overide
    @Override
    // Mengembalikan representasi string dari objek Anggota
    public String toString() {
        return "Nama : " + nama + "\nID   : " + id;
    }
}