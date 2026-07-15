package SRC.SistemIO;

import SRC.SistemData.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PeminjamanFileIO {

    public static void simpanPeminjaman(List<Peminjaman> daftarPeminjaman, String namaFile) {
        try (FileWriter writer = new FileWriter(namaFile)) {
            for (Peminjaman p : daftarPeminjaman) {
                Anggota anggota = p.getAnggota();
                Buku buku = p.getBuku();
                double dendaSaatIni = p.getDenda();
                writer.write(anggota.getNama() + ";" + anggota.getId() + ";" + buku.getJudul() + ";" + p.getStatus() + ";" + String.format("%.0f", dendaSaatIni) + "\n");
            }
            System.out.println("Data peminjaman berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file peminjaman: " + e.getMessage());
        }
    }

    public static void bacaPeminjaman(String namaFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            System.out.println("-----------------------------------------------------");
            System.out.println("DATA PEMINJAMAN");
            System.out.println();

            while ((baris = reader.readLine()) != null) {
                if (baris.isBlank()) continue;
                String[] bagian = baris.split(";");
                if (bagian.length >= 5) {
                    String nama = bagian[0];
                    String id = bagian[1];
                    String judul = bagian[2];
                    String status = bagian[3];
                    String denda = bagian[4];

                    System.out.println("-----------------------------------------------------");
                    System.out.println("Nama: " + nama);
                    System.out.println("ID: " + id);
                    System.out.println("Judul Buku: " + judul);
                    System.out.println("Status: " + status);
                    System.out.println("Denda: Rp " + denda);
                    System.out.println("-----------------------------------------------------");
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file peminjaman: " + e.getMessage());
        }
    }
}
