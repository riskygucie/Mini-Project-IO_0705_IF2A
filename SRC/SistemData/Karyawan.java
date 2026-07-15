
package SRC.SistemData;

public class Karyawan extends Anggota {

    public Karyawan(String idAnggota, String nama, String np) {
        super(idAnggota, nama, np);
    }

    @Override
    public String getKodeJenis() {
        return "KARYAWAN";
    }

    @Override
    public String toString() {
        return "KARYAWAN :\n" + super.toString() + "\nJabatan : " + infoTambahan;
    }
}