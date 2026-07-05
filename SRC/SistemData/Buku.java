// Package File: SRC/SistemData/Buku.java
package SRC.SistemData;

//Class Buku
public class Buku {
    
    // Judul buku
    private String judul;
    // Jumlah stok
    private int stok;

    // Constructor
    public Buku(String judul, int stok) {
        this.judul = judul;
        this.stok = stok;
    }

    // Getter judul
    public String getJudul() {
        return judul;
    }

    // Getter stok
    public int getStok() {
        return stok;
    }

    // Setter stok
    public void setStok(int stok) {
        this.stok = stok;
    }

    // Overide toString
    @Override
    // Mengembalikan representasi string dari objek Buku
    public String toString() {
    return "Judul : " + judul + "\nStok  : " + stok;
    }

}