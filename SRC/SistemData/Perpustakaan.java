package SRC.SistemData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Perpustakaan {

    private final List<Buku> koleksiBuku;
    private final List<Anggota> daftarAnggota;
    private final List<Peminjaman> daftarTransaksi;

    private static final String DATA_DIR = "data";
    private static final String FILE_BUKU = "data_buku.txt";
    private static final String FILE_ANGGOTA = "data_anggota.txt";

    public Perpustakaan() {
        this.koleksiBuku = new ArrayList<>();
        this.daftarAnggota = new ArrayList<>();
        this.daftarTransaksi = new ArrayList<>();
    }

    private File getDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private File getDataFile(String namaFile) {
        return new File(getDataDirectory(), namaFile);
    }

    public void tambahBuku(Buku buku) {
        if (buku == null) {
            System.out.println("Data buku tidak valid.");
            return;
        }
        koleksiBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan: " + buku.getJudul());
    }

    public Buku cariBukuByKode(String kode) {
        for (Buku b : koleksiBuku) {
            if (b.getKodeBuku().equalsIgnoreCase(kode)) {
                return b;
            }
        }
        return null;
    }

    public void tampilkanSemuaBuku() {
        if (koleksiBuku.isEmpty()) {
            System.out.println("Belum ada data buku.");
            return;
        }
        System.out.println("=== DAFTAR BUKU ===");
        for (Buku b : koleksiBuku) {
            System.out.println(b);
        }
    }

    public void tambahAnggota(Anggota anggota) {
        if (anggota == null) {
            System.out.println("Data anggota tidak valid.");
            return;
        }
        daftarAnggota.add(anggota);
        System.out.println("Anggota berhasil ditambahkan.");
    }

    public Anggota cariAnggotaById(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }

    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return;
        }
        System.out.println("=== DAFTAR ANGGOTA ===");
        for (Anggota a : daftarAnggota) {
            a.tampilkanInfo();
        }
    }

    public boolean pinjamBuku(String kodeBuku, String idAnggota) {
        return pinjamBuku(kodeBuku, idAnggota, 7);
    }

    public boolean pinjamBuku(String kodeBuku, String idAnggota, int lamaHari) {
        if (lamaHari < 1 || lamaHari > 7) {
            System.out.println("Durasi peminjaman harus antara 1 sampai 7 hari.");
            return false;
        }

        Buku buku = cariBukuByKode(kodeBuku);
        Anggota anggota = cariAnggotaById(idAnggota);

        if (buku == null) {
            System.out.println("Buku dengan kode " + kodeBuku + " tidak ditemukan.");
            return false;
        }
        if (anggota == null) {
            System.out.println("Anggota dengan id " + idAnggota + " tidak ditemukan.");
            return false;
        }
        if (!buku.kurangiStok()) {
            System.out.println("Stok buku \"" + buku.getJudul() + "\" habis, tidak bisa dipinjam.");
            return false;
        }

        LocalDate tanggalPinjam = LocalDate.now();
        LocalDate tanggalHarusKembali = tanggalPinjam.plusDays(lamaHari);
        Peminjaman transaksi = new Peminjaman(anggota, buku, tanggalPinjam, tanggalHarusKembali);
        daftarTransaksi.add(transaksi);

        System.out.println("Peminjaman berhasil dicatat dengan id " + transaksi.getIdTransaksi());
        System.out.println("Batas pengembalian: " + tanggalHarusKembali);
        return true;
    }

    public void kembalikanBuku(String idTransaksi) {
        for (Peminjaman t : daftarTransaksi) {
            if (t.getIdTransaksi().equalsIgnoreCase(idTransaksi) && !t.isSudahKembali()) {
                double denda = t.kembalikanBuku();
                t.getBuku().tambahStok();
                System.out.println("Buku \"" + t.getBuku().getJudul() + "\" berhasil dikembalikan.");
                System.out.println("Stok buku sekarang: " + t.getBuku().getStok());
                if (denda > 0) {
                    System.out.println("Terlambat! Denda yang harus dibayar: Rp" + (int) denda);
                } else {
                    System.out.println("Tidak telat, denda: Rp0");
                }
                return;
            }
        }
        System.out.println("Transaksi dengan id " + idTransaksi + " tidak ditemukan / sudah dikembalikan.");
    }

    public void tampilkanSemuaTransaksi() {
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi peminjaman.");
            return;
        }
        System.out.println("=== DAFTAR TRANSAKSI ===");
        for (Peminjaman t : daftarTransaksi) {
            System.out.println(t);
        }
    }

    public void simpanDataKeFile() {
        File fileBuku = getDataFile(FILE_BUKU);
        try (BufferedWriter writerBuku = new BufferedWriter(new FileWriter(fileBuku))) {
            for (Buku b : koleksiBuku) {
                writerBuku.write(b.toFileString());
                writerBuku.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data buku: " + e.getMessage());
            return;
        }

        File fileAnggota = getDataFile(FILE_ANGGOTA);
        try (BufferedWriter writerAnggota = new BufferedWriter(new FileWriter(fileAnggota))) {
            for (Anggota a : daftarAnggota) {
                writerAnggota.write(a.toFileString());
                writerAnggota.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data anggota: " + e.getMessage());
            return;
        }

        System.out.println("Semua data berhasil disimpan ke " + fileBuku.getPath() + " dan " + fileAnggota.getPath());
    }

    public void muatDataDariFile() {
        File fileBuku = getDataFile(FILE_BUKU);
        try (BufferedReader readerBuku = new BufferedReader(new FileReader(fileBuku))) {
            koleksiBuku.clear();
            String baris;
            while ((baris = readerBuku.readLine()) != null) {
                if (baris.trim().isEmpty()) {
                    continue;
                }
                try {
                    koleksiBuku.add(Buku.fromFileString(baris));
                } catch (IllegalArgumentException e) {
                    System.out.println("Baris buku rusak/dilewati: " + baris);
                }
            }
        } catch (IOException e) {
            System.out.println("Belum ada file data buku tersimpan (" + fileBuku.getPath() + ").");
        }

        File fileAnggota = getDataFile(FILE_ANGGOTA);
        try (BufferedReader readerAnggota = new BufferedReader(new FileReader(fileAnggota))) {
            daftarAnggota.clear();
            String baris;
            while ((baris = readerAnggota.readLine()) != null) {
                if (baris.trim().isEmpty()) {
                    continue;
                }
                try {
                    String[] bagian = baris.split(";");
                    String tipe = bagian[0];
                    String id = bagian[1];
                    String nama = bagian[2];
                    String infoTambahan = bagian[3];
                    if (tipe.equalsIgnoreCase("MAHASISWA")) {
                        daftarAnggota.add(new Mahasiswa(id, nama, infoTambahan));
                    } else if (tipe.equalsIgnoreCase("DOSEN")) {
                        daftarAnggota.add(new Dosen(id, nama, infoTambahan));
                    } else {
                        System.out.println("Tipe anggota tidak dikenali, baris dilewati: " + baris);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Baris anggota rusak/dilewati: " + baris);
                }
            }
        } catch (IOException e) {
            System.out.println("Belum ada file data anggota tersimpan (" + fileAnggota.getPath() + ").");
        }

        System.out.println("Proses memuat data selesai.");
    }
}

