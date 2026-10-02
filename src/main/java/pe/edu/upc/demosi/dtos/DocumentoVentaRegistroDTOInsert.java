package pe.edu.upc.demosi.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DocumentoVentaRegistroDTOInsert {

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;

    @NotBlank(message = "El tipo de documento es obligatorio.")
    private String tipoDocumento;

    @NotEmpty(message = "Debe ingresar al menos un detalle de venta.")
    @Valid
    private List<DetalleDocumentoVentaDTOInsert> detalles;

    public DocumentoVentaRegistroDTOInsert() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<DetalleDocumentoVentaDTOInsert> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDocumentoVentaDTOInsert> detalles) {
        this.detalles = detalles;
    }
}