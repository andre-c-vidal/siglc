package org.example.siglc.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reestoque {
    private Integer id;
    private LocalDateTime dataHora;

    public Reestoque(int id, LocalDateTime dataHora) {
        setId(id);
        setDataHora(dataHora);
    }

    public void setId(Integer id) {
        if (id != null && id >= 0) this.id = id;
        else throw new IllegalArgumentException("ID invalido");
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = Objects.requireNonNull(dataHora, "Datahora invalida");
    }

    public Integer getId() {
        return id;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
