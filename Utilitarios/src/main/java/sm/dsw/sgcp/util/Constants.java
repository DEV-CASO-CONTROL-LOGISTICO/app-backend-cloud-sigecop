package sm.dsw.sgcp.util;

public class Constants {

    public static final String ROL_PROVEEDOR = "ROL_PR";

    public static class EstadoCotizacion {

        public static final Integer GENERADO = 1;
        public static final Integer ARCHIVADO = 2;
        public static final Integer APROBADO = 3;
    }

    public static class EstadoSolicitud {

        public static final Integer EN_PROCESO = 1;
        public static final Integer FINALIZADO = 2;
    }

    public static class EstadoPedido {

        public static final Integer GENERADO = 1;
        public static final Integer ENVIADO = 2;
        public static final Integer CON_CONFORMIDAD = 3;
        public static final Integer DEVUELTO = 4;
        public static final Integer PAGADO = 5;
    }

    public static class EstadoObligacion {
        public static final Integer GENERADO_AUTOMATICO = 1;
        public static final Integer PENDIENTE_DE_CONTABILIZAR = 2;
        public static final Integer CONTABILIZADO = 3;
        public static final Integer APROBADO = 4;
        public static final Integer PAGO_POR_CONTABILIZAR = 5;
        public static final Integer PAGO_REGISTRADO = 6;
    }

    public static class TipoArchivo {

        public static final Integer GUIA = 1;
        public static final Integer FACTURA = 2;
    }
    public static class RutaUpload {
        public static final String DIR_GUIA = "uploadsGuia";
        public static final String DIR_FACTURA = "uploadsFactura";
    }

    private Constants() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase");
    }
}
