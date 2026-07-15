package SRC.SistemData;

public abstract class Anggota {

    protected String nama;
    protected String idAnggota;
    protected String infoTambahan;

    public Anggota(String idAnggota, String nama, String infoTambahan) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.infoTambahan = infoTambahan;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return idAnggota;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public String getInfoTambahan() {
        return infoTambahan;
    }

    public void tampilkanInfo() {
        System.out.println(this);
    }

    public abstract String getKodeJenis();

    public String toFileString() {
        return getKodeJenis() + ";" + idAnggota + ";" + nama + ";" + infoTambahan;
    }

    @Override
    public String toString() {
        return "Nama : " + nama + "\nID   : " + idAnggota;
    }
}