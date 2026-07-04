package org.example.siglc.model.entity;

import java.time.LocalDateTime;

public class Reestoque {
    private int id;
    private LocalDateTime dataHora;

    public Reestoque(int id, LocalDateTime dataHora) {
        setId(id);
        setDataHora(dataHora);
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else throw new IllegalArgumentException("ID invalido");
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora != null) this.dataHora = dataHora;
        else throw new IllegalArgumentException("dataHora invalido");
    }

    public int getId() {
        return id;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
