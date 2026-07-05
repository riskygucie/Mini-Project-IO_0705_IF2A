// Package File: SRC/SistemIO/BukuFileIO.java
package SRC.SistemIO;

// Import Class dan Library
import SRC.SistemData.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class BukuFileIO
public class BukuFileIO {

    // Method
    public static void simpanKeFile(List<Buku> daftarBuku, String namaFile) {
        // Exceptions Try-Catch
        try (FileWriter writer = new FileWriter(namaFile)) {
            // Looping
            for (Buku b : daftarBuku) {
                writer.write(b.getJudul() + ";" + b.getStok() + "\n");
            }
            System.out.println("Data berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    // Method
    public static List<Buku> bacaDariFile(String namaFile) {
        // ArrayList
        List<Buku> daftarBuku = new ArrayList<>();

        // Exceptions Try-Catch
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            // Variable Penampung
            String baris;
            // Looping
            while ((baris = reader.readLine()) != null) {
                // Jika Kosong Maka Lanjutkan
                if (baris.isBlank()) continue;
                String[] bagian = baris.split(";");
                // Mengambil Data
                String judul = bagian[0];
                int stok = Integer.parseInt(bagian[1]); // String -> int
                daftarBuku.add(new Buku(judul, stok));
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }

        // Kembalikan daftarBuku
        return daftarBuku;
    }
}