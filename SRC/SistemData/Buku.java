package SRC.SistemData;

public class Buku {

    private String kodeBuku;
    private String judul;
    private String penulis;
    private int stok;

    public Buku(String kodeBuku, String judul, String penulis, int stok) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        setStok(stok);
    }

    public Buku(String judul, int stok) {
        this("", judul, "", stok);
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = (stok < 0) ? 0 : stok;
    }

    public boolean kurangiStok() {
        if (stok > 0) {
            stok--;
            return true;
        }
        return false;
    }

    public void tambahStok() {
        stok++;
    }

    public String toFileString() {
        return kodeBuku + ";" + judul + ";" + penulis + ";" + stok;
    }

    public static Buku fromFileString(String data) {
        String[] bagian = data.split(";");
        if (bagian.length < 4) {
            throw new IllegalArgumentException("Format buku tidak valid");
        }
        return new Buku(bagian[0], bagian[1], bagian[2], Integer.parseInt(bagian[3]));
    }

    @Override
    public String toString() {
        return "Kode  : " + kodeBuku + "\nJudul : " + judul + "\nPenulis : " + penulis + "\nStok  : " + stok;
    }
}