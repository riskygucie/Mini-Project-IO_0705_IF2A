// Package File: SRC/SistemMain/main.java
package SRC.SistemMain;

// Import Library dan Class
import SRC.SistemIO.BukuFileIO;
import SRC.SistemIO.AnggotaFileIO;
import SRC.SistemIO.PeminjamanFileIO;
import SRC.SistemData.*;
import java.util.ArrayList;
import java.util.List;

// Class Main
public class Main {

        // Method Utama
        public static void main(String[] args) {

                // ArrayList daftarAnggota
                List<Anggota> daftarAnggota = new ArrayList<>();

                //Data Anggota
                daftarAnggota.add(new Mahasiswa("Rangga", "M001"));
                daftarAnggota.add(new Dosen("Pak Andi", "D001"));
                daftarAnggota.add(new Karyawan("Budi", "K001"));

                // Simpan Data Anggota ke Anggota.txt
                AnggotaFileIO.simpanAnggota(daftarAnggota, "HasilProject/anggota.txt");

                // ArrayList Hasil Anggota
                List<Anggota> hasil = AnggotaFileIO.bacaAnggota("HasilProject/anggota.txt");
                
                // Print Header
                System.out.println("-----------------------------------------------------");
                System.out.println("DATA ANGGOTA");
                System.out.println();

                // looping
                for (Anggota a : hasil) {
                        System.out.println(a);
                        System.out.println();
                }
                System.out.println("-----------------------------------------------------");

                // ArrayList daftarBuku
                List<Buku> daftarBuku = new ArrayList<>();

                // Data Buku
                daftarBuku.add(new Buku("Pemrograman Java", 10));
                daftarBuku.add(new Buku("Sistem Basis Data", 0));
                daftarBuku.add(new Buku("Kalkulus", 2));

                // Simpan Data Buku ke Buku.txt
                BukuFileIO.simpanKeFile(daftarBuku,"HasilProject/buku.txt");

                // ArrayList hasilBuku
                List<Buku> hasilBuku = BukuFileIO.bacaDariFile("HasilProject/buku.txt");
               
                // Print Header
                System.out.println("-----------------------------------------------------");
                System.out.println("DATA BUKU");
                System.out.println();

                // Looping
                for (Buku b : hasilBuku) {
                        System.out.println(b);
                        System.out.println();
                }
                System.out.println("-----------------------------------------------------");
                // ArrayList daftarPeminjaman
                List<Peminjaman> daftarPeminjaman = new ArrayList<>();
                
                // Data Peminjaman
                daftarPeminjaman.add(new Peminjaman(hasil.get(0), hasilBuku.get(0)));
                daftarPeminjaman.add(new Peminjaman(hasil.get(1), hasilBuku.get(1)));
                daftarPeminjaman.add(new Peminjaman(hasil.get(2), hasilBuku.get(2)));
                
                // Simpan peminjaman ke Peminjaman.txt
                PeminjamanFileIO.simpanPeminjaman(daftarPeminjaman, "HasilProject/peminjaman.txt");
                
                // Baca peminjaman dari Peminjaman.txt dan tampilkan datanya
                PeminjamanFileIO.bacaPeminjaman("HasilProject/peminjaman.txt");
                AnggotaFileIO.simpanAnggota(daftarAnggota,"HasilProject/anggota.txt");
                BukuFileIO.simpanKeFile(daftarBuku,"HasilProject/buku.txt");
        }
}
