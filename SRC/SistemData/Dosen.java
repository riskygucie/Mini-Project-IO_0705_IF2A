package SRC.SistemData;

public class Dosen extends Anggota {

    public Dosen(String idAnggota, String nama, String nip) {
        super(idAnggota, nama, nip);
    }

    @Override
    public String getKodeJenis() {
        return "DOSEN";
    }

    @Override
    public String toString() {
        return "DOSEN :\n" + super.toString() + "\nNIP : " + infoTambahan;
    }
}