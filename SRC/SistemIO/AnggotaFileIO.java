// Package File: SRC/SistemIO/AnggotaFileIO.java
package SRC.SistemIO;

// Import library dan class
import SRC.SistemData.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class AnggotaFileIO 
public class AnggotaFileIO{

    // Method
    public static void simpanAnggota(List<Anggota> daftar, String namaFile) {
        // Exceptions Try-Catch
        try (FileWriter writer = new FileWriter(namaFile)) {
            // Looping
            for (Anggota a : daftar) {
            writer.write(a.getNama() + ";" + a.getId() + ";" + a.getKodeJenis() + "\n");
            }
            System.out.println("Data anggota berhasil disimpan ke " + namaFile);
        } catch (IOException e){
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    // Method
    public static List<Anggota> bacaAnggota(String namaFile) {
        // ArrayList 
        List<Anggota> hasil = new ArrayList<>();

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
                String nama = bagian[0];
                String id = bagian[1];
                String jenis = bagian[2];

                // Code Anggota
                if (jenis.equals("M")) {
                    hasil.add(new Mahasiswa(nama, id));
                } else if (jenis.equals("D")) {
                    hasil.add(new Dosen(nama, id));
                } else if (jenis.equals("K")) {
                    hasil.add(new Karyawan(nama, id));
                } else {
                    System.out.println("Jenis anggota tidak dikenal.");
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }

        // Kembalikan Hasil
        return hasil;
    }
}