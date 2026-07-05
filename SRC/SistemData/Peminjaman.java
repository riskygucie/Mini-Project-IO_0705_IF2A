// Package File: SRC/SistemData/Peminjaman.java
package SRC.SistemData;

//Class Peminjaman
public class Peminjaman {

    // Anggota yang meminjam buku
    private Anggota anggota;
    // Buku yang dipinjam
    private Buku buku;

    // Constructor
    public Peminjaman(Anggota anggota, Buku buku) {
        this.anggota = anggota;
        this.buku = buku;
    }

    // Getter anggota
    public Anggota getAnggota() {
        return anggota;
    }

    // Getter buku
    public Buku getBuku() {
        return buku;
    }

    // Overide toString
    @Override
    // Mengembalikan representasi string dari objek Peminjaman
    public String toString() {
        return anggota.getNama() + " meminjam buku \"" + buku.getJudul() + "\"";
    }

}