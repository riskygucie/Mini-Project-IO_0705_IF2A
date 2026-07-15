package SRC.SistemData;

import java.time.LocalDate;

public class PinjamBuku extends Peminjaman {

    public PinjamBuku(Anggota anggota, Buku buku, LocalDate tanggalPinjam, LocalDate tanggalHarusKembali) {
        super(anggota, buku, tanggalPinjam, tanggalHarusKembali);
    }

    public String infoPeminjaman() {
        return "Peminjam: " + getAnggota().getNama() + "\nBuku: " + getBuku().getJudul() + "\nDenda: Rp "
                + String.format("%.0f", getDenda());
    }
}
