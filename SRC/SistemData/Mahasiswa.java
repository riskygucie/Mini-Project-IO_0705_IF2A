package SRC.SistemData;

public class Mahasiswa extends Anggota {

    public Mahasiswa(String idAnggota, String nama, String npm) {
        super(idAnggota, nama, npm);
    }

    @Override
    public String getKodeJenis() {
        return "MAHASISWA";
    }

    @Override
    public String toString() {
        return "MAHASISWA :\n" + super.toString() + "\nNPM : " + infoTambahan;
    }
}
