package com.example.Coopera.Modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MultaMoraId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "tramo_dia_min_atraso", nullable = false)
    private Integer tramoDiaMinAtraso;

    @Column(name = "tramo_dia_max_atraso", nullable = false)
    private Integer tramoDiaMaxAtraso;

    public MultaMoraId() {}

    public MultaMoraId(Integer tramoDiaMinAtraso, Integer tramoDiaMaxAtraso) {
        this.tramoDiaMinAtraso = tramoDiaMinAtraso;
        this.tramoDiaMaxAtraso = tramoDiaMaxAtraso;
    }

    public Integer getTramoDiaMinAtraso() {
        return tramoDiaMinAtraso;
    }

    public void setTramoDiaMinAtraso(Integer tramoDiaMinAtraso) {
        this.tramoDiaMinAtraso = tramoDiaMinAtraso;
    }

    public Integer getTramoDiaMaxAtraso() {
        return tramoDiaMaxAtraso;
    }

    public void setTramoDiaMaxAtraso(Integer tramoDiaMaxAtraso) {
        this.tramoDiaMaxAtraso = tramoDiaMaxAtraso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultaMoraId that = (MultaMoraId) o;
        return tramoDiaMinAtraso.equals(that.tramoDiaMinAtraso) && tramoDiaMaxAtraso.equals(that.tramoDiaMaxAtraso);
    }

    @Override
    public int hashCode() {
        int result = tramoDiaMinAtraso != null ? tramoDiaMinAtraso.hashCode() : 0;
        result = 31 * result + (tramoDiaMaxAtraso != null ? tramoDiaMaxAtraso.hashCode() : 0);
        return result;
    }
}
