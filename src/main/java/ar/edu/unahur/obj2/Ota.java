package ar.edu.unahur.obj2;

import org.joda.time.DateTime;
import ar.edu.unahur.obj2.proveedores.Proveedor;

import java.util.List;
import java.util.Set;

public class Ota {

    private DistribuidorDeTrafico distribuidorDeTrafico;

    private Proveedor amadeus;
    private Proveedor sabre;
    private Proveedor worldspan;

    public Ota(DistribuidorDeTrafico distribuidorDeTrafico) {
        this.distribuidorDeTrafico = distribuidorDeTrafico;

    }

    public List<Vuelo> buscarVuelos(DateTime fecha, String origen, String destino) {
        Proveedor codigoProveedor = distribuidorDeTrafico.proveedor();

        // TODO Implementar

        return codigoProveedor.buscarVuelo(fecha,origen,destino);
    }

    public Boleto reservar(Vuelo vuelo, Set<Pasajero> pasajeros) {
       Proveedor codigoProveedor = distribuidorDeTrafico.proveedor();

        // TODO Implementar
        return codigoProveedor.comprar(vuelo,pasajeros);
    }


}
