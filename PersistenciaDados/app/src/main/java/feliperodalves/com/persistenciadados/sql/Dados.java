package feliperodalves.com.persistenciadados.sql;

public class Dados {
    private long id;
    private String dado;

    @Override
    public String toString() {
        return dado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }
}
