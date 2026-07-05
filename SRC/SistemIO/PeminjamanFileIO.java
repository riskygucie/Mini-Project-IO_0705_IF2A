// Package File: SRC/SistemIO/PeminjamanFileIO.java
package SRC.SistemIO;

// Import Class dan Library
import SRC.SistemData.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class PeminjamanFileIO
public class PeminjamanFileIO {

    // Method
    public static void simpanPeminjaman(List<Peminjaman> daftarPeminjaman, String namaFile) {
        // Exceptions Try-Catch
        try (FileWriter writer = new FileWriter(namaFile)) {
            // Looping
            for (Peminjaman p : daftarPeminjaman) {
                // Mengambil data anggota dan buku
                Anggota anggota = p.getAnggota();
                Buku buku = p.getBuku();
                int stockSisa = buku.getStok() - 1;
                String status = (stockSisa < 0) ? "buku sedang kosong" : String.valueOf(stockSisa);
               
                // Format Penulisan
                writer.write(anggota.getNama() + "," + anggota.getId() + "," + buku.getJudul() + "," + status + "\n");
            }
            System.out.println("Data peminjaman berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file peminjaman: " + e.getMessage());
        }
    }

    // Method
    public static void bacaPeminjaman(String namaFile) {
        // Exceptions Try-Catch
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            // Variable Penampung
            String baris;
            // Print Header
            System.out.println("-----------------------------------------------------");
            System.out.println("DATA PEMINJAMAN");
            System.out.println();

            // Looping
            while ((baris = reader.readLine()) != null) {
                // Jika Kosong Maka Lanjutkan
                if (baris.isBlank()) continue;
                String[] bagian = baris.split(",");
                // Jika Panjang Bagian >= 4 Maka Tampilkan Data
                if (bagian.length >= 4) {
                    // Mengambil Data
                    String nama = bagian[0];
                    String id = bagian[1];
                    String judul = bagian[2];
                    String stockSisa = bagian[3];
                    
                    // Print Data Peminjaman
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Nama: " + nama);
                    System.out.println("ID: " + id);
                    System.out.println("Judul Buku: " + judul);
                    System.out.println("Status: " + stockSisa);
                    System.out.println("-----------------------------------------------------");
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file peminjaman: " + e.getMessage());
        }
    }
}
