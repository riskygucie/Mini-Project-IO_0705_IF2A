package SRC.SistemData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Peminjaman {
    private static int counter = 0;

    private final String idTransaksi;
    private final Anggota anggota;
    private final Buku buku;
    private final LocalDate tanggalPinjam;
    private final LocalDate tanggalHarusKembali;
    private LocalDate tanggalKembali;
    private double denda;
    private boolean sudahDikembalikan;

    public Peminjaman(Anggota anggota, Buku buku, LocalDate tanggalPinjam, LocalDate tanggalHarusKembali) {
        this.idTransaksi = "TRX" + (++counter);
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalHarusKembali = tanggalHarusKembali;
        this.sudahDikembalikan = false;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalHarusKembali() {
        return tanggalHarusKembali;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public boolean isSudahKembali() {
        return sudahDikembalikan;
    }

    public double getDenda() {
        if (!sudahDikembalikan) {
            if (LocalDate.now().isAfter(tanggalHarusKembali)) {
                long hariTerlambat = ChronoUnit.DAYS.between(tanggalHarusKembali, LocalDate.now());
                this.denda = hariTerlambat * 5000;
            } else {
                this.denda = 0;
            }
        }
        return denda;
    }

    public double kembalikanBuku() {
        return kembalikanBuku(LocalDate.now());
    }

    public double kembalikanBuku(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
        this.sudahDikembalikan = true;

        if (tanggalKembali.isAfter(tanggalHarusKembali)) {
            long hariTerlambat = ChronoUnit.DAYS.between(tanggalHarusKembali, tanggalKembali);
            this.denda = hariTerlambat * 5000;
        } else {
            this.denda = 0;
        }
        return this.denda;
    }

    public String getStatus() {
        if (sudahDikembalikan) {
            return "Sudah dikembalikan";
        }
        return "Belum dikembalikan";
    }

    @Override
    public String toString() {
        String info = idTransaksi + " | " + anggota.getNama() + " meminjam buku \"" + buku.getJudul() + "\"";
        info += "\nStatus: " + getStatus();
        info += "\nHarus kembali: " + tanggalHarusKembali;

        if (sudahDikembalikan) {
            info += "\nTanggal kembali: " + tanggalKembali;
        }

        if (denda > 0) {
            info += "\nDenda: Rp " + String.format("%.0f", denda);
        }
        return info;
    }
}